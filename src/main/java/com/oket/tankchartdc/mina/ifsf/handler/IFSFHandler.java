package com.oket.tankchartdc.mina.ifsf.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.NozzleOutType;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.websocket.BizWebSocketServer;
import com.oket.tankchartdc.dao.AbnormalDao;
import com.oket.tankchartdc.entity.AbnormalEntity;
import com.oket.tankchartdc.mina.MinaIoHandler;
import com.oket.tankchartdc.mina.ifsf.bean.*;
import com.oket.tankchartdc.mina.ifsf.codec.IFSFMessage;
import com.oket.tankchartdc.mina.process.IFSFProcess;
import com.oket.tankchartdc.service.AbnormalService;
import com.oket.tankchartdc.websocket.WebSocketServer;
import com.oket.util.AirUtils;
import com.oket.util.CommonUtil;
import com.oket.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ifsf处理器
 *
 * @author SunBiaoLong
 */
@Component
@Slf4j
public class IFSFHandler extends IoHandlerAdapter implements MinaIoHandler {
	private WebSocketServer webSocketServer;

	//上一次数据接收的时间
	public static Date ifsfReciveTime;
	@Autowired
	AbnormalDao abnormalDao;
	@Autowired
	AbnormalService abnormalService;

	@Autowired
	private IFSFProcess ifsfProcess;
	@Resource
	BizWebSocketServer bizWebSocketServer;

	@Override
	public WebSocketServer getWebSocketServer() {
		return webSocketServer;
	}

	@Override
	public void setWebSocketServer(WebSocketServer websocketServer) {
		this.webSocketServer = websocketServer;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.info("session created");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		try {

			updateError();
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
		log.info("session open");
	}


	@Override
	public void sessionClosed(IoSession session) throws Exception {
		try {
			saveError();
		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		session.closeNow();
		log.info("session close");
	}

	/*
	 * 保存ifsf断开异常
	 */
	private void saveError() {
		AbnormalEntity abnormalEntity = new AbnormalEntity();
		abnormalEntity.setDitType("IFSF");
		abnormalEntity.setErrorType(1);
		abnormalEntity.setStartTime(new Date(System.currentTimeMillis()/1000*1000));
		abnormalService.save(abnormalEntity);
		log.info("IFSF断开连接，当前时间"+abnormalEntity.getStartTime());
		bizWebSocketServer.sendMessageServelog(abnormalEntity);
		websocketSendMessage("IFSF断开连接！");
	}

	/*
	 * 给上一条关闭的结束时间赋值
	 */
	private void updateError(){
		Date now = new Date();
		log.info("IFSF建立连接，当前时间"+now);
		QueryWrapper<AbnormalEntity> queryWrapper = new QueryWrapper();
		queryWrapper.lambda().eq(AbnormalEntity::getDitType,"IFSF");
		queryWrapper.lambda().eq(AbnormalEntity::getErrorType,1);
		queryWrapper.lambda().orderByDesc(AbnormalEntity::getStartTime);
		List<AbnormalEntity> results =abnormalDao.selectList(queryWrapper);
		if(results.size()!=0) {
			AbnormalEntity abnormalEntity = results.get(0);
			if(abnormalEntity.getEndTime() == null) {
				abnormalEntity.setEndTime(now);
				abnormalService.updateById(abnormalEntity);
				JSONObject jsonObject = CommonUtil.successJson(abnormalEntity);
				jsonObject.put("type", 4);
				bizWebSocketServer.sendMessageServelog(abnormalEntity);
			}
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.error("Unexpected exception.", cause);
		session.closeNow();

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		Date now = new Date();
		if(ifsfReciveTime!=null) {
			try {
				QueryWrapper<AbnormalEntity> queryWrapper = new QueryWrapper();
				queryWrapper.lambda().eq(AbnormalEntity::getDitType, "IFSF");
				try {
					queryWrapper.lambda().eq(AbnormalEntity::getStartTime, TimeUtils.date2String(ifsfReciveTime));
				} catch (Exception e) {
					log.error("日期格式转化出错了" + e);
				}
				queryWrapper.last("LIMIT 1");
				AbnormalEntity dbAbnormal = abnormalService.getOne(queryWrapper);
				//更新
				if (AirUtils.hv(dbAbnormal)) {
					dbAbnormal.setEndTime(now);
					abnormalService.updateById(dbAbnormal);
					bizWebSocketServer.sendMessageServelog(dbAbnormal);
				}
			}catch (Exception e){
				log.error(e.getMessage(),e);
			}

		}
		//判断是否数据长时间未发送
		ifsfReciveTime = now;
		log.info("received: " + message);
		if (message instanceof IFSFMessage) {
			IFSFMessage ifsfMessage = (IFSFMessage) message;
			String originHex = message.toString().substring(message.toString().indexOf("originHex=")).substring(10);
			websocketSendMessage("IFSF原发数据,类型：" + getMessageType(ifsfMessage) + " received:" + originHex.substring(0, originHex.length() - 1));
			processIFSFMessage(ifsfMessage);
			//不需要应答
		}
		websocketSendMessage("send:" + "OK");
	}

	/*
	 * 判断是否数据长时间未发送
	 */
	@Scheduled(cron = "0 */2 * * * ?")	//  每隔2分钟执行一次
	private void checkReciveAbnormal() {
		//第一条数据不判断
		Date now = new Date();
		if(ifsfReciveTime!=null){
			//本次数据接收时间距离上次断开时间超过10分钟，认为异常
			if(now.getTime() - ifsfReciveTime.getTime()>600*1000){
				log.info("IFSF数据长时间未发送，当前时间"+now+"---上次数据发送时间"+ifsfReciveTime);
				QueryWrapper<AbnormalEntity> queryWrapper = new QueryWrapper();
				queryWrapper.lambda().eq(AbnormalEntity::getDitType,"IFSF");
				try {
					queryWrapper.lambda().eq(AbnormalEntity::getStartTime,TimeUtils.date2String(ifsfReciveTime));
				}
				catch (Exception e){
					log.error("日期格式转化出错了"+e);
				}
				queryWrapper.last("LIMIT 1");
				AbnormalEntity dbAbnormal = abnormalService.getOne(queryWrapper);
				//不更新
				if(AirUtils.hv(dbAbnormal)){
//					dbAbnormal.setEndTime(now);
//					abnormalService.updateById(dbAbnormal);
				}
				//新增
				else{
					AbnormalEntity abnormalEntity = new AbnormalEntity();
					abnormalEntity.setDitType("IFSF");
					abnormalEntity.setErrorType(2);
					try {
						abnormalEntity.setStartTime(TimeUtils.string2Date(TimeUtils.date2String(ifsfReciveTime),TimeUtils.FORMAT1));
					}
					catch (Exception e){
						log.error("日期格式转化出错了"+e);
					}
					abnormalService.save(abnormalEntity);
					bizWebSocketServer.sendMessageServelog(abnormalEntity);
				}
				websocketSendMessage("长时间没有数据推送，上次推送时间为"+ifsfReciveTime);
			}
		}
	}

	/**
	 * 获取数据类型
	 *
	 * @param ifsfMessage
	 * @return
	 */
	private String getMessageType(IFSFMessage ifsfMessage) {
		if (ifsfMessage.getBody() != null && ifsfMessage.getBody().getDitIFSFDataBody() != null) {
			return ifsfMessage.getBody().getDitIFSFDataBody().getDesc();
		} else {
			return "其他类型数据";
		}

	}

	private void processIFSFMessage(IFSFMessage ifsfMessage) {
		IDitIFSFDataBody ditIFSFDataBody = ifsfMessage.getBody().getDitIFSFDataBody();
		if (ditIFSFDataBody instanceof IFSFInventory) {
			log.debug("液位数据");
			Inventory inventory = IFSFInventory.convertInventory(
					(IFSFInventory) ditIFSFDataBody,
					ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20);
			inventory.setType("液位数据");
			final String content = JSON.toJSONString(inventory,
					SerializerFeature.PrettyFormat,
					SerializerFeature.WriteMapNullValue,
					SerializerFeature.WriteDateUseDateFormat);
			websocketSendMessage("IFSF接收数据为：" + content);
			ifsfProcess.processInventory(inventory);
		} else if (ditIFSFDataBody instanceof IFSFTransaction) {
			log.debug("正常交易数据");
			Integer nozzleNo = ifsfProcess.getNozzle(ifsfMessage.getHeader().getSendNode(),
					(byte) (ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));

			if (nozzleNo == null) {
				log.error("未找到对应的油枪,节点:" + ifsfMessage.getHeader().getSendNode()
						+ ";加油点:" + (ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			} else {
				BzNozzleOut hoseOut = IFSFTransaction.convertHoseOut(
						(IFSFTransaction) ditIFSFDataBody,
						nozzleNo);
				logMessage(hoseOut );
			}

		} else if (ditIFSFDataBody instanceof IFSFOfflineRecord) {
			log.debug("脱机交易数据");
			Integer nozzleNo = ifsfProcess.getNozzle(ifsfMessage.getHeader().getSendNode(),
					(byte) (ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			if (nozzleNo == null) {
				log.error("未找到对应的油枪,节点:" + ifsfMessage.getHeader().getSendNode()
						+ ";加油点:" + (ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			} else {
				BzNozzleOut hoseOut = IFSFOfflineRecord.convertHoseOut(
						(IFSFOfflineRecord) ditIFSFDataBody,
						nozzleNo);
				hoseOut.setType(NozzleOutType.OFFLINE);
				logMessage( hoseOut);
			}


		} else if (ditIFSFDataBody instanceof FuelingPointAndHoseStatus) {
			FuelingPointAndHoseStatus hoseStatus = (FuelingPointAndHoseStatus) ditIFSFDataBody;
			hoseStatus.setHostId(ifsfProcess.getNozzle(ifsfMessage.getHeader().getSendNode(),
					ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			if (hoseStatus.getHostId() == null) {
				log.error("未找到对应的油枪,节点:" + ifsfMessage.getHeader().getSendNode()
						+ ";加油点:" + (ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			} else {
				ifsfProcess.processNozzleState(FuelingPointAndHoseStatus.convertToState(hoseStatus));
				hoseStatus.setType("加油开始结束数据");
				logMessage( hoseStatus);

			}

		} else if (ditIFSFDataBody instanceof IFSFOnFueling) {
			log.debug("加油中数据");
			IFSFOnFueling ifsfOnFueling = (IFSFOnFueling) ditIFSFDataBody;
			ifsfOnFueling.setNode(ifsfMessage.getHeader().getSendNode());
			ifsfOnFueling.setHoseId(ifsfProcess.getNozzle(ifsfMessage.getHeader().getSendNode()
					, ifsfMessage.getBody().getDataBaseAddress()[0] - 0x20));
			if (ifsfOnFueling.getHoseId()!=null){
				ifsfProcess.processFueling(IFSFOnFueling.convertFromFueling(ifsfOnFueling));
			}
			ifsfOnFueling.setType("加油中数据");
			logMessage( ifsfOnFueling);
		}
	}

	private void logMessage( Object object) {
		final String content = JSON.toJSONString(object,
				SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat);
		websocketSendMessage("IFSF接收数据为：" + content);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

	}


}

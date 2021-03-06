package com.oket.delivery.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.delivery.DeliveryConfirm;
import com.oket.delivery.dao.DeliveryConfirmDao;
import com.oket.delivery.dao.DeliveryDAO;
import com.oket.delivery.service.DeliveryConfirmService;
import com.oket.delivery.service.DeliveryService;
import com.oket.tank4station.dao.DbInventorCycleDAO;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import com.oket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-04-08
 */
@Service
public class DeliveryConfirmServiceImpl extends ServiceImpl<DeliveryConfirmDao, DeliveryConfirm> implements DeliveryConfirmService {


	@Override
	public JSONObject query(JSONObject req) {
		CommonUtil.fillPageParam(req);
		int count = baseMapper.coutDeliveryConfirm(req);
		return CommonUtil.successPage(req, baseMapper.selectDeliveryConfirm(req), count);
	}


	@Override
	public List<DeliveryConfirm> getConfirm(Integer deliveryId) {
		QueryWrapper<DeliveryConfirm> qw = new QueryWrapper<>();
		qw.lambda().eq(DeliveryConfirm::getDeliveryId, deliveryId);
		return list(qw);
	}

	@Override
	public void updateConfirm(JSONObject jsonObject) {
		List<Integer> idLists = Arrays.stream(jsonObject.getString("ids").split(",")).map(Integer::new).collect(Collectors.toList());
		QueryWrapper<DeliveryConfirm> qw = new QueryWrapper<>();
		qw.lambda().in(DeliveryConfirm::getCycleId, idLists);
		List<DeliveryConfirm> deliveryConfirmList = baseMapper.selectList(qw);
		if (!deliveryConfirmList.isEmpty()){
			if (jsonObject.getBoolean("flag")) {
				deliveryConfirmList.forEach(x -> x.setFlag(true));
			} else {
				deliveryConfirmList.forEach(x -> x.setFlag(false));
			}
			this.updateBatchById(deliveryConfirmList);
		}
	}

}

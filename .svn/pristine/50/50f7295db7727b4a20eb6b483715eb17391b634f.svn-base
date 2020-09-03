package com.oket.tankchartdc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.tankchartdc.entity.DitEntity;
import com.oket.tankchartdc.dao.DitDao;
import com.oket.tankchartdc.mina.ifsf.IFSFAcceptor;
import com.oket.tankchartdc.mina.json.JSONAcceptor;
import com.oket.tankchartdc.service.DitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.tankchartdc.service.SimulatorService;
import com.oket.util.CommonUtil;
import com.oket.util.GetIpAndPortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.oket.tankchartdc.mina.ifsf.IFSFAcceptor.ifsfFlag;
import static com.oket.tankchartdc.mina.json.JSONAcceptor.jsonFlag;
import static com.oket.tankchartdc.service.SimulatorService.ifsfTestFlag;
import static com.oket.tankchartdc.service.SimulatorService.jsonTestFlag;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
@Service
public class DitServiceImpl extends ServiceImpl<DitDao, DitEntity> implements DitService {
    @Autowired
    DitDao ditDao;
    @Autowired
    IFSFAcceptor ifsfAcceptor;
    @Autowired
    JSONAcceptor jsonAcceptor;
    @Autowired
    SimulatorService simulatorService;
    @Override
    public DitEntity select(DitEntity ditEntity){
        //服务端查询端口号
        QueryWrapper<DitEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(DitEntity::getType,ditEntity.getType());
        DitEntity result = ditDao.selectOne(wrapper);
        result.setWebsocketIp(GetIpAndPortUtils.getLocalIP());
        result.setWebsocketPort(GetIpAndPortUtils.getLocalPort());
        if(ditEntity.getType()==1){
            result.setIfsfIsRunning(ifsfFlag);
            result.setJsonIsRunning(jsonFlag);
            return result;
        }
        //模拟器端查询端口号
        else if(ditEntity.getType()==2){
            result.setIfsfIsRunning(ifsfTestFlag);
            result.setJsonIsRunning(jsonTestFlag);
            return result;
        }
        return null;
    }
    @Override
    public int change(DitEntity ditEntity){
        //服务端修改端口号
        if(ditEntity.getType()==1) {
            //更新ifsf的端口号
            if (ditEntity.getIfsf() != null) {
                //先查询当前isfs端口号
                int lastIfsf = this.selectIfsf();
                //把传过来的端口更新
                ditEntity.setId(1);
                ditDao.updateById(ditEntity);
                //关闭重连
                this.closeIfsf(ditEntity);
                this.runIfsf(ditEntity);
                if (ifsfFlag) {
                    return 1;
                }//如果失败，回滚
                else {
                    ditEntity.setIfsf(lastIfsf);
                    ditDao.updateById(ditEntity);
                    //并重连上一次的
                    this.runJson(ditEntity);
                    return 0;
                }
            }
            //更新json的端口号
            else if (ditEntity.getJson() != null) {
                //先查询当前isfs端口号
                int lastjson = this.selectJson();
                //把传过来的端口更新
                ditEntity.setId(1);
                ditDao.updateById(ditEntity);
                //关闭重连
                this.closeJson(ditEntity);
                this.runJson(ditEntity);
                if (jsonFlag) {
                    return 1;
                }
                //失败回滚
                else {
                    ditEntity.setJson(lastjson);
                    ditDao.updateById(ditEntity);
                    //并重连上一次的
                    this.runJson(ditEntity);
                    return 0;
                }
            }
        }
        //服务端修改端口号
        else if(ditEntity.getType()==2){
            //更新ifsf的端口号
            ditEntity.setId(2);
            ditDao.updateById(ditEntity);
            return 1;
        }
        return 0;
    }
    @Override
    public void runIfsf(DitEntity ditEntity){
        if(ditEntity.getType()==1){
            ifsfAcceptor.restart();
        }
    }

    @Override
    public void runJson(DitEntity ditEntity){
        if(ditEntity.getType()==1){
            jsonAcceptor.restart();
        }
    }

    @Override
    public void closeIfsf(DitEntity ditEntity){
        if(ditEntity.getType()==1){
            ifsfAcceptor.close();
        }
    }

    @Override
    public void closeJson(DitEntity ditEntity){
        if(ditEntity.getType()==1) {
            jsonAcceptor.close();
        }
    }

    @Override
    public int selectIfsf(){
        return ditDao.selectIfsf();
    }

    @Override
    public int selectJson(){
        return ditDao.selectJson();
    }
    @Override
    public int selectTestJson(){
        return ditDao.selectJson();
    }
    @Override
    public int selectTestIfsf(){
        return ditDao.selectJson();
    }
}

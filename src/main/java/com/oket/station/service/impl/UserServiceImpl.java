package com.oket.station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.station.UserEntity;
import com.oket.station.dao.UserDao;
import com.oket.station.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

	@Override
	public List<UserEntity> query(JSONObject req) {
		//查询条件 根据需要写
		QueryWrapper wrapper = new QueryWrapper();
		//查询条件
		wrapper.eq("USER_NAME", req.getString("userName"));
		wrapper.eq("PWD", DigestUtils.md5Hex(req.getString("pwd")));
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<String> getMune(JSONObject jsonObject) {
		List<String> result = new ArrayList<>();
		if (jsonObject.getString("userName").equals("admin")) {
			result.add("ALL");
		} else if (jsonObject.getString("userName").equals("master")) {
			result.add("Dashboard");
			result.add("history");
			result.add("Delivery");
			result.add("Invertory");
			result.add("Hoseout");
			result.add("confirm");
			result.add("Total");
			result.add("basedata");
			result.add("TankDevice");
			result.add("TankerDevice");
			result.add("Oil");
			result.add("OilGunDevice");
			result.add("DitInfo");
			result.add("StationDetail");
		}
		return result;
	}

	@Override
	public void update(UserEntity userEntity){
		userEntity.setPwd(DigestUtils.md5Hex(userEntity.getPwd()));
		this.updateById(userEntity);
	}
}

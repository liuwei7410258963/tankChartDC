package com.oket.oil.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.oil.OilEntity;
import com.oket.oil.cache.OilCacheManager;
import com.oket.oil.dao.OilDAO;
import com.oket.oil.service.OilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 孙彪龙
 * @since 2019-10-14
 */
@Service
public class OilServiceImpl extends ServiceImpl<OilDAO, OilEntity> implements OilService {

	@Autowired
	private OilCacheManager oilCacheManager;

	@Override
	public IPage<OilEntity> query(JSONObject req, boolean isPage) {
		List<OilEntity> oilEntityList = null;
		//查询条件 根据需要写
		QueryWrapper<OilEntity> wrapper = new QueryWrapper<>();
		// 参数根据需要判断来写
		Page<OilEntity> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
		//油品名
		if (req.getString("shortName") != null) {
			wrapper.lambda().like(OilEntity::getShortName, req.getString("shortName"));
		}
		if (req.getString("fullName") != null) {
			wrapper.lambda().like(OilEntity::getFullName, req.getString("fullName"));
		}
		if (req.getString("code") != null) {
			wrapper.lambda().like(OilEntity::getCode, req.getString("code"));
		}
		if (req.get("type") != null) {
			wrapper.lambda().eq(OilEntity::getType, req.getString("type"));

		}
		if (req.get("status") != null) {
			wrapper.lambda().eq(OilEntity::getStatus, req.get("status"));
		}
		if (isPage) {
			oilEntityList = baseMapper.selectPageExt(page, wrapper);
		} else {
			//不分页的数据
			oilEntityList = baseMapper.selectPageExt(null, wrapper);
		}

		page.setRecords(oilEntityList);
		return page;
	}

	@Override
	public boolean queryExistOil(OilEntity oilEntity) {
		QueryWrapper<OilEntity> wrapper = new QueryWrapper<>();
		//油品名
		if (oilEntity.getShortName() != null) {
			wrapper.lambda().eq(OilEntity::getShortName, oilEntity.getShortName());
		}
		if (oilEntity.getFullName() != null) {
			wrapper.lambda().eq(OilEntity::getFullName, oilEntity.getFullName());
		}
		//油品编号
		if (oilEntity.getCode() != null) {
			wrapper.lambda().eq(OilEntity::getCode, oilEntity.getCode());
		}
		oilEntity = baseMapper.selectOne(wrapper);
		if (oilEntity == null) {
			return true;
		}
		return false;
	}


	@Override
	public OilEntity saveNewOil(String oilCode) {
		OilEntity oilEntity = new OilEntity();
		oilEntity.setCode(oilCode);
		//可以界面上修改名称
		oilEntity.setFullName(oilCode + "全称待命名");
		oilEntity.setShortName(oilCode + "简称待命名");
		this.save(oilEntity);
		OilCacheManager.putOil(oilCode, oilEntity);
		return oilEntity;
	}

	@Override
	public OilEntity getOilByCode(String oilCode) {
		final OilEntity oil = OilCacheManager.getOil(oilCode);
		if (oil == null) {
			QueryWrapper<OilEntity> wrapper = new QueryWrapper<>();
			wrapper.lambda().eq(OilEntity::getCode, oilCode);
			final OilEntity oilEntity = baseMapper.selectOne(wrapper);
			if (oilEntity != null) {
				oilCacheManager.refresh();
				return oilEntity;
			} else {
				return null;
			}
		} else {
			return oil;
		}
	}
}

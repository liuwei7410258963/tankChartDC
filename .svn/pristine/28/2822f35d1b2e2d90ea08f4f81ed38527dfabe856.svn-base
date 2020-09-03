package com.oket.dispenser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.dispenser.dao.BzNozzleOutLastDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @description: 加油机业务相关的缓存管理器
 * @author: SunBiaoLong
 * @create: 2019-12-13 13:40
 **/
@Component
@Slf4j
public class DispenserCacheManager {
	/**
	 * 最一笔加油机付出数
	 */
	private static Map<String, BzNozzleOutLast> NOZZLE_OUT_LAST_MAP = new ConcurrentHashMap<>();
	@Resource
	private BzNozzleOutLastDAO bzNozzleOutLastDAO;

	@PostConstruct
	public void init() {
		try {
			final List<BzNozzleOutLast> bzNozzleOutLasts = bzNozzleOutLastDAO.selectList(null);
			if (bzNozzleOutLasts != null && !bzNozzleOutLasts.isEmpty()) {
				NOZZLE_OUT_LAST_MAP = bzNozzleOutLasts
						.parallelStream()
						.collect(Collectors.toConcurrentMap(
								BzNozzleOutLast::getNozzleNo,
								nozzleOutLast -> nozzleOutLast
						));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取油枪的最后一笔销售数据
	 *
	 * @param nozzleNo
	 * @return
	 */
	public BzNozzleOutLast getNozzleOutLast(String nozzleNo) {
		if (nozzleNo == null) {
			return null;
		} else {
			BzNozzleOutLast bzNozzleOutLast = NOZZLE_OUT_LAST_MAP.get(nozzleNo);
			if (bzNozzleOutLast == null) {
				bzNozzleOutLast = bzNozzleOutLastDAO.selectOne(new QueryWrapper<BzNozzleOutLast>().lambda().eq(BzNozzleOutLast::getNozzleNo, nozzleNo));
				if (bzNozzleOutLast != null) {
					NOZZLE_OUT_LAST_MAP.put(bzNozzleOutLast.getNozzleNo(), bzNozzleOutLast);
					return bzNozzleOutLast;
				} else {
					return null;
				}
			} else {
				return bzNozzleOutLast;
			}
		}
	}

	/**
	 * 更新最后一笔数据
	 *
	 * @param bzNozzleOutLast
	 */
	public synchronized void updateNozzleOutLast(BzNozzleOutLast bzNozzleOutLast) {
		if (bzNozzleOutLast != null && bzNozzleOutLast.getNozzleNo() != null) {
			NOZZLE_OUT_LAST_MAP.put(bzNozzleOutLast.getNozzleNo(), bzNozzleOutLast);
		}
	}

	/**
	 * 获取最后的销售数据
	 *
	 * @return
	 */
	public Collection<BzNozzleOutLast> getAllNozzleOutLast() {
		return NOZZLE_OUT_LAST_MAP.values();
	}
}

package com.oket.tankchartdc;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import com.oket.oil.cache.OilCacheManager;
import com.oket.tank4station.Inventory;
import com.oket.tank4station.LevelTrace;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

/**
 * @description: 液位周期
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:54
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "db_inventory_cycle")
public class DbInventoryCycle extends BaseEntity {

	/**
	 * 装油液位阈值
	 */
	public final static double UNLOADING_LEVEL_GAP = 300;
	/**
	 * 装油分钟持续时间阈值
	 */
	public final static int UNLOADING_MIN_GAP = 5;
	public final static double STABLE_VARIANCE = 5;
	/**
	 * 两次卸油间隔-单位分钟。如果间隔较小，认为是同一次卸油
	 */
	public final static int TWO_UNLOADING_GAP = 30;
	private int tankNo;
	/**
	 * 周期id,每个罐从1开始递增.
	 */
	private int deliveryId;
	private String oilCode;
	private String oilName;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	private Double startLevel;
	private Double startWaterLevel;
	private Double startVolume;
	private Double startTemperature;
	private Double startWaterVolume;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Double endLevel;
	private Double endWaterLevel;
	private Double endVolume;
	private Double endTemperature;
	private Double endWaterVolume;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date unloadFinishedTime;
	private Double unloadFinishedLevel;
	private Double unloadFinishedWaterLevel;
	private Double unloadFinishedVolume;
	private Double unloadFinishedTemperature;
	private Double unloadFinishedWaterVolume;
	/**
	 * 卸油量
	 * unloadFinishedVolume-startVolume（如果有卸油，因为安装部署后第一笔可能没有）
	 */
	private Double unloadVolume;
	/**
	 * 销售量
	 * unloadVolume-endTime
	 */
	private Double sellVolume;
	/**
	 * 周期变化量
	 */
	private Double volume;

	/**
	 * 卸油期间付油量
	 */
	private double nozzleOutWhenDelivery;
	/**
	 * 周期状态,默认是从卸油开始
	 */
	private CycleState cycleState = CycleState.UNLOADING;
	private Integer minutes;
	private Status status = Status.ENABLE;

	//导出诊断数据的最后操作时间，展示用
	@TableField(exist = false)
	private String lastTime;
	//是否导出过该周期的诊断数据，展示用
	@TableField(exist = false)
	private boolean flag;

	@TableField(exist = false)
	private double startV20;
	@TableField(exist = false)
	private double unloadFinishedV20;
	@TableField(exist = false)
	private double endV20;
	/**
	 * 卸油V20
	 */
	@TableField(exist = false)
	private double unloadV20;

	public double getUnloadV20(){
		return getUnloadFinishedV20() - getStartV20();
	}
	/**
	 * 获取开始V20
	 * @return
	 */
	public double getStartV20(){
		return OilCacheManager.getV20(oilCode, startVolume==null?0.0:startVolume, startTemperature==null?0.0:startTemperature);
	}

	/**
	 * 获取卸油完成的V20
	 * @return
	 */
	public double getUnloadFinishedV20(){
		return OilCacheManager.getV20(oilCode, unloadFinishedVolume==null?(startVolume==null?0.0:startVolume):unloadFinishedVolume, unloadFinishedTemperature==null?(startTemperature==null?0.0:startTemperature):unloadFinishedTemperature);
	}

	@Override
	public String toString() {
		return "DbInventoryCycle{" +
				"tankNo=" + tankNo +
				", deliveryId=" + deliveryId +
				", oilCode='" + oilCode + '\'' +
				", oilName='" + oilName + '\'' +
				", startTime=" + startTime +
				", startLevel=" + startLevel +
				", startWaterLevel=" + startWaterLevel +
				", startVolume=" + startVolume +
				", startTemperature=" + startTemperature +
				", startWaterVolume=" + startWaterVolume +
				", endTime=" + endTime +
				", endLevel=" + endLevel +
				", endWaterLevel=" + endWaterLevel +
				", endVolume=" + endVolume +
				", endTemperature=" + endTemperature +
				", endWaterVolume=" + endWaterVolume +
				", unloadFinishedTime=" + unloadFinishedTime +
				", unloadFinishedLevel=" + unloadFinishedLevel +
				", unloadFinishedWaterLevel=" + unloadFinishedWaterLevel +
				", unloadFinishedVolume=" + unloadFinishedVolume +
				", unloadFinishedTemperature=" + unloadFinishedTemperature +
				", unloadFinishedWaterVolume=" + unloadFinishedWaterVolume +
				", unloadVolume=" + unloadVolume +
				", sellVolume=" + sellVolume +
				", volume=" + volume +
				", nozzleOutWhenDelivery=" + nozzleOutWhenDelivery +
				", cycleState=" + cycleState +
				", minutes=" + minutes +
				", status=" + status +
				", lastTime='" + lastTime + '\'' +
				", flag=" + flag +
				", startV20=" + startV20 +
				", unloadFinishedV20=" + unloadFinishedV20 +
				", endV20=" + endV20 +
				'}';
	}

	/**
	 * 获取结束的V20
	 * @return
	 */
	public double getEndV20(){
		return OilCacheManager.getV20(oilCode, endVolume==null?(startVolume==null?0.0:startVolume):endVolume
				, endTemperature==null?(startTemperature==null?0.0:startTemperature):endTemperature);
	}
	public long getMinutes() {
		if (endTime == null) {
			return 0;
		} else {
			return ((this.getEndTime().getTime() - this.getStartTime().getTime()) / 1000 / 60);
		}
	}

	public static DbInventoryCycle init(Inventory inventory, CycleState cycleState, int deliveryId) {
		DbInventoryCycle dbInventoryCycle = new DbInventoryCycle();
		dbInventoryCycle.setTankNo(inventory.getTankNo());
		dbInventoryCycle.setOilCode(inventory.getOilCode());
		dbInventoryCycle.setOilName(inventory.getOilName());
		dbInventoryCycle.start(inventory);
		dbInventoryCycle.setCycleState(cycleState);
		dbInventoryCycle.deliveryId = deliveryId;
		return dbInventoryCycle;
	}

	/**
	 * 初始化
	 *
	 * @param trace
	 * @return
	 */
	public static DbInventoryCycle init(LevelTrace trace, CycleState cycleState) {
		DbInventoryCycle dbInventoryCycle = new DbInventoryCycle();
		dbInventoryCycle.setTankNo(trace.getTankNo());
		dbInventoryCycle.setOilCode(((Inventory)trace.getFirstLevel()).getOilCode());
		dbInventoryCycle.setOilName(((Inventory)trace.getFirstLevel()).getOilName());
		dbInventoryCycle.start((Inventory) trace.getFirstLevel());
		dbInventoryCycle.setCycleState(cycleState);
		dbInventoryCycle.setStatus(Status.ENABLE);
		return dbInventoryCycle;
	}

	public static DbInventoryCycle init(DbInventoryCycle dest, CycleState cycleState) {
		DbInventoryCycle dbInventoryCycle = new DbInventoryCycle();
		dbInventoryCycle.setTankNo(dest.getTankNo());

		dbInventoryCycle.setOilCode(dest.getOilCode());
		dbInventoryCycle.setOilName(dest.getOilName());
		start(dbInventoryCycle, dest);
		dbInventoryCycle.setCycleState(cycleState);
		dbInventoryCycle.setStatus(Status.ENABLE);
		return dbInventoryCycle;
	}

	public void start(Inventory inventory) {
		this.setStartTime(inventory.getTime());
		this.setStartLevel(inventory.getLevel());
		this.setStartWaterLevel(inventory.getWaterLevel());
		this.setStartVolume(inventory.getVolume());
		this.setStartTemperature(inventory.getTemperature());
		this.setStartWaterVolume(inventory.getWaterVolume());
	}

	public static DbInventoryCycle start(DbInventoryCycle target, DbInventoryCycle dest) {
		target.setStartTime(dest.getStartTime());
		target.setStartLevel(dest.getStartLevel());
		target.setStartWaterLevel(dest.getStartWaterLevel());
		target.setStartVolume(dest.getStartVolume());
		target.setStartTemperature(dest.getStartTemperature());
		target.setStartWaterVolume(dest.getStartWaterVolume());
		return target;
	}


	/**
	 * 结束当前周期
	 *
	 * @param inventory
	 */
	public void closed(Inventory inventory) {
		this.setEndTime(inventory.getTime());
		this.setEndLevel(inventory.getLevel());
		this.setEndWaterLevel(inventory.getWaterLevel());
		this.setEndVolume(inventory.getVolume());
		this.setEndTemperature(inventory.getTemperature());
		this.setEndWaterVolume(inventory.getWaterVolume());
		if (this.getStartTime() != null) {
			long min = (this.getEndTime().getTime() - this.getStartTime().getTime()) / 1000 / 60;
			this.setMinutes((int) min);
		}
		if (this.getStartVolume() != null) {
			this.setVolume(this.getEndVolume() - this.getStartVolume());
		}
		if (this.getUnloadFinishedVolume() != null) {
			this.setSellVolume(this.getUnloadFinishedVolume() - this.getEndVolume());
		}
		this.setCycleState(CycleState.CLOSED);
	}

	/**
	 * 卸油完成
	 *
	 * @param inventory
	 */
	public void unloadFinished(Inventory inventory) {
		this.setUnloadFinishedTime(inventory.getTime());
		this.setUnloadFinishedLevel(inventory.getLevel());
		this.setUnloadFinishedWaterLevel(inventory.getWaterLevel());
		this.setUnloadFinishedVolume(inventory.getVolume());
		this.setUnloadFinishedTemperature(inventory.getTemperature());
		this.setUnloadFinishedWaterVolume(inventory.getWaterVolume());
		if (this.getStartVolume() != null) {
			this.unloadVolume = this.unloadFinishedVolume - this.getStartVolume();
		}
		this.setCycleState(DbInventoryCycle.CycleState.ACCOMPLISH_UNLOADING);
	}

	/**
	 * 周期状态
	 */
	@JSONType(serializeEnumAsJavaBean = true)
	@Getter
	public enum CycleState {
		/**
		 * 新创建的
		 */
		NEW(1, "开始"),
		/**
		 * 卸油状态
		 */
		UNLOADING(2, "正在卸油"),
		/**
		 * 完成卸油
		 */
		ACCOMPLISH_UNLOADING(3, "卸油完成"),
		/**
		 * 销售状态
		 */
		SELLING(4, "销售中"),
		/**
		 * 结束
		 */
		CLOSED(5, "结束");
		/**
		 * 状态。--数据库中的值
		 */
		@EnumValue
		private final int state;
		/**
		 * 状态名
		 */
		private final String name;

		CycleState(int state, String name) {
			this.state = state;
			this.name = name;
		}
	}
}

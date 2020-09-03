package com.oket.dispenser;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 油枪状态信息
 * @author: SunBiaoLong
 * @create: 2020-03-19 11:19
 **/
@Data
public class NozzleState {
	/**
	 * 油枪编号
	 */
	private int nozzleNo;
	/**
	 * 当前油枪状态
	 */
	private State currentState;
	/**
	 * 当前时间
	 */
	@JSONField(format = "YYYY-MM-dd HH:mm:ss")
	private Date time;
	/**
	 * 是否已经开始
	 */
	private boolean start = false;
	/**
	 * 付油信息
	 */
	private List<TransientAmount> transientAmountList;
	/**
	 * 最后一笔付油量
	 */
	private TransientAmount lastAmount;


	public NozzleState(int nozzleNo, State currentState) {
		this.nozzleNo = nozzleNo;
		this.currentState = currentState;
	}


	/**
	 * 是否正在加油
	 *
	 * @return
	 */
	public boolean isOnFueling() {
		return start&&(currentState.equals(State.SUSPENDED_FUELLING)
				|| currentState.equals(State.FUELLING));
	}

	public boolean stateIsOnFueling() {
		return currentState.equals(State.SUSPENDED_FUELLING)
				|| currentState.equals(State.FUELLING);
	}

	/**
	 * 获取当前金额
	 *
	 * @return
	 */
	public double getCurrentAmount() {
		if (lastAmount == null) {
			return 0;
		} else {
			return lastAmount.getAmount();
		}
	}

	/**
	 * 获取当前加油量
	 *
	 * @return
	 */
	public double getCurrentVolume() {
		if (lastAmount == null) {
			return 0;
		} else {
			return lastAmount.getVolume();
		}
	}


	/**
	 * 获取当前金额
	 *
	 * @return
	 */
	@JSONField(format = "YYYY-MM-dd HH:mm:ss")
	public Date getCurrentTime() {
		if (lastAmount != null) {
			//最后的交易时间不为null时，比对结束时间
			if (this.time != null) {
				return lastAmount.getTime().getTime() > this.time.getTime() ? lastAmount.getTime() : this.time;
			} else {
				return lastAmount.getTime();
			}
		} else {
			return this.getTime();
		}
	}

	/**
	 * 添加付油信息
	 *
	 * @param fueling
	 */
	public void addFueling(TransientAmount fueling) {
		if (transientAmountList == null) {
			transientAmountList = new ArrayList<>();
		}
		transientAmountList.add(fueling);
		lastAmount = fueling;
	}


	/**
	 * 清空正在加油中的数据
	 */
	public void clearFueling() {
		if (transientAmountList != null && !transientAmountList.isEmpty()) {
			this.transientAmountList.clear();
		}
		this.lastAmount = null;
	}

	@Getter
	public enum State {
		/**
		 * 油枪不允许操作，出现故障、进行参数设置时油枪进入不可操作状态
		 */
		INOPERATIVE(1),
		/**
		 * 油枪关闭
		 */
		CLOSE(2),
		/**
		 * 空闲
		 */
		IDLE(3),
		/**
		 * 请求授权
		 */
		CALLING(4),
		/**
		 * 已授权
		 */
		AUTHORIZED(5),
		/**
		 * 开始加油
		 */
		STARTED(6),
		/**
		 * 加油中
		 */
		FUELLING(8),
		/**
		 * 暂停开始加油
		 */
		SUSPENDED_STARTED(7),
		/**
		 * 暂停加油中
		 */
		SUSPENDED_FUELLING(9);
		private final int state;


		State(int state) {
			this.state = state;
		}

		/**
		 * 获取加油状态
		 *
		 * @param state
		 * @return
		 */
		public static State getState(int state) {
			for (State value : State.values()) {
				if (value.state == state) {
					return value;
				}
			}
			return null;
		}

	}

	/**
	 * 实时数据
	 */
	@Data
	public class TransientAmount {
		/**
		 * 金额
		 */
		private double amount;
		/**
		 * 体积
		 */
		private double volume;

		/**
		 * 时间
		 */
		@JSONField(format = "YYYY-MM-dd HH:mm:ss")
		private Date time;

		public TransientAmount(double amount, double volume, Date time) {
			this.amount = amount;
			this.volume = volume;
			this.time = time;
		}
	}

}

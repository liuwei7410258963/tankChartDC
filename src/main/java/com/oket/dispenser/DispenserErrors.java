/**
 *
 */
package com.oket.dispenser;

import java.util.*;

import com.oket.util4biz.TimeInterval;


/**
 * 油枪精度对象，每支油枪可能有多个油枪精度项，且每个油枪精度项有不同的作用时间
 * @author 王恒
 *
 */

public class DispenserErrors {
	private List<DispenserErrorItem> errs = new ArrayList<DispenserErrorItem>();
	private Map<TimeInterval, DispenserErrorItem> errmap = new HashMap<TimeInterval, DispenserErrorItem>();
	private String hoseID;

	public DispenserErrors(String hoseId) {
		hoseID = hoseId;
	}

	/**
	 * 油枪进度项
	 * @return the errs
	 */
	public DispenserErrorItem getErrorItem(BzNozzleOut out) {

		DispenserErrorItem ret = null;
		if (errmap.size() == 0) {
			this.errItemList2ErrItemMap();
		}
		Iterator<TimeInterval> keys = errmap.keySet().iterator();
		while (keys.hasNext()) {
			TimeInterval timeI = keys.next();
			if (timeI.isInclude(out.getStartTime())) {
				ret = errmap.get(timeI);
				break;
			}
		}
		return ret;
	}

	/**
	 * 把误差项转化成map格式以方便获取对应的ErrorItem
	 */
	public void errItemList2ErrItemMap() {
		this.errmap.clear();
		if (this.errs.size() == 1) {
			DispenserErrorItem errI = errs.get(0);
			TimeInterval timeInterval = new TimeInterval(errI.getFromDate(), new Date(System.currentTimeMillis()));
			errmap.put(timeInterval, errI);
			return;
		}
		for (int i = 0; i < errs.size(); i++) {
			DispenserErrorItem item = errs.get(i);
			if (i < errs.size() - 1) {
				TimeInterval timeInterval = new TimeInterval(item.getFromDate(), item.getEndDate());
				errmap.put(timeInterval, item);
			} else {
				Date t = new Date(System.currentTimeMillis());
				TimeInterval timeInterval = new TimeInterval(item.getFromDate(), t);
				item.setEndDate(t);
				errmap.put(timeInterval, item);
			}
		}
	}

	/**
	 * 返回油枪ID
	 * @return the nozzleNo
	 */
	public String getHoseID() {
		return hoseID;
	}

	/**
	 *  添加油枪误差，首先要保证油枪误差的时间和序号的连贯性:</p>
	 *  1、序号从0开始；</p>
	 *  2、时间必须开始时间早于结束时间否则，无法添加成功，返回false；</p>
	 *  3、要检查添加的时间不应该在现有的油枪精度的时间段内否则返回false；
	 * @param item
	 * @return
	 */
	public boolean addDispenserErrorItem(DispenserErrorItem item) {
		boolean ret = false;
		if (errs.size() > 0) {

			for (int i = 0; i < errs.size(); i++) {
				DispenserErrorItem tmpItem = errs.get(i);
				//检查新误差项是否在原有时间范围内
				//1新误差项开始时间在原有误差项时间之前，直接返回添加失败
				if (tmpItem.getFromDate().after(item.getFromDate())) {
					return ret;
				}
				//目前油枪误差结束时间没有，因此，添加项的开始时间即为当前项的结束时间，并且添加成功
				if (tmpItem.getEndDate() == null) {
					tmpItem.setEndDate(item.getFromDate());
					ret = true;
					this.errs.add(item);
					item.setSequence(i + 1);
					return ret;
				}
				//结束项非null，且结束项的时间晚于添加项的开始时间，表示油枪误差时间跨度错误
				if (tmpItem.getEndDate().after(item.getFromDate())) {
					return ret;
				}
			}
		} else {
			//id从0开始
			item.setSequence(0);
			this.errs.add(item);
			ret = true;
		}
		return ret;
	}
	@Override

	public String toString() {
		String ret = "nozzleNo:" + this.hoseID + "\n";
		for (DispenserErrorItem item : errs) {
			ret += item.toString() + "\n";
		}
		return ret;
	}

}

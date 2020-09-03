package com.oket.tank4station.tankchart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 抽象容积表
 *
 * @author sunBiaolong
 * @date 2020-04-27
 */
public abstract class AbstractTankChart implements ITankChartVolumeFunction, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2407607263294053361L;
	protected int maxHeight = 0;

	protected int minHeight = 0;

	protected ITankChartVolumeFunction function;
	/**
	 * 油罐容易表数据对
	 */
	protected List<TankChartValue> values = new ArrayList<>();
	/**
	 * 当前正在修改的高度容积表数据对
	 */
	private TankChartValue editingTankChartValue = null;

	@Override
	public double getMMChangeRate(double startH, double endH) {
		if (endH - startH == 0) {
			return 0;
		}
		return Math.abs((getVolume(endH) - getVolume(startH))
				/ (endH - startH));
	}

	/**
	 * 根据高度获取容积表内的高度数据;
	 * 1）根据高度寻找到最接近指定高度的容积表值；
	 */
	@Override
	public double getVolume(double height) {
		double volume = 0;
		if (height == 0) {
			return 0;
		}
		if (height < minHeight) {
			//throw new NotHeightRangeException("高度超出数据范围！此容积表高度范围为："+minHeight+"~~"+maxHeight+" 当前高度选取为：="+height);
			return 0;
		}
		if (values == null || values.isEmpty()) {
			//throw new NotHeightRangeException("容积表数据未初始化，无法生成容积表");
			return 0;
		}
		//超出最大高度的时候,体积的值为最大体积
		if (height >= maxHeight) {
			return values.get(values.size() - 1).getVolume();
		}

		//定位容积表值位置的索引
		int index = values.size();

		for (int i = 0; i < index; i++) {
			if (height == values.get(i).getHeight()) {
				volume = values.get(i).getVolume();
				break;
			} else if (i + 1 < index && height > values.get(i).getHeight() && height < values.get(i + 1).getHeight()) {
				double minV = values.get(i).getVolume();
				double maxV = values.get(i + 1).getVolume();
				volume = minV + (maxV - minV) * (height - values.get(i).getHeight()) / (values.get(i + 1).getHeight() - values.get(i).getHeight());
				break;
			}
		}
		return volume;
	}

	/**
	 * 计算两个高度内的容积表计算差值，startH建议为较低高度，endH为较高高度值，返回为正值，否则返回负值，
	 * 需要自行进行转换处理；计算公式为ret=getVolume(endH)-getVolume(startH);
	 *
	 * @param startH
	 * @param endH
	 * @return
	 */
	public double getIntervalVolume(double startH, double endH) {
		double ret = 0;
		ret = getVolume(endH) - getVolume(startH);
		return ret;
	}

	public List<TankChartValue> getValues() {
		return values;
	}

	/**
	 * 返回指定高度下的TankChartValue数据对
	 *
	 * @param height
	 * @return
	 */
	public TankChartValue getTankChartValue(int height) {
		TankChartValue ret = null;

		if (values.size() > 0) {
			TankChartValue tmp = values.get(0);
			int gap = tmp.getGapHeight();
			int index = (int) (height / gap);
			if (index == values.size()) {
				index--;
			}
			tmp = values.get(index);
			while (tmp != null) {
//找到指定高度的数据对
				if (tmp.getHeight() == height) {
					ret = tmp;
					return ret;
				} else if (tmp.getHeight() < height) {
					tmp = tmp.getNextHeightVolumeMap();
				} else if (tmp.getHeight() > height) {
					tmp = tmp.getPreHeightVolumeMap();
				}
			}
		}
		return ret;
	}

	protected void addTankChartValue(TankChartValue value) {
		if (this.values.size() > 0) {
			//取最后一个元素，作为添加入的value前一个值
			TankChartValue preValue = values.get(values.size() - 1);

			if (value.getHeight() > preValue.getHeight()) {
				int hGap = value.getHeight() - preValue.getHeight();
				int vGap = (int) (value.getVolume() - preValue.getVolume());
				int id = preValue.getId() + 1;
				TankChartValue tmp = new TankChartValue(value.getHeight(), value.getVolume(), hGap, vGap, id);
				//兼容文件容积表装载处理，因为文件容积表无法知道油罐最大高度是多少，然后函数容积表可以知道最大高度，
				//当有最大高度值时需要采用外部设置的容积表值
				this.maxHeight = Math.max(value.getHeight(), maxHeight);
				//把加入的value作为前一个容积表值的下一个值加入的这个值
				preValue.setNextHeightVolumeMap(tmp);
				//把当前的容积表值的前一容积表值的下一个值
				tmp.setPreHeightVolumeMap(preValue);
				values.add(tmp);

			} else {
				throw new TankChartValueException("加入的容积表值异常，加入的容积表值高度小于现有容积表值！加入的容积表值为："
						+ value.toString() + "原有容积表值最后一个值为：" + preValue.toString());
			}
		} else {//第一个元素
			TankChartValue tmp = new TankChartValue(value.getHeight(), value.getVolume(), value.getHeight(), (int) value.getVolume(), 1);
			minHeight = value.getHeight();
			values.add(tmp);
		}


	}

	/**
	 * 需要修改的的数据对
	 *
	 * @param value
	 */
	public void update(TankChartValue value) {
		if (value == null) {
			return;
		}
		this.editingTankChartValue = value;
		int curV = (int) value.getVolume();
		TankChartValue tmp = value.getNextHeightVolumeMap();
		while (tmp != null) {
			curV += tmp.getGapVolume();
			tmp.setVolume(curV);
			tmp = tmp.getNextHeightVolumeMap();
		}

	}

	/**
	 * 获取最后一条容积表值
	 *
	 * @return 需要对返回结果进行非null检查，如果容积表为空，则返回null。{@link TankChartValue}
	 */
	public TankChartValue getMaxHeightTankChartValues() {
		TankChartValue ret = null;
		if (values.size() > 0) {
			ret = values.get(values.size() - 1);
		}
		return ret;
	}

	/**
	 * 对List<TankChartValue> values根据高度进行排序，isAscendingOrder为true时进行对高度升序排序（高度低的在前），否则以高度降序排序。
	 *
	 * @param values
	 * @param isAscendingOrder
	 * @author 王恒
	 */
	private void sortTankChartValuesByHeight(List<TankChartValue> values, boolean isAscendingOrder) {
		final boolean isAscending = isAscendingOrder;
		Comparator<TankChartValue> comparator = new Comparator<TankChartValue>() {
			@Override
			public int compare(TankChartValue src, TankChartValue value) {
				int ret = 0;
				//按照升序排序
				if (isAscending) {
					ret = src.getHeight() < value.getHeight() ? -1 : 1;
				} else {
					ret = src.getHeight() > value.getHeight() ? -1 : 1;
				}


				return ret;
			}
		};
		Collections.sort(values, comparator);

	}

	@Override
	public int getMaxHeight() {
		return maxHeight;
	}

	@Override
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	@Override
	public int getMinHeight() {
		return minHeight;
	}

	@Override
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * 对TankChartValue进行接触链表关系解除。由于在做系列化时链表在数据较多情况下，
	 * 会出现资源不足情况，做系列化之前需要进行解除链表。
	 *
	 * @author 王恒
	 * @since 2016-10-09
	 */
	public void disChainTankChartValue() {
		for (TankChartValue v : values) {
			v.setNextHeightVolumeMap(null);
			v.setPreHeightVolumeMap(null);
		}
	}

	/**
	 * 对TankChartValue进行接触链表关系解除。由于在做系列化时链表在数据较多情况下，
	 * 会出现资源不足情况，做系列化之前需要进行解除链表。
	 *
	 * @author 王恒
	 * @since 2016-10-09
	 */
	public void chainingTankChartValue() {
		if (values.size() < 2) {
			return;
		}
		this.sortTankChartValuesByHeight(values, true);
		TankChartValue first = values.get(0);
		TankChartValue next = values.get(1);
		first.setNextHeightVolumeMap(next);

		TankChartValue last = values.get(values.size() - 1);
		TankChartValue pre = values.get(values.size() - 2);
		last.setPreHeightVolumeMap(pre);
		for (int i = 1; i < values.size() - 1; i++) {
			TankChartValue v = values.get(i);
			v.setNextHeightVolumeMap(values.get(i + 1));
			v.setPreHeightVolumeMap(values.get(i - 1));
		}

	}

	public TankChartValue getEditingTankChartValue() {
		return editingTankChartValue;
	}
}
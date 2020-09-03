package com.oket.tank4station.tankchart;

import lombok.Data;

import java.io.Serializable;

/**
 * 容积表值
 * @author sunBiaolong
 * @date 2020-04-27
 */
@Data
public class TankChartValue implements Serializable, Comparable<Object> {
	

	/**实现可系列化 */
	private static final long serialVersionUID = -5613826833094679349L;
	private int height=0;
	private double volume=0;
	/**比前面一个数据对的高度差 单位mm*/
	private int gapHeight=1;
	/**比前面一个数据对的体积差 单位升*/
	private int gapVolume=0;
	private int id=0;
	private TankChartValue preHeightVolumeMap=null;
	private TankChartValue nextHeightVolumeMap=null;
	private boolean gapVolumeModify=false;
	public TankChartValue(int height, double volume, int gapHeight, int gapVolume,int id) {
		super();
		this.height = height;
		this.volume = volume;
		this.gapHeight = gapHeight;
		this.gapVolume = gapVolume;
		this.id=id;
	
	}
	public TankChartValue(int height, double volume) {
		super();
		this.height = height;
		this.volume = volume;
	}

/**
 * 比较两个对象是否相等，返回值：
 * <p>0   表示相等；</p>
 * <p>-1  参数指定的高度小于此高度</p>
 * <p>1   参数指定的高度大于此高度</p>
 */
	@Override
	public int compareTo(Object o) {
		int ret=-1;
		TankChartValue tmp=(TankChartValue)o;
		if(this.height==tmp.height&&this.volume==tmp.getVolume()){
			ret=0;
		}
		if(this.height>tmp.height){
			ret=-1;
		}
		if(this.height<tmp.height){
			ret=1;
		}
		return ret;
	}


	@Override
	public String toString(){
		return String.format("%1$d,%2$.2f,%3$d,%4$d,%5$d",id,height,volume,gapHeight,gapVolume);
	}
	public String toPrintString(){
		return String.format("%1$d,%2$.2f",height,volume);
	}

}

/**
 *
 */
package com.oket.tank4station;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class TankConstant implements Serializable {

	/**油罐封头形状:平面封头 int*/
	public final static int TANK_HEAD_TYPE_PLANE = 10;
	/**油罐封头形状:平面封头名称 String*/
	public final static String TANK_HEAD_TYPE_PLANE_NAME = "平面封头";
	/**油罐封头形状:半椭球 int*/
	public final static int TANK_HEAD_TYPE_OVAL = 2;
	/**油罐封头形状:半椭球名称 String*/
	public final static String TANK_HEAD_TYPE_OVAL_NAME = "椭球封头";
	/**油罐封头形状:球缺 int*/
	public final static int TANK_HEAD_TYPE_BALLMISSING = 1;
	/**油罐封头形状:球缺名称 String*/
	public final static String TANK_HEAD_TYPE_BALLMISSING_NAME = "球缺封头";
	/**罐封头形状:未知 int*/
	public final static int TANK_HEAD_TYPE_UNKNOWN = -1;
	/**罐封头形状:未知名称 String*/
	public final static String TANK_HEAD_TYPE_UNKNOWN_NAME = "未定义";
	/**油罐封头最大长度为500mm*/
	public final static int TANK_HEAD_MAX_LENGTH = 500;
	/**油罐封头最大小度为50mm*/
	public final static int TANK_HEAD_MIN_LENGTH = 300;
	/**油罐封头直边长度L2最大长度为500mm*/
	public final static int TANK_L2_MAX_LENGTH = 500;
	/**油罐封头直边长度L2最小长度为0mm 默认值未0*/
	public final static int TANK_L2_MIN_LENGTH = 0;
	/**油罐主圆桶L1最小度为3500mm即3.5米长*/
	public final static int TANK_L1_MIN_LENGTH = 3500;
	/**油罐主圆桶L1最大长度为12000mm即12米长*/
	public final static int TANK_L1_MAX_LENGTH = 12000;
	/**油罐封头L3最小度为50mm即0.05米长*/
	public final static int TANK_L3_MIN_LENGTH = 50;
	/**油罐封头桶L3最大长度为1000mm即1米长*/
	public final static int TANK_L3_MAX_LENGTH = 1000;
	/**油罐液位仪安装位置LG离最高点的长度最小长度为600mm即0.6米长*/
	public final static int TANK_LG_MIN_LENGTH = 600;
	/**油罐液位仪安装位置LG离最高点的长度最大长度为1100mm即11米长*/
	public final static int TANK_LG_MAX_LENGTH = 11000;
	/**当前支持封头类型的集合 String[]*/
	public final static String[] HEADTYPES = {TANK_HEAD_TYPE_BALLMISSING_NAME, TANK_HEAD_TYPE_PLANE_NAME, TANK_HEAD_TYPE_OVAL_NAME,
			TANK_HEAD_TYPE_UNKNOWN_NAME};
	/**
	 *
	 */
	private static final long serialVersionUID = -857039564294476407L;
}
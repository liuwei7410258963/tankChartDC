package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.parser.DitMessageBody;
import lombok.Data;

import java.util.List;

/**
 * @description: ifsf消息体
 * @author: Longer
 * @create: 2019-11-12 09:56
 **/
@Data
public class IFSFBody implements DitMessageBody {
	/**
	 * ifsf数据库地址长度
	 */
	private byte dataBaseAddressLength;
	/**
	 * ifsf数据库地址--是指的ifsf协议中的数据库，并非mysql数据库
	 *
	 */
	private byte[] dataBaseAddress;
	/**
	 * 业务数据
	 */
	private IDitIFSFDataBody ditIFSFDataBody;
}

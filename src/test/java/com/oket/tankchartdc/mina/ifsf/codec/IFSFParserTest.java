package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.ifsf.bean.IDitIFSFDataBody;
import com.oket.tankchartdc.mina.ifsf.bean.IFSFInventory;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class IFSFParserTest {

	@Test
	public void processTime() {
		IDitIFSFDataBody ditIFSFDataBody = new IFSFInventory();
		ditIFSFDataBody.setTime(new Date());
		IFSFParser ifsfParser = new IFSFParser();
		ifsfParser.processTime(ditIFSFDataBody,System.currentTimeMillis()+12312237193L,123);
		System.out.println(ditIFSFDataBody.getTime().toLocaleString());
	}
}
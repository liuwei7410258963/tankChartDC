package com.oket.tankchartdc.mina.json.bean;

import com.oket.util.MathExtend;
import com.oket.util.StringExUtils;
import org.junit.Test;

public class DITApiEnumTest {

	@Test
	public void test(){
		System.out.println(StringExUtils.byteArrayToHexString("P91_10035".getBytes()));
	}

	@Test
	public void test2(){
		String h = "20101";
		byte as=9;
		String ass = "P91_10022";
		int aa = 168;
		String json = "{\"data\":[{\"1\":\"374657\",\"2\":\"300865\",\"3\":\"2020-06-03\",\"4\":\"5\",\"5\":\"5.48\",\"6\":\"167.00\",\"7\":\"30.4800000000\"}],\"msgID\":\"4447\",\"source\":\"BJ08\",\"time\":\"2020-06-03 11:06:27\"}";
		byte[] a = h.getBytes();
		byte[] all = new byte[187];
		System.arraycopy(a,0,all,0,a.length);
		all[a.length] = 9;
		int loh = a.length;
		a = ass.getBytes();
		System.arraycopy(a,0,all,loh+1,a.length);
		loh = loh + 1 + a.length;
		a=MathExtend.intToByteArray(aa);
		System.arraycopy(a,0,all,loh,a.length);
		loh += a.length;
		a = json.getBytes();
		System.arraycopy(a,0,all,loh+1,a.length);

		System.out.println(StringExUtils.byteArrayToHexString(all));
	}
}
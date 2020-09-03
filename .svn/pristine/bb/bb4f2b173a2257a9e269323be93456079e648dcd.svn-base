package com.oket.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ObjectUtil {

	/**
     * 对象转byte[]
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] objectToBytes(Object obj) throws Exception {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		byte[] bytes = bo.toByteArray();
		bo.close();
		oo.close();
		return bytes;
	}

	/**
	 * byte[]转对象
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object bytesToObject(byte[] bytes) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream sIn = new ObjectInputStream(in);
		return sIn.readObject();
	}

	/**
	 * object转int,非int型返回0
	 * 
	 * @param o
	 * @return
	 */
	public static int o2i(Object o) {
		try {
			return Integer.valueOf(Objects.toString(o));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * object转string
	 * 
	 * @param o
	 * @return
	 */
	public static String o2s(Object o) {
		return Objects.toString(o);
	}

	/**
	 * String转int 非数字类型返回0
	 * 
	 * @param str
	 * @return
	 */
	public static int s2i(String str) {
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * double转int
	 * 
	 * @param d
	 * @return
	 */
	public static int d2i(Double d) {
		return d.intValue();
	}

	/**
	 * int转double
	 * 
	 * @param i
	 * @return
	 */
	public static double i2d(int i) {
		return Double.valueOf(o2s(i));
	}

	/**
	 * object转double
	 * 
	 * @param o
	 * @return
	 */
	public static double o2d(Object o) {
		return Double.valueOf(o2s(o));
	}

	/**
	 * 是否为空
	 * 
	 * @param o 字符串类型对象
	 * @return
	 */
	public static boolean isNull(Object o) {
		return o == null || StringUtils.isBlank(Objects.toString(o));
	}

	/**
	 * 是否非空
	 * 
	 * @param o 字符串类型对象
	 * @return
	 */
	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}

	/**
	 * 是否空
	 * 
	 * @param c 集合类型
	 * @return
	 */
	public static boolean isNull(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	/**
	 * 是否非空
	 * 
	 * @param c 集合类型
	 * @return
	 */
	public static boolean isNotNull(Collection<?> c) {
		return !isNull(c);
	}

	/**
	 * 是否空
	 * 
	 * @param o 数组
	 * @return
	 */
	public static boolean isNull(Object[] o) {
		return o == null || o.length == 0;
	}

	/**
	 * 是否非空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNotNull(Object[] o) {
		return !isNull(o);
	}

  /**
   * 获取利用反射获取类里面的值和名称
   *
   * @param obj
   * @return
   * @throws IllegalAccessException
   */
  public static Map<String,Object > objectToMap(Object obj) throws IllegalAccessException {
    Map<String, Object> map = new HashMap<>();
    Class<?> clazz = obj.getClass();
    System.out.println(clazz);
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      String fieldName = field.getName();
      Object value = field.get(obj);
      map.put(fieldName, value);
    }
    return map;
  }


	/**
	 * List的深度COPY,以序列化的方式实现，被复制的对象需要实现序列化
	 * @param src
	 * @return COPY后的List
	 */
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

	/**
	 * 深度拷贝对象，,以序列化的方式实现，被复制的对象需要实现序列化
	 * @param src
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T deepCopy(T src) throws IOException,ClassNotFoundException{

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		T dest = (T)in.readObject();
		return dest;
	}

    /**
     * 判断 一个对象的各个属性都为空值
     * @param object
     * @param ignoreProperties 忽略的属性
     * @return true：全是空，false：不全为空
     * @throws IllegalAccessException
     */
	public static Boolean allValueIsNull(Object object,String... ignoreProperties) throws IllegalAccessException {
		Boolean result = new Boolean(true);
		Class<?> clazz = object.getClass();
		for (Field field:clazz.getDeclaredFields()){
			field.setAccessible(true);
			String fieldName = field.getName();
			//不判断串行版本号
			if ("serialVersionUID".equals(fieldName)){
			    continue;
            }
            //不判断忽略属性
            List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
            if (isNotNull(ignoreList)){
                if (ignoreList.contains(fieldName)){
                    continue;
                }
            }
			Object value = field.get(object);
			if (isNotNull(value)){
			    result = false;
			    break;
            }
		}
		return result;


	}


}
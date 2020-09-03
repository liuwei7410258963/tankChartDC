package com.oket.util;


/**
 * 简单的Twitter_Snowflake,不支持分布式.<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 51位时间截(毫秒级)，注意， 51位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 *
 *@author SunBiaolong
 *@date 2020-05-14
 */
public class SimpleSnowflakeIdWorker {

	// ==============================Fields===========================================
	/**
	 * 开始时间截 (2020-01-01 00:00:00.000)
	 */
	private final long twepoch = 1577808000000L;

	/**
	 * 序列在id中占的位数
	 */
	private final long sequenceBits = 12L;

	/**
	 * 时间截向左移12位(12)
	 */
	private final long timestampLeftShift = sequenceBits;

	/**
	 * 生成序列的掩码，这里为095 4(0b111111111111=0xfff=4095)
	 */
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);


	/**
	 * 毫秒内序列(0~4095)
	 */
	private long sequence = 0L;

	/**
	 * 上次生成ID的时间截
	 */
	private long lastTimestamp = -1L;

	//==============================Constructors=====================================

	/**
	 * 构造函数
	 */
	public SimpleSnowflakeIdWorker() {
	}

	// ==============================Methods==========================================

	/**
	 * 获得下一个ID (该方法是线程安全的)
	 *
	 * @return SnowflakeId
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();

		//如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(
					String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		//如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			//毫秒内序列溢出
			if (sequence == 0) {
				//阻塞到下一个毫秒,获得新的时间戳
				timestamp = tilNextMillis(lastTimestamp);
			}
		}
		//时间戳改变，毫秒内序列重置
		else {
			sequence = 0L;
		}

		//上次生成ID的时间截
		lastTimestamp = timestamp;

		//移位并通过或运算拼到一起组成64位的ID
		return ((timestamp - twepoch) << timestampLeftShift)
				| sequence;
	}

	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 *
	 * @param lastTimestamp 上次生成ID的时间截
	 * @return 当前时间戳
	 */
	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 *
	 * @return 当前时间(毫秒)
	 */
	protected long timeGen() {
		return System.currentTimeMillis();
	}


}
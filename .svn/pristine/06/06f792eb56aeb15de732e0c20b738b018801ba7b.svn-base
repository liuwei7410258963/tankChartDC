package com.oket.dispenser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BzTraceRelOutGroupServiceImplTest {
	private static final Logger logger = LoggerFactory
			.getLogger(BzTraceRelOutGroupServiceImplTest.class);

	private static ExecutorService pool = Executors.newFixedThreadPool(1);

	public static void change(String inputPath, String outputPath, String ffmpegPath) {

		getPath();
		if (!checkfile(inputPath)) {
			logger.info(inputPath + "is not file");
			return;
		}

		if (process(inputPath, outputPath, ffmpegPath)) {
			logger.info("转换成功。请移步" + outputPath + "查看");
		}
	}

	/**
	 * 获取Path 路径
	 */
	public static void getPath() {
		// 先获取当前项目路径，在获得源文件、目标文件、转换器的路径
		File diretory = new File("");
		try {
			String currPath = diretory.getAbsolutePath();
//            inputPath = "C:\\Users\\Administrator\\Desktop\\CH1-20200226155642-20200226155643.avi";
//            outputPath = "C:\\Users\\Administrator\\Desktop\\";
//            ffmpegPath = "E:\\project\\PRACTICETEST\\src\\ffmpeg\\bin\\";
			logger.info("视频路径为：" + currPath);
		} catch (Exception e) {
			logger.info("获取文件路径出错");
		}
	}

	public static boolean process(final String inputPath, final String outputPath, final String ffmpegPath) {

		//0:FFmpeg  1:mencoder  9:无对应类型
		int type = checkContentType(inputPath);
		logger.info("判断FFmpeg能否转换  0:FFmpeg  1:mencoder  9:无法转换" + type);
		final boolean status = false;

		//FFmpeg能解析格式  直接转MP4
		if (type == 0) {
			logger.info("开始转成mp4格式");
			//记录开始时间
			long startTime = System.currentTimeMillis();
			//定义线程
//			Callable call = new Callable<Boolean>() {
//				@Override
//				public Boolean call() throws Exception {
			try {
				processMp4(inputPath, outputPath, ffmpegPath);
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//					return true;
//				}
//			};

//			// 手动控制线程
//			Future<Boolean> result = pool.submit(new FutureTask(()->{
//				System.out.println("");
//				return true;
//			}));

//			try {
//				// 如果在超时时间内，没有数据返回：则抛出TimeoutException异常
//				Object callResult = result.get(10, TimeUnit.SECONDS);
//				System.out.println(callResult);
//			} catch (InterruptedException e) {
//
//				logger.error("InterruptedException发生");
//				return false;
//			} catch (ExecutionException e) {
//				logger.error("ExecutionException发生");
//				return false;
//			} catch (TimeoutException e) {
//				logger.error("TimeoutException发生，意味着线程超时报错");
//				return false;
//			}
			logger.info("主程序执行完成……");
			//记录结束时间
			long endTime = System.currentTimeMillis();
			//执行时长
			float excTime = (float) (endTime - startTime) / 1000;
			logger.info("执行时长" + excTime + "s");
			logger.info("视频转换完成状态" + status);
			return status;
		}

		// FFmpeg无法解析格式，使用mencoder转换AVI后再FFmpeg转MP4
//        if (type == 1) {
//            System.out.println("mencoder转换AVI");
//            //记录开始时间
//            long startTime = System.currentTimeMillis();
//            String avifilepath = processAVI(type);
//            if (avifilepath == null) {
//                System.out.println("avi文件没有得到");
//                return false;
//            } else {
//                status = processMp4(inputPath,outputPath,ffmpegPath);
//            }
//            //记录结束时间
//            long endTime = System.currentTimeMillis();
//            //执行时长
//            float excTime = (float) (endTime - startTime) / 1000;
//            System.out.println("执行时长" + excTime + "s");
//            return status;
//        }

		if (type == 9) {
			logger.info("格式非法！");
			return false;
		}

		return status;
	}

	/**
	 * 判断FFmpeg能否转换  0:FFmpeg  1:mencoder  9:无法转换
	 *
	 * @return
	 */
	private static int checkContentType(String inputPath) {
		String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length()).toLowerCase();

		// ffmpeg能解析的格式：（avi mpg wmv 3gp mov mp4 asf asx flv wmv9 rm rmvb ）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}

		/**
		 * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),先用工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		 */
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}

		return 9;
	}

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}


	/**
	 * ffmpeg能解析的格式  直接转换MP4
	 *
	 * @param
	 * @return
	 */
	private static ProcessStatus  processMp4(String inputPath, String outputPath, String ffmpegPath) throws TimeoutException, InterruptedException, IOException {

		if (!checkfile(inputPath)) {
			logger.error(inputPath + " is not file");
			//TODO:
			return null;
		}
		List<String> command = new ArrayList<String>();
		command.add(ffmpegPath + "ffmpeg");
		command.add("-i");
		command.add(inputPath);
		command.add("-c:v");
		command.add("libx264");
		command.add("-mbd");
		command.add("0");
		command.add("-c:a");
		command.add("aac");
		command.add("-strict");
		command.add("-2");
		command.add("-pix_fmt");
		command.add("yuv420p");
		command.add("-movflags");
		command.add("faststart");
		command.add(outputPath);
		Process videoProcess = null;
		videoProcess= new ProcessBuilder(command).redirectErrorStream(true).start();
		Worker worker = new Worker(videoProcess);
		worker.start();
		ProcessStatus ps = worker.getProcessStatus();
		try {
			worker.join(10*1000);
			if (ps.exitCode == ProcessStatus.CODE_STARTED) {
				// not finished
				worker.interrupt();
				throw new TimeoutException();
			} else {
				return ps;
			}
		} catch (InterruptedException | TimeoutException e) {
			// canceled by other thread.
			worker.interrupt();
			throw e;
		} finally {
			videoProcess.destroy();
		}
//		try {
//			videoProcess= new ProcessBuilder(command).redirectErrorStream(true).start();
//
//			new PrintStream(videoProcess.getErrorStream()).start();
//			videoProcess.waitFor(10, TimeUnit.SECONDS);
//			logger.info("视频转换成功！！！");
//			return true;
//		} catch (Exception e) {
//			logger.error("视频转换失败，原因是：" + e);
//			return false;
//		}finally {
//			if (videoProcess!=null) {
//				videoProcess.destroy();
//			}
//		}
	}

	private static class Worker extends Thread {
		private final Process process;
		private ProcessStatus ps;

		private Worker(Process process) {
			this.process = process;
			this.ps = new ProcessStatus();
		}

		public void run() {
			try {
				InputStream is = process.getInputStream();
				ps.output =BzTraceRelOutGroupServiceImplTest.toString(is);
				ps.exitCode = process.waitFor();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		public ProcessStatus getProcessStatus() {
			return this.ps;
		}
	}
	public static class ProcessStatus {
		public static final int CODE_STARTED = -257;
		public volatile int exitCode;
		public volatile String output;
	}

	public static void main(String[] args) {
		process("F:\\a.avi", "F:\\a.mp4", "D:\\ideawork\\backup\\sub-Backup\\doc\\ffmpeg\\bin\\");
	}

	public static String toString(InputStream inputStream){
		BufferedReader bw = null;
		try {
			bw = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = bw.readLine()) != null) {
				stringBuffer.append(line);
			}
			logger.info("done");
			return stringBuffer.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
	static class PrintStream extends Thread {
		java.io.InputStream inputStream = null;

		public PrintStream(java.io.InputStream is) {
			inputStream = is;
		}

		@Override
		public void run() {
			BufferedReader bw = null;
			try {
				bw = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
				String line = null;
				while ((line = bw.readLine()) != null) {
					logger.error(line);
				}
				logger.info("done");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
	}
}

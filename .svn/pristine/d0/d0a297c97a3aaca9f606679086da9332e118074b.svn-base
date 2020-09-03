package com.oket.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

public class ReadWriteFile {
	private static Logger logger = LoggerFactory.getLogger(ReadWriteFile.class);

	/**
	 * 创建指定的txt文件
	 */
	public static boolean createTxtFile(String fileInfo) {
		File file = new File(fileInfo);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}

	}

	public void writeListToFile(String absolutePath, String fileName, List<String> content) {
		File file = checkFileExist(absolutePath, fileName);
		ReadWriteFile.writeFile(file, content);
	}

	/**
	 * 读取指定txt文件内容
	 */
	public static List<String> readTxtFile(String fileInfo) throws Exception {
		List<String> contents = new ArrayList<String>();
		String read = "";
		FileReader fileReader = new FileReader(fileInfo);
		BufferedReader bufReader = new BufferedReader(fileReader);
		while ((read = bufReader.readLine()) != null) {
			contents.add(read);
		}
		bufReader.close();
		fileReader.close();
		return contents;
	}

	/**
	 * 追加写入指定txt文件
	 */
	public static boolean appendTxtFile(File file, List<String> contents) {
		boolean appended = false;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			for (int i = 0; i < contents.size(); i++) {
				writer.write((String) contents.get(i));
				writer.newLine();
			}
			appended = true;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {

					logger.error("", e);
				}
			}
		}
		return appended;
	}

	public static boolean appendTxtFile(File file, String content) {
		boolean appended = false;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));

			writer.write(content);
			writer.newLine();
			appended = true;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
		return appended;
	}

	/**
	 * 写入指定文件
	 */
	public static boolean writeFile(File file, List<String> contents) {
		boolean writed = false;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < contents.size(); i++) {
				writer.write((String) contents.get(i));
				writer.newLine();
			}
			writed = true;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
		return writed;
	}

	/**
	 * 写入指定文件
	 */
	public static boolean writeFile(File file, String contents) {
		boolean writed = false;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(contents);
			writer.newLine();
			writed = true;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
		return writed;
	}

	public static void writeToCsv(String filePath, String fileName, boolean append, List<Object[]> values) throws IOException {
		final File file = ReadWriteFile.checkFileExist(filePath, fileName);
		FileOutputStream fos = new FileOutputStream(file, append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡");
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
		csvPrinter.printRecords(values);
		csvPrinter.flush();
		csvPrinter.close();
	}

	public static void writeToCsv(String filePath, String fileName, boolean append, Object... values) throws IOException {
		final File file = ReadWriteFile.checkFileExist(filePath, fileName);
		FileOutputStream fos = new FileOutputStream(file, append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡");
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
		csvPrinter.printRecord(values);
		csvPrinter.flush();
		csvPrinter.close();
	}


	/**
	 * 判断文件路径是否存在，不存在则创建
	 *
	 * @param path
	 */
	public static void checkPathExist(String path) {
		java.io.File file = new java.io.File(path);
		if (file.isDirectory()) {
		} else {
			file.mkdirs();
		}
	}

	/**
	 * @param filePath
	 * @param fileName
	 */
	public static File checkFileExist(String filePath, String fileName) {
		ReadWriteFile.checkPathExist(filePath);
		File file = new File(filePath, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("文件创建失败", e);
			}
		}
		return file;
	}

	/**
	 * 文件拷贝
	 *
	 * @param srcPath :源路径
	 * @param dstPath :目的路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean copyFile(String srcPath, String dstPath) {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			srcChannel = new FileInputStream(srcPath).getChannel();
			dstChannel = new FileOutputStream(dstPath).getChannel();
			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		} finally {
			try {
				if (srcChannel != null) {
					srcChannel.close();
				}
				if (dstChannel != null) {
					dstChannel.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 获取扩展名
	 *
	 * @param name
	 * @return
	 */
	public static String getExtendName(String name) {
		String extendName = new String("");
		if (name == null || name.length() <= 0) {
			return extendName;
		}
		if (name.indexOf(".") == -1 && name.indexOf("/") == -1) {
			return name;
		}
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * 读取properties属性文件
	 *
	 * @param filePath :文件路径
	 * @return
	 */
	public static Properties readPropertiesFile(String filePath) {
		FileInputStream fis = null;
		Properties pro = null;
		try {
			fis = new FileInputStream(filePath);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
		return pro;
	}

	/**
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static boolean createTxtFile(String path, String fileName) {
		File file = new File(path, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("", e);
				return false;
			}
		}
		return true;

	}

	/**
	 * 获取目录下所有文件(按文件时间排序)
	 *
	 * @param path
	 * @return
	 */

	public static List<File> getFileSort(String path) {
		List<File> list = getFiles(path);
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<File>() {
				@Override
				public int compare(File file, File newFile) {
					if (file.lastModified() < newFile.lastModified()) {
						return -1;
					} else if (file.lastModified() == newFile.lastModified()) {
						return 0;
					} else {
						return 1;
					}
				}
			});
		}
		return list;

	}

	/**
	 * 获取目录下所有文件(按文件时间排序)
	 *
	 * @param path
	 * @return
	 */

	public static List<File> getFileSortEx(String path) {
		List<File> files = new ArrayList<>();
		List<File> list = getAllFiles(path,files);
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<File>() {
				@Override
				public int compare(File file, File newFile) {
					if (file.lastModified() < newFile.lastModified()) {
						return -1;
					} else if (file.lastModified() == newFile.lastModified()) {
						return 0;
					} else {
						return 1;
					}
				}
			});
		}
		return list;

	}
	/**
	 * 获取目录下所有文件,包含子目录
	 *
	 * @param path
	 * @param files
	 * @return
	 */
	public static List<File> getAllFiles(String path, List<File> files) {
		File realFile = new File(path);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getAllFiles(file.getAbsolutePath(), files);
				} else {
					files.add(file);
				}
			}
		}
		return files;
	}

	/**
	 * 获取目录下所有文件,不包含子目录
	 *
	 * @param path
	 * @return
	 */
	public static List<File> getFiles(String path) {
		List<File> files = new ArrayList<File>();
		File realFile = new File(path);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles(new FileNameSelector("txt"));
			for (File file : subfiles) {
				if (file.isDirectory()) {

				} else {
					files.add(file);
				}
			}
		}
		return files;
	}

	//获取不带后缀名的文件名
	public static String getFileNameWithoutSuffix(File file){
		String fileName = file.getName();
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	//获取不带后缀名的文件名
	public static String getParentFileNameWithoutPrefix(File file){
		String fileName = file.getParent();
		return fileName.substring(fileName.lastIndexOf("\\")+1);
	}
	//删除指定文件
	public static void deleteFiles(String path){
		File file = new File(path);
		if (file.exists()) { // 如果已存在,删除旧文件
			file.delete();
		}
	}


	static class FileNameSelector implements FilenameFilter {
		String extension = ".";

		public FileNameSelector(String fileExtensionNoDot) {
			extension += fileExtensionNoDot;
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(extension);
		}
	}
}

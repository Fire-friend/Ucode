package cn.firefriend.code.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GetFileContentUtil {
	public static String readToString(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 */
	public static File findFiles(String baseDirName, String targetFileName) {
		if(baseDirName == null || targetFileName == null){
			return null;
		}
		File baseDir = new File(baseDirName); // 创建一个File对象
		if (!baseDir.exists() || !baseDir.isDirectory()) { // 判断目录是否存在
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
		}
		String tempName = null;

		File tempFile = null;
		File[] files = baseDir.listFiles();
		if (files.length == 0) {// 该文件夹下没有文件，为空文件夹
			System.out.println("为空文件夹");
			return null;
		}
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (tempName.equals(targetFileName)) {
					System.out.println(tempFile.getAbsoluteFile().toString());
					return tempFile.getAbsoluteFile();
				}
			} else {
				File findFile = findFiles(tempFile.getAbsolutePath(), targetFileName);
				if (findFile != null) {
					return findFile.getAbsoluteFile();
				}
			}
		}
		return null;
	}

}

package cn.firefriend.code.service;

import java.io.File;

public interface FileService {
	
	/**
	 * 获取指定文件的内容
	 * @param path 文件路径
	 * @return 字符串的形式返回
	 */
	public String getFileContent(String path);
}

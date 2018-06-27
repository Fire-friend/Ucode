package cn.firefriend.code.service.Impl;

import java.io.File;

import org.springframework.stereotype.Service;

import cn.firefriend.code.service.FileService;
import cn.firefriend.code.util.GetFileContentUtil;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String getFileContent(String path) {
		File file = new File(path);
		if(!file.isFile()){
			return "该文件不是合法文件！";
		}
		String readToString = GetFileContentUtil.readToString(path);
		return readToString;
	}

}

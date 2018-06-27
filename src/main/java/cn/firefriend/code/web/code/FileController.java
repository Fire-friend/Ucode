package cn.firefriend.code.web.code;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.firefriend.code.service.FileService;
import cn.firefriend.code.util.GetFileContentUtil;
import cn.firefriend.code.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/getFileContent.do")
	@ResponseBody
	public Map<String, String> getFileContent(HttpServletRequest request){
		Map<String,String> modelMap = new HashMap<String, String>();
		
		String codeName = HttpServletRequestUtil.getString(request, "codeName");
		String path = HttpServletRequestUtil.getString(request, "path");
		File findFiles = GetFileContentUtil.findFiles(path, codeName);
		if(findFiles == null){
			modelMap.put("info", "该文件不合法");
		}
		String fileContent = fileService.getFileContent(findFiles.toString());
		String replace = fileContent.replace("<", "&lt");
		String replace2 = replace.replace(">", "&gt");
		modelMap.put("info", replace2);
		return modelMap;
	}
}

package cn.firefriend.code.util;

import java.util.HashMap;
import java.util.Map;

import cn.firefriend.code.entity.Code;

public class AddMapToListUtil {
	public static Map<String ,String> creatCodeMap(Code code){
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "1");
		map.put("codeName", code.getCodeName());
		map.put("codeImgPath", code.getImagePath());
		map.put("description", code.getDescription());
		map.put("creat_time", code.getCreatTime().toString());
		if(code.isPublic()){
			map.put("isPublic", "true");
		}else{
			map.put("isPublic", "false");
		}
		return map;
	}
}

package cn.firefriend.code.web.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/test", method=RequestMethod.GET)
public class TestController {
	
	@RequestMapping("/jsonArr")
	@ResponseBody
	public List<Map<String,String>> jsonArrayTest(){
		Map<String,String> map1 = new HashMap<String, String>();
		map1.put("a", "1");
		map1.put("b", "1");
		
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("a", "1");
		map2.put("b", "2");
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(map1);
		list.add(map2);
		return list;
	}

}

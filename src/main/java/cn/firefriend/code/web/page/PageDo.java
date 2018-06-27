package cn.firefriend.code.web.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/page",method=RequestMethod.GET)
public class PageDo {
	
	@RequestMapping(value="/pageindex")
	public String page1(){
		return "normal/userindex";
	}
	
	@RequestMapping(value="/creat_project")
	public String page3(){
		return "normal/creat_project";
	}
	
	@RequestMapping(value="/projects_creat")
	public String page4(){
		return "normal/projects_creat";
	}
	
	@RequestMapping(value="/projects_join")
	public String page5(){
		return "normal/projects_join";
	}
	
	@RequestMapping(value="/projects_detail")
	public String page6(){
		return "normal/projects_detail";
	}
	
	@RequestMapping(value="/agile_board")
	public String page7(){
		return "normal/agile_board";
	}
	
}

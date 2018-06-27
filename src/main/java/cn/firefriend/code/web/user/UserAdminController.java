package cn.firefriend.code.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/useradmin",method=RequestMethod.GET)
public class UserAdminController {
	
	@RequestMapping(value="/login")
	public String userLogin(){
		return "login";
	}
	
	@RequestMapping(value="/register")
	public String userregister(){
		return "register";
	}
}

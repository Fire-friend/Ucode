package cn.firefriend.code.web.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.firefriend.code.dto.UserExecution;
import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.UserStateEnum;
import cn.firefriend.code.service.UserService;
import cn.firefriend.code.util.HttpServletRequestUtil;
import cn.firefriend.code.util.SendJMail;
import cn.firefriend.exception.UserException;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@SuppressWarnings("finally")
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, String> register(HttpServletRequest request) {
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		String email = HttpServletRequestUtil.getString(request, "email");
		String phone = HttpServletRequestUtil.getString(request, "phone");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUsername(username);
		Map<String,String> modelMap = new HashMap<String, String>();
		
		try {
			UserExecution addUser = userService.addUser(user);
			int state = addUser.getState();
			if(state == 1){
				modelMap.put("status", "0");
			}else{
				modelMap.put("status", "1");
				modelMap.put("msg", addUser.getStateInfo());
			}
		} catch (UserException e) {
			modelMap.put("status", "1");
			modelMap.put("msg", "注册失败");
			e.printStackTrace();
		}finally {
			return modelMap;
		}
		
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request){
		Map<String,String> modelMap = new HashMap<String, String>();
		String email = HttpServletRequestUtil.getString(request, "email");
		String password = HttpServletRequestUtil.getString(request, "password");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		UserExecution queryUser = userService.queryUser(user);
		if(queryUser.getState() != 1){
			modelMap.put("status", "1");
			modelMap.put("msg", queryUser.getStateInfo());
		}else{
			modelMap.put("status", "0");
			User userSession = queryUser.getUser();
			request.getSession().setAttribute("user", userSession);
		}
		return modelMap;
	}
	/**
	 * 验证邮箱唯一
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public Map<String, Integer> checkEmail(HttpServletRequest request) {
		Map<String, Integer> modelMap = new HashMap<String, Integer>();
		String emailAdress = HttpServletRequestUtil.getString(request, "emailAdress");
		boolean userisExist = userService.UserisExist(emailAdress);
		if (userisExist == true) {
			modelMap.put("status", 0);
		} else {
			modelMap.put("status", 1);
		}
		return modelMap;
	}

	@RequestMapping("/sendEmail")
	@ResponseBody
	public Map<String, String> sendEmail(HttpServletRequest request) {
		Map<String, String> modelMap = new HashMap<String, String>();
		String type = HttpServletRequestUtil.getString(request, "type");
		//如果type=veryfy，为前台获取邮箱验证需求
		if(type.equals("veryfy")){
			String sessionCode = (String) request.getSession().getAttribute("emailVeryfyCode");
			String code = HttpServletRequestUtil.getString(request, "code");
			if(!code.equals(sessionCode)){
				modelMap.put("status", "0");
				modelMap.put("msg", "邮箱验证码错误！");
			}else{
				modelMap.put("status", "1");
			}
			return modelMap;
		}
		
		String emailAdress = HttpServletRequestUtil.getString(request, "emailAdress");
		// 地址为空直接返回
		if (emailAdress == null) {
			modelMap.put("status", "1");
			modelMap.put("msg", "输入邮箱为空！");
			return modelMap;
		}
		// 生成6位随机数作为验证码
		Random random = new Random();
		String veryfyCode = "";
		for (int i = 0; i < 6; i++) {
			veryfyCode += random.nextInt(10);
		}
		boolean sendMail = SendJMail.sendMail(emailAdress, "欢迎注册ucode,您的验证码是" + veryfyCode);
		request.getSession().setAttribute("emailVeryfyCode", veryfyCode);// 将验证码存入session中
		System.out.println("邮箱验证码：" + veryfyCode);
		if (sendMail == false) {
			modelMap.put("status", "1");
			modelMap.put("msg", "发送邮件失败！");
		}else{
			modelMap.put("status", "0");
		}
		return modelMap;
	}
	
	/**
	 * 获取后台生成的验证码用于前端验证
	 * @param request
	 * @return
	 */
	@RequestMapping("/imageVailCode")
	@ResponseBody
	public Map<String,String> imageVailCode(HttpServletRequest request){
		Map<String,String> modelMap = new HashMap<String, String>();
		String veryCodeKatcha = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(veryCodeKatcha != null){
			modelMap.put("veryfyCode", veryCodeKatcha.toUpperCase());
		}
		return modelMap;
	}
	
	/**
	 * 获取session中的用户
	 * @param request
	 * @return status:0|1 ，0表示session中无用户
	 */
	@RequestMapping("/getSessionUser")
	@ResponseBody
	public Map<String,String> getSessionUser(HttpServletRequest request){
		Map<String, String> modelMap = new HashMap<String, String>();
		User user = null;
		user = (User) request.getSession().getAttribute("user");
		if(user == null){
			modelMap.put("status", "0");
		}else{
			modelMap.put("status", "1");
			modelMap.put("username", user.getUsername());
		}
		return modelMap;
		
	}
}

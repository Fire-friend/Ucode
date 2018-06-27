package cn.firefriend.code.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	/**
	 * 验证验证码
	 * @param request
	 * @return bool值
	 */
	public static boolean checkVeryCode(HttpServletRequest request) {
		//获取katcha中生成验证码
		String veryCodeKatcha = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//获取用户输入验证码
		String userCode = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		//比较验证码
		if(userCode == null || !userCode.toLowerCase().equals(veryCodeKatcha.toLowerCase())){
			return false;
		}
		return true;
	}
}

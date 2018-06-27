package cn.firefriend.code.service;

import cn.firefriend.code.dto.UserExecution;
import cn.firefriend.code.entity.User;
import cn.firefriend.exception.UserException;

public interface UserService {
	/**
	 * 注册用户
	 * @param user 注册用户
	 * @return 注册用户信息
	 * @throws UserException 用户异常
	 */
	public UserExecution addUser(User user) throws UserException; 
	
	/**
	 * 查询单个用户，用于登录信息验证
	 * @param user
	 * @return
	 */
	public UserExecution queryUser(User user); 
	
	/**
	 * 通过邮箱查询用户，用于注册，验证邮箱是否存在
	 * @param email 验证邮箱
	 * @return true为存在，false为不存在 
	 */
	public boolean UserisExist(String email);
}

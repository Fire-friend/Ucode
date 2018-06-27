package cn.firefriend.code.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.firefriend.code.dao.UserMapper;
import cn.firefriend.code.dto.UserExecution;
import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.UserStateEnum;
import cn.firefriend.code.service.UserService;
import cn.firefriend.code.util.MD5Utils;
import cn.firefriend.exception.UserException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper usermapper;
	
	@Override
	public UserExecution addUser(User user) throws UserException {
		if(user == null){
			return new UserExecution(UserStateEnum.NULL_USER_INFO);
		}
		try {
			//md5加密
			String pwd = MD5Utils.md5(user.getPassword());
			user.setPassword(pwd);
			
			int record = usermapper.insertUser(user);
			if(record < 1){
				return new UserExecution(UserStateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			throw new UserException("addUser error:"+e.getMessage());
			
		}
		return new UserExecution(UserStateEnum.SUCCESS,user);
	}

	@Override
	public UserExecution queryUser(User user) {
		if(user.getEmail() == null){
			return new UserExecution(UserStateEnum.NULL_USER_EMAIL);
		}
		User dataUser = usermapper.queryUserByEmail(user.getEmail());
		if(dataUser == null){
			return new UserExecution(UserStateEnum.NULL_USER_INFO);
		}
		//比较密码
		String md5Pwd = MD5Utils.md5(user.getPassword());
		
		if(dataUser.getPassword().equals(md5Pwd) ){
			return new UserExecution(UserStateEnum.SUCCESS,dataUser);
		}else{
			return new UserExecution(UserStateEnum.LOGIN_ERROR);
		}
	}

	@Override
	public boolean UserisExist(String email) {
		User user = null;
		user = usermapper.queryUserByEmail(email);
		return user == null?false:true;
		
	}

}

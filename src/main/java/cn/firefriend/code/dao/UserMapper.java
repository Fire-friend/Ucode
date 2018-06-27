package cn.firefriend.code.dao;

import java.util.List;

import cn.firefriend.code.entity.User;

public interface UserMapper {
	//通过邮箱查询
	public User queryUserByEmail(String email);
	
	//添加用户
	public int insertUser(User user);
	
	//查询所有用户
	public List<User> queryUserList();
	
	//更新用户
	public int updateUser(User user);
	
}

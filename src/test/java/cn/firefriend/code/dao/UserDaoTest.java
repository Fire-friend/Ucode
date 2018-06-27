package cn.firefriend.code.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.firefriend.code.base.BaseTest;
import cn.firefriend.code.entity.User;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private UserMapper usermapper;
	
	@Test
	public void queryUserByEmailTest(){
		User user = usermapper.queryUserByEmail("admin");
	}
	
	@Test
	public void queryListTest(){
		List<User> list = usermapper.queryUserList();
	}
	
	@Test
	public void insertUserTest(){
		User user = new User();
		user.setEmail("7771");
		user.setPassword("777");
		user.setPhone("777");
		user.setUsername("777");
		int record = usermapper.insertUser(user);
		System.out.println(record);
	}
	
	@Test
	public void updateUserTest(){
		User user = new User();
		user.setPassword("123");
		user.setEmail("test");
		int record = usermapper.updateUser(user);
		
	}
}

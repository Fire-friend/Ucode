package cn.firefriend.code.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.firefriend.code.base.BaseTest;
import cn.firefriend.code.dto.UserExecution;
import cn.firefriend.code.entity.User;
import cn.firefriend.exception.UserException;

public class UserServiceTest extends BaseTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void addUserTest(){
		User user = new User();
		user.setUsername("demo");
		user.setPassword("456");
		user.setEmail("test");
		user.setPhone("789");
		try {
			UserExecution userExecution = userService.addUser(user);
			String info = userExecution.getStateInfo();
			assertEquals("操作成功", info);
			System.out.println(info);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

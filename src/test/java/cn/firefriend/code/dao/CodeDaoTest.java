package cn.firefriend.code.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.firefriend.code.base.BaseTest;
import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;

public class CodeDaoTest extends BaseTest{
	@Autowired
	private CodeMapper codeMapper;
	
	@Test
	public void insertTest(){
		Code code = new Code();
		code.setCreatTime(new Date());
		code.setCodeName("test");
		code.setDescription("test");
		code.setPublic(true);
		code.setPath("testpath");
		int record = codeMapper.insertCode(code);
		System.out.println("code:" + code.getCodeId());
	}
	
	@Test
	public void insertUserCodeTest(){
		Code code = new Code();
		User user = new User();
		user.setUserId(1);
		code.setCodeId(6);
		code.setUser(user);
		code.setPermissions(10);
		int record = codeMapper.insertCodeUser(code);
	}
	
	@Test
	public void queryAllTest(){
		User user = new User();
		user.setUserId(8);
		List<Code> codeList = codeMapper.queryAll(user);
		assertEquals(3,codeList.size());
	}
	
	@Test
	public void queryByCodeName(){
		Code code = new Code();
		code.setCodeName("test3");
		User user = new User();
		user.setUserId(8);
		code.setUser(user);
		Code codeQuery = codeMapper.queryByCodeNameAndUser(code);
		System.out.println("12");
		assertEquals(codeQuery.getCodeName(), "test3");
	}
	
	@Test
	public void queryByCodeId(){
		Code code = new Code();
		code.setCodeId(33);
		code.setPermissions(10);
		Code codeQuery = codeMapper.queryByCodeId(code);
		assertEquals(codeQuery.getCodeId(), 33);
	}
	
}

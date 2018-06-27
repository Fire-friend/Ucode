package cn.firefriend.code.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.firefriend.code.base.BaseTest;
import cn.firefriend.code.dto.CodeExecution;
import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.CodeStateEnum;

public class CodeServiceTest extends BaseTest {

	@Autowired
	private CodeService codeService;

	/**
	 * 创建新工程测试
	 */
	@Test
	public void insertProTest() {
		User user = new User();
		user.setUserId(1);
		user.setEmail("269182663@qq.com");
		Code code = new Code();
//		code.setCreatTime(new Date());
		code.setCodeName("test");
		code.setDescription("test");
		code.setPublic(true);
//		code.setPath("testpath1");
		code.setUser(user);
		code.setPermissions(10);
		CodeExecution codeExecution = codeService.insertProject(code);
		assertEquals(CodeStateEnum.SUCCESS.getStateInfo(),codeExecution.getStateInfo());
	}
	
	/**
	 * 旧工程添加成员测试
	 */
	@Test
	public void insertProTest2() {
		User user = new User();
		user.setUserId(2);
		Code code = new Code();
		code.setCodeId(14);
		code.setUser(user);
		code.setPermissions(5);
		CodeExecution codeExecution = codeService.insertProject(code);
		assertEquals(CodeStateEnum.SUCCESS.getStateInfo(),codeExecution.getStateInfo());
	}
	
	@Test
	public void queryAllTest(){
		User user = new User();
		user.setUserId(1);
		CodeExecution codeExecution = codeService.queryAllProject(user);
		List<Code> codeList = codeExecution.getCodeList();
		assertEquals(2, codeList.size());
	}
	
	@Test
	public void queryByCodeId(){
		User user = new User();
		Code code = new Code();
		code.setCodeName("test3");
		user.setUserId(8);
		code.setUser(user);
		CodeExecution queryCode = codeService.queryCode(code);
		Code codeDetail = queryCode.getCode();
		assertEquals(codeDetail.getCodeId(), 33);
	}
}

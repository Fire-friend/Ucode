package cn.firefriend.code.service;

import cn.firefriend.code.dto.CodeExecution;
import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;

public interface CodeService {
	/**
	 * 
	 * 如果permissions=10,新建工程,先将项目信息插入tb_code中，再将user和code关联信息插入tb_code_user中。<br/>
	 * 如果permissions=5,旧工程添加成员，直接将user和code关联信息插入tb_code_user中。<br/><br/>
	 * 采用事物管理，失败进行回滚事物
	 * @param code code中的user应包含当前用户信息,同时包含权限信息
	 * @return CodeExecution包含状态信息，及code类
	 */
	public CodeExecution insertProject(Code code);
	
	/**
	 * 查询用户所有项目
	 * @param user 根据userId进行查询所有
	 * @return 
	 */
	public CodeExecution queryAllProject(User user);
	
	/**
	 * 查询指定项目详细信息
	 * @param code 包含当前用户信息和codeName
	 * @return 返回项目详细信息和项目创建者信息
	 */
	public CodeExecution queryCode(Code code);
}

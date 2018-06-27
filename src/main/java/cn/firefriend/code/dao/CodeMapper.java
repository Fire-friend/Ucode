package cn.firefriend.code.dao;

import java.util.List;

import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;

public interface CodeMapper {
	
	/**
	 * 插入项目，插入code表
	 * @param code
	 * @return
	 */
	public int insertCode(Code code);
	/**
	 * 插入项目，插入code_user表
	 * @param code
	 * @return
	 */
	public int insertCodeUser(Code code);
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public List<Code> queryAll(User user);
	
	/**
	 * 根据codeName和当前用户查询code信息
	 * @param code
	 * @return
	 */
	public Code queryByCodeNameAndUser(Code code);
	
	/**
	 * 根据codeId查询工程admin用户,permissions为10
	 * @param code
	 * @return
	 */
	public Code queryByCodeId(Code code);
}

package cn.firefriend.code.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.firefriend.code.dao.CodeMapper;
import cn.firefriend.code.dto.CodeExecution;
import cn.firefriend.code.entity.Code;
import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.CodeStateEnum;
import cn.firefriend.code.service.CodeService;
import cn.firefriend.code.util.GitUtil;
import cn.firefriend.exception.CodeException;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeMapper codeMapper;

	@Override
	@Transactional // 开启事物
	public CodeExecution insertProject(Code code) {
		if (code == null) {
			return new CodeExecution(CodeStateEnum.NULL_CODE);
		} else if (code.getUser() == null) {
			return new CodeExecution(CodeStateEnum.NULL_USER_INFO);
		}
		try {
			try {
				String proPath = GitUtil.creatDir(code.getUser().getEmail(), code.getCodeName());
				if (proPath == null) {
					throw new CodeException("proPath为空");
				}
				//克隆远程仓库
				boolean creatCloneRep = GitUtil.creatCloneRep(proPath);
				if(creatCloneRep == false){
					throw new CodeException("克隆远程仓库失败");
				}
				code.setPath(proPath);// 将仓库相对路径添加进code
			} catch (Exception e) {
				throw new CodeException("远程仓库创建失败！" + e.getMessage());
			}
			try {
				/**
				 * 如果permissions为10，代表新建项目，否则为旧项目添加成员
				 * 先进行插入code表，将项目信息插入，code实体类会自动返回插入的code_id存入codeId中
				 */
				if (code.getPermissions() == 10) {
					code.setCreatTime(new Date());
					int record = codeMapper.insertCode(code);
					if (record <= 0) {
						throw new CodeException("插入tb_code失败");
					}
				}
			} catch (Exception e) {
				throw new CodeException("插入tb_code失败:" + e.getMessage());
			}
			try {
				/**
				 * 再进行插入code_user表，关联信息进行插入
				 */
				int record = codeMapper.insertCodeUser(code);
				if (record <= 0) {
					throw new CodeException("插入tb_code_user失败");
				}
			} catch (Exception e) {
				throw new CodeException("插入tb_code_user失败:" + e.getMessage());
			}
		} catch (Exception e) {
			throw new CodeException("insertPro error:" + e.getMessage());
		}
		return new CodeExecution(CodeStateEnum.SUCCESS, code);
	}

	
	public CodeExecution queryAllProject(User user) {
		if(user == null){
			return new CodeExecution(CodeStateEnum.NULL_USER_INFO);
		}
		try {
			List<Code> allProject = codeMapper.queryAll(user);
			return new CodeExecution(CodeStateEnum.SUCCESS,allProject);
		} catch (Exception e) {
			throw new CodeException("查询所有项目失败:" + e.getMessage());
		}
	}


	@Override
	public CodeExecution queryCode(Code code) {
		if(code == null){
			return new CodeExecution(CodeStateEnum.NULL_CODE);
		}else if(code.getUser() == null){
			return new CodeExecution(CodeStateEnum.NULL_USER_INFO);
		}
		try {
			Code codeTemp = codeMapper.queryByCodeNameAndUser(code);
			Code codeDetail = codeMapper.queryByCodeId(codeTemp);
			return new CodeExecution(CodeStateEnum.SUCCESS,codeDetail);
		} catch (Exception e) {
			throw new CodeException(e.getMessage());
		}
	}

}

package cn.firefriend.code.dto;

import java.util.List;

import cn.firefriend.code.entity.Code;
import cn.firefriend.code.enums.CodeStateEnum;
public class CodeExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 数量
	private int count;

	// 操作的code
	private Code code;

	// code列表（查询店铺列表时使用）
	private List<Code> codeList;

	public CodeExecution() {
	}

	// 查询用户失败构造方法
	public CodeExecution(CodeStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 查询用户成功方法
	public CodeExecution(CodeStateEnum stateEnum, Code code) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.code = code;
	}

	public CodeExecution(CodeStateEnum stateEnum, List<Code> codeList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.codeList = codeList;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public int getCount() {
		return count;
	}

	public Code getCode() {
		return code;
	}

	public List<Code> getCodeList() {
		return codeList;
	}
}

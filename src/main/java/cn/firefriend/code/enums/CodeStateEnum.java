package cn.firefriend.code.enums;

public enum CodeStateEnum {
	CHECK(0, "审核中"),  SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(
			-1001, "操作失败"), NULL_CODE(-1002, "项目为空"), NULL_USER_INFO(
			-1003, "用户信息为空");
	//状态
	private int state;
	//状态标识
	private String stateInfo;

	private CodeStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}

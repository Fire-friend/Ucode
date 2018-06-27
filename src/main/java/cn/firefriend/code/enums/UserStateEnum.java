package cn.firefriend.code.enums;

public enum UserStateEnum {
	CHECK(0, "审核中"),  SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(
			-1001, "操作失败"), NULL_USER_EMAIL(-1002, "UserEmail为空"), NULL_USER_INFO(
			-1003, "用户信息为空"),LOGIN_ERROR(-1004,"用户名密码不匹配");
	//状态
	private int state;
	//状态标识
	private String stateInfo;

	private UserStateEnum(int state, String stateInfo) {
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

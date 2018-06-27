package cn.firefriend.code.dto;

import java.util.List;

import cn.firefriend.code.entity.User;
import cn.firefriend.code.enums.UserStateEnum;
public class UserExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	// 操作的user
	private User user;

	// shop列表（查询店铺列表时使用）
	private List<User> userList;

	public UserExecution() {
	}

	// 查询用户失败构造方法
	public UserExecution(UserStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 查询用户成功方法
	public UserExecution(UserStateEnum stateEnum, User user) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}

	public UserExecution(UserStateEnum stateEnum, List<User> userList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.userList = userList;
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

	public User getUser() {
		return user;
	}

	public List<User> getUserList() {
		return userList;
	}
	
	
}

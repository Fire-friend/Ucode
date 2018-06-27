package cn.firefriend.code.entity;

import java.util.Date;

public class Code {
	private int codeId;
	private String codeName;//项目名称
	private String path;//项目路径
	private boolean isPublic;//是否公开，默认为公开
	private Date creatTime;//创建时间
	private String description;//项目描述
	private User user;//插入用户
	private int permissions;//权限等级 10为创建者，5为成员
	private String imagePath = "resources/img/time.jpg";//项目图片
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public int getCodeId() {
		return codeId;
	}
	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}

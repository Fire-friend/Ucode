package cn.firefriend.code.entity;

public class Massage {
	private long to_code_id;
	
	private String content;
	
	private long from_user_id;

	public long getTo_code_id() {
		return to_code_id;
	}

	public void setTo_code_id(long to_code_id) {
		this.to_code_id = to_code_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(long from_user_id) {
		this.from_user_id = from_user_id;
	}
	
	
}

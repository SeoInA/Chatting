package com.ina.message.VO;

import java.util.Date;

public class UserVO {
	private String user_id;
	private String name;
	private int unReadCount;
	
	@Override
	public String toString() {
		return "UserVO [user_id=" + user_id + ", name=" + name + ", unReadCount=" + unReadCount + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}
}
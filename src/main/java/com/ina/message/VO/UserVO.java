package com.ina.message.VO;

import java.sql.Timestamp;
import java.util.Date;

public class UserVO {
	private int userid;
	private String username;
	private String nick;
	private String userpw;
	
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", username=" + username + ", nick=" + nick + ", userpw=" + userpw + "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
}
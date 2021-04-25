package com.ina.message.VO;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component("cSession")
public class ChatSession {
	
	private static ArrayList<Integer> loginUser = new ArrayList<Integer>();
	
	public void addLoginUser(int id) {
		loginUser.add(id);
	}
	
	public void removeLoginUser(int id) {
		loginUser.remove(id);
	}

	public static ArrayList<Integer> getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(ArrayList<Integer> loginUser) {
		ChatSession.loginUser = loginUser;
	}
}

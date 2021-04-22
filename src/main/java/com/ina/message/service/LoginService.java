package com.ina.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ina.message.VO.UserVO;
import com.ina.message.DAO.LoginDAO;

@Service
public class LoginService{

	@Autowired
	 LoginDAO loginDAO;
	
	public UserVO login(UserVO vo) throws Exception {
		UserVO returnVO = null;
		try {
			returnVO = loginDAO.readMemberWithIDPW(vo.getNickName(), vo.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = null; //실행하다 문제가 생겼을때 해당 데이터를 보내지않겠다는 의미 = 예외처리
		}
		return returnVO;
	}
	
	
	public int userIdCheck(String user_id) throws Exception {
		return loginDAO.checkOverId(user_id);
	}
	
}

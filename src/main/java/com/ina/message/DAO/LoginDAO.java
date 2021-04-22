package com.ina.message.DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ina.message.VO.UserVO;
@Repository
public class LoginDAO {
	
	@Autowired
	SqlSession sqlSession;

	private static String namespace ="com.ina.message.mappers.LoginMapper";

	public UserVO readMemberWithIDPW(String userid, String userpw) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(namespace+".login", paramMap);
	}

	public int checkOverId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".checkOverId",user_id);
	}

}

package com.ina.login;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class LoginDAO {
	
	@Autowired
	SqlSession sqlSession;

	private static String namespace ="com.ina.message.mappers.LoginMapper";

	public UserVO readMemberWithIDPW(String userid, String userpw) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		System.out.println(paramMap);
		
		return sqlSession.selectOne(namespace+".login", paramMap);
	}

	public int checkOverId(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".checkOverId",user_id);
	}
	
	public UserVO getUserInfo(int id) throws Exception{
		return sqlSession.selectOne(namespace+".getUserInfo",id);
	}

	public List<UserVO> userList() throws Exception{
		return sqlSession.selectList(namespace+".userList");
	}
}

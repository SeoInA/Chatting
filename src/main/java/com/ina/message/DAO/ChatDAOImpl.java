package com.ina.message.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.MessageVO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	SqlSession session;
	

	private static String namespace = "com.ina.message.mappers.ChatMapper";
	
	@Override
	public void createRoom(ChatRoomVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".createRoom" , vo);
	}

	@Override
	public ChatRoomVO isRoom(ChatRoomVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		ChatRoomVO roomvo = null;
		roomvo = session.selectOne(namespace+".isRoom", vo);
		System.out.println(roomvo);
		
		return roomvo;
	}

	@Override
	public void insertMessage(MessageVO vo) throws Exception {
		session.insert(namespace+".insertMessage" , vo);
	}

	@Override
	public String getPartner(ChatRoomVO vo) throws Exception {
		List<MessageVO> mvo = session.selectList(namespace+".getPartner", vo);
		
		return mvo.get(0).getReceiver_id();
	}

//
//
//	@Override
//	public String getProfile(String str) throws Exception {
//		return session.selectOne(namespace+".getProfile" , str);
//	}
//


	@Override
	public String getName(String str) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getName" , str);
	}

	@Override
	public List<MessageVO> getMessageList(String str) throws Exception {
		// TODO Auto-generated method stub

			return session.selectList(namespace+".getMessageList" , str);
	}
	
	@Override
	public List<ChatRoomVO> getRoomList(String str) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".getRoomList",str);
	}

	@Override
	public List<ChatRoomVO> getRoomList2(String str) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".getRoomList2" , str);
	}


/*
	@Override
	public MessageVO getRecentMessage(String str) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getRecentMessage" , str);
	}*/
	
//	@Override
//	public List<ChatRoomVO> getRoomListTutor(String str) throws Exception {
//		// TODO Auto-generated method stub
//		return session.selectList(namespace+".getRoomListTutor" , str);
//	}

	
	@Override
	public void updateReadTime(int class_id, String user_id, String TUTOR_USER_user_id) throws Exception {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> map = new HashMap<String, Object> ();
		
		map.put("sender_id", TUTOR_USER_user_id);
		map.put("receiver_id", user_id);
		//map.put("CLASS_class_id", class_id);
		session.update(namespace+".updateReadTime" , map);
		
	}



	@Override
	public int getUnReadCount(String TUTOR_USER_user_id, int class_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object> ();
		
		map.put("sender_id", TUTOR_USER_user_id);
		map.put("reeiver_id", user_id);
		//map.put("CLASS_class_id", class_id);
		
		
		return session.selectOne(namespace+".getUnReadCount" , map);
	}


	@Override
	public int getAllCount(String str) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object> ();
		
		map.put("receiver_id", str);
		map.put("sender_id", str);
		if(session.selectOne(namespace+".getAllCount" ,map) ==null) {
			return 0;
		}else {
			
			return session.selectOne(namespace+".getAllCount" ,map);
		}
	}
	/*
	@Override
	public int getUnReadCountTutor(String TUTOR_USER_user_id, int class_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object> ();
		
		map.put("TUTOR_USER_user_id", TUTOR_USER_user_id);
		map.put("USER_user_id", user_id);
		map.put("CLASS_class_id", class_id);
		
		return session.selectOne(namespace+".getUnReadCountTutor" , map);
	}
	*/
}


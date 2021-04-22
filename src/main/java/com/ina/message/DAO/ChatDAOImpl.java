package com.ina.message.DAO;
import java.util.HashMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.UserVO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	SqlSession session;
	

	private static String namespace = "com.ina.message.mappers.ChatMapper";


	@Override
	public void addChatRoom(ChatRoomVO chatRoom) throws IOException {
		session.insert(namespace+".addChatRoom",chatRoom);
		
	}

	@Override
	public void updateFileName(int sender_id, String fileName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sender_id", sender_id);
		map.put("fileName", fileName);
		session.insert(namespace+".updateFileName",map);
	}
	
	@Override
	public int countByChatId(int receiver_id, int sender_id)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sender_id", sender_id);
		map.put("receiver_id", receiver_id);
		return session.selectOne(namespace+".countByChatId",map);
	}

	@Override
	public ChatRoomVO findByChatId(int receiver_id, int sender_id)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sender_id", sender_id);
		map.put("receiver_id", receiver_id);
		return session.selectOne(namespace+".findByChatId",map);
	}

	@Override
	public int getId(int receiver_id, int sender_id)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sender_id", sender_id);
		map.put("receiver_id", receiver_id);
		return session.selectOne(namespace+".getRoomId",map);
	}

	@Override
	public void updateChatReadBuy(int id, int chatReadBuy) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatReadRec", chatReadBuy);
		session.update(namespace+".updateChatReadRec",map);
	}

	@Override
	public void updateChatReadSell(int id, int chatReadSell) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("chatReadSend", chatReadSell);
		session.update(namespace+".updateChatReadSend",map);
	}
	
	@Override
	public List<UserVO> userList() throws Exception{
		return session.selectList(namespace+".userList");
	}
	
	@Override
	public String getNameByID(int id) throws Exception{
		return session.selectOne(namespace+".getNameByID",id);
	}
}


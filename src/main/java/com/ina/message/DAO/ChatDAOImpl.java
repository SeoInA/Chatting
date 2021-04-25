package com.ina.message.DAO;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.MessageVO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	SqlSession sqlSession;
	

	private static String namespace = "com.ina.message.mappers.ChatMapper";

	@Override
	public ChatRoomVO selectChatRoom(String roomId) {
		return sqlSession.selectOne(namespace+".selectChatRoom", roomId);
	}
	
	@Override
	public int insertMessage(MessageVO chatMessage) {
		return sqlSession.insert(namespace+".insertMessage", chatMessage);
	}
	
	@Override
	public List<MessageVO> messageList(String roomId) {
		return sqlSession.selectList(namespace+".messageList", roomId);
	}

	@Override
	public int createChat(ChatRoomVO room) {
		return sqlSession.insert(namespace+".createChat", room);
	}

	@Override
	public ChatRoomVO searchChatRoom(ChatRoomVO room) {
		return sqlSession.selectOne(namespace+".searchChatRoom", room);
	}

	@Override
	public List<ChatRoomVO> chatRoomList(String userEmail) {
		return sqlSession.selectList(namespace+".chatRoomList", userEmail);
	}

	@Override
	public int selectUnReadCount(MessageVO message) {
		return sqlSession.selectOne(namespace+".selectUnReadCount",message);
	}

	@Override
	public int updateCount(MessageVO message) {
		return sqlSession.update(namespace+".updateCount", message);
	};
}


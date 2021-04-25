package com.ina.message.DAO;

import java.util.List;
import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.MessageVO;

public interface ChatDAO {

	public ChatRoomVO selectChatRoom(String roomId);
	
	public int insertMessage(MessageVO chatMessage);
	
	public List<MessageVO> messageList(String roomId);
	public int createChat(ChatRoomVO room);

	public ChatRoomVO searchChatRoom(ChatRoomVO room);

	public List<ChatRoomVO> chatRoomList(String userEmail);

	public int selectUnReadCount(MessageVO message);

	public int updateCount(MessageVO message);
}
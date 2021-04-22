package com.ina.message.DAO;

import java.util.List;
import java.io.IOException;

import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.UserVO;

public interface ChatDAO {

	public void addChatRoom(ChatRoomVO chatRoom) throws IOException;

	public void updateFileName(int id, String fileName) throws Exception;

	public int countByChatId(int receiver_id, int sender_id)throws Exception;

	public ChatRoomVO findByChatId(int receiver_id, int sender_id)throws Exception;

	public int getId(int receiver_id, int sender_id)throws Exception;
	
public void updateChatReadBuy(int id, int chatReadBuy)throws Exception;

	public void updateChatReadSell(int id, int chatReadSell)throws Exception;
	
	public List<UserVO> userList() throws Exception;
	
	public String getNameByID(int id) throws Exception;
}
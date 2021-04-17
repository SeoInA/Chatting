package com.ina.message.VO;

public class ChatRoomVO {

	
	private String chatroom_id;
	private String sender_id;
	private String receiver_id;
	
	public String getChatroom_id() {
		return chatroom_id;
	}
	public void setChatroom_id(String chatroom_id) {
		this.chatroom_id = chatroom_id;
	}
	
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	@Override
	public String toString() {
		return "ChatRoomVO [chatroom_id=" + chatroom_id + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id
				+ "]";
	}
	
}
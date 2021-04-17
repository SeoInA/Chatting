package com.ina.message.VO;

import java.util.Date;

import com.google.gson.Gson;

public class MessageVO {
	
	private String message_id;
	private String sender;
	private String receiver;
	private String content;
	private Date sendTime;
	private Date readTime;
	private String chatroom_id;
	private String sender_id;
	private String receiver_id;
//	private String user_profileImagePath;
//	private String receiver_user_profileImagePath;

	private int unReadCount;

	@Override
	public String toString() {
		return "MessageVO [message_id=" + message_id + ", sender=" + sender + ", receiver=" + receiver + ", content="
				+ content + ", sendTime=" + sendTime + ", readTime=" + readTime + ", chatroom_id=" + chatroom_id
				+ ", sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", unReadCount=" + unReadCount + "]";
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

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

	public int getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}
	
	public static MessageVO convertMessage(String source) {
		MessageVO message = new MessageVO();
		Gson gson = new Gson();
		message = gson.fromJson(source,  MessageVO.class);
		return message;
	}
	
}
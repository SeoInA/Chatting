package com.ina.message.VO;
import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatRoomVO{

	private int id;
	private int sender_id;
	private int receiver_id;
	private String fileName;
	private Timestamp regDate;
	private String senderName;
	private String receiverName;
	private String content;
	private String sendTime;
	private int chatReadSend;
	private int chatReadRec;
	
	@JsonCreator
	public ChatRoomVO (@JsonProperty("content") String content,@JsonProperty("sender_id") int sender_id,@JsonProperty("senderName") String senderName,@JsonProperty("sendTime") String sendTime) {
		this.content = content;
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.sender_id = sender_id;
	}
	public ChatRoomVO() {
		super();
		
	}
	public ChatRoomVO(int id, int sender_id, int receiver_id, String fileName,
			Timestamp regDate, String senderName, String receiverName, int chatReadSend, int chatReadRec,String content, String sendTime) {
		
		this.id = id;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.fileName = fileName;
		this.regDate = regDate;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.chatReadSend = chatReadSend;
		this.chatReadRec = chatReadRec;
		this.content=content;
		this.sendTime=sendTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getChatReadSend() {
		return chatReadSend;
	}
	public void setChatReadSend(int chatReadSend) {
		this.chatReadSend = chatReadSend;
	}
	public int getChatReadRec() {
		return chatReadRec;
	}
	public void setChatReadRec(int chatReadRec) {
		this.chatReadRec = chatReadRec;
	}
	@Override
	public String toString() {
		return "ChatRoomVO [id=" + id + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", fileName="
				+ fileName + ", regDate=" + regDate + ", senderName=" + senderName + ", receiverName=" + receiverName
				+ ", content=" + content + ", sendTime=" + sendTime + ", chatReadSend=" + chatReadSend
				+ ", chatReadRec=" + chatReadRec + "]";
	}
	
}
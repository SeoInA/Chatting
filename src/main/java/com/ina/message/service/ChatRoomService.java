package com.ina.message.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ina.message.DAO.ChatDAO;
import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.UserVO;

@Service
public class ChatRoomService {
    
    @Autowired
    ChatDAO chatDao;
    
    //application.properties에 설정
   // @Value("${file.upload.path.txt}")
   
    public void addChatRoom(ChatRoomVO chatRoom) throws IOException {
        
        chatDao.addChatRoom(chatRoom);
        
    }
    
    //no connection with DB
    public List<ChatRoomVO> readChatHistory(ChatRoomVO chatRoom,String fileUploadPath) throws IOException {
        
        String pathName = fileUploadPath + chatRoom.getFileName();
        
        //DB에 저장된 chat.txt 파일을 읽어옴 
        BufferedReader br = new BufferedReader(new FileReader(pathName));
        //View에 ChatRoom 객체로 전달
        ChatRoomVO chatRoomLines = new ChatRoomVO();
        List<ChatRoomVO> chatHistory = new ArrayList<ChatRoomVO>();
 
        String chatLine;
        int idx = 1;
        
        while ((chatLine = br.readLine()) != null) {
            
            //1개 메시지는 3줄(보낸사람,메시지내용,보낸시간)로 구성돼있음
            int answer = idx % 3;
            if (answer == 1) {
                //보낸사람
                chatRoomLines.setSenderName(chatLine);
                idx++;
            } else if (answer == 2) {
                //메시지내용
                chatRoomLines.setContent(chatLine);
                idx++;
            } else {
                //보낸시간
                chatRoomLines.setSendTime(chatLine);
                //메시지 담긴 ChatRoom 객체 List에 저장
                chatHistory.add(chatRoomLines);
                //객체 초기화, 줄(row)인덱스 초기화
                chatRoomLines = new ChatRoomVO();
                idx = 1;
            }            
        }
        
        return chatHistory;
    }
    

    public void updateFileName(int sender_id, String fileName)throws Exception {
 
        chatDao.updateFileName(sender_id, fileName);
    }
    
    public void createFile(int sender_id, int receiver_id,String fileUploadPath) throws Exception {
        
        String fileName = sender_id + "_" + receiver_id + ".txt";
        String pathName = fileUploadPath + fileName;
        //File 클래스에 pathName 할당
        File txtFile = new File(pathName);
        //로컬경로에 파일 생성
        txtFile.createNewFile();
        
        chatDao.updateFileName(sender_id, fileName);
    }
    
    public int countByChatId(int receiver_id,int sender_id) throws Exception{
    	return chatDao.countByChatId(receiver_id,sender_id);
    }
    public ChatRoomVO findByChatId(int receiver_id,int sender_id) throws Exception{
    	return chatDao.findByChatId(receiver_id,sender_id);
    }
    public int getId(int receiver_id,int sender_id) throws Exception{
		
		return chatDao.getId(receiver_id,sender_id);
	}
    
  //no connection with DB
  	public void appendMessage(ChatRoomVO chatRoom,String fileUploadPath) throws Exception {
  		
  		
  		ChatRoomVO chatRoomAppend = chatDao.findByChatId(chatRoom.getReceiver_id(),chatRoom.getSender_id());
  				
  		String pathName = fileUploadPath + chatRoomAppend.getFileName();
  		
  		FileOutputStream fos = new FileOutputStream(pathName, true);
  		String content = chatRoom.getContent();
  		String senderName = chatRoom.getSenderName();
  		int senderId = chatRoom.getSender_id();
  		String sendTime = chatRoom.getSendTime();
  		System.out.println("print:" + content);
  		
  		String writeContent = senderName + "\n" + content + "\n" + "[" +  sendTime + "]" + "\n";
  		
  		byte[] b = writeContent.getBytes();
  		
  		fos.write(b);
  		fos.close();
  		
  		System.out.println("senderId: "+ senderId);
  		System.out.println("receiverId: "+ chatRoom.getReceiver_id());
  		if (senderId==chatRoom.getReceiver_id()) {
  			updateChatReadBuy(chatRoom.getId(), 0);
  		} else {
  			updateChatReadSell(chatRoom.getId(), 0);
  		}
  		
  	}
  	
	public void updateChatReadBuy(int id, int chatReadBuy)throws Exception {
		
		chatDao.updateChatReadBuy(id, chatReadBuy);
		
	}

	public void updateChatReadSell(int id, int chatReadSell) throws Exception{
		
		chatDao.updateChatReadSell(id, chatReadSell);
		
	}
	
	
//	UserList 불러오기
	public List<UserVO> userList() throws Exception{
		return chatDao.userList();
	}
	
	//id에 맞는 이름 가져오기
	public String getNameByID(int id) throws Exception{
		return chatDao.getNameByID(id);
	}
}
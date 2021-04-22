package com.ina.message.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ina.message.VO.MessageVO;
import com.ina.message.VO.ChatRoomVO;
import com.ina.message.VO.UserVO;
import com.ina.message.VO.TimeUtils;
import com.ina.message.service.ChatRoomService;

@Controller
public class ChatController {
	@Autowired
    private SimpMessagingTemplate simpMessage;
    
    @Autowired
    private ChatRoomService chatRoomService;
    
  //채팅으로 거래하기(productInfo 화면)
    @RequestMapping(value="/chatMessage/{id}", method=RequestMethod.GET)
    public String getWebSocketWithSockJs(@PathVariable int id,Model model, HttpSession session, 
            @ModelAttribute("chatRoom") ChatRoomVO chatRoom,HttpServletRequest request) throws Exception {
        int receiver_id=id;
        //productInfo화면에서 Chat화면에 전달해줄 parameter
    	String receiverName=chatRoomService.getNameByID(receiver_id);
        UserVO login = (UserVO) session.getAttribute("login");
        chatRoom.setReceiver_id(receiver_id);
        chatRoom.setReceiverName(receiverName);
        chatRoom.setSender_id(login.getId());
        chatRoom.setSenderName(login.getName());
        chatRoom.setFileName(login.getId() + "_" + receiver_id + ".txt");
        
        String fileUploadPath=request.getSession().getServletContext().getRealPath("/") + "resources/chat_file/";
        //이미 chatRoom이 만들어져있는지 확인
        if (chatRoomService.countByChatId(receiver_id, chatRoom.getSender_id()) > 0) {
            //get ChatRoomInfo
            ChatRoomVO chatRoomTemp = chatRoomService.findByChatId(receiver_id, chatRoom.getSender_id());
            
            //load existing chat history
            List<ChatRoomVO> chatHistory = chatRoomService.readChatHistory(chatRoomTemp,fileUploadPath);
            System.out.println(chatHistory);
            //transfer chatHistory Model to View
            model.addAttribute("chatHistory", chatHistory);
            
        } else {
            //chatRoom 생성            
            chatRoomService.addChatRoom(chatRoom);            
            //text file 생성
            chatRoomService.createFile(chatRoom.getSender_id(), receiver_id,fileUploadPath);                                
        }
 
            //chatRoom Add 시 생성될 chatId
            chatRoom.setId(chatRoomService.getId(receiver_id, chatRoom.getSender_id()));
          
            //chatRoom 객체 Model에 저장해 view로 전달
            model.addAttribute("chatRoom", chatRoom);
        
        return "chatRoom";
    }

	
	@MessageMapping("/chat")
    public void send(ChatRoomVO chatRoom,HttpServletRequest request) throws Exception {
		String fileUploadPath=request.getSession().getServletContext().getRealPath("/") + "resources/chat_file/";;
        //append message to txtFile
        chatRoomService.appendMessage(chatRoom,fileUploadPath);
        
        int id = chatRoom.getId();
        String url = "/user/" + id + "/queue/message";
        System.out.println("here?");
        simpMessage.convertAndSend(url, new ChatRoomVO(chatRoom.getContent(), chatRoom.getSender_id(),chatRoom.getSenderName(), chatRoom.getSendTime())); 
    }
}

package com.ina.message;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ina.message.VO.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ina.message.VO.ChatRoomVO;
import com.ina.message.service.ChatRoomService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Locale locale, Model model) throws Exception{
		
		HttpSession session = request.getSession();
		UserVO vo=new UserVO();
		vo.setId((Integer)session.getAttribute("ID"));
		vo.setName((String)session.getAttribute("Name"));
		session.setAttribute("login", vo);
		
		List<UserVO> list=chatRoomService.userList();
		ObjectMapper mapper=new ObjectMapper();
		String userList=mapper.writeValueAsString(list);
		System.out.println(list);
		model.addAttribute("list",list);
		return "list";
	}
	
}

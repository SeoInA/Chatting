package com.ina.message;

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

import com.ina.login.LoginService;
import com.ina.login.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		if(session.getAttribute("id")==null) {
			return "list";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Locale locale, Model model) throws Exception{
		
		HttpSession session = request.getSession();
		UserVO vo=new UserVO();
		vo.setUserid((Integer)session.getAttribute("ID"));
		vo.setUsername((String)session.getAttribute("Name"));
		session.setAttribute("login", vo);
		
		List<UserVO> list=loginService.userList();
		model.addAttribute("list", list);
		return "list";
	}
	
}

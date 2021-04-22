package com.ina.message;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ina.message.VO.UserVO;
import com.ina.message.service.LoginService;

@Controller 
public class LoginController { 
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String loginForm(Model model) throws Exception { 
		model.addAttribute("userVO", new UserVO()); 
		return "login"; 
	} 
	//로그인 성공 or 실패
	@RequestMapping(value="/login/loginProcess",method=RequestMethod.POST)
	public ModelAndView loginProcess(HttpSession session,UserVO user) throws Exception {
		ModelAndView mav;

		if(session.getAttribute("login")!=null) { //기존에 login이라는 세션 값이 존재할 경
			session. removeAttribute("login"); //기존 값을 제거한
		}
		System.out.println(user);
			//로그인이 성공하면 User 객체를 반환한다.
		UserVO one=loginService.login(user);
		System.out.println(one);
		if(one!=null) {//로그인 성공
				
			session.setAttribute("Name", one.getName()); //세션에 login이란 이름으로 User 객체를 저장한다.
			session.setAttribute("ID", one.getId()); //세션에 login이란 이름으로 User 객체를 저장한다.
			session.setAttribute("email", one.getEmail());
			session.setAttribute("login", one);
				
			mav=new ModelAndView("redirect:/list");
		}
		else {
			mav=new ModelAndView("login");
		}
		System.out.println("loginProcess controller end");
		return mav;
	}
}

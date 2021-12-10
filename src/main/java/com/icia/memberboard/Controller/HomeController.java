package com.icia.memberboard.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.memberboard.Service.memberBoardService;
import com.icia.memberboard.Service.memberBoardServiceInter;
import com.icia.memberboard.dto.memberBoardDTO;

@Controller
public class HomeController {
	
	@Autowired
	private memberBoardServiceInter ms;
	
	@Autowired
	private HttpSession session ;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		HttpSession session = request.getSession();
        session.invalidate();		
		return "index";
	
	
	}
	// 회원가입 폼으로 이동
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String saveform() {
		
		return "signup";	
	}
	// 회원가입정보 DB 전달
	@RequestMapping (value="/save" ,method=RequestMethod.POST)
	public String save(@ModelAttribute memberBoardDTO member) throws IllegalStateException, IOException {
		ms.save(member);
		
		return "index";
	}
	// 회원가입 아이디 중복처리
	@RequestMapping(value="/idDuplicate", method=RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam ("m_id") String m_id) {
		
		System.out.println("MemberController.idDuplicate() : "+m_id);
		String result = ms.idDuplicate(m_id);
		System.out.println("result : "+result);
		return result; // "ok" or "no"
	}
	
	
	// 로그인 폼으로 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginform() {
		
		return "login";	
	}
	//로그인 처리
	@RequestMapping (value="/login" ,method=RequestMethod.POST)
	public String login(@ModelAttribute memberBoardDTO member,Model model)  {
		memberBoardDTO loginmember=ms.login(member);
		model.addAttribute("member", loginmember);
		System.out.println(loginmember);
		if(loginmember==null) 
			return "login";
		else if (loginmember.getM_id().equals("admin")) {
			session.setAttribute("loginID", loginmember.getM_id());
			//session.setAttribute("loginPW", loginmember.getM_password());
			return "board/admin";
		}
		else { 
			
			session.setAttribute("loginID", loginmember.getM_id());
			//session.setAttribute("loginPW", loginmember.getM_password());
			return "redirect:board/paging?page="+1+ "&id="+loginmember.getM_id();
			
		}
	}
	
	// 마이페이지 폼으로 이동
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(@RequestParam ("m_id") String m_id,Model model) {
		memberBoardDTO member=ms.mypage(m_id);
		model.addAttribute("member", member);
		return "/mypage";	
	}
	
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(@ModelAttribute memberBoardDTO member) throws IllegalStateException, IOException{
		System.out.println(member);
		ms.memberUpdate(member);
		return "redirect:board/paging?page="+1 ;	
	}
	
	
}
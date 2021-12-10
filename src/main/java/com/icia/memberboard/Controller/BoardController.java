package com.icia.memberboard.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icia.memberboard.Service.BoardService;
import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.dto.PageDTO;
import com.icia.memberboard.dto.memberBoardDTO;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
//	// 로그아웃
//	@RequestMapping (value="logout", method=RequestMethod.GET)
//	public String logout(HttpServletRequest request) {
//		System.out.println("Asdfasdfasdfasdf");
//		HttpSession session = request.getSession();
//        session.invalidate();
//        return "/index";
//	}
	
	// 전체목록
	@RequestMapping (value="board/findAll", method=RequestMethod.GET)
	public String findAll(Model model) {	
		List <BoardDTO> BoardList=bs.findAll();	
		model.addAttribute("board",BoardList);
		return "/board/findAll";
	}
	
	// 페이징 처리
	@RequestMapping(value="board/paging", method=RequestMethod.GET)	
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model, @RequestParam(value="pageShow", required=false, defaultValue="3") int pageShow) {
		
		
		List<BoardDTO> boardList = bs.pagingList(page,pageShow);  
		PageDTO paging = bs.paging(page,pageShow); 
		model.addAttribute("board", boardList);
		model.addAttribute("paging", paging);
		System.out.println("11111111111"+pageShow);
		return "board/findAll";
	}
		
	 @RequestMapping(value="board/pageShow", method=RequestMethod.POST) public
	 String pageShow(@RequestParam(value="pageShow") int pageShow) {
		 System.out.println("22222222222222222"+pageShow);
	  return "/board/findAll"; }
	 
	
	// 게시글 작성 폼 이동
	@RequestMapping (value="board/save", method=RequestMethod.GET)
	public String saveform() {	
		return "/board/save";
	}
	
	// 게시글 작성
	@RequestMapping (value="board/save", method=RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException{	
		bs.save(board);
		return "redirect:/board/paging" ;
	}
	
	// 조회 페이지 이동 
	@RequestMapping (value="board/view", method=RequestMethod.GET)
	public String view(Model model ,@RequestParam ("b_number") long b_number) {
		BoardDTO board=bs.view(b_number);
		model.addAttribute("board", board);
		List <CommentDTO> commentList=bs.commentView(b_number);
		model.addAttribute("commentList", commentList);
		
		return "/board/view";	
	}
	
	// 게시글 삭제
	@RequestMapping (value="board/delete", method=RequestMethod.GET)
	public String delete(@RequestParam ("b_number") long b_number) {
		bs.delete(b_number);
		return "redirect:/board/paging";	
	}
	
	//업데이트 폼 이동 자료 넘기기
	@RequestMapping (value="board/update", method=RequestMethod.GET)
	public String updateform(@RequestParam ("b_number") long b_number, Model model) {
		BoardDTO board=bs.view(b_number);
		model.addAttribute("board", board);
		
		return "/board/update";	
	}
	// 업데이트 처리
	@RequestMapping (value="board/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		System.out.println(board);
		bs.update(board);
		return "redirect:/board/paging";
	}
	

	
	
	
	//admin 회원목록
	@RequestMapping (value="board/memberList", method=RequestMethod.GET)
	public String memberList(Model model ) {
		List <memberBoardDTO> memberList =bs.memberList();
		model.addAttribute("member", memberList);
		return "/board/memberList";	
	}
	
	// 회원 삭제	
	@RequestMapping (value="board/memberList/delete", method=RequestMethod.GET)
	public String memberDelete(@RequestParam ("m_number") long m_number) {
		System.out.println("mnum : " + m_number);
		bs.memberDelete(m_number);
		
		return "redirect:/board/memberList";	
	}
	
	// 검색기능
	@RequestMapping(value="/board/search", method=RequestMethod.GET)
	public String search(@RequestParam ("searchtype") String searchtype,
						 @RequestParam ("keyword") String keyword ,Model model) {
		List <BoardDTO> boardList=bs.search(searchtype,keyword);
		System.out.println(boardList);
		model.addAttribute("board", boardList);
		return "/board/findAll";
	}
	

}

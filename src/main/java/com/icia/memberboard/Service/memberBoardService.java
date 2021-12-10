package com.icia.memberboard.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.memberboard.Repository.memberBoardRepository;
import com.icia.memberboard.dto.memberBoardDTO;

@Service
public class memberBoardService implements memberBoardServiceInter{
	
	@Autowired
	private memberBoardRepository mr;
	
	// 회원가입
	@Override
	public void save(memberBoardDTO member) throws IllegalStateException, IOException {
		
		MultipartFile m_file = member.getM_file();
		
		String m_filename = m_file.getOriginalFilename();
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		System.out.println("m_filename: " + m_filename);

		// String savePath = "D:\\source\\Spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		String savePath = "D:\\development_Heo\\source\\spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		
		if(!m_file.isEmpty())  {
			m_file.transferTo(new File(savePath)); 
		}
		member.setM_filename(m_filename);
		
		mr.save(member);
	}

	// 로그인
	@Override
	public memberBoardDTO login(memberBoardDTO member) {
		memberBoardDTO loginmember=mr.login(member);
		return loginmember;
	}

	//@Override
	public String idDuplicate(String m_id) {
		String result=mr.idDuplicate(m_id);
		System.out.println("서비스 실행서비스 실행서비스 실행서비스 실행서비스 실행");
		System.out.println("서비스서비스서비스서비스" +m_id);
		System.out.println("서비스서비스서비스서비스" + result);
		if (result==null)
			return "ok"; //조회결과가 없기 때문에 해당 아이디는 사용 가능
		else
			return "no"; //조회결과가 있기 때문에 해당 아이디는 사용 불가능
	}

	@Override
	public memberBoardDTO mypage(String m_id) {
		return mr.mypage(m_id);
	}

	@Override
	public void memberUpdate(memberBoardDTO member) throws IllegalStateException, IOException {
		MultipartFile m_file = member.getM_file();
		
		String m_filename = m_file.getOriginalFilename();
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		System.out.println("m_filename: " + m_filename);

		String savePath = "D:\\development_Heo\\source\\spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		
		if(!m_file.isEmpty())  {
			m_file.transferTo(new File(savePath)); 
		}
		member.setM_filename(m_filename);
		mr.memberUpdate(member);
		
	}



}

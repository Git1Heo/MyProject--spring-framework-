package com.icia.memberboard.Service;

import java.io.IOException;

import com.icia.memberboard.dto.memberBoardDTO;



public interface memberBoardServiceInter {
	
	public void save (memberBoardDTO member) throws IllegalStateException, IOException;
	public memberBoardDTO login(memberBoardDTO member);
	public String idDuplicate(String m_id);
	public memberBoardDTO mypage(String m_id);
	public void memberUpdate(memberBoardDTO member) throws IllegalStateException, IOException;
}

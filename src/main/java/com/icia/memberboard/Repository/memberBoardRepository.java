package com.icia.memberboard.Repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memberboard.dto.memberBoardDTO;

@Repository
public class memberBoardRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void save(memberBoardDTO member) {
		sql.insert("memberBoard.save", member);
		
	}

	public memberBoardDTO login(memberBoardDTO member) {
		return sql.selectOne("memberBoard.login", member);
	}

	public String idDuplicate(String m_id) {
		return sql.selectOne("memberBoard.idDuplicate",m_id);
	}

	public memberBoardDTO mypage(String m_id) {
		return sql.selectOne("memberBoard.mypage", m_id);
	}

	public void memberUpdate(memberBoardDTO member) {
		sql.update("memberBoard.memberUpdate", member);
		
	}

}

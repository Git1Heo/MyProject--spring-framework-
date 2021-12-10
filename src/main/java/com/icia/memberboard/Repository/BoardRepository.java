package com.icia.memberboard.Repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.dto.memberBoardDTO;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSessionTemplate sql;
	
	public int boardCount() {
		return sql.selectOne("Board.count");
	}

	public List<BoardDTO> pagingList1(Map<String, Integer> pagingParam) {
		return sql.selectList("Board.pagingList1", pagingParam);
	}

	public List<BoardDTO> findAll() {
		return sql.selectList("Board.findAll");
	}

	public void save(BoardDTO board) {
		sql.insert("Board.save", board);		
	}

	public BoardDTO view(long b_number) {
		sql.update("Board.hits", b_number);
		return sql.selectOne("Board.view", b_number);
	}

	public void delete(long b_number) {
		sql.delete("Board.delete",b_number);
		
	}

	public void update(BoardDTO board) {
		sql.update("Board.update", board);
		
	}

	public List<memberBoardDTO> memberList() {
		return sql.selectList("Board.memberList");
	}

	public void memberDelete(long m_number) {
		sql.delete("Board.memberDelete", m_number);
		
	}

	public List<BoardDTO> search(Map<String, String> searchParam) {
		return sql.selectList("Board.search",searchParam);
	}

	// 보드 넘버에 맞는 댓글 출력
	public List<CommentDTO> commentView(long b_number) {
		return sql.selectList("Board.commentView", b_number);
	}
}

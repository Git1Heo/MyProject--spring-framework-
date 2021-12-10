package com.icia.memberboard.Service;

import java.io.IOException;
import java.util.List;

import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.dto.PageDTO;
import com.icia.memberboard.dto.memberBoardDTO;

public interface BoardService {

	public List<BoardDTO> pagingList(int page,int pageShow);

	public PageDTO paging(int page, int pageShow);

	public List<BoardDTO> findAll();

	public void save(BoardDTO board)  throws IllegalStateException, IOException;

	public BoardDTO view(long b_number);

	public void delete(long b_number);

	public void update(BoardDTO board) throws IllegalStateException, IOException;

	public List<memberBoardDTO> memberList();

	public void memberDelete(long m_number);

	public List<BoardDTO> search(String searchtype, String keyword);

	public List<CommentDTO> commentView(long b_number);

}

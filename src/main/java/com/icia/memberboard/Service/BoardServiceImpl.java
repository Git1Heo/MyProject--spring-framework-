package com.icia.memberboard.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.memberboard.Repository.BoardRepository;
import com.icia.memberboard.dto.BoardDTO;
import com.icia.memberboard.dto.CommentDTO;
import com.icia.memberboard.dto.PageDTO;
import com.icia.memberboard.dto.memberBoardDTO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository br;
	
	private static int PAGE_LIMIT = 3; 
	private static int BLOCK_LIMIT = 3; 

			@Override
			public List<BoardDTO> pagingList(int page, int pageShow) {
				if(pageShow !=3 )
					PAGE_LIMIT=pageShow;
				
				int pagingStart = (page-1) * PAGE_LIMIT;
				Map<String, Integer> pagingParam = new HashMap<String, Integer>();
				pagingParam.put("start", pagingStart);
				pagingParam.put("limit", PAGE_LIMIT);
				List<BoardDTO> pagingList = br.pagingList1(pagingParam);
				for(BoardDTO b: pagingList) {
					System.out.println(b.toString());
				}
				return pagingList;
			}

			@Override
			public PageDTO paging(int page,int pageShow) {
				if(pageShow !=3 )
					PAGE_LIMIT=pageShow;

				int boardCount = br.boardCount(); 
				int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT)); 
				int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1; 
				int endPage = startPage + BLOCK_LIMIT - 1; 
				if(endPage > maxPage)
					endPage = maxPage; 
				PageDTO paging = new PageDTO();
				paging.setPage(page);
				paging.setStartPage(startPage);
				paging.setEndPage(endPage);
				paging.setMaxPage(maxPage);
				
				System.out.println("paging.toString(): "+ paging.toString());
				
				return paging;
			}

			@Override
			public List<BoardDTO> findAll() {
				List <BoardDTO> BoardList=br.findAll();
				return BoardList;
			}
			
			// 게시글 저장(파일)
			@Override
			public void save(BoardDTO board) throws IllegalStateException, IOException {
				MultipartFile b_file = board.getB_file();
				
				String b_filename = b_file.getOriginalFilename();
				b_filename = System.currentTimeMillis() + "-" + b_filename;
				System.out.println("b_filename: " + b_filename);

				//String savePath = "D:\\source\\Spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\board\\"+b_filename;
				String savePath = "D:\\development_Heo\\source\\spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\board\\"+b_filename;
				if(!b_file.isEmpty())  {
					b_file.transferTo(new File(savePath)); 
				}
				board.setB_filename(b_filename);
				
				br.save(board);
				
			}

			@Override
			public BoardDTO view(long b_number) {
				BoardDTO board=br.view(b_number);
				return board;
			}

			@Override
			public void delete(long b_number) {
				br.delete(b_number);
				
			}
			//업데이트 처리
			@Override
			public void update(BoardDTO board)throws IllegalStateException, IOException {
				// 파일 수정
				MultipartFile b_file = board.getB_file();
				
				String b_filename = b_file.getOriginalFilename();
				b_filename = System.currentTimeMillis() + "-" + b_filename;
				System.out.println("b_filename: " + b_filename);

				// String savePath = "D:\\source\\Spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\board\\"+b_filename;
				String savePath = "D:\\development_Heo\\source\\spring\\memberBoardProject\\src\\main\\webapp\\resources\\upload\\board\\"+b_filename;
				if(!b_file.isEmpty())  {
					b_file.transferTo(new File(savePath)); 
				}
				board.setB_filename(b_filename);
				
				br.update(board);
				
			}

			@Override
			public List<memberBoardDTO> memberList() {
				List<memberBoardDTO>memberList=br.memberList();
				return memberList;
			}

			@Override
			public void memberDelete(long m_number) {
				br.memberDelete(m_number);
				
			}

			@Override
			public List<BoardDTO> search(String searchtype, String keyword) {
				Map<String,String> searchParam=new HashMap<String,String>();
				searchParam.put("type", searchtype);
				searchParam.put("word", keyword);
				List<BoardDTO> boardList=br.search(searchParam);
				return boardList;
			}

			@Override
			public List<CommentDTO> commentView(long b_number) {
				
				return br.commentView(b_number);
			}
			
			
			
			
}

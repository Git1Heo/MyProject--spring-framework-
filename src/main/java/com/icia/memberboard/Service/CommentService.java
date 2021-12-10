package com.icia.memberboard.Service;

import java.util.List;

import com.icia.memberboard.dto.CommentDTO;

public interface CommentService {

	void save(CommentDTO comment);

	List<CommentDTO> findAll(long b_number);

}

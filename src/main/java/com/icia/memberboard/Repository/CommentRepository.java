package com.icia.memberboard.Repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.memberboard.dto.CommentDTO;

@Repository
public class CommentRepository {
	@Autowired
	private SqlSessionTemplate sql;
	
	public void save(CommentDTO comment) {
		sql.insert("Comment.save",comment);
		
	}

	public List<CommentDTO> findAll(long b_number) {
		return sql.selectList("Comment.findall", b_number);
	}

}

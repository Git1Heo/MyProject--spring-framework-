package com.icia.memberboard.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private long b_number;
	private String b_title;
	private String b_writer;
	private String b_contents;
	private long b_hits;
	private Timestamp b_date;
	
	private MultipartFile b_file;
	private String b_filename;
}

## DB

멤버 테이블
~~~~
create table member_table(
	m_number bigint auto_increment,
    m_id varchar(20),
    m_password varchar(20),
    m_name varchar(7),
    m_mail varchar(20),
    m_phone varchar(20),
	m_filename varchar(40),
    
    constraint unique (m_id),
    constraint primary key (m_number)

);
~~~~

보드 테이블
~~~~
create table board_table(
	b_number bigint auto_increment,
	b_title varchar(30),
	b_writer varchar(20),
    b_contents varchar(200),
    b_hits int,
    b_date datetime,
    
    b_filename varchar(40),
	constraint primary key (b_number),
    constraint foreign key (b_writer) references member_table(m_id)

);
~~~~

댓글 
~~~~
create table comment_table(
	c_number bigint auto_increment,
	b_number bigint,
	c_writer varchar(20),
    c_contents varchar(200),
    c_date datetime,
    
	constraint primary key (c_number),
    constraint foreign key (b_number) references board_table(b_number),
	constraint foreign key (c_writer) references member_table(m_id)
);
~~~~

## JSP

### index.jsp
- 회원가입과 로그인 페이지로 이동

### signup.jsp
- 회원가입
    - 각 정보를 입력받아 아이디가 중복이 아니라면 DB에 값을 저장
    - 중복되지 않는 ID인 경우에만 회원가입
        - function sign()
        - idDuplicate의 출력값이 GOOD 일때만 회원가입 가능 하고 나머지는 alert처리
- 아이디 중복확인
    - function idDuplicate()
    - ajax를 활용해 DB에서 입력한 아이디가 있는지 검사

### login.jsp
- 입력한 값으로 select를 수행하여 없는 경우에는 다시 login창으로 가고 있는 경우에는 게시글 페이지로 이동 (아이디가 admin이면 관리자 페이지로 이동)
- 세션을 활용해 아이디와 비밀번호 보관

### memberList.jsp
- 운영자페이지에서 올 수 있는 페이지
- 회원 목록을 볼 수 있으며 회원 삭제 가능

### mypage.jsp
- 개인정보 수정 페이지
- 수정 버튼 클릭시 update 함수가 실행되며 세션에 저장된 비밀번호와 입력한 비밀번호가 일치하는경우에만 수정 가능 그외에는 alert 처리

### admin.jsp
- 회원목록과 글목록 페이지로 이동

### findAll
- 게시글 목록 페이지
- 로그아웃 클릭시 세션이 초기화되며 인덱스 페이지로 이동
- 마이페이지로 이동
- 검색 기능
- 게시글 옆의 조회를 클릭시 view 페이지로 이동
- 페이징 처리
- select로 5,10 숫자 클릭시 해당 숫자만큼 한 화면에 게시글 표시
    - 다시는 기본값이 3개식 화면에 띄우지 못함
- 게시글 작성 페이지 이동

### save.jsp
- 게시글쓰기 페이지
- 입력한 값이 BoardDTO 형태로 담겨 DB에 저장

### update.jsp
- 게시글 수정 페이지
-입력한 값이 BoardDTO 형태로 담겨 DB에 업데이트

### view.jsp
- 게시글 상세보기와 댓글 페이지
- 이벤트 리스너를 활용하여 댓글을 등록하고 출력





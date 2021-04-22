select user (), database ();

select max(BOARD_NUM) from board; 

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(26, '김상건', '1111', '마칠시간', '5시', 'test.txt', 26, 0, 0, 0, '2021-03-03');

select * from board;

delete from board where BOARD_NUM =25;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);

-- list 페이징
/*
 * 	[1][2][3]
 * 
 * (page -1) * 10 => 1 page 0, 2 page => 10, 3 page =>20
 */

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT
	  , BOARD_FILE, BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
	from board
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
 limit 0, 10;

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT
	  , BOARD_FILE, BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
	from board
 order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
 limit 10, 10;

-- listcount
select count(*) from board; 

-- selectArticle
select * from board where BOARD_NUM = 1;

-- read board
select * from board;

-- 조회수 증가
update board 
	set BOARD_READCOUNT = BOARD_READCOUNT + 1
 where BOARD_NUM = 22;

-- 글 삭제
select * from board where BOARD_NUM = 26;

delete 
  from board 
 where BOARD_NUM = 26;

select * from board where BOARD_NUM = 22 and BOARD_PASS = '1234';

-- 글 수정
update board
	set board_subject = '수업시작 시간입니다.', board_content = '9시에 시작'
 where board_num = 30;

-- 글에 대한 답변 달기
select * from board where BOARD_RE_REF = 20;

update board
	set BOARD_RE_SEQ = BOARD_RE_SEQ + 1
 where BOARD_RE_REF = 30 and BOARD_RE_SEQ > 0;

INSERT INTO board 
 (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, 
  BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ) 
 VALUES (26, '김상건?111', '1111', '6시', 'ggggg', '', 23, 1, 1);









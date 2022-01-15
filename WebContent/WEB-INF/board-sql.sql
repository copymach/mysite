-- webdb board 테이블 만들기



--테이블 상태 확인
select * from board;

--테이블 삭제 초기화
drop table board;

--테이블 내용만 초기화 
TRUNCATE TABLE board;

--id 번호 자동생성 꼬일때 초기화 (리셋)
drop SEQUENCE seq_board_no; 
drop SEQUENCE seq_hit_no; 

-- 모든 정보 보기 (카디전)
SELECT *
FROM board
;

--방명록 guestbook 테이블 만들기
create table    board (
                no number(10), --게시물식별번호                
                title varchar2(500), --NOT NULL 제목
                content varchar2(4000), --내용
                hit number (10), --조회수 30개도 에러난다 
                reg_date date NOT NULL, --등록일
                user_no number(10) NOT NULL, --회원식별번호 포린키
                primary key (no), --프라이머리 키 지정
                CONSTRAINT board_fk FOREIGN KEY (user_no)
                REFERENCES users(no)
);


--시퀀스 조회 (서버내 모든 대상)
SELECT * FROM USER_SEQUENCES;

-- board no 번호 자동생성을 위한 시퀀스 sequence
create sequence seq_board_no
increment by 1
START WITH 1
nocache
;


--게시판 정보 입력해보기 식별번호(no시퀀스), 제목, 내용, 조회수, 등록일, 작성자번호(user_no)
INSERT INTO board 
VALUES (seq_board_no.nextval, '와봤습니다', '구경왔습니다' , 13 , sysdate, 3);

INSERT INTO board 
VALUES (seq_board_no.nextval, '나도 왔습니다', '발도장 콩' , 9 , sysdate, 2);

INSERT INTO board 
VALUES (seq_board_no.nextval, '날이 춥네요', '손이시려워 꽁' , 3 , sysdate, 1);


-- 수정기능 (id n번)의 데이터를 변경
UPDATE board
SET title = '동해물과백두산이'
WHERE no = 11 ;


-- 테이블에서 (no번) 데이터를 삭제해 보세요
TRUNCATE TABLE board WHERE no = 1;

DELETE FROM board WHERE no = 2 ;


--커밋 
commit;

--롤백
rollback;






--모든 정보 출력 카티젼
select *
from board bd, users us
where bd.user_no = us.no;

select * from board;
select * from users;

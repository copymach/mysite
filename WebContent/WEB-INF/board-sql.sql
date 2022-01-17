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

INSERT INTO board 
VALUES (seq_board_no.nextval, '공이 웃으면 뭔지 알아', '풋볼' , 7 , sysdate, 4);

INSERT INTO board 
VALUES (seq_board_no.nextval, '바나나가 웃으면 뭐게', '바나나킥' , 21 , sysdate, 5);

INSERT INTO board 
VALUES (seq_board_no.nextval, '누가 내 과자 가져갔음?', '가져간 사람 빨리 도로 가져오셈 ' , 15 , sysdate, 6);

INSERT INTO board 
VALUES (seq_board_no.nextval, '서울대입구역 맛집 아는 사람', '3번출구 8층 거기 돈가스 먹을만함 ㅇㅇ ' , 31 , sysdate, 7);



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

--게시판 목록용
select  bd.no bno
        ,title
        ,content
        ,hit
        ,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate 
        ,user_no uno
        ,ur.id id
        ,ur.password password
        ,ur.name name        
from board bd, users ur
where bd.user_no = ur.no
order by regDate desc
;


--게시판 내용
select  bd.no bno
        ,title
        ,content
        ,hit
        ,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate 
        ,user_no uno
        ,ur.id id
        ,ur.password password
        ,ur.name name        
from board bd, users ur
where bd.user_no = ur.no
and bd.no = 11;
;
-- hit 티베로 조회수 증가 
--혹시 필드가 varchar 타입인 경우 TO_NUMBER로 캐스팅 하고
--혹시 NOT NULL 허용일지도 모름으로 NVL로 NULL값을 0으로 치환
UPDATE board 
set HIT = NVL(HIT, 0) + 1
WHERE board.no = 11;

UPDATE board 
set HIT = hit + 1
WHERE board.no = 11;

SELECT   * FROM board;
SELECT   * FROM users;

--삭제
DELETE FROM board
WHERE board.no = 14
and board.user_no =4
;

--getBoardId
select * FROM board
where user_no = 4
and no = 14;


--게시물 수정 업데이트
UPDATE board 
set     title = '수정한 제목',
        content = '수정한 내용'
WHERE board.no = 11;

select * from board;

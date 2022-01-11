/* webdb users 테이블 만들기 */



--테이블 상태 확인
select * from users;

--테이블 삭제 초기화
drop table users;

--id 번호 자동생성 꼬일때 초기화 (리셋)
drop SEQUENCE seq_users_no; 

-- 모든 정보 보기 (카디전)
SELECT *
FROM users
;


--시퀀스 조회
SELECT * FROM USER_SEQUENCES;


--방명록 guestbook 테이블 만들기
create table    users (
                no number(10), --식별번호
                id varchar2(20) UNIQUE, --아이디
                password varchar2(20) NOT NULL, --비밀번호
                name varchar2(20), --이름
                gender varchar2(10), --성별
                primary key (no) --프라이머리 키 지정
);



-- 저자 id번호 자동생성을 위한 시퀀스 sequence
create sequence seq_users_no
increment by 1
START WITH 1
nocache
;


--방명록 정보 입력해보기 (자동생성id, 아이디, 비밀번호, 이름, 성별)
INSERT INTO users VALUES (seq_users_no.nextval, '1234', '1234', '원투쓰리포', 'male');

INSERT INTO users VALUES (seq_users_no.nextval, 'bill', '1234', '빌게이츠', 'male');

INSERT INTO users VALUES (seq_users_no.nextval, 'mursk', '1234', '일론머스크', 'male');

INSERT INTO users VALUES (seq_users_no.nextval, 'jkbug', '1234', '주커버그', 'male');

INSERT INTO users VALUES (seq_users_no.nextval, 'mykim', '1234', '김미영팀장', 'female');

INSERT INTO users VALUES (seq_users_no.nextval, 'lisasu', '1234', '리사수', 'female');

INSERT INTO users VALUES (seq_users_no.nextval, 'merkel', '1234', '메르켈총리', 'female');

INSERT INTO users VALUES (seq_users_no.nextval, 'buffett', '1234', 'WarrenEdwardBuffett', 'male');



-- 수정기능 (id n번)의 데이터를 변경
UPDATE users
SET name = '홍길동'
WHERE no = 2 ;


-- 테이블에서 (id n번) 데이터를 삭제해 보세요
TRUNCATE TABLE users WHERE no = 1;

DELETE FROM users WHERE no = 2 ;


--커밋 
commit;

--롤백
rollback;




select * from guestbook;

select  no
        ,name
        ,password
        ,content
        ,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate
from guestbook
order by regDate desc
;


select password
from guestbook
where no = 1 
;


DELETE FROM guestbook 
WHERE no = 1 
and password = 4321
;


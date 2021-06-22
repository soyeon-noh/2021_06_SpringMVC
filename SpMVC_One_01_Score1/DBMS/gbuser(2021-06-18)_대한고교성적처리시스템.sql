SHOW databases;

CREATE DATABASE score;

SHOW DATABASES;

USE score;
SHOW TABLES;


CREATE TABLE tbl_student (
	st_num	CHAR(8)		PRIMARY KEY,
	st_name	VARCHAR(20)	NOT NULL,
	st_dept	VARCHAR(20)	NOT NULL,
	st_grade	INT	NOT NULL,
	st_tel	VARCHAR(15)	NOT NULL,
	st_addr	VARCHAR(125)
);

CREATE TABLE tbl_score (
	sc_seq BIGINT AUTO_INCREMENT PRIMARY KEY,
	sc_stnum	CHAR(8)	NOT NULL,
	sc_subject	VARCHAR(20)	NOT NULL,
	sc_score	INT	NOT NULL
);

ALTER TABLE tbl_score 
ADD CONSTRAINT fk_student
FOREIGN KEY(sc_stnum)
REFERENCES tbl_student(st_num);

ALTER TABLE tbl_student
ADD UNIQUE INDEX (st_name, st_dept, st_tel);

-- 학생정보, 성적정보 등록 (1)
INSERT INTO tbl_student (st_num, st_name, st_dept, st_grade, st_tel, st_addr)
VALUE('20210001', '김만두', '컴퓨터공학', '4', '010-0000-0000', '광주 북구 일곡동');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210001', '웹프로그래밍실습', '100');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210001', '알고리즘', '80');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210001', '자료구조', '90');

-- 중복방지 확인 (학생이름, 학과명, 전화번호)
INSERT INTO tbl_student (st_num, st_name, st_dept, st_grade, st_tel, st_addr)
VALUE('20210002', '김만두', '컴퓨터공학', '3', '010-0000-0000', '광주 북구');

-- 학생정보, 성적정보 등록 (2)
INSERT INTO tbl_student (st_num, st_name, st_dept, st_grade, st_tel, st_addr)
VALUE('20210002', '박만두', '경제학', '2', '010-0000-0000', '광주 북구 일곡동');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210002', '경제학원론2', '100');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210002', '국제무역론', '60');
INSERT INTO tbl_score (sc_stnum, sc_subject, sc_score)
VALUE('20210002', '수학1', '50');

SELECT * FROM tbl_student;
SELECT * FROM tbl_score;

DELETE FROM tbl_score
WHERE sc_stnum = '20210001';

DROP TABLE tbl_student;
DROP TABLE tbl_score;

CREATE VIEW view_HOME AS
(
	SELECT st_num h_num, 
		ST.st_name h_name, 
		ST.st_view_homedept h_dept,
		ST.st_grade h_grade,
		COUNT(SC.sc_subject) h_count,
		SUM(SC.sc_score) h_sum,
		ROUND(SUM(SC.sc_score)/COUNT(SC.sc_subject),2) h_avg
	FROM  tbl_student ST
		LEFT JOIN tbl_score SC
			ON ST.st_num = SC.sc_stnum
	GROUP BY st_num
);

DROP VIEW view_HOME;


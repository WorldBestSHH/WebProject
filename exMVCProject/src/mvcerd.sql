
/* Drop Tables */

DROP TABLE order CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE zipcode CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE member
(
	userid varchar2(10) NOT NULL,
	name varchar2(20),
	zipcode varchar2(5) NOT NULL,
	PRIMARY KEY (userid)
);


CREATE TABLE order
(
	userid varchar2(10) NOT NULL,
	pcode varchar2(5) NOT NULL
);


CREATE TABLE product
(
	pcode varchar2(5) NOT NULL,
	pname varchar2(20),
	PRIMARY KEY (pcode)
);


CREATE TABLE zipcode
(
	zipcode varchar2(5) NOT NULL,
	sido varchar2(10),
	PRIMARY KEY (zipcode)
);



/* Create Foreign Keys */

ALTER TABLE order
	ADD FOREIGN KEY (userid)
	REFERENCES member (userid)
;


ALTER TABLE order
	ADD FOREIGN KEY (pcode)
	REFERENCES product (pcode)
;


ALTER TABLE member
	ADD FOREIGN KEY (zipcode)
	REFERENCES zipcode (zipcode)
;




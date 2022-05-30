CREATE TABLE reservation
(
   reservedate DATETIME DEFAULT SYSDATE,
  campname VARCHAR2(255),
  checkin date,
  checkout date,
  clientid VARCHAR2(255),
  roomid NUMBER(5),
  roomname VARCHAR2(255)
)

DROP TABLE IF EXISTS SUPERHERO;

/**************************************************************/

CREATE TABLE SUPERHERO (
  ID NUMBER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(15) NOT NULL
);

INSERT INTO SUPERHERO (NAME) VALUES ('SPIDERMAN'), ('BATMAN');

/**************************************************************/
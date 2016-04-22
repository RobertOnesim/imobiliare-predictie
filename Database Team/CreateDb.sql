DROP TABLE PROPRIETATI cascade constraints;
DROP TABLE IMAGINI cascade constraints;
DROP TABLE GPS cascade constraints;
DROP TABLE TEXT_MINING cascade constraints;


CREATE TABLE PROPRIETATI(
  id_proprietate INTEGER PRIMARY KEY,
  titlu VARCHAR2(100) NOT NULL,
  tip VARCHAR2(20) NOT NULL,
  pret FLOAT NOT NULL,
  moneda VARCHAR2(5),
  suprafata VARCHAR(20),
  numar_camere NUMBER(2),
  an_constructie VARCHAR2(15),
  descriere VARCHAR2(2000),
  evaluare INTEGER,
  taguri VARCHAR2(1000),
  adresa VARCHAR2(100),
  vandut INTEGER
);


CREATE TABLE TEXT_MINING (
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  avantaje VARCHAR2(100),
  dezavantaje VARCHAR2(100)
);


CREATE TABLE GPS (
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  longitudine VARCHAR2(20),
  latitudine VARCHAR2(20)
);


CREATE TABLE IMAGINI(
  id_imagine INTEGER PRIMARY KEY,
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  imagine VARCHAR2(100)
);
  
  
  
  
  
  
  
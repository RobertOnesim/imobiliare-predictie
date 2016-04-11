DROP TABLE UTILIZATORI cascade constraints;
DROP TABLE PROPRIETATI cascade constraints;
--DROP TABLE FACILITATI cascade constraints;
DROP TABLE ANUNTURI cascade constraints;
DROP TABLE PROPRIETATI_VANDUTE cascade constraints;
DROP TABLE ADRESE_PROPRIETATI cascade constraints;
DROP TABLE IMAGINI cascade constraints;
DROP TABLE AGENTIE cascade constraints;
DROP TABLE CONTACTE cascade constraints;
DROP TABLE GPS cascade constraints;
DROP TABLE TEXT_MINING cascade constraints;


CREATE TABLE UTILIZATORI(
  id_utilizator INTEGER PRIMARY KEY,
  nume VARCHAR2(20) NOT NULL,
  prenume VARCHAR2(20) NOT NULL,
  numar_telefon VARCHAR(20) NOT NULL,
  mail VARCHAR2(30) NOT NULL
);

CREATE TABLE PROPRIETATI(
  id_proprietate INTEGER PRIMARY KEY,
  id_utilizator INTEGER REFERENCES UTILIZATORI(id_utilizator),
  tip_proprietare VARCHAR2(20) NOT NULL,
  pret INTEGER NOT NULL,
  moneda VARCHAR2(5),
  suprafata VARCHAR(10),
  numar_camere NUMBER(2),
  an_constructie DATE,
  descriere VARCHAR2(200),
  evaluare INTEGER,
  taguri VARCHAR2(1000)
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


CREATE TABLE AGENTIE(
  id_agentie INTEGER PRIMARY KEY,
  nume VARCHAR2(30),
  telefon VARCHAR2(15),
  strada VARCHAR2(20),
  numar VARCHAR2(10),
  oras VARCHAR2(20),
  judet VARCHAR2(20),
  cod_postal VARCHAR2(7)
);

CREATE TABLE ANUNTURI(
  id_anunt INTEGER PRIMARY KEY,
  data_publicare DATE,
  pret INTEGER,
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  id_agentie INTEGER REFERENCES AGENTIE(id_agentie)
);
  
CREATE TABLE PROPRIETATI_VANDUTE(
  id_anunt INTEGER REFERENCES ANUNTURI(id_anunt),
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  pret_final INTEGER,
  moneda VARCHAR2(5),
  data_vanzare DATE,
  PRIMARY KEY(id_anunt,id_proprietate)
);
  
  
CREATE TABLE ADRESE_PROPRIETATI(
  id INTEGER PRIMARY KEY,
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  strada VARCHAR2(20),
  numar VARCHAR2(10),
  bloc VARCHAR2(10),
  scara VARCHAR2(10),
  etaj INTEGER,
  oras VARCHAR2(20),
  judet VARCHAR2(20)
);    


CREATE TABLE IMAGINI(
  id_imagine INTEGER PRIMARY KEY,
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  imagine VARCHAR2(100)
);
  



CREATE TABLE CONTACTE(
  id_contact INTEGER PRIMARY KEY,
  id_agentie INTEGER REFERENCES  AGENTIE(id_agentie),
  nume VARCHAR2(20),
  prenume VARCHAR2(20),
  telefon VARCHAR2(15),
  email VARCHAR2(30)
);
  

/*
CREATE TABLE FACILITATI (
  id_proprietate INTEGER REFERENCES PROPRIETATI(id_proprietate),
  termopane INTEGER,
  centrala_termica INTEGER,
  tip VARCHAR2(20)
);*/
  
  
  
  
  
  
  
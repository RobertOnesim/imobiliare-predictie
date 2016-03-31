DROP TABLE UTILIZATORI cascade constraints;
DROP TABLE PROPRIETATI cascade constraints;
DROP TABLE facilitati cascade constraints;
DROP TABLE ANUNTURI cascade constraints;
DROP TABLE PROPRIETATI_VANDUTE cascade constraints;
DROP TABLE ADRESA_PRORIETATE cascade constraints;
DROP TABLE IMAGINI cascade constraints;
DROP TABLE AGENTIE cascade constraints;
DROP TABLE CONTACT cascade constraints;
DROP TABLE ADRESA_AGENTIE cascade constraints;


CREATE TABLE UTILIZATORI(
  id_utilizator CHAR(5) PRIMARY KEY,
  nume VARCHAR2(20) NOT NULL,
  prenume VARCHAR2(20) NOT NULL,
  numar_telefon VARCHAR(20) NOT NULL,
  mail VARCHAR2(30) NOT NULL
);

CREATE TABLE PROPRIETATI(
  id_proprietate CHAR(5) PRIMARY KEY,
  id_utilizator CHAR(5) REFERENCES UTILIZATORI(id_utilizator),
  tip_proprietare VARCHAR2(20) NOT NULL,
  zona VARCHAR2(20),
  suprafata VARCHAR(10),
  numar_camere NUMBER(2),
  an_constructie NUMBER(4)
);


CREATE TABLE AGENTIE(
  id_agentie CHAR(5) PRIMARY KEY,
  nume VARCHAR2(30),
  telefon VARCHAR2(15)
);

CREATE TABLE ANUNTURI(
  id_anunt CHAR(5) PRIMARY KEY,
  data_publicare DATE,
  id_proprietate REFERENCES PROPRIETATI(id_proprietate),
  id_agentie CHAR(5) REFERENCES AGENTIE(id_agentie)
);
  
CREATE TABLE PROPRIETATI_VANDUTE(
  id_anunt CHAR(5) REFERENCES ANUNTURI(id_anunt),
  id_proprietate CHAR(5) REFERENCES PROPRIETATI(id_proprietate),
  PRIMARY KEY(id_anunt,id_proprietate)
);
  
  
CREATE TABLE ADRESA_PRORIETATE(
  id CHAR(5) PRIMARY KEY,
  id_proprietate CHAR(5) REFERENCES PROPRIETATI(id_proprietate),
  strada VARCHAR2(20),
  numar VARCHAR2(10),
  bloc VARCHAR2(10),
  scara VARCHAR2(10),
  etaj INTEGER,
  oras VARCHAR2(20),
  judet VARCHAR2(20)
);    
  
CREATE TABLE IMAGINI(
  id_imagine CHAR(5) PRIMARY KEY,
  id_proprietate CHAR(5) REFERENCES PROPRIETATI(id_proprietate),
  imagine VARCHAR2(100)
);
  

CREATE TABLE FACILITATI (
  id_proprietate CHAR(5) REFERENCES PROPRIETATI(id_proprietate),
  termopane INTEGER,
  centrala_termica INTEGER,
  tip VARCHAR2(20)
);

CREATE TABLE CONTACT(
  id_contact INTEGER PRIMARY KEY,
  id_agentie CHAR(5) REFERENCES  AGENTIE(id_agentie),
  nume VARCHAR2(20),
  prenume VARCHAR2(20),
  telefon VARCHAR2(15),
  email VARCHAR2(30)
);
  
CREATE TABLE ADRESA_AGENTIE(
  id_AGENTIE CHAR(5) REFERENCES AGENTIE(id_AGENTIE),
  strada VARCHAR2(20),
  numar VARCHAR2(10),
  bloc VARCHAR2(10),
  scara VARCHAR2(10),
  etaj INTEGER,
  oras VARCHAR2(20),
  judet VARCHAR2(20)
);

  

  
  
  
  
  
  
  
use esihdb;
# Creating core tables
CREATE TABLE if not exists Course
(
    PK_idCourse int(10) NOT NULL AUTO_INCREMENT,
    title       int(10),
    description varchar(255),
    credits     int(10),
    PRIMARY KEY (PK_idCourse)
);

CREATE TABLE if not exists Materials
(
    PK_idMaterials int(10) NOT NULL AUTO_INCREMENT,
    records        float,
    dateEnroll     date,
    FK_idStudent   int(10) NOT NULL,
    FK_idCourse    int(10) NOT NULL,
    PRIMARY KEY (PK_idMaterials)
);

CREATE TABLE if not exists Materials_Session
(
    FK_idMaterials int(10) NOT NULL,
    FK_idSession   int(10) NOT NULL,
    PRIMARY KEY (FK_idMaterials, FK_idSession)
);

CREATE TABLE if not exists Professor
(
    PK_idProfessor int(10) NOT NULL AUTO_INCREMENT,
    lname          varchar(255),
    fname          varchar(255),
    gender         varchar(255),
    yob            int(10),
    dateEnroll     date,
    PRIMARY KEY (PK_idProfessor)
);

CREATE TABLE if not exists Professor_Course
(
    FK_idProfessor int(10) NOT NULL,
    FK_idCourse    int(10) NOT NULL,
    FK_idSession   int(10) NOT NULL,
    teachingDate   date    NOT NULL,
    PRIMARY KEY (FK_idProfessor, FK_idCourse)
);

CREATE TABLE if not exists Session
(
    session_name int(10),
    PK_idSession int(10) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (PK_idSession)
);

CREATE TABLE if not exists Student
(
    PK_idStudent int(10) NOT NULL AUTO_INCREMENT,
    fname        varchar(255),
    lname        varchar(255),
    gender       varchar(255),
    yob          int(10),
    PRIMARY KEY (PK_idStudent)
);

# Adding constrainst to existed tables
ALTER TABLE Professor_Course
    ADD CONSTRAINT FKProfessor_681446 FOREIGN KEY (FK_idSession)
        REFERENCES Session (PK_idSession);

ALTER TABLE Professor_Course
    ADD CONSTRAINT FKProfessor_727593 FOREIGN KEY (FK_idProfessor)
        REFERENCES Professor (PK_idProfessor);

ALTER TABLE Professor_Course
    ADD CONSTRAINT FKProfessor_242669 FOREIGN KEY (FK_idCourse)
        REFERENCES Course (PK_idCourse);

ALTER TABLE Materials_Session
    ADD CONSTRAINT FKMaterials_853052 FOREIGN KEY (FK_idMaterials)
        REFERENCES Materials (PK_idMaterials);

ALTER TABLE Materials_Session
    ADD CONSTRAINT FKMaterials_238276 FOREIGN KEY (FK_idSession)
        REFERENCES Session (PK_idSession);

ALTER TABLE Materials
    ADD CONSTRAINT FKMaterials30392 FOREIGN KEY (FK_idCourse)
        REFERENCES Course (PK_idCourse);

ALTER TABLE Materials
    ADD CONSTRAINT FKMaterials971180 FOREIGN KEY (FK_idStudent)
        REFERENCES Student (PK_idStudent);

# Creating Jointures tables
CREATE TABLE if not exists Course_Level
(
    FK_idCourse int(10) NOT NULL,
    FK_idLevel  int(11) NOT NULL,
    PRIMARY KEY (FK_idCourse, FK_idLevel)
);

CREATE TABLE if not exists Degree
(
    PK_idDegree int(11) NOT NULL AUTO_INCREMENT,
    degree_name varchar(255),
    length      int(11),
    PRIMARY KEY (PK_idDegree)
);

CREATE TABLE if not exists Degree_Course
(
    FK_idDegree int(11) NOT NULL,
    FK_idCourse int(10) NOT NULL,
    PRIMARY KEY (FK_idDegree, FK_idCourse)
);

CREATE TABLE if not exists Level
(
    PK_idLevel  int(11) NOT NULL AUTO_INCREMENT,
    objectifs   varchar(255),
    level_name  varchar(255),
    description varchar(255),
    PRIMARY KEY (PK_idLevel)
);

# Adding Constraints to existed tables
ALTER TABLE Course_Level
    ADD CONSTRAINT FKCourse_Lev227122 FOREIGN KEY (FK_idLevel)
        REFERENCES Level (PK_idLevel);
ALTER TABLE Degree_Course
    ADD CONSTRAINT FKDegree_Cou289413 FOREIGN KEY (FK_idDegree)
        REFERENCES Degree (PK_idDegree);

# Create addStudent Procedure dedicated only to add new students
delimiter //
create procedure addStudent(
    in fname_para varchar(255),
    in lname_para varchar(255),
    in gender_para varchar(255),
    in yob_para int
)
begin
    insert into Student(fname, lname, gender, yob)
    VALUES (fname_para,lname_para,gender_para,yob_para);
end //
delimiter ;
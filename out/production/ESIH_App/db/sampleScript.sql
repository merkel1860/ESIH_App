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

create table if not exists Degree_Level
(
    FK_idDegree int not null,
    FK_idLevel  int not null,
    constraint FKDegree_Level1539
        foreign key (FK_idDegree)
            references esihdb.Degree (PK_idDegree),
    constraint FKDegree_Level1540
        foreign key (FK_idLevel)
            references esihdb.Level (PK_idLevel),
    constraint PK_Degree_Level1538
        primary key (FK_idLevel, FK_idDegree)
);
# Adding Constraints to existed tables
ALTER TABLE Course_Level
    ADD CONSTRAINT FKCourse_Lev227122 FOREIGN KEY (FK_idLevel)
        REFERENCES Level (PK_idLevel);
ALTER TABLE Degree_Course
    ADD CONSTRAINT FKDegree_Cou289413 FOREIGN KEY (FK_idDegree)
        REFERENCES Degree (PK_idDegree);

# Create addStudent Procedure dedicated to adding new students
delimiter //
create procedure addStudent(in fname_para varchar(255),
                            in lname_para varchar(255),
                            in gender_para varchar(255),
                            in yob_para int)
begin
    insert into Student(fname, lname, gender, yob)
    VALUES (fname_para, lname_para, gender_para, yob_para);
end //
delimiter ;

# Create isIDValid for checking student id validity
delimiter //
create procedure isIDValid(in id int)
begin
    declare status boolean default false;
    if (select esihdb.Student.PK_idStudent
        from Student
        where PK_idStudent = id) != 0 then
        set status = true;
    end if;
    select status;
end //

# Create procedure to check if a degree proram exists
delimiter //
create procedure isDegreeExisted(in name varchar(250), in id int)
begin
    declare status int default 0;
    select PK_idDegree
    into status
    from Degree
    where degree_name = name
       or PK_idDegree = id;
    if status != 0 then
        set status = 1;
        select status;
    else
        select status;
    end if;
end //

# Create procedure to get Degree program by name
create procedure getDegreeByName(in name varchar(250))
begin
    select *
    from Degree
    where degree_name = name;
end //

# Function to test the existence of a degree
SET GLOBAL log_bin_trust_function_creators = 1;
delimiter //
create function isDegreeExisted(name varchar(250), id int)
    returns int

begin
    declare status int default 0;
    select PK_idDegree
    into status
    from Degree
    where degree_name = name
       or PK_idDegree = id;
    if status != 0 then
        set status = 1;
    end if;
    return status;
end //

# Create procedure to insert new tuple to Degree
delimiter //
create procedure insertDegree(in name varchar(250), in years int)
begin
    declare status int default 1;
    if (isDegreeExisted(name, 0) = 0) then
        insert into Degree(degree_name, length)
            value (name, years);
        set status = 0;
    else
        select status;
    end if;
end //

# Create procedure get all degree program from db
delimiter //
create procedure getDegreeListFromDB()
begin
    select PK_idDegree, degree_name, length from Degree;
end //

# Create Function to test validation of Leve
delimiter //
create function isLevelExisted(name varchar(250), id int)
    returns int

begin
    declare status int default 0;
    select PK_idLevel into status from Level
    where level_name = name
       or PK_idLevel = id;
    if status != 0 then
        set status = 1;
    end if ;
    return status;
end //

# Creating a procedure that insert new Level  based on degree title (No Script)
delimiter //
create
    definer = nebel@localhost procedure insertLevel(IN leveName varchar(250),
                                                    IN obj varchar(250), in descrip varchar(250), in degreeName varchar(250))
begin
    declare status int default 1;
    declare fk_level, fk_degree int default 0;
    if(isLevelExisted(leveName,0) = 0) then
        insert into Level(level_name, description, objectifs)
            value (leveName, descrip, obj);

        set fk_level = getLevelIDByTitle(leveName);
        set fk_degree = getDegreeIDByTitle(degreeName);

        insert into Degree_Level(FK_idDegree, FK_idLevel)
            value(fk_degree, fk_level);

        set status = 0;
    else select status;
    end if ;
end;

# Creating a function that returns Level id based on level title (No Script)
delimiter //
create function getLevelIDByTitle(name varchar(250))
    returns int
begin
    declare id int default 0;
    select PK_idLevel into id from Level l
    where l.level_name = name;
    return id;
end //


# Creating a function that returns Degree id based on level title (No Script)
delimiter //
create function getDegreeIDByTitle(name varchar(250))
    returns int
begin
    declare id int default 0;
    select PK_idDegree into id from Degree d
    where d.degree_name = name;
    return id;
end //


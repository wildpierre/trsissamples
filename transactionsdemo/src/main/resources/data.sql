insert into "school" ("school_number", "school_name") values (1, 'Факультет 1');
insert into "school" ("school_number", "school_name") values (2, 'Факультет 2');
insert into "school" ("school_number", "school_name") values (3, 'Факультет 3');
insert into "school" ("school_number", "school_name") values (4, 'Факультет 4');
insert into "school" ("school_number", "school_name") values (5, 'Факультет 5');


insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('111','Кафедра трех единиц', 1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('112','Кафедра двух единиц и двойки',1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('113','Кафедра двух единиц и тройки',1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('121','Кафедра единицы, двойки и еще единицы',1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('122','Кафедра единицы и двух двоек',1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('123','Кафедра единицы, двойки и тройки',1);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('411','Кафедра четверки и двух единиц',4);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('412','Кафедра четверки, единицы и двойки',4);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('413','Кафедра четверки, единицы и тройки',4);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('421','Кафедра четверки, двойки и единицы',4);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('422','Кафедра четверки и двух двоек',4);
insert into "dept" ("dept_number", "dept_name", "dept_school_id") values ('423','Кафедра четверки, двойки и тройки',4);


insert into "user" ("login", "hash") values ('guest','$2a$10$6mf3CesQx9eRGB4B3sjr8e1eSr5cYO/zt87bwYVdA4O8rmjDMDdHO');

insert ignore into sec_group(groupname, groupdesc) values("MANAGERS", "This group contains all managers in the firm.");
insert ignore into sec_group(groupname, groupdesc) values("LABORERS", "This group contains all laborers in the firm.");
insert ignore into sec_group(groupname, groupdesc) values("CLERKS", "This group contains all clerks in the firm.");

insert ignore into sec_user(username, password) values("musah1", SHA2("passmusah", 256));
insert ignore into sec_user(username, password) values("kwame1", SHA2("passkwame", 256));
insert ignore into sec_user(username, password) values("jterry1", SHA2("passjohn", 256));
insert ignore into sec_user(username, password) values("amy1", SHA2("passamy", 256));

insert ignore into sec_user_groups(username, groupname) values("musah1", "MANAGERS");
insert ignore into sec_user_groups(username, groupname) values("musah1", "LABORERS");
insert ignore into sec_user_groups(username, groupname) values("musah1", "CLERKS");
insert ignore into sec_user_groups(username, groupname) values("kwame1", "LABORERS");
insert ignore into sec_user_groups(username, groupname) values("jterry1", "CLERKS");
insert ignore into sec_user_groups(username, groupname) values("amy1", "MANAGERS");
insert ignore into sec_user_groups(username, groupname) values("amy1", "LABORERS");
insert ignore into sec_user_groups(username, groupname) values("amy1", "CLERKS");

insert into department(departmentid, departmentdescription, departmentname) values("SPRYNG", "In charge of car spraying and painting services.", "Spraying");
insert into department(departmentid, departmentdescription, departmentname) values("BDY", "In charge of body repairs.", "Body Repairs");
insert into department(departmentid, departmentdescription, departmentname) values("ENGN", "In charge of engine repairs.", "Engine Repairs");
insert into department(departmentid, departmentdescription, departmentname) values("MGMT", "Individuals who play managerial roles in the company", "Management");

insert into employee(id, firstname, lastname, role, hiredate, salary, department_id, username) values (1, "Musah", "Codjoe", "CEO", "1984-08-02", 200000, "MGMT", "musah1");
insert into employee(id, firstname, lastname, role, hiredate, salary, department_id, username) values (2, "John", "Terry", "Secretary", "1992-05-22", 100000, "MGMT", "jterry1");
insert into employee(id, firstname, lastname, role, hiredate, salary, department_id, username) values (3, "Amy", "Wilson", "Managing Director", "1990-04-04", 150000, "MGMT", "amy1");
insert into employee(id, firstname, lastname, role, hiredate, salary, department_id, username) values (4, "Kwame", "Ohene", "Mechanic", "2002-09-04", 70000, "ENGN", "kwame1");

insert into parts(serialnumber, brandname, category, chassisnumber, enginenumber, manufacturedate, model, odometerreading, purchasedate, registrationnumber, type) values(256712, "Cummins", "Automotive Accessories", 567345, 8354894, "1995-06-05", "M185", 120000, "1998-11-22", 453789, "Radio");
insert into parts(serialnumber, brandname, category, chassisnumber, enginenumber, manufacturedate, model, odometerreading, purchasedate, registrationnumber, type) values(2367472, "Caterpillar", "Automotive Accessories", 5566356, 895906, "1997-06-09", "C173895", 180000, "2001-10-20", 527076, "CD Player");
insert into parts(serialnumber, brandname, category, chassisnumber, enginenumber, manufacturedate, model, odometerreading, purchasedate, registrationnumber, type) values(6379274, "Ford", "Automotive Accessories", 89366483, 9725547, "2001-08-02", "K7839956", 20000, "2009-11-22", 893690, "Bluetooth");
insert into parts(serialnumber, brandname, category, chassisnumber, enginenumber, manufacturedate, model, odometerreading, purchasedate, registrationnumber, type) values(16894536728, "Ford", "Automotive Accessories", 15684, 243836, "1993-11-11", "C451027", 180000, "2001-11-20", 893657, "Rear View Mirror");

insert into tool(serialnumber, brandname, category, model, type) values(67208, "Stihl", "Cutting", "S15", "Chainsaw");
insert into tool(serialnumber, brandname, category, model, type) values(145273, "Estwing", "Hammer", "E4562", "Ballpein");
insert into tool(serialnumber, brandname, category, model, type) values(4237, "Hakko", "Cutting", "CHP170", "Plier");
insert into tool(serialnumber, brandname, category, model, type) values(12345, "Narex", "Cutting", "N6783", "Chisel");

insert into repairs(id, carplatenumber, repairdescription, dropoffdate, pickupdate, repaircompleted, part_id) values(1, "AG23343", "Broken radio", "2017-05-22", "2017-05-29", 1, 256712);
insert into repairs(id, carplatenumber, repairdescription, dropoffdate, pickupdate, repaircompleted, part_id) values(2, "AH56347", "Broken mirror", "2018-04-04", "2018-04-05", 1, 16894536728);



delete from group_subject where id >0;
delete from teacher_subject where id >0;
delete from student where id > 0;
delete from groups where id > 0;
delete from subject where id > 0;
delete from teacher where id > 0;

insert into groups(id, name ,specification) values
    (1,'Computer Science LEVEL first','Note: Individual program requirements may exceed the following minima.

Minimum 128 total semester hours required

Minimum 2.000 GPA required

Students must earn a minimum of 64  semester hours in order to receive a bachelor’s degree.');

insert into groups(id, name ,specification) values (2,'Computer Science LEVEL second','Note: Individual program requirements may exceed the following minima.

Minimum 120 total semester hours required

Minimum 2.000 GPA required

Students must earn a minimum of 64  semester hours in order to receive a bachelor’s degree.')
;


insert into subject(id, name ,description) values
                                               (1,'Introduction to Computational Science and Engineering','Follows the same pedagogy as 6.0002 (Introduction to Computational Thinking and Data Science), including Python, but is set in the context of computational science and engineering and uses applications from across science and engineering including dynamics, mechanics, robotics, heat transfer, climate science, chemistry, biology, aerospace and others.

This course provides an introduction to computational algorithms for understanding of scientific phenomena and designing of engineering systems. Topics include: computational algorithms to simulate time-dependent phenomena; optimize and control applications from science and engineering; and quantify uncertainty in problems involving randomness, including an introduction to probability and statistics.

Credit cannot also be received for 6.0002. Can be combined with 6.0001 (Introduction to Computer Science Programming in Python) for REST credit.'),
                                               (2,'Linear Algebra and Optimization','Covers same fundamental concepts as 18.06 with a view toward modeling, computation, and applications. Integrates geometric, algebraic, and computational viewpoints.

Unified introduction to linear algebra and optimization, their interconnections, and applications throughout science and engineering. Topics include: vectors, matrices, eigenvalues, singular values, least-squares, convex optimization, linear/quadratic programming, gradient descent, Newton’s method. Viewpoint will emphasize conceptual, geometric, and computational aspects. Applications from many domains, including networks, signal processing, and machine learning.

Approved substitution for 18.06 for EECS and Math students.'),
                                               (3,'Modeling with Machine Learning','Teaches students from a range of majors to translate a problem into a machine learning (ML) formulation and find appropriate tools for solving it. Students enroll in two 6-unit modules, run in tandem over the course of a full term—the common core, which covers ML fundamentals, and one of four discipline-specific modules that build on the core material.'),
                                               (4,'Core Module','Focuses on modeling with machine learning methods with an eye towards applications in engineering and sciences. Students will be introduced to modern machine learning methods, from supervised to unsupervised models, with an emphasis on newer neural approaches. Develops understanding of how and why the methods work from the point of view of modeling, and when they are applicable. Using concrete examples, covers formulation of machine learning tasks, adapting and extending methods to given problems, and how the methods can and should be evaluated. Students taking graduate version complete additional assignments. Enrollment limited.'),
                                               (5,'Programming Skills and Computational Thinking in-Context','Teaches experimental section of 6.0001 (Introduction to Computer Science and Programming in Python) in tandem with the Physics I GIR. In the fall term, one section of ES.801 (ESG version of 8.01, 12 units) is paired with one section of 6.0001 (6 units). Students in these sections will enroll in both subjects and receive separate course grades for each subject. This pilot will generate a library of problems that connect and combine basic computation in Python with basic concepts in the physics GIRs.'),
                                               (6,'Nanoelectronics and Computing Systems','Studies interaction between materials, semiconductor physics, electronic devices, and computing systems. Develops intuition of how transistors operate. Topics range from introductory semiconductor physics to modern state-of-the-art nano-scale devices. Considers how innovations in devices have driven historical progress in computing, and explores ideas for further improvements in devices and computing. Students apply material to understand how building improved computing systems requires knowledge of devices, and how making the correct device requires knowledge of computing systems. Includes a design project for practical application of concepts, and labs for experience building silicon transistors and devices.'),
                                               (7,'Fundamentals of Programming','Introduces fundamental concepts of programming. Designed to develop skills in applying basic methods from programming languages to abstract problems. Topics include programming and Python basics, computational concepts, software engineering, algorithmic techniques, data types, and recursion.  Lab component consists of software design, construction, and implementation of design. Enrollment may be limited.');

insert into student(id, first_name ,second_name,last_name,gender,date_birth,group_id) values
                                                                                          (1,'Bradley','Alexander','Abbe','MALE','02-03-99',1),
                                                                                          (2,'Martin','Quinn','Abbett','MALE','01-04-00',2),
                                                                                          (3,'Max','Mendoza','Alan','MALE','02-05-01',1),
                                                                                          (4,'Stephen','Allen','Alden','MALE','12-05-00',2),
                                                                                          (5,'Dominic','Clarke','Beaner','MALE','03-06-02',1),
                                                                                          (6,'Theobold','Elliott','Bear','MALE','04-07-98',2),
                                                                                          (7,'Davy','Mills','Bearfield','MALE','05-08-00',1),
                                                                                          (8,'Dominic','Bowman','Beatley','MALE','06-09-01',2),
                                                                                          (9,'Zachary','Mason','Beaver','MALE','07-10-02',1),
                                                                                          (10,'Keith','Oliver','Chace','MALE','08-11-98',2),
                                                                                          (11,'Manfred','Webb','Chadburn','MALE','09-12-99',1),
                                                                                          (12,'Milton','Daniels','Chaddick','MALE','10-01-00',2),
                                                                                          (13,'Robin','Greenwood','Chadwell','MALE','11-02-01',1),
                                                                                          (14,'Justin','Rodriquez','Chaffey','MALE','12-03-02',2),
                                                                                          (15,'Ford','Simonds','Chalkley','MALE','13-04-98',1),
                                                                                          (16,'Ferris','Eland','Chamberlain','MALE','14-05-99',2),
                                                                                          (17,'Rocky','Black','Dabler','MALE','15-06-00',1),
                                                                                          (18,'Stanley','Shillingford','Dabrowski','MALE','16-07-01',2),
                                                                                          (19,'Lance','Ryan','Dach','MALE','17-08-02',1),
                                                                                          (20,'Joe','Currey','Dack','MALE','18-09-98',2),
                                                                                          (21,'Lawrence','Bates','Daehn','MALE','19-10-99',1),
                                                                                          (22,'Ryan','Hammond','Dagel','MALE','20-11-00',2),
                                                                                          (23,'Vernon','Terry','Daggett','MALE','21-12-01',1),
                                                                                          (24,'Alvin','Heptinstall','Dague','MALE','22-01-02',2),
                                                                                          (25,'Harlan','Howard','Eacker','MALE','23-02-98',1),
                                                                                          (26,'Don','Vasquez','Earthman','MALE','24-03-99',2),
                                                                                          (27,'Winthrop','Willis','Eason','MALE','25-04-00',1),
                                                                                          (28,'Leroy','Moreno','Eastland','MALE','26-05-01',2),
                                                                                          (29,'Kennard','Houle','Ehrhard','MALE','27-06-02',1),
                                                                                          (30,'Austin','Boyd','Faaborg','MALE','28-07-98',2),
                                                                                          (31,'Baxter','Barlow','Fabbro','MALE','29-08-99',1),
                                                                                          (32,'Marc','Dickinson','Fagerstrom','MALE','30-09-00',2),
                                                                                          (33,'Calvert','Gutierrez','Fahlgren','MALE','01-10-01',1),
                                                                                          (34,'Shaun','Fisher','Fenoglio','MALE','02-11-02',2),
                                                                                          (35,'Godfrey ','Fox','Gaa','MALE','03-12-03',1),
                                                                                          (36,'Esmond ','Nash','Gadsden','MALE','04-01-98',2),
                                                                                          (37,'Angela','Boyd','Gage','FEMALE','05-02-99',1),
                                                                                          (38,'Polly','Schneider','Gauker','FEMALE','06-03-00',2),
                                                                                          (39,'Linette','Butler','Gearheart','FEMALE','07-04-01',1),
                                                                                          (40,'Elizabeth','Long','Baker','FEMALE','08-05-02',2),
                                                                                          (41,'Eda','Fuller','Owen','FEMALE','09-06-98',1),
                                                                                          (42,'Sacha','Knight','Wilson','FEMALE','10-07-99',2),
                                                                                          (43,'Blanche',' Twitty','Burton','FEMALE','11-08-00',1),
                                                                                          (44,'Elena','Burgess','Harris','FEMALE','12-09-01',2),
                                                                                          (45,'Rosalind','Lipsey','Stevens','FEMALE','13-10-02',1),
                                                                                          (46,'Harriet','Cook','Robinson','FEMALE','14-11-98',2),
                                                                                          (47,'Ivory','Knight','Lewis','FEMALE','15-12-00',1),
                                                                                          (48,'Molly','Francis','Walker','FEMALE','16-01-01',2),
                                                                                          (49,'Faye','Macy','Payne','FEMALE','17-02-02',1),
                                                                                          (50,'Bridget','Hayes','Baker','FEMALE','18-03-98',2),
                                                                                          (51,'Joan','Silva','Owen','FEMALE','19-04-99',1),
                                                                                          (52,'Guinevere','Wood','Holmes','FEMALE','20-05-00',2),
                                                                                          (53,'Cassandra','Chambers','Chapman','FEMALE','21-06-01',1),
                                                                                          (54,'Echo','Ruiz','Webb','FEMALE','22-07-02',2),
                                                                                          (55,'Madison','Harding','Allen','FEMALE','23-08-98',1),
                                                                                          (56,'Lizzie','Alexander','Jones','FEMALE','24-09-99',2),
                                                                                          (57,'Daisy','Cobb','Davidson','FEMALE','25-10-00',1),
                                                                                          (58,'Tilda',' Vargas','Foster','FEMALE','26-11-01',2),
                                                                                          (59,'London','Cook','Matthews','FEMALE','27-12-02',1),
                                                                                          (60,'Sadie','Peay','White','FEMALE','28-01-00',2),
                                                                                          (61,'Michelle','Joseph','Griffiths','FEMALE','01-02-01',1),
                                                                                          (62,'Clarissa','Fraley','Knight','FEMALE','02-03-02',2),
                                                                                          (63,'Camille','White','Corbyn','FEMALE','03-04-98',1),
                                                                                          (64,'Monica','Duncan','Young','FEMALE','04-05-99',2),
                                                                                          (65,'Roberta','Rowe','Evans','FEMALE','05-06-00',1),
                                                                                          (66,'Kayla','Middleton','Smith','FEMALE','06-07-01',2),
                                                                                          (67,'Callie','Banks','Wright','FEMALE','07-08-02',1),
                                                                                          (68,'Roberta','Franklin','Jenkins','FEMALE','08-09-98',2),
                                                                                          (69,'Pearl','Blair','Hughes','FEMALE','09-10-99',1),
                                                                                          (70,'Louise','Harding','Hall','FEMALE','10-11-00',2),
                                                                                          (71,'Adele','Vega','Armstrong','FEMALE','11-12-01',1),
                                                                                          (72,'Prudence','Fisher','Cox','FEMALE','12-01-02',2)
;
insert into group_subject(id, group_id, subject_id ) values
                                                         (1,1,1),
                                                         (2,1,3),
                                                         (3,1,5),
                                                         (4,1,7),
                                                         (5,2,2),
                                                         (6,2,4),
                                                         (7,2,6);



insert into teacher(id, first_name ,second_name,last_name,gender,date_birth,category) values
                                                                                          (1,'Oliver','Alexander','Williams','MALE','02-03-99','first'),
                                                                                          (2,'Harry','Quinn','Peters','MALE','01-04-00','Second'),
                                                                                          (3,'Jack','Mendoza','Gibson','MALE','02-05-01','Third'),
                                                                                          (4,'Jacob','Allen','Martin','MALE','12-05-00','first'),
                                                                                          (5,'Charley','Clarke','Jordan','MALE','03-06-02','Second'),
                                                                                          (6,'Thomas','Elliott','Jackson','MALE','04-07-98','Third'),
                                                                                          (7,'George','Mills','Grant','MALE','05-08-00','first'),
                                                                                          (8,'Oscar','Bowman','Beatley','MALE','06-09-01','Second'),
                                                                                          (9,'Zachary','Mason','Davis','MALE','07-10-02','Third')

;



insert into teacher_subject(id, teacher_id, subject_id ) values
                                                             (1,1,1),
                                                             (2,1,3),
                                                             (3,1,5),
                                                             (4,1,7),
                                                             (5,2,2),
                                                             (6,2,4),
                                                             (7,2,6),
                                                             (8,3,1),
                                                             (9,3,3),
                                                             (10,3,5),
                                                             (11,4,7),
                                                             (12,4,2),
                                                             (13,5,4),
                                                             (14,6,6),
                                                             (15,6,3),
                                                             (16,6,5),
                                                             (17,6,7),
                                                             (18,7,2),
                                                             (19,7,4),
                                                             (20,8,6),
                                                             (21,8,3),
                                                             (22,8,5),
                                                             (23,9,7),
                                                             (24,9,2),
                                                             (25,9,4),
                                                             (26,9,6);



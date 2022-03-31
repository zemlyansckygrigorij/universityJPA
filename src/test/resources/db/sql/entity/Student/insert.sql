
delete from student where id_group = 1;
delete from groups where id = 1;

insert into groups(id, name, specification) values(1 , 'GroupTest', 'DescriptionTest');
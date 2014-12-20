DROP DATABASE IF EXISTS ddb;
CREATE DATABASE ddb DEFAULT CHARACTER SET utf8;

##new user
grant select,insert,update,delete on ddb.* to ganjp@localhost identified by "1";
grant all privileges on ddb.* to ganjp@localhost identified by '1';

##modify user
update user set password=password('ganjp') where user='root';


##delete user
DELETE FROM user WHERE User="exampleUser";

COMMIT;
flush privileges;
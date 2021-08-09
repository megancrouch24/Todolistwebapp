DROP TABLE if exists items cascade;
CREATE TABLE items
 (
 id LONG PRIMARY KEY AUTO_INCREMENT,
 `description` VARCHAR(50), 
 priority VARCHAR(50)
 );
 
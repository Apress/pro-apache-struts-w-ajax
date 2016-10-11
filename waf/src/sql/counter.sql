DROP TABLE if exists counter;
CREATE TABLE counter(
  seq_num      BIGINT      NOT NULL,
  counter_name VARCHAR(25) NOT NULL,
  PRIMARY KEY(counter_name)
) TYPE=BDB;

INSERT INTO counter VALUES(8,"member_id_pk");
INSERT INTO counter VALUES(8,"story_id_pk");
INSERT INTO counter VALUES(8,"comment_id_pk");
INSERT INTO counter VALUES(5,"member_rv");
INSERT INTO counter VALUES(5,"story_rv");
INSERT INTO counter VALUES(5,"comment_rv");

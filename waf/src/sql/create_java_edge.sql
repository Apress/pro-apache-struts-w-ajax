--
--    The following script will create the Java Edge Database.
--    
DROP DATABASE if exists waf;
CREATE DATABASE waf;
USE waf;

DROP TABLE if exists member;
CREATE TABLE member (
   member_id     BIGINT UNSIGNED  PRIMARY KEY,
   first_name    VARCHAR(50)   ,
   last_name     VARCHAR(50)   ,
   userid        VARCHAR(8)    ,
   password      VARCHAR(8)    ,
   email         VARCHAR(100)  ,
   row_version   BIGINT UNSIGNED
);

DROP TABLE if exists story;
CREATE TABLE story (
  story_id      BIGINT UNSIGNED PRIMARY KEY    ,
  member_id     BIGINT UNSIGNED                ,
  story_title   VARCHAR(100)         ,
  story_intro   VARCHAR(255)        ,
  story_body    BLOB        ,
  submission_date DATE              ,
  row_version     BIGINT UNSIGNED
);

DROP TABLE if exists story_comment;
CREATE TABLE story_comment (
   comment_id   BIGINT UNSIGNED PRIMARY KEY     ,
   story_id     BIGINT UNSIGNED                 ,
   member_id    BIGINT UNSIGNED                 ,
   comment_body BLOB         ,
   submission_date DATE              ,
   row_version  BIGINT UNSIGNED
);


# -----------------------------------------------------------------------
# OJB_HL_SEQ
# -----------------------------------------------------------------------
drop table if exists OJB_HL_SEQ;

CREATE TABLE OJB_HL_SEQ
(
    TABLENAME VARCHAR (175) NOT NULL,
    FIELDNAME VARCHAR (70) NOT NULL,
    MAX_KEY INTEGER,
    GRAB_SIZE INTEGER,
    PRIMARY KEY(TABLENAME,FIELDNAME)
);

# -----------------------------------------------------------------------
# OJB_LOCKENTRY
# -----------------------------------------------------------------------
drop table if exists OJB_LOCKENTRY;

CREATE TABLE OJB_LOCKENTRY
(
    OID_ VARCHAR (250) NOT NULL,
    TX_ID VARCHAR (50) NOT NULL,
    TIMESTAMP_ DECIMAL,
    ISOLATIONLEVEL INTEGER,
    LOCKTYPE INTEGER,
    PRIMARY KEY(OID_,TX_ID)
);

# -----------------------------------------------------------------------
# OJB_NRM
# -----------------------------------------------------------------------
drop table if exists OJB_NRM;

CREATE TABLE OJB_NRM
(
    NAME VARCHAR (250) NOT NULL,
    OID_ LONGBLOB,
    PRIMARY KEY(NAME)
);

# -----------------------------------------------------------------------
# OJB_DLIST
# -----------------------------------------------------------------------
drop table if exists OJB_DLIST;

CREATE TABLE OJB_DLIST
(
    ID INTEGER NOT NULL,
    SIZE_ INTEGER,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# OJB_DLIST_ENTRIES
# -----------------------------------------------------------------------
drop table if exists OJB_DLIST_ENTRIES;

CREATE TABLE OJB_DLIST_ENTRIES
(
    ID INTEGER NOT NULL,
    DLIST_ID INTEGER NOT NULL,
    POSITION_ INTEGER,
    OID_ LONGBLOB,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# OJB_DSET
# -----------------------------------------------------------------------
drop table if exists OJB_DSET;

CREATE TABLE OJB_DSET
(
    ID INTEGER NOT NULL,
    SIZE_ INTEGER,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# OJB_DSET_ENTRIES
# -----------------------------------------------------------------------
drop table if exists OJB_DSET_ENTRIES;

CREATE TABLE OJB_DSET_ENTRIES
(
    ID INTEGER NOT NULL,
    DLIST_ID INTEGER NOT NULL,
    POSITION_ INTEGER,
    OID_ LONGBLOB,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# OJB_DMAP
# -----------------------------------------------------------------------
drop table if exists OJB_DMAP;

CREATE TABLE OJB_DMAP
(
    ID INTEGER NOT NULL,
    SIZE_ INTEGER,
    PRIMARY KEY(ID)
);

# -----------------------------------------------------------------------
# OJB_DMAP_ENTRIES
# -----------------------------------------------------------------------
drop table if exists OJB_DMAP_ENTRIES;

CREATE TABLE OJB_DMAP_ENTRIES
(
    ID INTEGER NOT NULL,
    DMAP_ID INTEGER NOT NULL,
    KEY_OID LONGBLOB,
    VALUE_OID LONGBLOB,
    PRIMARY KEY(ID)
);
  
 

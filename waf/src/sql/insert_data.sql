--
--    Inserts all of the base data for the Java Edge project

-- Inserts some dummy data into the member table
DELETE FROM member;
INSERT INTO member VALUES(1,'Anonymous','','anon','password','None',1);
INSERT INTO member VALUES(2,'John','Wei','jwei','asqrocks','jwei@javaedge.com',1);
INSERT INTO member VALUES(3,'Kamlesh','Patel','kpatel','snowflak','kpatel@javaedge.com',1);
INSERT INTO member VALUES(4,'Dale','Busse','dbusse','jgordon1','dbusse@javaedge.com',1);
INSERT INTO member VALUES(5,'Jean-Roch','Grenetier','jroch','java123','jroch@javaedge.com',1);

--Inserts some dummy data into the story table
INSERT INTO  story (story_id, member_id, story_title, story_intro, story_body, submission_date, row_version) VALUES(1,1,'Knoppix Linux Rocks',
                              'I ran across a great linux distribution.  Its called Knoppix.  Completely boots off a CD. Check it out at: http://www.knoppix.org/',
                                 'Knoppix is a bootable CD with a collection of GNU/Linux software',
                              CURDATE(),1);
INSERT INTO  story VALUES(2,1,'New Book Released:  Open Source for Beginners',
                              'New book available on Open Source Development.  A must have for beginners.',
                              'Open Source for Beginners introduces the reader to the power and flexibility of OS tools.' ,
                              CURDATE(),1);
INSERT INTO  story VALUES(3,3,'J2EE vrs. .NET',
                              'Found an interesting article comparing J2EE vrs. Microsofts .NET',
                              'The Manhattan Project has published a new write paper examining .NET.', 
                              CURDATE(),1);

INSERT INTO story_comment VALUES(1,1,3,'Great linux distribution.  Popped it into my laptop and instantly recognized my wireless network.', CURDATE(),1);
INSERT INTO story_comment VALUES(2,1,4,'I had problems getting Knoppix to run properly on my Compaq Presario - 1200 Laptop.  Anyone else have this problem?', CURDATE(),1);



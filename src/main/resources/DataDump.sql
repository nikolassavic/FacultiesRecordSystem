
--
-- Dumping data for table `faculties`
--

INSERT INTO `faculties` VALUES (27,'fname2','fAddress2','email2@email.com','fPhone2','2019-07-20 10:13:56','2019-07-20 12:34:49'),(28,'fname1','fAddress1','email1@email.com','fPhone1','2019-07-20 12:20:30','2019-07-20 12:20:30'),(29,'fname3','fAddress3','email3@email.com','fPhone3','2019-07-20 12:20:45','2019-07-20 12:20:45');


--
-- Dumping data for table `students`
--

INSERT INTO `students` VALUES ('1234567891234','zika','Zikic','2009-12-30','dasfasfasf1','das1@gamil.com',NULL,'2019-07-20 12:23:28','2019-07-20 12:23:28'),('2234567891234','zika','Zhkic','2009-12-30','dasfasfasf2','das2@gamil.com',NULL,'2019-07-20 12:23:51','2019-07-20 12:23:51'),('4234567891234','aka','zvaka','2009-12-30','dasfasfasf4','das4@gamil.com',NULL,'2019-07-20 10:35:12','2019-07-20 10:35:12');


--
-- Dumping data for table `faculties_students`
--

INSERT INTO `faculties_students` VALUES (28,'1234567891234'),(27,'2234567891234'),(28,'2234567891234'),(29,'2234567891234'),(27,'4234567891234'),(28,'4234567891234');


--
-- Dumping data for table `roles`
--

INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');


--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES (1,'user','$2a$10$BNn.QyI3eVaSwm2zV02ZFekC/V8sFCA7ARSqHEI5QWGc/7ob/EQZu',2),(2,'admin','$2a$10$BNn.QyI3eVaSwm2zV02ZFekC/V8sFCA7ARSqHEI5QWGc/7ob/EQZu',1);


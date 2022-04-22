-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: lms-plusplus
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `assignment_title` text,
  `assignment_description` text,
  `order_in_lesson` int DEFAULT NULL,
  `assignment_type` varchar(45) DEFAULT NULL,
  `lesson_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) DEFAULT NULL,
  `lesson_quantity` int DEFAULT NULL,
  `course_description` varchar(255) DEFAULT NULL,
  `course_avatar` varchar(255) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `journey` varchar(45) DEFAULT NULL,
  `order_in_journey` int DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_exam`
--

DROP TABLE IF EXISTS `course_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_exam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `exam_id` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `comment` text,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(45) DEFAULT NULL,
  `exam_type` varchar(45) DEFAULT NULL,
  `order_in_course` int DEFAULT NULL,
  `course_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exam_question`
--

DROP TABLE IF EXISTS `exam_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text,
  `option_a` text,
  `option_b` text,
  `option_c` text,
  `option_d` text,
  `answer` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `order_in_exam` int DEFAULT NULL,
  `exam_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(255) DEFAULT NULL,
  `lesson_description` varchar(255) DEFAULT NULL,
  `lesson_avatar` varchar(255) DEFAULT NULL,
  `order_in_course` int DEFAULT NULL,
  `lesson_type` varchar(45) DEFAULT NULL,
  `course_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(45) DEFAULT NULL,
  `material_url` text,
  `order_in_session` int DEFAULT NULL,
  `material_type` varchar(45) DEFAULT NULL,
  `session_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  `material_video_url` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quizz`
--

DROP TABLE IF EXISTS `quizz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizz_name` text,
  `quizz_type` varchar(45) DEFAULT NULL,
  `order_in_session` int DEFAULT NULL,
  `session_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quizz_question`
--

DROP TABLE IF EXISTS `quizz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizz_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text,
  `option_a` text,
  `option_b` text,
  `option_c` text,
  `option_d` text,
  `answer` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `order_in_quizz` int DEFAULT NULL,
  `quizz_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `session_name` varchar(255) DEFAULT NULL,
  `order_in_lesson` int DEFAULT NULL,
  `session_type` varchar(45) DEFAULT NULL,
  `lesson_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `slide`
--

DROP TABLE IF EXISTS `slide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slide` (
  `id` int NOT NULL AUTO_INCREMENT,
  `slide_avatar` varchar(255) DEFAULT NULL,
  `slide_detail` text,
  `slide_video_time` int DEFAULT NULL,
  `slide_chunk` text,
  `order_in_material` int DEFAULT NULL,
  `material_id` int NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expired_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `achievement` int(11) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
--
-- Table structure for table `user_exam`
--

DROP TABLE IF EXISTS `user_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_exam` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `exam_id` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `comment` text,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_exam_answer`
--

DROP TABLE IF EXISTS `user_exam_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_exam_answer` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `exam_question_id` int DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_quizz`
--

DROP TABLE IF EXISTS `user_quizz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_quizz` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `quizz_id` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `comment` text,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_quizz_answer`
--

DROP TABLE IF EXISTS `user_quizz_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_quizz_answer` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `quizz_question_id` int DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28  9:30:45
DROP TABLE IF EXISTS `api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `http_method` varchar(45) DEFAULT NULL,
  `pattern` varchar(500) DEFAULT NULL,
  `permission_name` varchar(45) DEFAULT NULL,
  `is_required_access_token` tinyint(4) DEFAULT NULL,
  `should_check_permission` tinyint(4) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api`
--

LOCK TABLES `api` WRITE;
/*!40000 ALTER TABLE `api` DISABLE KEYS */;
INSERT INTO `api` VALUES (1,'get data by lesson id ','GET','/room/lesson/{lesson_id}','lesson.getById',1,1,'2021-06-12 09:34:27','2021-06-12 09:34:27','ACTIVE'),(2,'get list leson by course id','GET','/room/course/{course_id}','lesson.getByCourserId',1,1,'2021-06-12 12:44:38','2021-06-12 12:44:38','ACTIVE'),(3,'get quizz detail by quizz id','GET','/room/quizz-question/{quizz_id}','quizz.getById',1,1,'2021-06-12 14:53:18','2021-06-12 14:53:18','ACTIVE'),(4,'get material detail by material id','GET','/room/slide/{material_id}','material.getById',1,1,'2021-06-12 14:53:18','2021-06-12 14:53:18','ACTIVE'),(5,'get  user\'s course information','GET','/user/course','course.getByUserId',1,1,'2021-06-14 15:34:51','2021-06-14 15:34:51','ACTIVE'),(6,'get user\'s information','GET','/user/profile','user.getProfile',1,1,'2021-06-14 15:44:27','2021-06-14 15:44:27','ACTIVE'),(7,'login','POST','/user/login','user.login',0,0,'2021-06-17 02:32:30','2021-06-17 02:32:30','ACTIVE'),(8,'register','POST','/user/register','user.register',0,0,'2021-06-17 02:35:48','2021-06-17 02:35:48','ACTIVE'),(9,'delete user','DELETE','/admin/user/{user_id}','user.delete',1,1,'2021-06-17 07:18:04','2021-06-17 07:18:04','ACTIVE'),(10,'logout','GET','/user/logout','user.logout',1,0,'2021-06-17 15:35:09','2021-08-05 15:39:35','ACTIVE'),(11,'add user course','POST','/admin/user_course','userCourse.addCourse',1,1,'2021-06-17 15:35:09','2021-06-17 15:35:09','ACTIVE'),(12,'update user_course by id','PUT','/admin/user_course','userCourse.updateById',1,1,'2021-06-21 11:25:26','2021-06-21 11:25:26','ACTIVE'),(13,'delete user_course by id','DELETE','/admin/user_course','userCourse.deleteById',1,1,'2021-06-22 02:33:19','2021-06-22 02:33:19','ACTIVE'),(14,'get user course by id','GET','/admin/user_course/search','userCourse.getById',1,1,'2021-06-30 02:24:02','2021-06-30 02:24:02','ACTIVE'),(16,'add user exam answer','POST','/admin/user_exam_answer','user.addExamAnswer',1,1,'2021-06-30 02:29:04','2021-06-30 02:29:04','ACTIVE'),(17,'update user exam answer','PUT','/admin/user_exam_answer/{id}','user.updateExamAnswer',1,1,'2021-06-30 02:29:04','2021-06-30 02:29:04','ACTIVE'),(18,'delete user exam answer','DELETE','/admin/user_exam_answer/{id}','user.deleteExamAnswer',1,1,'2021-06-30 02:29:04','2021-06-30 02:29:04','ACTIVE'),(19,'get user exam answer by id','GET','/user_exam_answer/{id}','user.getExamAnswerById',1,1,'2021-06-30 02:29:04','2021-06-30 02:29:04','ACTIVE'),(20,'add user exam','POST','/admin/user_exam','user.addExam',1,1,'2021-06-30 02:34:47','2021-06-30 02:34:47','ACTIVE'),(21,'update user exam','PUT','/admin/user_exam/{id}','user.updateExam',1,1,'2021-06-30 02:34:47','2021-06-30 02:34:47','ACTIVE'),(22,'delete user exam','DELETE','/admin/user_exam/{id}','user.deleteExam',1,1,'2021-06-30 02:34:47','2021-06-30 02:34:47','ACTIVE'),(23,'get user exam by id','GET','/user_exam/{id}','user.getExamById',1,1,'2021-06-30 02:34:47','2021-06-30 02:34:47','ACTIVE'),(24,'add user quizz answer','POST','/admin/user_quizz_answer','user.addQuizzAnswer',1,1,'2021-06-30 03:06:10','2021-06-30 03:06:10','ACTIVE'),(25,'update user quizz answer','PUT','/admin/user_quizz_answer/{id}','user.updateQuizzAnswer',1,1,'2021-06-30 03:06:10','2021-06-30 03:06:10','ACTIVE'),(26,'delete user quizz answer','DELETE','/admin/user_quizz_answer/{id}','user.deleteQuizzAnswer',1,1,'2021-06-30 03:06:10','2021-06-30 03:06:10','ACTIVE'),(27,'get user quizz answer','GET','/user_quizz_answer/{id}','user.getQuizzAnswerById',1,1,'2021-06-30 03:06:10','2021-06-30 03:06:10','ACTIVE'),(28,'add user quizz','POST','/admin/user_quizz','user.addQuizz',1,1,'2021-06-30 03:12:09','2021-06-30 03:12:09','ACTIVE'),(29,'update user quizz','PUT','/admin/user_quizz/{id}','user.updateQuizz',1,1,'2021-06-30 03:12:09','2021-06-30 03:12:09','ACTIVE'),(30,'delete user quizz','DELETE','/admin/user_quizz/{id}','user.deleteQuizz',1,1,'2021-06-30 03:12:09','2021-06-30 03:12:09','ACTIVE'),(31,'get user quizz','GET','/user_quizz/{id}','user.getQuizzById',1,1,'2021-06-30 03:12:09','2021-06-30 03:12:09','ACTIVE'),(32,'add assignment','POST','/admin/assignment','assignment.add',1,1,'2021-07-03 01:33:49','2021-07-03 01:33:49','ACTIVE'),(33,'update assignment','PUT','/admin/assignment/{assignment_id}','assignment.update',1,1,'2021-07-03 01:33:49','2021-07-03 01:33:49','ACTIVE'),(34,'delete assignment by id','DELETE','/admin/assignment/{assignment_id}','assignment.delete',1,1,'2021-07-03 01:33:49','2021-07-03 01:33:49','ACTIVE'),(35,'search assignment','GET','/admin/assignment/search','assignment.search',1,1,'2021-07-03 01:33:49','2021-07-03 01:33:49','ACTIVE'),(36,'add course','POST','/admin/course','course.add',1,1,'2021-07-03 01:36:10','2021-07-03 01:36:10','ACTIVE'),(37,'update course','PUT','/admin/course/{course_id}','course.update',1,1,'2021-07-03 01:36:10','2021-07-03 01:36:10','ACTIVE'),(38,'delate course','DELETE','/admin/course/{course_id}','course.delete',1,1,'2021-07-03 01:36:10','2021-07-03 01:36:10','ACTIVE'),(39,'search course','GET','/admin/course/search','course.search',1,1,'2021-07-03 01:36:10','2021-07-03 01:36:10','ACTIVE'),(41,'add exam','POST','/admin/exam','exam.add',1,1,'2021-07-03 01:44:19','2021-07-03 01:44:19','ACTIVE'),(42,'update exam','PUT','/admin/exam/{exam_id}','exam.update',1,1,'2021-07-03 01:44:19','2021-07-03 01:44:19','ACTIVE'),(43,'delete exam','DELETE','/admin/exam/{exam_id}','exam.delete',1,1,'2021-07-03 01:44:19','2021-07-03 01:44:19','ACTIVE'),(44,'search exam','GET','/admin/exam/search','exam.search',1,1,'2021-07-03 01:44:19','2021-07-03 01:44:19','ACTIVE'),(45,'add exam question','POST','/admin/exam_question','examQuestion.add',1,1,'2021-07-03 01:46:39','2021-07-03 01:46:39','ACTIVE'),(46,'update exam question','PUT','/admin/exam_question/{examquestion_id}','examQuestion.update',1,1,'2021-07-03 01:46:39','2021-07-03 01:46:39','ACTIVE'),(47,'delete exam question','DELETE','/admin/exam_question/{examquestion_id}','examQuestion.delete',1,1,'2021-07-03 01:46:39','2021-07-03 01:46:39','ACTIVE'),(48,'search exam question','GET','/admin/exam_question/search','examQuestion.search',1,1,'2021-07-03 01:46:39','2021-07-03 01:46:39','ACTIVE'),(49,'add lesson','POST','/admin/lesson','lesson.add',1,1,'2021-07-03 01:50:18','2021-07-03 01:50:18','ACTIVE'),(50,'update lesson','PUT','/admin/lesson/{lesson_id}','lesson.update',1,1,'2021-07-03 01:50:18','2021-07-03 01:50:18','ACTIVE'),(51,'delete lesson','DELETE','/admin/lesson/{lesson_id}','lesson.delete',1,1,'2021-07-03 01:50:18','2021-07-03 01:50:18','ACTIVE'),(52,'search lesson','GET','/admin/lesson/search','lesson.search',1,1,'2021-07-03 01:50:18','2021-07-03 01:50:18','ACTIVE'),(54,'add material','POST','/admin/material','material.add',1,1,'2021-07-03 01:54:33','2021-07-03 01:54:33','ACTIVE'),(55,'update material','PUT','/admin/material/{material_id}','material.update',1,1,'2021-07-03 01:54:33','2021-07-03 01:54:33','ACTIVE'),(56,'delete material','DELETE','/admin/material/{material_id}','material.delete',1,1,'2021-07-03 01:54:33','2021-07-03 01:54:33','ACTIVE'),(57,'search material','GET','/admin/material/search','material.search',1,1,'2021-07-03 01:54:33','2021-07-03 01:54:33','ACTIVE'),(58,'add quizz','POST','/admin/quizz','quizz.add',1,1,'2021-07-03 01:57:27','2021-07-03 01:57:27','ACTIVE'),(59,'update quizz','PUT','/admin/quizz/{quizz_id}','quizz.update',1,1,'2021-07-03 01:57:27','2021-07-03 01:57:27','ACTIVE'),(60,'delete quizz','DELETE','/admin/quizz/{quizz_id}','quizz.delete',1,1,'2021-07-03 01:57:27','2021-07-03 01:57:27','ACTIVE'),(61,'search quizz','GET','/admin/quizz/search','quizz.search',1,1,'2021-07-03 01:57:27','2021-07-03 01:57:27','ACTIVE'),(62,'add quizz question','POST','/admin/quizz_question','quizzQuestion.add',1,1,'2021-07-03 02:00:08','2021-07-03 02:00:08','ACTIVE'),(63,'add quizz question','PUT','/admin/quizz_question/{quizzquestion_id}','quizzQuestion.update',1,1,'2021-07-03 02:00:08','2021-07-03 02:00:08','ACTIVE'),(64,'delete quizz question','DELETE','/admin/quizz_question/{quizzquestion_id}','quizzQuestion.delete',1,1,'2021-07-03 02:00:08','2021-07-03 02:00:08','ACTIVE'),(65,'search quizz question','GET','/admin/quizz_question/search','quizzQuestion.search',1,1,'2021-07-03 02:00:08','2021-07-03 02:00:08','ACTIVE'),(67,'add session','POST','/admin/session','session.add',1,1,'2021-07-03 02:03:21','2021-07-03 02:03:21','ACTIVE'),(68,'update session','PUT','/admin/session/{session_id}','session.update',1,1,'2021-07-03 02:03:21','2021-07-03 02:03:21','ACTIVE'),(69,'delete session','DELETE','/admin/session/{session_id}','session.delete',1,1,'2021-07-03 02:03:21','2021-07-03 02:03:21','ACTIVE'),(70,'search session','GET','/admin/session/search','session.search',1,1,'2021-07-03 02:03:21','2021-07-03 02:03:21','ACTIVE'),(71,'add slide','POST','/admin/slide','slide.add',1,1,'2021-07-03 02:08:50','2021-07-03 02:08:50','ACTIVE'),(72,'update slide','PUT','/admin/slide/{slide_id}','slide.update',1,1,'2021-07-03 02:08:50','2021-07-03 02:08:50','ACTIVE'),(73,'delete slide','DELETE','/admin/slide/{slide_id}','slide.delete',1,1,'2021-07-03 02:08:50','2021-07-03 02:08:50','ACTIVE'),(74,'search slide','GET','/admin/slide/search','slide.search',1,1,'2021-07-03 02:08:50','2021-07-03 02:08:50','ACTIVE'),(76,'get list suggest courses','GET','/room/course/sugguest','course.getSuggest',1,1,'2021-07-05 08:59:21','2021-07-05 08:59:21','ACTIVE'),(77,'get list completed courses','GET','/room/course/completed','course.getCompleted',1,1,'2021-07-05 08:59:21','2021-07-05 08:59:21','ACTIVE'),(78,'upload file','POST','/admin/file/upload','file.upload',1,1,'2021-07-13 01:52:36','2021-07-13 01:52:36','ACTIVE'),(80,'search user exam','GET','/admin/user_exam/search','user.searchExam',1,1,'2021-07-16 07:38:31','2021-07-16 07:38:31','ACTIVE'),(81,'search user exam answer','GET','/admin/user_exam_answer/search','user.searchExamAnswer',1,1,'2021-07-16 07:38:31','2021-07-16 07:38:31','ACTIVE'),(82,'search userquizz','GET','/admin/user_quizz/search','user.searchQuizz',1,1,'2021-07-16 07:38:31','2021-07-16 07:38:31','ACTIVE'),(83,'search user quizz answer','GET','/admin/user_quizz_answer/search','user.searchQuizzAnswer',1,1,'2021-07-16 10:47:01','2021-07-16 10:47:01','ACTIVE'),(84,'answers','POST','/admin/quizz_question/answer','quizzQuestion.answer',1,1,'2021-07-25 16:34:54','2021-07-25 16:34:54','ACTIVE'),(85,'search user','GET','/admin/user/search','user.search',1,1,'2021-08-03 15:22:48','2021-08-03 15:22:48','ACTIVE'),(86,'add user','POST','/admin/user','user.add',1,1,'2021-08-04 11:08:32','2021-08-04 11:08:32','ACTIVE'),(87,'update user','PUT','/admin/user/{user_id}','user.update',1,1,'2021-08-04 11:18:37','2021-08-04 11:18:37','ACTIVE'),(88,'get user\'s detail','GET','/admin/user/{user_id}','user.getDetailById',1,1,'2021-08-04 11:18:37','2021-08-04 11:18:37','ACTIVE'),(89,'search api','GET','/admin/api/search','api.search',1,1,'2021-08-05 01:50:11','2021-08-05 01:50:11','ACTIVE'),(90,'add api','POST','/admin/api','api.add',1,1,'2021-08-05 02:25:52','2021-08-05 02:25:52','ACTIVE'),(91,'update api','PUT','/admin/api/{apiId}','api.update',1,1,'2021-08-05 02:25:52','2021-08-05 02:25:52','ACTIVE'),(92,'delete api','DELETE','/admin/api/{apiId}','api.delete',1,1,'2021-08-05 02:26:17','2021-08-05 02:26:17','ACTIVE'),(93,'search role','GET','/admin/role/search','role.search',1,1,'2021-08-05 03:25:40','2021-08-05 03:25:40','ACTIVE'),(94,'add role','POST','/admin/role','role.add',1,1,'2021-08-05 03:25:40','2021-08-05 03:25:40','ACTIVE'),(95,'update role','PUT','/admin/role/{roleId}','role.update',1,1,'2021-08-05 03:25:40','2021-08-05 03:25:40','ACTIVE'),(96,'delete role','DELETE','/admin/role/{roleId}','role.delete',1,1,'2021-08-05 03:25:40','2021-08-05 03:25:40','ACTIVE'),(97,'get detail role','GET','/admin/role/{roleId}','role.getDetailById',1,1,'2021-08-05 03:26:08','2021-08-05 03:26:08','ACTIVE'),(98,'login user admin','POST','/admin/user/login','admin.login',0,0,'2021-08-08 02:05:23','2021-08-08 02:05:23','ACTIVE'),(99,'get admin profile','GET','/admin/user/profile','admin.profile',1,1,'2021-08-08 04:20:56','2021-08-08 15:30:35','ACTIVE'),(100,'search room','GET','/admin/room/search','room.search',1,1,'2021-08-10 13:51:55','2021-08-10 13:51:55','ACTIVE'),(101,'add room','POST','/admin/room','room.add',1,1,'2021-08-10 13:51:55','2021-08-10 13:51:55','ACTIVE'),(102,'update room','PUT','/admin/room/{roomId}','room.update',1,1,'2021-08-10 13:51:55','2021-08-10 13:51:55','ACTIVE'),(103,'delete room','DELETE','/admin/room/{roomId}','room.delete',1,1,'2021-08-10 13:51:55','2021-08-10 13:51:55','ACTIVE'),(104,'search voucher','GET','/admin/voucher/search','voucher.search',1,1,'2021-08-11 06:32:45','2021-08-11 06:32:45','ACTIVE'),(105,'add voucher','POST','/admin/voucher','voucher.add',1,1,'2021-08-11 06:32:45','2021-08-11 06:32:45','ACTIVE'),(106,'update voucher','PUT','/admin/voucher/{voucherId}','voucher.update',1,1,'2021-08-11 06:32:45','2021-08-11 06:32:45','ACTIVE'),(107,'delete voucher','DELETE','/admin/voucher/{voucherId}','voucher.delete',1,1,'2021-08-11 06:32:45','2021-08-11 06:32:45','ACTIVE'),(108,'search billing','GET','/admin/billing/search','billing.search',1,1,'2021-08-11 09:01:26','2021-08-11 09:01:26','ACTIVE'),(109,'add billing','POST','/admin/billing','billing.add',1,1,'2021-08-11 09:01:26','2021-08-11 09:01:26','ACTIVE'),(110,'update billing','PUT','/admin/billing/{billingId}','billing.update',1,1,'2021-08-11 09:01:26','2021-08-11 09:01:26','ACTIVE'),(111,'delete billing','DELETE','/admin/billing/{billingId}','billing.delete',1,1,'2021-08-11 09:01:26','2021-08-11 09:01:26','ACTIVE'),(112,'search revenue','GET','/admin/revenue/search','revenue.search',1,1,'2021-08-11 09:36:47','2021-08-11 09:36:47','ACTIVE'),(113,'add revenue','POST','/admin/revenue','revenue.add',1,1,'2021-08-11 09:36:47','2021-08-11 09:36:47','ACTIVE'),(114,'update revenue','PUT','/admin/revenue/{revenueId}','revenue.update',1,1,'2021-08-11 09:36:47','2021-08-11 09:36:47','ACTIVE'),(115,'delete revenue','DELETE','/admin/revenue/{revenueId}','revenue.delete',1,1,'2021-08-11 09:36:47','2021-08-11 09:36:47','ACTIVE'),(116,'add room user','POST','/admin/room-user','roomUser.add',1,1,'2021-08-11 13:14:07','2021-08-11 13:14:07','ACTIVE'),(117,'delete room user','DELETE','/admin/room-user','room-User.delete',1,1,'2021-08-11 13:14:07','2021-08-11 13:14:07','ACTIVE'),(118,'get user\'s profile','GET','/admin/user/profile','adminUser.profile',1,1,'2021-08-12 07:54:42','2021-08-12 07:54:42',NULL),(119,'search room mentor','GET','/admin/room-mentor/search','roomMentor.search',1,1,'2021-08-14 09:22:32','2021-08-14 09:22:32',NULL),(120,'add room mentor','POST','/admin/room-mentor','roomMentor.add',1,1,'2021-08-14 09:22:32','2021-08-14 09:22:32',NULL),(121,'update room mentor','PUT','/admin/room-mentor','roomMentor.update',1,1,'2021-08-14 09:22:32','2021-08-14 09:22:32',NULL),(122,'delete room mentor','DELETE','/admin/room-mentor','roomMentor.delete',1,1,'2021-08-14 09:22:32','2021-08-14 09:22:32',NULL),(123,'search room user','GET','/admin/room-user/search','roomUser.search',1,1,'2021-08-14 15:37:02','2021-08-14 15:37:02',NULL),(124,'get user to add ','GET','/admin/room-user','roomUser.getuser',1,1,'2021-08-15 02:43:35','2021-08-15 02:43:35',NULL),(125,'get user to add','GET','/admin/room-mentor','roomMentor.getuser',1,1,'2021-08-15 02:43:35','2021-08-15 02:43:35',NULL),(126,'get user in the room user','GET','/admin/room-user/{roomId}','roomUser.getUserInRoomUser',1,1,'2021-08-16 12:30:18','2021-08-16 12:30:18',NULL),(127,'get user in the room mentor','GET','/admin/room-mentor/{roomId}','roomMentor.getUserInRoomMentor',1,1,'2021-08-17 09:19:26','2021-08-17 09:19:26',NULL),(128,'get all user student to add','GET','/admin/user_course','userCourse.getUserToAdd',1,1,'2021-08-17 09:50:38','2021-08-17 09:50:38',NULL),(129,'get all user in the course','GET','/admin/user_course/{courseId}','userCourse.getAllUserInCourse',1,1,'2021-08-17 09:50:38','2021-08-17 09:50:38',NULL);
/*!40000 ALTER TABLE `api` ENABLE KEYS */;
UNLOCK TABLES;

  CREATE TABLE `role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(45) NULL,
    `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`));

CREATE TABLE `role_api` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `api_id` INT NOT NULL,
  `permission_name` VARCHAR(255) NULL,
  `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

  INSERT INTO `role_api` VALUES (2,1,2,'2021-06-12 12:44:53','2021-06-12 12:44:53'),(3,1,3,'2021-06-12 14:53:35','2021-06-12 14:53:35'),(4,1,4,'2021-06-12 14:53:35','2021-06-12 14:53:35'),(5,1,5,'2021-06-14 15:35:09','2021-06-14 15:35:09'),(6,1,6,'2021-06-14 15:44:37','2021-06-14 15:44:37'),(7,1,9,'2021-06-17 08:02:51','2021-06-17 08:02:51'),(8,1,11,'2021-06-17 08:02:51','2021-06-17 08:02:51'),(9,1,12,'2021-06-21 11:25:36','2021-06-21 11:25:36'),(10,1,10,'2021-06-21 11:35:29','2021-06-21 11:35:29'),(11,1,13,'2021-06-22 02:33:34','2021-06-22 02:33:34'),(12,1,14,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(13,1,15,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(14,1,16,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(15,1,17,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(16,1,18,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(17,1,19,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(18,1,20,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(19,1,21,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(20,1,22,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(21,1,23,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(22,1,24,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(23,1,25,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(24,1,26,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(25,1,27,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(26,1,28,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(27,1,29,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(28,1,30,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(29,1,31,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(30,1,32,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(31,1,33,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(32,1,34,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(33,1,35,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(34,1,36,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(35,1,37,'2021-07-03 02:16:25','2021-07-03 02:16:25'),(36,1,38,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(37,1,39,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(38,1,40,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(39,1,41,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(40,1,42,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(41,1,43,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(42,1,44,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(43,1,45,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(44,1,46,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(45,1,47,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(46,1,48,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(47,1,49,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(48,1,50,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(49,1,51,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(50,1,52,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(51,1,53,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(52,1,54,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(53,1,55,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(54,1,56,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(55,1,57,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(56,1,58,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(57,1,59,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(58,1,60,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(59,1,61,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(60,1,62,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(61,1,63,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(62,1,64,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(63,1,65,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(64,1,66,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(65,1,67,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(66,1,68,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(67,1,69,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(68,1,70,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(69,1,71,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(70,1,72,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(71,1,73,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(72,1,74,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(73,1,75,'2021-07-03 02:16:26','2021-07-03 02:16:26'),(74,2,1,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(75,2,2,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(76,2,3,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(77,2,4,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(78,2,5,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(79,2,6,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(80,2,10,'2021-07-03 03:10:07','2021-07-03 03:10:07'),(81,1,76,'2021-07-05 09:00:08','2021-07-05 09:00:08'),(82,1,77,'2021-07-05 09:00:08','2021-07-05 09:00:08'),(85,1,78,'2021-07-13 01:53:29','2021-07-13 01:53:29'),(86,1,80,'2021-07-16 11:05:25','2021-07-16 11:05:25'),(87,1,81,'2021-07-16 11:05:25','2021-07-16 11:05:25'),(88,1,82,'2021-07-16 11:05:25','2021-07-16 11:05:25'),(89,1,83,'2021-07-16 11:05:25','2021-07-16 11:05:25'),(90,1,85,'2021-08-02 14:46:12','2021-08-02 14:46:12'),(91,1,86,'2021-08-04 11:08:48','2021-08-04 11:08:48'),(92,1,87,'2021-08-04 11:18:55','2021-08-04 11:18:55'),(93,1,88,'2021-08-04 11:18:55','2021-08-04 11:18:55'),(94,1,89,'2021-08-05 01:50:34','2021-08-05 01:50:34'),(95,1,90,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(96,1,91,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(97,1,92,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(98,1,93,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(99,1,94,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(100,1,95,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(101,1,96,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(102,1,97,'2021-08-05 03:27:34','2021-08-05 03:27:34'),(113,1,99,'2021-08-08 08:19:32','2021-08-08 08:19:32'),(141,1,100,'2021-08-10 13:52:22','2021-08-10 13:52:22'),(142,1,101,'2021-08-10 13:52:22','2021-08-10 13:52:22'),(143,1,102,'2021-08-10 13:52:22','2021-08-10 13:52:22'),(144,1,103,'2021-08-10 13:52:22','2021-08-10 13:52:22'),(145,1,104,'2021-08-11 06:33:55','2021-08-11 06:33:55'),(146,1,105,'2021-08-11 06:33:55','2021-08-11 06:33:55'),(147,1,106,'2021-08-11 06:33:55','2021-08-11 06:33:55'),(148,1,107,'2021-08-11 06:33:55','2021-08-11 06:33:55'),(149,1,108,'2021-08-11 09:01:56','2021-08-11 09:01:56'),(150,1,109,'2021-08-11 09:01:56','2021-08-11 09:01:56'),(151,1,110,'2021-08-11 09:01:56','2021-08-11 09:01:56'),(152,1,111,'2021-08-11 09:01:56','2021-08-11 09:01:56'),(153,1,112,'2021-08-11 09:37:07','2021-08-11 09:37:07'),(154,1,113,'2021-08-11 09:37:07','2021-08-11 09:37:07'),(155,1,114,'2021-08-11 09:37:07','2021-08-11 09:37:07'),(156,1,115,'2021-08-11 09:37:07','2021-08-11 09:37:07'),(157,1,116,'2021-08-11 13:15:12','2021-08-11 13:15:12'),(158,1,117,'2021-08-11 13:15:12','2021-08-11 13:15:12'),(164,1,118,'2021-08-12 07:54:53','2021-08-12 07:54:53'),(165,1,119,'2021-08-14 09:23:06','2021-08-14 09:23:06'),(166,1,120,'2021-08-14 09:23:06','2021-08-14 09:23:06'),(167,1,121,'2021-08-14 09:23:06','2021-08-14 09:23:06'),(168,1,122,'2021-08-14 09:23:06','2021-08-14 09:23:06'),(169,1,123,'2021-08-14 15:37:19','2021-08-14 15:37:19'),(170,1,124,'2021-08-15 02:47:47','2021-08-15 02:47:47'),(171,1,125,'2021-08-15 02:47:47','2021-08-15 02:47:47'),(172,1,126,'2021-08-16 12:30:32','2021-08-16 12:30:32'),(173,1,127,'2021-08-17 09:51:15','2021-08-17 09:51:15'),(174,1,128,'2021-08-17 09:51:15','2021-08-17 09:51:15'),(175,1,129,'2021-08-17 09:53:32','2021-08-17 09:53:32');


CREATE TABLE `voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_code` varchar(45) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `expired_time` timestamp(6) NULL DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `remain` int(11) DEFAULT NULL,
  `noted` text,
  `course_id_apply` text,
  `user_id_apply` text,
  `updated_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `created_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

DROP TABLE IF EXISTS `room_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `room_mentor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_mentor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `position` varchar(45) DEFAULT NULL,
  `created_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) DEFAULT NULL,
  `room_description` varchar(500) DEFAULT NULL,
  `start_date` timestamp(6) NULL DEFAULT NULL,
  `end_date` timestamp(6) NULL DEFAULT NULL,
  `total_user` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `created_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `revenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revenue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `debit` bigint(20) DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `voucher_id` int(11) DEFAULT NULL,
  `saler_id` int(11) DEFAULT NULL,
  `ref_id` int(11) DEFAULT NULL,
  `noted` text,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `billling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billling` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `student_name` varchar(45) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `noted` text,
  `saler_id` int(11) DEFAULT NULL,
  `saler_name` varchar(45) DEFAULT NULL,
  `bill_img` text,
  `receipts` text,
  `created_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `updated_time` timestamp(6) NULL DEFAULT CURRENT_TIMESTAMP(6),
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `user_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lesson_id` int(11) NOT NULL,
  `room_id` int(11) DEFAULT NULL,
  `attend_status` varchar(45) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `mentor_c_user` text,
  `user_c_mentor` text,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


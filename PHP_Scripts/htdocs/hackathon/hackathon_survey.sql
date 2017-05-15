-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2016 at 11:21 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hackathon_survey`
--
CREATE DATABASE IF NOT EXISTS `hackathon_survey` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `hackathon_survey`;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--
-- Creation: Jul 20, 2016 at 11:48 AM
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL,
  `text` varchar(255) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `comment`:
--   `id`
--       `survey` -> `id`
--

-- --------------------------------------------------------

--
-- Table structure for table `survey`
--
-- Creation: Jul 20, 2016 at 11:54 AM
--

DROP TABLE IF EXISTS `survey`;
CREATE TABLE IF NOT EXISTS `survey` (
  `title` varchar(40) NOT NULL,
  `text` text NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `likes` int(11) NOT NULL,
  `dislikes` int(11) NOT NULL,
  `user` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `survey`:
--

--
-- Dumping data for table `survey`
--

INSERT DELAYED IGNORE INTO `survey` (`title`, `text`, `id`, `likes`, `dislikes`, `user`) VALUES
('test title', 'this is sample text', 11, 0, 0, 'adk'),
('another title', 'this is another sample test for another title', 12, 0, 0, 'bhushan');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--
-- Creation: Jul 20, 2016 at 12:00 PM
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `survey_id` int(11) NOT NULL,
  `tagname` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tags`:
--   `survey_id`
--       `survey` -> `id`
--


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for comment
--

--
-- Metadata for survey
--

--
-- Metadata for tags
--

--
-- Metadata for hackathon_survey
--

--
-- Dumping data for table `pma__relation`
--

INSERT DELAYED IGNORE INTO `pma__relation` (`master_db`, `master_table`, `master_field`, `foreign_db`, `foreign_table`, `foreign_field`) VALUES
('hackathon_survey', 'comment', 'id', 'hackathon_survey', 'survey', 'id'),
('hackathon_survey', 'tags', 'survey_id', 'hackathon_survey', 'survey', 'id');
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

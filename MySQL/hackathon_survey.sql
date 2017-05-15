-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2016 at 02:19 AM
-- Server version: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hackathon_survey`
--
CREATE DATABASE IF NOT EXISTS `hackathon_survey` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `hackathon_survey`;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) DEFAULT NULL,
  `text` varchar(40) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `tag` varchar(20) DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  `liked` varchar(10) DEFAULT NULL,
  `visibility` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `text`, `user`, `tag`, `rating`, `liked`, `visibility`) VALUES
(14, 'abc', 'abc', 'abc', 'abc', 'abc', 'abc'),
(14, 'abc', 'abc', 'abc', 'abc', 'abc', 'abc'),
(14, 'bbb', 'bbb', 'bbb', 'bbb', 'bbb', 'bbbb'),
(15, 'Are you nuts ?', 'Bhushan', 'AK', '2.0', 'dislike', 'yes'),
(15, 'sa', 'B', 'sa', '2.0', 'like', 'yes'),
(16, 'Whats Going on ?', '404', 'OOO', '3.0', 'dislike', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
CREATE TABLE IF NOT EXISTS `survey` (
  `title` varchar(40) NOT NULL,
  `text` text NOT NULL,
`id` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `dislikes` int(11) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `survey`
--

INSERT INTO `survey` (`title`, `text`, `id`, `likes`, `dislikes`, `user`) VALUES
('Git Survey', 'How much do you like Git ?', 14, 0, 0, 'Bhushan'),
('Stash', 'What about stash', 15, 0, 0, 'Rocky'),
('Tej', 'How is tej ?', 16, 0, 0, 'B'),
('Vaccation', 'Can we go to Mumbai ?', 17, 0, 0, 'Bhushan'),
('Sneha', 'How is she ?', 18, 0, 0, 'b'),
('ABC', 'how are u?', 19, 0, 0, 'Bhushan'),
('Bh', '123', 20, 0, 0, 'Bhushan'),
('akjsdaksj', '324', 21, 0, 0, 'Bhushan'),
('NBU', 'How is the product? How can ?', 22, 0, 0, 'Nikita'),
('B', 'B', 23, 0, 0, 'B');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `survey`
--
ALTER TABLE `survey`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `survey`
--
ALTER TABLE `survey`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

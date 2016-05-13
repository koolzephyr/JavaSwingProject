-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2016 at 07:48 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `visitor_log`
--

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `pid` bigint(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(70) NOT NULL,
  `middlename` varchar(50) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `email` varchar(250) NOT NULL,
  `type` varchar(8) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`pid`, `firstname`, `middlename`, `lastname`, `username`, `password`, `address`, `contact`, `email`, `type`) VALUES
(1, 'Sameer', '', 'Koirala', 'sameer.koirala', 'sameer', 'Koteshwor', '9849188508', 'sameer.koirala@deerwalk.edu.np', 'Student'),
(2, 'Anish', '', 'Thakuri', 'anish.thakuri', 'anish', 'Gokarna', '9865095106', 'anish.thakuri@deerwalk.edu.np', 'Student'),
(3, 'admin', '', 'admin', 'admin.admin', 'admin', '', '9090909090', '', 'Admin'),
(4, 'Anil', '', 'Lama', 'anil.lama', 'anil', 'Narayantar', '9808841927', 'anil.lama@deerwalk.edu.np', 'Student'),
(5, 'Sunil', '', 'Shrestha', 'sunil.shrestha', 'sunil', 'Sankhu', '9841446764', 'sunil.shrestha@deerwalk.edu.np', 'Student'),
(6, 'Bidish', '', 'Acharya', 'bidish.acharya', 'bidish', 'Sifal', '9849789069', 'bidish.acharya@deerwalk.edu.np', 'Student'),
(7, 'Sanjeev', '', 'Mainali', 'sanjeev.mainali', 'sanjeev', '', '9898989898', '', 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `visit`
--

CREATE TABLE IF NOT EXISTS `visit` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pid` bigint(10) NOT NULL,
  `visittime` time NOT NULL,
  `visitdate` date NOT NULL,
  `purpose` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `visit`
--

INSERT INTO `visit` (`id`, `pid`, `visittime`, `visitdate`, `purpose`) VALUES
(1, 1, '21:49:52', '2016-05-12', 'student'),
(2, 2, '00:01:51', '2016-05-13', 'Study'),
(3, 1, '07:14:42', '2016-05-13', 'study'),
(4, 4, '09:48:22', '2016-05-13', 'Java Project'),
(5, 5, '09:55:17', '2016-05-13', 'Java Swing Project Presentation'),
(6, 6, '11:00:45', '2016-05-13', 'Bunk Java Swing Presentation'),
(7, 3, '11:09:07', '2016-05-13', 'Admin'),
(8, 7, '13:13:08', '2016-05-13', 'study');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `users` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 04, 2015 at 12:26 AM
-- Server version: 5.5.38-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `q4music`
--

-- --------------------------------------------------------

--
-- Table structure for table `Challenge`
--

CREATE TABLE IF NOT EXISTS `Challenge` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FromID` int(11) NOT NULL,
  `Code` varchar(30) COLLATE utf8_turkish_ci NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `OwnerScore` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FromID` (`FromID`),
  KEY `CategoryID` (`CategoryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=29 ;

--
-- Dumping data for table `Challenge`
--

INSERT INTO `Challenge` (`ID`, `FromID`, `Code`, `CategoryID`, `OwnerScore`) VALUES
(10, 55, '97500325442506041481', 3, 75),
(11, 74, '89746274322071319051', 3, 23),
(12, 55, '87197124203669553754', 3, -12),
(13, 55, '09451305543679753623', 3, -76),
(14, 74, '44076209170534529935', 3, -41),
(15, 74, '92822673137718898376', 3, -72),
(16, 74, '73601704158824403155', 3, -17),
(17, 55, '05996726976140718161', 4, -57),
(18, 55, '90574832637651583676', 4, -10),
(19, 55, '23073743544174017692', 3, 120),
(20, 55, '58467695840114681555', 3, -65),
(21, 55, '55627140077384245150', 3, -65),
(22, 64, '74488476810728725555', 3, 10),
(23, 55, '35409693367654342638', 7, -453),
(24, 55, '92311410182482406806', 3, 120),
(25, 81, '90037676780798680087', 3, 50),
(26, 55, '98095994397991215080', 3, -100),
(27, 55, '31833166408965805530', 3, 5),
(28, 55, '30100090990056100744', 3, -35);

-- --------------------------------------------------------

--
-- Table structure for table `ChallengeQuestions`
--

CREATE TABLE IF NOT EXISTS `ChallengeQuestions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ChallengeID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `IsTrue` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `QuestionID` (`QuestionID`),
  KEY `ChallengeID` (`ChallengeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=94 ;

--
-- Dumping data for table `ChallengeQuestions`
--

INSERT INTO `ChallengeQuestions` (`ID`, `ChallengeID`, `QuestionID`, `IsTrue`) VALUES
(39, 18, 50, 0),
(40, 18, 46, 0),
(41, 18, 44, 0),
(42, 18, 47, 1),
(43, 18, 51, 1),
(44, 19, 56, 1),
(45, 19, 43, 1),
(46, 19, 42, 1),
(47, 19, 55, 1),
(48, 19, 45, 1),
(49, 20, 45, 0),
(50, 20, 56, 0),
(51, 20, 41, 0),
(52, 20, 55, 0),
(53, 20, 42, 1),
(54, 21, 43, 0),
(55, 21, 41, 0),
(56, 21, 55, 0),
(57, 21, 45, 0),
(58, 21, 56, 1),
(59, 22, 42, 0),
(60, 22, 56, 0),
(61, 22, 55, 0),
(62, 22, 41, 1),
(63, 22, 45, 1),
(64, 23, 40, 0),
(65, 23, 36, 0),
(66, 23, 34, 0),
(68, 23, 57, 1),
(69, 24, 43, 1),
(70, 24, 42, 1),
(71, 24, 56, 1),
(72, 24, 45, 1),
(73, 24, 55, 1),
(74, 25, 55, 0),
(75, 25, 41, 0),
(76, 25, 43, 1),
(77, 25, 45, 1),
(78, 25, 42, 1),
(79, 26, 42, 0),
(80, 26, 41, 0),
(81, 26, 55, 0),
(82, 26, 45, 0),
(83, 26, 43, 0),
(84, 27, 45, 0),
(85, 27, 41, 0),
(86, 27, 42, 1),
(87, 27, 55, 1),
(88, 27, 56, 1),
(89, 28, 42, 0),
(90, 28, 56, 0),
(91, 28, 45, 0),
(92, 28, 41, 1),
(93, 28, 55, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Choices`
--

CREATE TABLE IF NOT EXISTS `Choices` (
  `ChoiceId` int(11) NOT NULL AUTO_INCREMENT,
  `Choice` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `ChoiceOrder` int(11) NOT NULL,
  `QuestionId` int(11) NOT NULL,
  PRIMARY KEY (`ChoiceId`),
  KEY `QuestionId` (`QuestionId`),
  KEY `QuestionId_2` (`QuestionId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=166 ;

--
-- Dumping data for table `Choices`
--

INSERT INTO `Choices` (`ChoiceId`, `Choice`, `ChoiceOrder`, `QuestionId`) VALUES
(62, 'John', 0, 34),
(63, 'Mals', 1, 34),
(64, 'Sivasspor', 2, 34),
(65, 'ddd', 3, 34),
(66, 'dskannfds', 0, 35),
(67, 'dsafjkdfs', 1, 35),
(68, 'CCC', 2, 35),
(69, 'VVV', 3, 35),
(70, 'weqewr', 0, 36),
(71, 'wewewe', 1, 36),
(72, 'CCCC', 2, 36),
(73, 'rewrewe', 3, 36),
(86, 'NO', 0, 40),
(87, 'NO', 1, 40),
(88, 'OK', 2, 40),
(89, 'NOoo', 3, 40),
(90, 'Taylor Swift', 0, 41),
(91, 'Adele', 1, 41),
(92, 'Madonna', 2, 41),
(93, 'Hayley Williams', 3, 41),
(94, 'Rihanna', 0, 42),
(95, 'Kelly Clarkson', 1, 42),
(96, 'Lady GAGA', 2, 42),
(97, 'Yolante', 3, 42),
(98, 'Lily Allien', 0, 43),
(99, 'Tori Kelly', 1, 43),
(100, 'Amy Macdonald', 2, 43),
(101, 'Beyonce', 3, 43),
(102, 'Apollo 13', 0, 44),
(103, 'Armageddon', 1, 44),
(104, 'The Day After Tomorrow', 2, 44),
(105, 'Prestige', 3, 44),
(106, 'Sting', 0, 45),
(107, 'Burak Yesilyurt', 1, 45),
(108, 'Sam Martin', 2, 45),
(109, 'Rihanna', 3, 45),
(110, 'Mike Patton', 0, 46),
(111, 'Eddie Vedder', 1, 46),
(112, 'Kurt Cobain', 2, 46),
(113, 'Roger Waters', 3, 46),
(114, 'Give It Away', 0, 47),
(115, 'Suck My Kiss', 1, 47),
(116, 'Californication', 2, 47),
(117, 'Under The Bridge', 3, 47),
(118, 'Hope', 0, 49),
(119, 'Victory', 1, 49),
(120, 'Glory', 2, 49),
(121, 'Life', 3, 49),
(122, 'Axl Rose', 0, 50),
(123, 'Jason Newsted', 1, 50),
(124, 'Slash', 2, 50),
(125, 'Matt Sorum', 3, 50),
(126, 'Angus Young', 0, 51),
(127, 'Angry Anderson', 1, 51),
(128, 'Ace Frehley', 2, 51),
(129, 'Slash', 3, 51),
(130, '1986', 0, 54),
(131, '1997', 1, 54),
(132, '1996', 2, 54),
(133, '2004', 3, 54),
(134, 'Fergie', 0, 55),
(135, 'Tove Lo', 1, 55),
(136, 'Meghan Trainor', 2, 55),
(137, 'Iggy Azalea', 3, 55),
(138, 'Black Eyed Peas', 0, 56),
(139, 'Destiny''s Child', 1, 56),
(140, 'The Fugees', 2, 56),
(141, 'TLC', 3, 56),
(142, 'Ludwig van Beethoven', 0, 57),
(143, 'Mozart', 1, 57),
(144, 'Yanni', 2, 57),
(145, 'Pyotr Ilyich Tchaikovsky', 3, 57);

-- --------------------------------------------------------

--
-- Table structure for table `Hints`
--

CREATE TABLE IF NOT EXISTS `Hints` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(250) COLLATE utf8_turkish_ci NOT NULL,
  `QuestionID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `QuestionID` (`QuestionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=43 ;

--
-- Dumping data for table `Hints`
--

INSERT INTO `Hints` (`ID`, `Name`, `QuestionID`) VALUES
(17, 'Yesilkurt5867', 34),
(18, 'jnsdjjkdf', 35),
(19, 'jnwejnadwnjneaf', 36),
(23, 'dsdff', 40),
(24, 'Rolling In the Deep is famous song of artist.', 41),
(25, 'Please Don''t Stop Music song is one of excellent songs of his/her.', 42),
(26, 'She/He married with Jay-Z.', 43),
(27, 'Film''s release year is 1998', 44),
(28, 'Song that names "If I Ever Lose My Faith in You" is famous song of artist.', 45),
(29, 'He/She is born at 1968', 46),
(30, 'Song is released on March 10', 47),
(31, 'Thay album includes 11 songs.', 49),
(32, 'He/She has own metal group.', 50),
(33, 'He/She is born at 1955', 51),
(34, 'Bon Jovi''s second single, "Who Says You Can''t Go Home", from the album Have A Nice Day goes to number one in the U.S. Hot Country Charts for two weeks in the same year.', 54),
(35, 'She is the female vocalist for the hip hop group The Black Eyed Peas.', 55),
(36, 'Kelly Rowland, and Michelle Williams was member of this music group.', 56),
(37, 'He was a German composer and pianist.', 57);

-- --------------------------------------------------------

--
-- Table structure for table `Questions`
--

CREATE TABLE IF NOT EXISTS `Questions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL,
  `CorrectAnswer` int(1) NOT NULL,
  `PositiveScore` int(10) NOT NULL,
  `NegativeScore` int(10) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `CategoryID` (`CategoryID`),
  KEY `CategoryID_2` (`CategoryID`),
  KEY `CategoryID_3` (`CategoryID`),
  KEY `CategoryID_4` (`CategoryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=63 ;

--
-- Dumping data for table `Questions`
--

INSERT INTO `Questions` (`ID`, `Name`, `CorrectAnswer`, `PositiveScore`, `NegativeScore`, `CategoryID`) VALUES
(34, 'Burak', 1, 32, 58, 7),
(35, 'AliVeli', 3, 3232, 232, 7),
(36, 'dasd', 0, 43, 444, 7),
(40, 'Test81', 0, 3, 3, 7),
(41, 'London born Miss Adkins is better known by which name?', 1, 20, 20, 3),
(42, 'What is the better known stage name of Robyn Fenty?', 0, 25, 15, 3),
(43, 'Whose 2013 world tour was called ''The Mrs Carter Show''?', 3, 20, 15, 3),
(44, 'Aerosmith had a number one hit with ''I Don''t Wanna Miss A Thing''. What film soundtrack is it from?', 1, 15, 15, 4),
(45, 'Which singer is married to Trudie Styler?', 0, 40, 35, 3),
(46, 'Who was the lead singer in the band Faith No More?', 0, 15, 10, 4),
(47, 'The Red Hot Chili Peppers hit number one in 1992 with which song?', 3, 15, 15, 4),
(49, 'Jon Bon Jovi''s 1990 number one hit was entitled ''Blaze Of ''what'' ?', 2, 15, 15, 4),
(50, 'Who of the following was not a member of Guns ''N'' Roses?', 1, 15, 15, 4),
(51, 'Who is the lead guitarist of AC/DC?', 0, 15, 15, 4),
(54, 'In what year did Metallica''s ''Until It Sleeps'' hit the number one spot?', 2, 15, 15, 4),
(55, 'What artist''s real name is Stacy Ferguson?', 0, 15, 15, 3),
(56, 'Beyoncé was a member of which band before embarking on a solo career?', 1, 20, 20, 3),
(57, 'Which composer was able to write in spite of becoming almost totally deaf in later life?', 0, 20, 20, 7);

-- --------------------------------------------------------

--
-- Table structure for table `Singers`
--

CREATE TABLE IF NOT EXISTS `Singers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NameSurname` varchar(250) CHARACTER SET utf8 NOT NULL,
  `BirthDate` date NOT NULL,
  `isBand` int(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NameSurname` (`NameSurname`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `Singers`
--

INSERT INTO `Singers` (`ID`, `NameSurname`, `BirthDate`, `isBand`) VALUES
(2, 'Tarkan', '2014-11-04', 0),
(3, 'Duman', '2000-12-30', 1),
(4, 'maNga', '2000-12-30', 1),
(6, 'Kurban', '2000-12-30', 1),
(8, 'Athena', '0200-12-30', 1),
(9, 'Neset Ertas', '2003-11-03', 0),
(10, 'Beethoven', '2000-12-30', 0),
(11, 'Teoman', '2000-12-30', 0),
(13, 'Metallica', '2000-12-30', 1),
(14, 'Red Hot Chili Peppers', '2000-12-30', 1),
(15, 'In Flames', '2000-12-30', 1),
(18, 'Imagine Dragons', '2003-11-03', 1),
(26, 'P-era', '2000-12-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Songs`
--

CREATE TABLE IF NOT EXISTS `Songs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(250) CHARACTER SET utf8 NOT NULL,
  `TypeId` int(11) NOT NULL,
  `SingerId` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SongTypeId` (`TypeId`),
  KEY `SingerId` (`SingerId`),
  KEY `SongTypeId_2` (`TypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `Songs`
--

INSERT INTO `Songs` (`ID`, `Name`, `TypeId`, `SingerId`) VALUES
(1, 'Ben Böyleyim', 4, 8),
(2, 'Kuzu kuzu', 3, 2),
(5, 'Yürekten', 4, 3),
(6, 'Bir kadin çizeceksin', 4, 4),
(8, 'Yanibasimdan', 4, 3),
(9, 'Yalan', 4, 6),
(10, 'Gönül Dagi', 6, 9),
(11, 'Arsiz gönül', 4, 8),
(12, 'Turkish March', 7, 10),
(13, 'Anliyorsun degil mi', 4, 11),
(15, 'Sad but true', 8, 13),
(16, 'Californication', 8, 14),
(17, 'Rusted Nail', 8, 15),
(20, 'Öp', 3, 2),
(23, 'It''s time', 3, 18),
(33, 'Sensiz ben', 4, 26);

-- --------------------------------------------------------

--
-- Table structure for table `SongTypes`
--

CREATE TABLE IF NOT EXISTS `SongTypes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `SongTypes`
--

INSERT INTO `SongTypes` (`ID`, `Name`) VALUES
(7, 'Classical Music'),
(8, 'Metal'),
(3, 'Pop'),
(4, 'Rock'),
(6, 'Turkish Folk Music');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `ObjectId` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL,
  `EMail` varchar(250) NOT NULL,
  `FullName` varchar(250) NOT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `UserType` varchar(250) NOT NULL,
  `IsApproved` bit(1) NOT NULL DEFAULT b'1',
  `IsActive` bit(1) NOT NULL DEFAULT b'1',
  `Deleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`ObjectId`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `ObjectId` (`ObjectId`),
  UNIQUE KEY `EMail` (`EMail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=89 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`ObjectId`, `Username`, `Password`, `EMail`, `FullName`, `Gender`, `UserType`, `IsApproved`, `IsActive`, `Deleted`) VALUES
(28, 'calisan', '7c222fb2927d828af22f592134e8932480637c0d', 'adasd@dasda.com', 'calisan örnek3', NULL, 'user', b'1', b'1', b'0'),
(32, 'test1', '4e1517bf2695d4b9ce33f2426782ae6f5cc10ba3', 'bs@aa.com', 'burak', NULL, 'user', b'1', b'1', b'0'),
(34, 'alagacc', 'a415ab5cc17c8c093c015ccdb7e552aee7911aa4', 'cerenalagac@hotmail.com', 'Ceren Ala?aç', NULL, 'user', b'1', b'1', b'0'),
(35, 'burak', 'da7169592c4847350b7262ccf9f7b41b72c9d0be', 'burak@burak.com', 'burak', NULL, 'user', b'1', b'1', b'0'),
(37, 'a', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', 'a@a.com', 'a', NULL, 'user', b'1', b'1', b'0'),
(38, 's', 'a0f1490a20d0211c997b44bc357e1972deab8ae3', 's@d.com', 's', NULL, 'user', b'1', b'1', b'0'),
(39, 'asdd', '0e747aaa0f03a7b7bb9a964f47fe7c508be7b086', 'asdd@asd.co', 'asdd', NULL, 'user', b'1', b'1', b'0'),
(41, 'denemee', 'dd0b73cc6963eca9e284737bce54529f43d9d488', 'deneme@dene.com', 'denemee', NULL, 'user', b'1', b'1', b'0'),
(43, 'asd', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'a@a.c', 'asd', NULL, 'user', b'1', b'1', b'0'),
(44, 'n', '356a192b7913b04c54574d18c28d46e6395428ab', '1@gma.com', '1', NULL, 'user', b'1', b'1', b'0'),
(52, 'admin', '95c25d903b08651b295072ba2419c3b70a42ee40', 'admin@admin.com', 'Admin', b'1', 'admin', b'1', b'1', b'0'),
(53, 'eeee', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'ddddd@hotmail.com', 'dddd ffffff', NULL, 'user', b'1', b'1', b'0'),
(54, 'q', '22ea1c649c82946aa6e479e1ffd321e4a318b1b0', 'q@q.co', 'Admin', NULL, 'admin', b'1', b'1', b'0'),
(55, 'w', 'aff024fe4ab0fece4091de044c58c9ae4233383a', 'w@w.co', 'User', NULL, 'user', b'1', b'1', b'0'),
(56, 'dene', '87b23d7d2083cf997c029824d43b13dba949bded', 'dene@dene.co', 'dene', b'0', 'user', b'1', b'1', b'0'),
(57, 'aaaa', '8cb2237d0679ca88db6464eac60da96345513964', 'AA@AA.com', 'dasa', b'1', 'user', b'0', b'1', b'0'),
(58, 'Deneme123', '8c6020afed12b762818931171d4c28c016b05492', 'jxceldogm@gmail.com', 'b', b'1', 'user', b'1', b'1', b'0'),
(59, 'ss', '388ad1c312a488ee9e12998fe097f2258fa8d5ee', 'aa@tt.com', 'aa', b'1', 'user', b'1', b'1', b'0'),
(60, 'Xerox', 'e677158c11661acbfb9e665da8699db94a645f39', 'a@b.com', 'Xerox', b'1', 'user', b'1', b'1', b'0'),
(63, 'deneme', 'd88ea461adab9a5d6d2d760f82bbd6b1ba81452e', 'asdasd@a.com', 'sadas asd', b'1', 'user', b'1', b'1', b'0'),
(64, 'emreuser', '427e9052dbbe1e157dc0c12e4b87412ef71003c3', 'emreuser2@test.com', 'emre user', b'1', 'user', b'1', b'1', b'0'),
(65, 'emreadmin', '89726b3ff406b7f007a129d98100cb6e00c5887e', 'emreuser3@test.com', 'emre admin', b'1', 'admin', b'1', b'1', b'0'),
(66, 'muziksever', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'abc@abc.com', 'unkown unknown', b'1', 'user', b'1', b'1', b'0'),
(67, 'sedcft', '013d35025bba540f93f53d3c1ae72f422136a8c7', 'kutayhek@gmail.com', 'Kutay', b'1', 'user', b'1', b'0', b'0'),
(68, 'fads', 'b1b3773a05c0ed0176787a4f1574ff0075f7521e', 'dasdas@das.com', 'asdasd', b'0', 'user', b'1', b'1', b'0'),
(69, 'dasda', 'b1b3773a05c0ed0176787a4f1574ff0075f7521e', 'dasda@ada.com', 'adsda', b'0', 'user', b'0', b'0', b'0'),
(70, 'emretest', 'd3960938025e8f1f7fd4b1bafdaf76a15a210203', 'emteda@dfand.com', 'emre test', b'1', 'user', b'1', b'1', b'0'),
(71, 'emrequiz', '38a30d1e0bae9d2d5e512e3d3cb60421afc86f6c', 'emr2euser3@test.com', 'emre quiz', b'1', 'user', b'1', b'1', b'0'),
(72, 'LebronJames', '90d24ae93fb9bb5bf283ab6842583733afc1ce4d', 'fucicek23@gmail.com', 'LeBron James', b'1', 'user', b'1', b'1', b'0'),
(73, 'burakim', '4e1517bf2695d4b9ce33f2426782ae6f5cc10ba3', 'h.burakyesilyurt@gmail.com', 'Burak Yesilyurt', b'1', 'user', b'1', b'1', b'0'),
(74, 'test58', '90f57a31a6d3510b5c1dc03daa8bada626d298d5', 'saas@sada.com', 'qwert', b'1', 'user', b'1', b'1', b'0'),
(75, 'semih', '4e1517bf2695d4b9ce33f2426782ae6f5cc10ba3', 'semih@semih.com', 'Semih Tuzun', b'1', 'user', b'1', b'1', b'0'),
(76, 'veli', 'b42a6d93d796915222f6ffb2ffdd6137d93c1cdb', 'ali@veli.com', 'ali veli', b'1', 'user', b'1', b'1', b'0'),
(77, 'erginer', '4abd5f002091d3b08f3f63603ef08aa9346f23e3', 'erginere@itu.edu.tr', 'Emre Erginer', b'1', 'user', b'1', b'1', b'0'),
(78, 'ggbbggbb', 'f7c3bc1d808e04732adf679965ccc34ca7ae3441', 'ggbb@mynet.com', 'gg bb', b'1', 'user', b'1', b'1', b'0'),
(79, 'ibrahim', 'b098bc9ecb7ad7d4a3f084fc12ed0aa7eb10dcc4', 'ibrahimdolapci0909@gmail.com', '?brahim DOLAPC?', b'1', 'user', b'1', b'1', b'0'),
(80, 'qq', '7b52009b64fd0a2a49e6d8a939753077792b0554', 'yy@gmail.com', 'aa', b'1', 'user', b'1', b'1', b'0'),
(81, 'Melih', '8cb2237d0679ca88db6464eac60da96345513964', 'melih@melih.com', 'Melih Yesilyurt', b'1', 'user', b'1', b'1', b'0'),
(82, 'BurakMac', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'burakmac@mac.com', 'BurakMac', b'1', 'user', b'1', b'1', b'0'),
(83, 'ernsnl', 'fa259675628c4d2c3593614538f945e2d2ea0807', 'ernsnl@gmail.com', 'ERen Senel', b'1', 'user', b'1', b'1', b'0'),
(84, 'cml', '3041039f6a18ebbf592e13815cd88aeb803784c0', 'asdml@sdf.com', 'Cumali Türkmeno?lu', b'1', 'user', b'1', b'1', b'0'),
(85, 'outter', '6216f8a75fd5bb3d5f22b6f9958cdede3fc086c2', 'rujlekesi68@hotmail.com', 'xxx', b'1', 'user', b'1', b'1', b'0'),
(86, 'serefemre', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'serefemre@seref.com', 'seref emre', b'1', 'user', b'1', b'1', b'0'),
(87, 'nagehan', '8cb2237d0679ca88db6464eac60da96345513964', 'nagehan@nagehan.com', 'nagehan ilhan', b'1', 'user', b'1', b'1', b'0'),
(88, 'cml2', 'cf87ea075b6317a88e190a456d37bf015d7aa3e6', 'turkmenogluc@gmail.com', 'Cumali asdfasdf', b'1', 'user', b'1', b'1', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `UserStatistics`
--

CREATE TABLE IF NOT EXISTS `UserStatistics` (
  `ObjectId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `QuizTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `QuizSongTypeId` int(11) NOT NULL,
  `Score` int(11) NOT NULL,
  `TrueAnswerCount` int(11) NOT NULL,
  `FalseAnswerCount` int(11) NOT NULL,
  PRIMARY KEY (`ObjectId`),
  KEY `UserId` (`UserId`),
  KEY `QuizSongTypeId` (`QuizSongTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=103 ;

--
-- Dumping data for table `UserStatistics`
--

INSERT INTO `UserStatistics` (`ObjectId`, `UserId`, `QuizTime`, `QuizSongTypeId`, `Score`, `TrueAnswerCount`, `FalseAnswerCount`) VALUES
(7, 64, '2014-11-11 20:42:37', 3, 40, 7, 3),
(8, 55, '2014-11-15 11:25:57', 3, 40, 7, 3),
(9, 64, '2014-11-15 21:50:51', 3, 40, 7, 3),
(10, 55, '2014-11-16 00:45:05', 3, -18, 3, 2),
(11, 70, '2014-11-16 00:54:44', 3, 40, 7, 3),
(12, 70, '2014-11-16 00:54:46', 3, 40, 7, 3),
(13, 70, '2014-11-16 00:54:48', 3, 40, 7, 3),
(14, 70, '2014-11-16 00:54:50', 3, 40, 7, 3),
(15, 70, '2014-11-16 00:56:19', 4, 40, 7, 3),
(16, 70, '2014-11-16 00:56:21', 4, 40, 7, 3),
(17, 70, '2014-11-16 00:56:23', 4, 40, 7, 3),
(18, 55, '2014-11-21 23:16:04', 3, -38, 1, 4),
(19, 55, '2014-11-22 13:25:06', 3, 2, 2, 3),
(20, 70, '2014-11-23 19:35:00', 3, 40, 7, 3),
(21, 70, '2014-11-23 19:35:05', 3, 40, 7, 3),
(22, 70, '2014-11-23 19:35:08', 3, 40, 7, 3),
(23, 70, '2014-11-23 19:35:10', 3, 40, 7, 3),
(30, 55, '2014-11-24 18:51:21', 3, -5, 0, 1),
(31, 55, '2014-11-26 20:43:22', 3, 10, 1, 0),
(36, 71, '2014-11-26 22:15:58', 8, 40, 7, 3),
(37, 71, '2014-11-26 22:16:03', 8, 40, 7, 3),
(38, 55, '2014-11-27 08:40:21', 3, -32, 0, 1),
(39, 55, '2014-11-27 08:42:27', 3, -32, 0, 1),
(40, 55, '2014-11-27 13:13:00', 3, -76, 1, 3),
(41, 55, '2014-11-27 13:15:19', 3, -76, 1, 3),
(42, 55, '2014-11-27 13:16:23', 3, -74, 1, 3),
(43, 55, '2014-11-27 13:24:13', 3, -96, 0, 4),
(44, 55, '2014-11-27 13:29:13', 3, -74, 1, 3),
(45, 55, '2014-11-27 16:49:36', 3, -19, 1, 3),
(46, 55, '2014-11-27 16:53:17', 3, -76, 1, 3),
(47, 55, '2014-11-27 17:01:41', 3, -74, 1, 3),
(48, 55, '2014-11-27 17:06:10', 3, -74, 1, 3),
(49, 55, '2014-11-27 17:09:00', 3, -76, 1, 3),
(50, 55, '2014-11-27 17:13:55', 3, -74, 1, 3),
(51, 55, '2014-11-27 17:14:35', 3, -76, 1, 3),
(52, 55, '2014-11-27 21:35:02', 3, -76, 1, 3),
(53, 55, '2014-11-27 21:49:42', 3, -76, 1, 3),
(54, 55, '2014-11-27 21:56:46', 3, -41, 1, 3),
(55, 55, '2014-11-27 22:06:28', 3, -76, 1, 3),
(56, 55, '2014-11-27 22:36:38', 3, -96, 0, 4),
(59, 55, '2014-11-28 12:09:58', 3, -76, 1, 3),
(60, 55, '2014-11-28 12:29:38', 3, -96, 0, 4),
(61, 55, '2014-11-28 12:31:46', 3, -76, 1, 3),
(64, 55, '2014-11-28 20:53:42', 3, -41, 1, 3),
(66, 55, '2014-11-28 22:00:30', 3, -72, 0, 2),
(69, 55, '2014-11-29 03:44:29', 4, -106, 1, 4),
(70, 55, '2014-11-29 18:58:17', 4, -57, 1, 4),
(71, 74, '2014-11-29 19:03:22', 4, -112, 0, 5),
(73, 55, '2014-11-29 21:49:26', 4, -15, 0, 1),
(74, 55, '2014-11-29 21:53:10', 4, -10, 2, 3),
(75, 55, '2014-11-29 22:27:12', 3, 120, 5, 0),
(76, 55, '2014-11-30 01:49:01', 3, 120, 5, 0),
(77, 55, '2014-11-30 02:05:36', 3, -65, 1, 4),
(78, 55, '2014-11-30 02:13:08', 3, -75, 1, 4),
(79, 55, '2014-11-30 02:48:11', 3, -45, 1, 4),
(80, 55, '2014-11-30 02:52:02', 3, -65, 1, 4),
(81, 74, '2014-11-30 03:01:14', 3, -75, 1, 4),
(82, 64, '2014-11-30 10:10:26', 3, 10, 2, 3),
(83, 70, '2014-11-30 10:13:50', 3, -25, 2, 3),
(85, 55, '2014-11-30 16:59:46', 7, -453, 2, 3),
(86, 74, '2014-11-30 17:02:20', 7, -363, 3, 2),
(87, 64, '2014-11-30 17:15:54', 3, -100, 0, 5),
(88, 55, '2014-11-30 17:27:08', 3, 120, 5, 0),
(89, 55, '2014-11-30 17:27:50', 4, -10, 2, 3),
(90, 55, '2014-11-30 17:29:11', 3, -60, 1, 4),
(91, 81, '2014-11-30 21:58:12', 3, 50, 3, 2),
(92, 55, '2014-12-01 00:12:52', 3, -100, 0, 5),
(93, 55, '2014-12-01 01:24:24', 3, 5, 3, 2),
(94, 74, '2014-12-01 01:26:38', 3, 50, 3, 2),
(95, 55, '2014-12-01 08:29:23', 3, -70, 1, 4),
(96, 55, '2014-12-01 09:27:30', 3, -35, 2, 3),
(97, 74, '2014-12-01 09:28:57', 3, -105, 0, 5),
(98, 37, '2014-12-01 22:27:51', 7, -232, 0, 1),
(101, 64, '2014-12-03 22:24:16', 3, -105, 0, 5),
(102, 74, '2014-12-08 21:42:08', 3, 30, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `UserTotalScore`
--

CREATE TABLE IF NOT EXISTS `UserTotalScore` (
  `ObjectId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `TotalScore` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ObjectId`),
  KEY `UserId` (`UserId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `UserTotalScore`
--

INSERT INTO `UserTotalScore` (`ObjectId`, `UserId`, `TotalScore`) VALUES
(2, 64, -115),
(3, 55, -2465),
(4, 70, 655),
(5, 71, 240),
(7, 74, -575),
(8, 81, 50),
(9, 37, -232);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Challenge`
--
ALTER TABLE `Challenge`
  ADD CONSTRAINT `Challenge_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `SongTypes` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ChallengeQuestions`
--
ALTER TABLE `ChallengeQuestions`
  ADD CONSTRAINT `ChallengeQuestions_ibfk_1` FOREIGN KEY (`ChallengeID`) REFERENCES `Challenge` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ChallengeQuestions_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `Questions` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Choices`
--
ALTER TABLE `Choices`
  ADD CONSTRAINT `Choices_ibfk_1` FOREIGN KEY (`QuestionId`) REFERENCES `Questions` (`ID`);

--
-- Constraints for table `Hints`
--
ALTER TABLE `Hints`
  ADD CONSTRAINT `Hints_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `Questions` (`ID`);

--
-- Constraints for table `Questions`
--
ALTER TABLE `Questions`
  ADD CONSTRAINT `Questions_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `SongTypes` (`ID`);

--
-- Constraints for table `Songs`
--
ALTER TABLE `Songs`
  ADD CONSTRAINT `Songs_ibfk_2` FOREIGN KEY (`SingerId`) REFERENCES `Singers` (`ID`),
  ADD CONSTRAINT `Songs_ibfk_1` FOREIGN KEY (`TypeId`) REFERENCES `SongTypes` (`ID`);

--
-- Constraints for table `UserStatistics`
--
ALTER TABLE `UserStatistics`
  ADD CONSTRAINT `UserStatistics_ibfk_2` FOREIGN KEY (`QuizSongTypeId`) REFERENCES `SongTypes` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `UserStatistics_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `Users` (`ObjectId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `UserTotalScore`
--
ALTER TABLE `UserTotalScore`
  ADD CONSTRAINT `UserTotalScore_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `Users` (`ObjectId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

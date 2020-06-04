-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  jeu. 04 juin 2020 à 21:57
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `exam_2.0`
--

-- --------------------------------------------------------

--
-- Structure de la table `choix`
--

DROP TABLE IF EXISTS `choix`;
CREATE TABLE IF NOT EXISTS `choix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idQuestion` int(11) DEFAULT NULL,
  `text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `correcte` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idQuestion` (`idQuestion`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `choix`
--

INSERT INTO `choix` (`id`, `idQuestion`, `text`, `correcte`) VALUES
(1, 1, 'Jeff Besoss', 1),
(2, 1, 'Steve Jobs', 0),
(3, 1, 'Cristiano Ronaldo', 0),
(4, 1, 'Mark Zekerberg', 0),
(8, 2, 'Jeff Besoss', 0),
(9, 2, 'Bill Gates', 1),
(10, 2, 'Mark Zekerberg', 0),
(11, 2, 'Elon Mask', 0),
(12, 3, 'Java', 0),
(13, 3, 'JavaScript', 0),
(14, 3, 'PHP', 1),
(15, 3, 'Python', 0),
(20, 5, 'Jeff Besoss', 0),
(21, 5, 'Mark Zekerberg', 0),
(22, 5, 'Bill Gates', 0),
(23, 5, 'Steve Jobs', 1),
(24, 6, '2004', 0),
(25, 6, '2005', 1),
(26, 6, '2006', 0),
(27, 6, '2007', 0),
(28, 7, '100 bit', 0),
(29, 7, '1000 bit', 0),
(30, 7, '1000000 bit', 1),
(31, 7, '10000 bit', 0);

-- --------------------------------------------------------

--
-- Structure de la table `etudiants`
--

DROP TABLE IF EXISTS `etudiants`;
CREATE TABLE IF NOT EXISTS `etudiants` (
  `CodeNational` varchar(20) NOT NULL,
  `IdFiliere` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Naissance` date NOT NULL,
  PRIMARY KEY (`CodeNational`),
  KEY `IdFiliere` (`IdFiliere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `etudiants`
--

INSERT INTO `etudiants` (`CodeNational`, `IdFiliere`, `Nom`, `Prenom`, `Naissance`) VALUES
('n130130130', 1, 'el filali', 'omar', '1999-08-01'),
('N134186140', 1, 'el allali', 'yassine', '1999-06-13');

-- --------------------------------------------------------

--
-- Structure de la table `exams`
--

DROP TABLE IF EXISTS `exams`;
CREATE TABLE IF NOT EXISTS `exams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeNatinalEtudiant` varchar(20) NOT NULL,
  `note` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `codeNatinalEtudiant` (`codeNatinalEtudiant`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `exams`
--

INSERT INTO `exams` (`id`, `codeNatinalEtudiant`, `note`) VALUES
(114, 'n130130130', 6);

-- --------------------------------------------------------

--
-- Structure de la table `filieres`
--

DROP TABLE IF EXISTS `filieres`;
CREATE TABLE IF NOT EXISTS `filieres` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Intitule` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Intitule` (`Intitule`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `filieres`
--

INSERT INTO `filieres` (`Id`, `Intitule`) VALUES
(4, 'Industrielle'),
(1, 'Informatique'),
(2, 'Mecanique'),
(5, 'Reseau et Telecommunication'),
(3, 'Systemes Embarqués');

-- --------------------------------------------------------

--
-- Structure de la table `professeurs`
--

DROP TABLE IF EXISTS `professeurs`;
CREATE TABLE IF NOT EXISTS `professeurs` (
  `nomUtilisateur` varchar(300) NOT NULL,
  `idFiliere` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `naissance` date NOT NULL,
  `admin` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nomUtilisateur`),
  KEY `idFiliere` (`idFiliere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `professeurs`
--

INSERT INTO `professeurs` (`nomUtilisateur`, `idFiliere`, `nom`, `prenom`, `naissance`, `admin`) VALUES
('admin', 1, 'el khrissi', 'youness', '1970-05-13', 1),
('prof1', 1, 'lakhrissi', 'youness', '1980-05-02', 0),
('prof2', 3, 'el allali', 'yassine', '1999-05-14', 0),
('prof3', 4, 'zenzi', 'hamid', '1970-05-15', 0);

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFiliere` int(11) NOT NULL,
  `inonce` varchar(2000) NOT NULL,
  `inonceType` enum('text','image','audio','video') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idFiliere` (`idFiliere`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `questions`
--

INSERT INTO `questions` (`id`, `idFiliere`, `inonce`, `inonceType`) VALUES
(1, 1, 'Quel est le nom du fondateur d\'AMAZON?', 'text'),
(2, 1, 'Quel est le nom du fondateur de MicroSoft?', 'text'),
(3, 1, 'Quel est le langage de programmation le plus utilisé en BackEnd? ', 'text'),
(5, 1, 'Quel est le fondateur d\'Appele', 'text'),
(6, 1, 'En quelle années Facebook a été crée?', 'text'),
(7, 1, 'Un MegaBit est equivalente à ?', 'text');

-- --------------------------------------------------------

--
-- Structure de la table `reponses`
--

DROP TABLE IF EXISTS `reponses`;
CREATE TABLE IF NOT EXISTS `reponses` (
  `idExam` int(11) NOT NULL,
  `idQuestion` int(11) NOT NULL,
  `reponse` tinyint(4) NOT NULL,
  KEY `idExam` (`idExam`),
  KEY `idQuestion` (`idQuestion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reponses`
--

INSERT INTO `reponses` (`idExam`, `idQuestion`, `reponse`) VALUES
(114, 1, 1),
(114, 2, 9),
(114, 3, 14),
(114, 5, 23),
(114, 6, 25),
(114, 7, 30);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `choix`
--
ALTER TABLE `choix`
  ADD CONSTRAINT `choix_ibfk_1` FOREIGN KEY (`idQuestion`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etudiants`
--
ALTER TABLE `etudiants`
  ADD CONSTRAINT `etudiants_ibfk_1` FOREIGN KEY (`IdFiliere`) REFERENCES `filieres` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Contraintes pour la table `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`codeNatinalEtudiant`) REFERENCES `etudiants` (`CodeNational`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `professeurs`
--
ALTER TABLE `professeurs`
  ADD CONSTRAINT `professeurs_ibfk_1` FOREIGN KEY (`idFiliere`) REFERENCES `filieres` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Contraintes pour la table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`idFiliere`) REFERENCES `filieres` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `reponses`
--
ALTER TABLE `reponses`
  ADD CONSTRAINT `reponses_ibfk_1` FOREIGN KEY (`idExam`) REFERENCES `exams` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `reponses_ibfk_2` FOREIGN KEY (`idQuestion`) REFERENCES `questions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 06-Dez-2023 às 05:17
-- Versão do servidor: 8.0.31
-- versão do PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbloja`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcategoria`
--

DROP TABLE IF EXISTS `tbcategoria`;
CREATE TABLE IF NOT EXISTS `tbcategoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tbcategoria`
--

INSERT INTO `tbcategoria` (`idCategoria`, `categoria`) VALUES
(4, 'Saúde'),
(10, 'Comida'),
(11, 'Eletrônico'),
(12, 'Jogo'),
(13, 'Filme');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbproduto`
--

DROP TABLE IF EXISTS `tbproduto`;
CREATE TABLE IF NOT EXISTS `tbproduto` (
  `idProduto` int NOT NULL AUTO_INCREMENT,
  `produto` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `idCategoria` int DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `descricao` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  PRIMARY KEY (`idProduto`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tbproduto`
--

INSERT INTO `tbproduto` (`idProduto`, `produto`, `idCategoria`, `valor`, `descricao`, `quantidade`) VALUES
(15, 'Batman Arkham', 12, 15.44, 'Jogo bom', 12),
(16, 'Garrafa Térmica 8', 4, 13.99, 'Garrafa boa', 30),
(17, 'X-Tudo', 10, 23.99, 'Lanche bom', 500),
(18, 'Micro-Ondas', 11, 399.99, 'Micro-Ondas bom', 3),
(19, 'Interestelar', 13, 39, 'Filme bom', 23);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

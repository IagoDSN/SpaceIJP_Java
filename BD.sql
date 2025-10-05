-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           12.0.2-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para space_ijppython
DROP DATABASE IF EXISTS `space_ijppython`;
CREATE DATABASE IF NOT EXISTS `space_ijppython` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci */;
USE `space_ijppython`;

-- Copiando estrutura para tabela space_ijppython.baselancamento
DROP TABLE IF EXISTS `baselancamento`;
CREATE TABLE IF NOT EXISTS `baselancamento` (
  `codbaseLancamento` int(11) NOT NULL AUTO_INCREMENT,
  `nomebase` varchar(150) NOT NULL,
  `paisbase` varchar(150) NOT NULL,
  `precoConstrucao` decimal(12,2) NOT NULL,
  PRIMARY KEY (`codbaseLancamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.baselancamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijppython.carga
DROP TABLE IF EXISTS `carga`;
CREATE TABLE IF NOT EXISTS `carga` (
  `codcarga` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(150) NOT NULL,
  `peso` decimal(5,1) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `Foguete_codFoguete` int(11) NOT NULL,
  PRIMARY KEY (`codcarga`,`Foguete_codFoguete`),
  KEY `fk_carga_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_carga_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.carga: ~1 rows (aproximadamente)
INSERT INTO `carga` (`codcarga`, `tipo`, `peso`, `descricao`, `Foguete_codFoguete`) VALUES
	(1, 'robôs', 50.1, 'robôs colonizadores', 2),
	(2, 'Caixote', 50.1, 'Entrega para Marciano', 5);

-- Copiando estrutura para tabela space_ijppython.cargo
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE IF NOT EXISTS `cargo` (
  `codcargo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCargo` varchar(150) NOT NULL,
  `salarioInicial` float NOT NULL DEFAULT 0,
  PRIMARY KEY (`codcargo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.cargo: ~5 rows (aproximadamente)
INSERT INTO `cargo` (`codcargo`, `nomeCargo`, `salarioInicial`) VALUES
	(1, 'Gerente', 200000),
	(2, 'Supervisor', 40000),
	(3, 'Cientista', 30000),
	(4, 'Piloto', 110000),
	(5, 'Engenheiro', 20000);

-- Copiando estrutura para tabela space_ijppython.destino
DROP TABLE IF EXISTS `destino`;
CREATE TABLE IF NOT EXISTS `destino` (
  `codDestino` int(11) NOT NULL AUTO_INCREMENT,
  `nomeLocal` varchar(150) NOT NULL,
  `distancia` float NOT NULL,
  `pressao` decimal(7,2) NOT NULL,
  `aceleracaoGravidade` decimal(7,3) NOT NULL,
  `tipo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codDestino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.destino: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijppython.financiamento
DROP TABLE IF EXISTS `financiamento`;
CREATE TABLE IF NOT EXISTS `financiamento` (
  `codFinanciamento` int(11) NOT NULL AUTO_INCREMENT,
  `patrocinador` varchar(100) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `Missoes_codMissao` int(11) NOT NULL,
  PRIMARY KEY (`codFinanciamento`,`Missoes_codMissao`),
  KEY `fk_Financiamento_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Financiamento_Missoes1` FOREIGN KEY (`Missoes_codMissao`) REFERENCES `missoes` (`codMissao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.financiamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijppython.foguete
DROP TABLE IF EXISTS `foguete`;
CREATE TABLE IF NOT EXISTS `foguete` (
  `codFoguete` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFoguete` varchar(150) NOT NULL,
  `maximoCombustivel` float NOT NULL,
  `quantCombustivel` float DEFAULT NULL,
  `velocidade` decimal(10,2) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`codFoguete`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.foguete: ~3 rows (aproximadamente)
INSERT INTO `foguete` (`codFoguete`, `nomeFoguete`, `maximoCombustivel`, `quantCombustivel`, `velocidade`, `status`) VALUES
	(1, 'Aguia', 1000, 0, 80000.00, 0),
	(2, 'Lua2.0', 5000, 0, 100000.00, 0),
	(5, 'JoãoHenrrique2', 10000, 0, 2000.00, 0);

-- Copiando estrutura para tabela space_ijppython.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(150) NOT NULL,
  `cpf` varchar(150) NOT NULL,
  `salarioAtual` decimal(8,2) NOT NULL,
  `rg` varchar(150) DEFAULT NULL,
  `telefone` varchar(150) NOT NULL,
  `cep` varchar(150) DEFAULT NULL,
  `dataNascimento` date NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `cargo_codcargo` int(11) NOT NULL,
  PRIMARY KEY (`codFuncionario`,`cargo_codcargo`),
  KEY `fk_Funcionario_cargo_idx` (`cargo_codcargo`),
  CONSTRAINT `fk_Funcionario_cargo` FOREIGN KEY (`cargo_codcargo`) REFERENCES `cargo` (`codcargo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.funcionario: ~2 rows (aproximadamente)
INSERT INTO `funcionario` (`codFuncionario`, `nomeFuncionario`, `cpf`, `salarioAtual`, `rg`, `telefone`, `cep`, `dataNascimento`, `status`, `cargo_codcargo`) VALUES
	(1, 'Iago Nunes', '1314146', 50000.00, '', '9834-234', '37-000', '1999-04-01', 1, 1),
	(3, 'João Gabriel', '2246012', 20000.00, '', '9245-6544', '', '2002-05-03', 1, 2);

-- Copiando estrutura para tabela space_ijppython.lancamentos
DROP TABLE IF EXISTS `lancamentos`;
CREATE TABLE IF NOT EXISTS `lancamentos` (
  `codLancamentos` int(11) NOT NULL AUTO_INCREMENT,
  `dataLancamento` date NOT NULL,
  `resultado` varchar(50) NOT NULL,
  `Foguete_codFoguete` int(11) NOT NULL,
  `Missoes_codMissao` int(11) NOT NULL,
  PRIMARY KEY (`codLancamentos`,`Foguete_codFoguete`,`Missoes_codMissao`),
  KEY `fk_Lancamentos_Foguete1_idx` (`Foguete_codFoguete`),
  KEY `fk_Lancamentos_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Lancamentos_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lancamentos_Missoes1` FOREIGN KEY (`Missoes_codMissao`) REFERENCES `missoes` (`codMissao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.lancamentos: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijppython.missoes
DROP TABLE IF EXISTS `missoes`;
CREATE TABLE IF NOT EXISTS `missoes` (
  `codMissao` int(11) NOT NULL AUTO_INCREMENT,
  `nomeMissao` varchar(100) NOT NULL,
  `objetivoMissao` mediumtext NOT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataFim` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `Destino_codDestino` int(11) NOT NULL,
  PRIMARY KEY (`codMissao`,`Destino_codDestino`),
  KEY `fk_Missoes_Destino1_idx` (`Destino_codDestino`),
  CONSTRAINT `fk_Missoes_Destino1` FOREIGN KEY (`Destino_codDestino`) REFERENCES `destino` (`codDestino`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.missoes: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijppython.sensores
DROP TABLE IF EXISTS `sensores`;
CREATE TABLE IF NOT EXISTS `sensores` (
  `codSensores` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `unidade` varchar(20) NOT NULL,
  `position` varchar(50) NOT NULL,
  `Foguete_codFoguete` int(11) NOT NULL,
  PRIMARY KEY (`codSensores`,`Foguete_codFoguete`),
  KEY `fk_Sensores_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_Sensores_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Copiando dados para a tabela space_ijppython.sensores: ~2 rows (aproximadamente)
INSERT INTO `sensores` (`codSensores`, `tipo`, `unidade`, `position`, `Foguete_codFoguete`) VALUES
	(1, 'Acelerômetro', '2000', 'Painel Principal', 1),
	(2, 'Luminosidade', 'LDR', 'Frontal', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

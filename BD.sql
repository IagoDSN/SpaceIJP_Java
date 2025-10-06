-- --------------------------------------------------------
-- Servidor: 127.0.0.1
-- Versão alvo: MySQL 8.x
-- Conversão do backup original MariaDB
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --------------------------------------------------------
-- Banco de dados: space_ijppython
-- --------------------------------------------------------

DROP DATABASE IF EXISTS `space_ijppython`;
CREATE DATABASE IF NOT EXISTS `space_ijppython`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `space_ijppython`;

-- --------------------------------------------------------
-- Tabela: baselancamento
-- --------------------------------------------------------

DROP TABLE IF EXISTS `baselancamento`;
CREATE TABLE `baselancamento` (
  `codbaseLancamento` INT NOT NULL AUTO_INCREMENT,
  `nomebase` VARCHAR(150) NOT NULL,
  `paisbase` VARCHAR(150) NOT NULL,
  `precoConstrucao` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`codbaseLancamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Tabela: foguete
-- --------------------------------------------------------

DROP TABLE IF EXISTS `foguete`;
CREATE TABLE `foguete` (
  `codFoguete` INT NOT NULL AUTO_INCREMENT,
  `nomeFoguete` VARCHAR(150) NOT NULL,
  `maximoCombustivel` FLOAT NOT NULL,
  `quantCombustivel` FLOAT DEFAULT NULL,
  `velocidade` DECIMAL(10,2) NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`codFoguete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `foguete` (`codFoguete`, `nomeFoguete`, `maximoCombustivel`, `quantCombustivel`, `velocidade`, `status`) VALUES
  (1, 'Aguia', 1000, 0, 80000.00, 0),
  (2, 'Lua2.0', 5000, 0, 100000.00, 0),
  (5, 'JoãoHenrrique2', 10000, 0, 2000.00, 0);

-- --------------------------------------------------------
-- Tabela: carga
-- --------------------------------------------------------

DROP TABLE IF EXISTS `carga`;
CREATE TABLE `carga` (
  `codcarga` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(150) NOT NULL,
  `peso` DECIMAL(5,1) NOT NULL,
  `descricao` VARCHAR(150) NOT NULL,
  `Foguete_codFoguete` INT NOT NULL,
  PRIMARY KEY (`codcarga`),
  KEY `fk_carga_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_carga_Foguete1` FOREIGN KEY (`Foguete_codFoguete`)
    REFERENCES `foguete` (`codFoguete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `carga` (`codcarga`, `tipo`, `peso`, `descricao`, `Foguete_codFoguete`) VALUES
  (1, 'robôs', 50.1, 'robôs colonizadores', 2),
  (2, 'Caixote', 50.1, 'Entrega para Marciano', 5);

-- --------------------------------------------------------
-- Tabela: cargo
-- --------------------------------------------------------

DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `codcargo` INT NOT NULL AUTO_INCREMENT,
  `nomeCargo` VARCHAR(150) NOT NULL,
  `salarioInicial` FLOAT NOT NULL DEFAULT 0,
  PRIMARY KEY (`codcargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `cargo` (`codcargo`, `nomeCargo`, `salarioInicial`) VALUES
  (1, 'Gerente', 200000),
  (2, 'Supervisor', 40000),
  (3, 'Cientista', 30000),
  (4, 'Piloto', 110000),
  (5, 'Engenheiro', 20000);

-- --------------------------------------------------------
-- Tabela: destino
-- --------------------------------------------------------

DROP TABLE IF EXISTS `destino`;
CREATE TABLE `destino` (
  `codDestino` INT NOT NULL AUTO_INCREMENT,
  `nomeLocal` VARCHAR(150) NOT NULL,
  `distancia` FLOAT NOT NULL,
  `pressao` DECIMAL(7,2) NOT NULL,
  `aceleracaoGravidade` DECIMAL(7,3) NOT NULL,
  `tipo` VARCHAR(150) DEFAULT NULL,
  PRIMARY KEY (`codDestino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Tabela: missoes
-- --------------------------------------------------------

DROP TABLE IF EXISTS `missoes`;
CREATE TABLE `missoes` (
  `codMissao` INT NOT NULL AUTO_INCREMENT,
  `nomeMissao` VARCHAR(100) NOT NULL,
  `objetivoMissao` MEDIUMTEXT NOT NULL,
  `dataInicio` DATE DEFAULT NULL,
  `dataFim` DATE DEFAULT NULL,
  `status` VARCHAR(50) DEFAULT NULL,
  `Destino_codDestino` INT NOT NULL,
  PRIMARY KEY (`codMissao`),
  KEY `fk_Missoes_Destino1_idx` (`Destino_codDestino`),
  CONSTRAINT `fk_Missoes_Destino1` FOREIGN KEY (`Destino_codDestino`)
    REFERENCES `destino` (`codDestino`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Tabela: financiamento
-- --------------------------------------------------------

DROP TABLE IF EXISTS `financiamento`;
CREATE TABLE `financiamento` (
  `codFinanciamento` INT NOT NULL AUTO_INCREMENT,
  `patrocinador` VARCHAR(100) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `Missoes_codMissao` INT NOT NULL,
  PRIMARY KEY (`codFinanciamento`),
  KEY `fk_Financiamento_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Financiamento_Missoes1` FOREIGN KEY (`Missoes_codMissao`)
    REFERENCES `missoes` (`codMissao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Tabela: funcionario
-- --------------------------------------------------------

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE `funcionario` (
  `codFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(150) NOT NULL,
  `cpf` VARCHAR(150) NOT NULL,
  `salarioAtual` DECIMAL(8,2) NOT NULL,
  `rg` VARCHAR(150) DEFAULT NULL,
  `telefone` VARCHAR(150) NOT NULL,
  `cep` VARCHAR(150) DEFAULT NULL,
  `dataNascimento` DATE NOT NULL,
  `status` TINYINT DEFAULT NULL,
  `cargo_codcargo` INT NOT NULL,
  PRIMARY KEY (`codFuncionario`),
  KEY `fk_Funcionario_cargo_idx` (`cargo_codcargo`),
  CONSTRAINT `fk_Funcionario_cargo` FOREIGN KEY (`cargo_codcargo`)
    REFERENCES `cargo` (`codcargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `funcionario` (`codFuncionario`, `nomeFuncionario`, `cpf`, `salarioAtual`, `rg`, `telefone`, `cep`, `dataNascimento`, `status`, `cargo_codcargo`) VALUES
  (1, 'Iago Nunes', '1314146', 50000.00, '', '9834-234', '37-000', '1999-04-01', 1, 1),
  (3, 'João Gabriel', '2246012', 20000.00, '', '9245-6544', '', '2002-05-03', 1, 2);

-- --------------------------------------------------------
-- Tabela: lancamentos
-- --------------------------------------------------------

DROP TABLE IF EXISTS `lancamentos`;
CREATE TABLE `lancamentos` (
  `codLancamentos` INT NOT NULL AUTO_INCREMENT,
  `dataLancamento` DATE NOT NULL,
  `resultado` VARCHAR(50) NOT NULL,
  `Foguete_codFoguete` INT NOT NULL,
  `Missoes_codMissao` INT NOT NULL,
  PRIMARY KEY (`codLancamentos`),
  KEY `fk_Lancamentos_Foguete1_idx` (`Foguete_codFoguete`),
  KEY `fk_Lancamentos_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Lancamentos_Foguete1` FOREIGN KEY (`Foguete_codFoguete`)
    REFERENCES `foguete` (`codFoguete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lancamentos_Missoes1` FOREIGN KEY (`Missoes_codMissao`)
    REFERENCES `missoes` (`codMissao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Tabela: sensores
-- --------------------------------------------------------

DROP TABLE IF EXISTS `sensores`;
CREATE TABLE `sensores` (
  `codSensores` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(50) NOT NULL,
  `unidade` VARCHAR(20) NOT NULL,
  `position` VARCHAR(50) NOT NULL,
  `Foguete_codFoguete` INT NOT NULL,
  PRIMARY KEY (`codSensores`),
  KEY `fk_Sensores_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_Sensores_Foguete1` FOREIGN KEY (`Foguete_codFoguete`)
    REFERENCES `foguete` (`codFoguete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sensores` (`codSensores`, `tipo`, `unidade`, `position`, `Foguete_codFoguete`) VALUES
  (1, 'Acelerômetro', '2000', 'Painel Principal', 1),
  (2, 'Luminosidade', 'LDR', 'Frontal', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

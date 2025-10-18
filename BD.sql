-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.42 - MySQL Community Server - GPL
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


-- Copiando estrutura do banco de dados para space_ijp
DROP DATABASE IF EXISTS `space_ijp`;
CREATE DATABASE IF NOT EXISTS `space_ijp` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `space_ijp`;

-- Copiando estrutura para tabela space_ijp.baselancamento
DROP TABLE IF EXISTS `baselancamento`;
CREATE TABLE IF NOT EXISTS `baselancamento` (
  `codbaseLancamento` int NOT NULL AUTO_INCREMENT,
  `nomebase` varchar(150) NOT NULL,
  `paisbase` varchar(150) NOT NULL,
  `precoConstrucao` decimal(12,2) NOT NULL,
  PRIMARY KEY (`codbaseLancamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.baselancamento: ~0 rows (aproximadamente)
INSERT INTO `baselancamento` (`codbaseLancamento`, `nomebase`, `paisbase`, `precoConstrucao`) VALUES
	(2, 'João Henrrique', 'Brasil', 3000000.00);

-- Copiando estrutura para tabela space_ijp.carga
DROP TABLE IF EXISTS `carga`;
CREATE TABLE IF NOT EXISTS `carga` (
  `codcarga` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(150) NOT NULL,
  `quant` int NOT NULL,
  `peso` decimal(5,1) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `Foguete_codFoguete` int NOT NULL,
  PRIMARY KEY (`codcarga`,`Foguete_codFoguete`),
  KEY `fk_carga_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_carga_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.carga: ~2 rows (aproximadamente)
INSERT INTO `carga` (`codcarga`, `tipo`, `quant`, `peso`, `descricao`, `Foguete_codFoguete`) VALUES
	(2, 'Robos', 5, 50.0, 'Robos colonizadores', 1),
	(3, 'caixotes', 4, 10.0, 'entrega para martim', 1);

-- Copiando estrutura para tabela space_ijp.cargo
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE IF NOT EXISTS `cargo` (
  `codcargo` int NOT NULL AUTO_INCREMENT,
  `nomeCargo` varchar(150) NOT NULL,
  `salarioInicial` varchar(150) NOT NULL,
  PRIMARY KEY (`codcargo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.cargo: ~1 rows (aproximadamente)
INSERT INTO `cargo` (`codcargo`, `nomeCargo`, `salarioInicial`) VALUES
	(1, 'Gerente', '10000'),
	(2, 'Cientista', '5000');

-- Copiando estrutura para tabela space_ijp.destino
DROP TABLE IF EXISTS `destino`;
CREATE TABLE IF NOT EXISTS `destino` (
  `codDestino` int NOT NULL AUTO_INCREMENT,
  `nomeLocal` varchar(150) NOT NULL,
  `distancia` float NOT NULL,
  `pressao` double NOT NULL DEFAULT (0),
  `aceleracaoGravidade` decimal(7,3) NOT NULL,
  `tipo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codDestino`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.destino: ~1 rows (aproximadamente)
INSERT INTO `destino` (`codDestino`, `nomeLocal`, `distancia`, `pressao`, `aceleracaoGravidade`, `tipo`) VALUES
	(1, 'Marte', 54600000, 0.006, 3.720, 'Rochoso');

-- Copiando estrutura para tabela space_ijp.financiamento
DROP TABLE IF EXISTS `financiamento`;
CREATE TABLE IF NOT EXISTS `financiamento` (
  `codFinanciamento` int NOT NULL AUTO_INCREMENT,
  `patrocinador` varchar(100) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `Missoes_codMissao` int NOT NULL,
  PRIMARY KEY (`codFinanciamento`,`Missoes_codMissao`),
  KEY `fk_Financiamento_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Financiamento_Missoes1` FOREIGN KEY (`Missoes_codMissao`) REFERENCES `missoes` (`codMissao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.financiamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela space_ijp.foguete
DROP TABLE IF EXISTS `foguete`;
CREATE TABLE IF NOT EXISTS `foguete` (
  `codFoguete` int NOT NULL AUTO_INCREMENT,
  `nomeFoguete` varchar(150) NOT NULL,
  `maximoCombustivel` float NOT NULL,
  `velocidade` decimal(10,2) NOT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`codFoguete`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.foguete: ~0 rows (aproximadamente)
INSERT INTO `foguete` (`codFoguete`, `nomeFoguete`, `maximoCombustivel`, `velocidade`, `status`) VALUES
	(1, 'Aguia', 5000, 80000.00, 0);

-- Copiando estrutura para tabela space_ijp.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codFuncionario` int NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(150) NOT NULL,
  `cpf` varchar(150) NOT NULL,
  `salarioAtual` decimal(8,2) NOT NULL,
  `rg` varchar(150) DEFAULT NULL,
  `telefone` varchar(150) NOT NULL,
  `cep` varchar(150) DEFAULT NULL,
  `dataNascimento` date NOT NULL,
  `status` tinyint DEFAULT NULL,
  `cargo_codcargo` int NOT NULL,
  `email` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`codFuncionario`,`cargo_codcargo`),
  KEY `fk_Funcionario_cargo_idx` (`cargo_codcargo`),
  CONSTRAINT `fk_Funcionario_cargo` FOREIGN KEY (`cargo_codcargo`) REFERENCES `cargo` (`codcargo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.funcionario: ~1 rows (aproximadamente)
INSERT INTO `funcionario` (`codFuncionario`, `nomeFuncionario`, `cpf`, `salarioAtual`, `rg`, `telefone`, `cep`, `dataNascimento`, `status`, `cargo_codcargo`, `email`) VALUES
	(1, 'Pedro ', '52954222209', 2.00, '10-100', '99999-91', '3776-0000', '1946-08-09', 1, 1, NULL);

-- Copiando estrutura para tabela space_ijp.lancamentos
DROP TABLE IF EXISTS `lancamentos`;
CREATE TABLE IF NOT EXISTS `lancamentos` (
  `codLancamentos` int NOT NULL AUTO_INCREMENT,
  `dataLancamento` date NOT NULL,
  `resultado` varchar(50) NOT NULL,
  `Foguete_codFoguete` int NOT NULL,
  `Missoes_codMissao` int NOT NULL,
  PRIMARY KEY (`codLancamentos`,`Foguete_codFoguete`,`Missoes_codMissao`),
  KEY `fk_Lancamentos_Foguete1_idx` (`Foguete_codFoguete`),
  KEY `fk_Lancamentos_Missoes1_idx` (`Missoes_codMissao`),
  CONSTRAINT `fk_Lancamentos_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`),
  CONSTRAINT `fk_Lancamentos_Missoes1` FOREIGN KEY (`Missoes_codMissao`) REFERENCES `missoes` (`codMissao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.lancamentos: ~0 rows (aproximadamente)
INSERT INTO `lancamentos` (`codLancamentos`, `dataLancamento`, `resultado`, `Foguete_codFoguete`, `Missoes_codMissao`) VALUES
	(1, '2025-09-30', '1', 1, 1);

-- Copiando estrutura para tabela space_ijp.missoes
missoes

-- Copiando dados para a tabela space_ijp.missoes: ~0 rows (aproximadamente)
INSERT INTO `missoes` (`codMissao`, `nomeMissao`, `objetivoMissao`, `dataInicio`, `dataFim`, `status`, `Destino_codDestino`) VALUES
	(1, 'Alpha2.0', 'Chegar a Marte e soltar os robôs colonizadores', '2025-05-14', '2025-09-30', 'Em progresso', 1);

-- Copiando estrutura para procedure space_ijp.proc_AlteraBaseLancamento
DROP PROCEDURE IF EXISTS `proc_AlteraBaseLancamento`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraBaseLancamento`(
	IN `vnomeBase` VARCHAR(150),
	IN `vcodBase` INT,
	IN `vpaisBase` CHAR(150),
	IN `vprecoConstrucao` DECIMAL(12,2)
)
BEGIN
	SELECT COUNT(*) INTO @existe FROM baselancamento WHERE codbaselancamento = vcodBase;

	if (@existe)
		then UPDATE baselancamento SET nomebase = vnomeBase, paisbase = vpaisBase, precoConstrucao = vprecoConstrucao 
		WHERE codbaseLancamento = vcodBase;
		ELSE SELECT "Base não encontrada" AS ERRO;
	END if;
	
	SELECT * FROM baselancamento;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraCarga
DROP PROCEDURE IF EXISTS `proc_AlteraCarga`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraCarga`(
	IN `vcodCarga` INT,
	IN `vtipo` VARCHAR(150),
	IN `vpeso` DECIMAL(5,1),
	IN `vdescricao` VARCHAR(150),
	IN `vfoguete_codFoguete` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM carga WHERE codcarga = vcodCarga;

	if (@existe)
		then UPDATE carga SET tipo = vtipo, peso = vpeso, descricao = vdescricao, Foguete_codFoguete = vfoguete_codFoguete
		WHERE codcarga = vcodCarga;
		ELSE SELECT "Carga não encontrada" AS ERRO;
	END if;
	
	SELECT * FROM carga;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraCargo
DROP PROCEDURE IF EXISTS `proc_AlteraCargo`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraCargo`(
	IN `vcodCargo` INT,
	IN `vnomeCargo` VARCHAR(150),
	IN `vsalarioInicial` VARCHAR(150)
)
BEGIN
SELECT COUNT(*) INTO @existe FROM cargo WHERE codcargo = vcodCargo;

	if (@existe)
		then UPDATE cargo SET nomeCargo = vnomeCargo, salarioInicial = vsalarioInicial 
		WHERE codcargo = vcodCargo;
		ELSE SELECT "Cargo não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM cargo;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraDestino
DROP PROCEDURE IF EXISTS `proc_AlteraDestino`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraDestino`(
	IN `vnomeLocal` VARCHAR(150),
	IN `vdistancia` FLOAT,
	IN `vpressao` DOUBLE,
	IN `vaceleracaoGravidade` DECIMAL(7,3),
	IN `vcodDestino` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM destino WHERE codDestino = vcodDestino;

	if (@existe)
		then UPDATE destino SET nomeLocal = vnomeLocal, distancia = vdistancia, pressao = vpressao,
		aceleracaoGravidade = vaceleracaoGravidade
		WHERE codDestino = vcodDestino;
		ELSE SELECT "Destino não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM destino;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraFinanciamento
DROP PROCEDURE IF EXISTS `proc_AlteraFinanciamento`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraFinanciamento`(
	IN `vcodFinanciamento` INT,
	IN `vpatrocinador` VARCHAR(100),
	IN `vvalor` DECIMAL(10,2),
	IN `vMissoes_codMissao` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM financiamento WHERE codFinanciamento = vcodFinanciamento;

	if (@existe)
		then UPDATE financiamento SET patrocinador = vpatrocinador, valor = vvalor, Missoes_codMissao = vMissoes_codMissao
		WHERE codFinanciamento = vcodFinanciamento;
		ELSE SELECT "Financiamento não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM Financiamento;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraFoguete
DROP PROCEDURE IF EXISTS `proc_AlteraFoguete`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraFoguete`(
	IN `vcodFoguete` INT,
	IN `vnomeFoguete` VARCHAR(150),
	IN `vmaximoCombustivel` FLOAT,
	IN `vvelocidade` DECIMAL(10,2),
	IN `vstatus` TINYINT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM Foguete WHERE codFoguete = vcodFoguete;

	if (@existe)
		then UPDATE Foguete SET nomeFoguete = vnomeFoguete, maximoCombustivel = vmaximoCombustivel,
		velocidade = vvelocidade, status = vstatus
		WHERE codFoguete = vcodFoguete;
		ELSE SELECT "Foguete não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM Foguete;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraFuncionario
DROP PROCEDURE IF EXISTS `proc_AlteraFuncionario`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraFuncionario`(
	IN `vcodFuncionario` INT,
	IN `vnomeFuncionario` VARCHAR(150),
	IN `vcpf` VARCHAR(150),
	IN `vsalarioAtual` DECIMAL(8,2),
	IN `vtelefone` VARCHAR(150),
	IN `vdataNascimento` DATE,
	IN `vcargo_codcargo` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM Funcionario WHERE codFuncionario = vcodFuncionario;

	if (@existe)
		then UPDATE Funcionario SET nomeFuncionario = vnomeFuncionario, cpf = vcpf, salarioAtual = vsalarioAtual,
		telefone = vtelefone, dataNascimento = vdataNascimento, cargo_codcargo = vcargo_codcargo
		WHERE codFuncionario = vcodFuncionario;
		ELSE SELECT "Funcionario não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM Funcionario;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraLancamentos
DROP PROCEDURE IF EXISTS `proc_AlteraLancamentos`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraLancamentos`(
	IN `vcodLancamentos` INT,
	IN `vresultado` VARCHAR(150),
	IN `vFoguete_codFoguete` INT,
	IN `vMissoes_codMissao` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM Lancamentos WHERE codLancamentos = vcodLancamentos;

	if (@existe)
		then UPDATE Lancamentos SET dataLancamento = vdataLancamento, resultado = vresultado,
		Foguete_codFoguete = vFoguete_codFoguete, Missoes_codMissao = vMissoes_codMissao
		WHERE codLancamentos = vcodLancamentos;
		ELSE SELECT "Funcionario não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM Lancamentos;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_AlteraMissoes
DROP PROCEDURE IF EXISTS `proc_AlteraMissoes`;
DELIMITER //
CREATE PROCEDURE `proc_AlteraMissoes`(
	IN `vnomeMissao` VARCHAR(100),
	IN `vobjetivoMissao` MEDIUMTEXT,
	IN `vdataInicio` DATE,
	IN `vdataFim` DATE,
	IN `vstatus` VARCHAR(50),
	IN `vDestino_codMissao` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM missoes WHERE codsensores = vcodsensores;

	if (@existe)
		then UPDATE missoes SET nomeMissao = vnomeMissao, objetivoMissao = vobjetivoMissao,
		dataInicio = vdataInicio, dataFim = vdataFim, status = vstatus, Destino_codMissao = vDestino_codMissao 
		WHERE codsensores = vcodsensores;
		ELSE SELECT "Missão não encontrada" AS ERRO;
	END if;
	
	SELECT * FROM missoes;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_Alterasensores
DROP PROCEDURE IF EXISTS `proc_Alterasensores`;
DELIMITER //
CREATE PROCEDURE `proc_Alterasensores`(
	IN `vtipo` VARCHAR(50),
	IN `vunidade` VARCHAR(20),
	IN `vposition` VARCHAR(50),
	IN `vcodsensores` INT
)
BEGIN
SELECT COUNT(*) INTO @existe FROM sensores WHERE codsensores = vcodsensores;

	if (@existe)
		then UPDATE sensores SET tipo = vtipo, unidade = vunidade, position = vposition,
		Foguete_codFoguete = Foguete_codFoguete
		WHERE codsensores = vcodsensores;
		ELSE SELECT "sensor não encontrado" AS ERRO;
	END if;
	
	SELECT * FROM sensores;	
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_deleta/base
DROP PROCEDURE IF EXISTS `proc_deleta/base`;
DELIMITER //
CREATE PROCEDURE `proc_deleta/base`(
	IN `vcodBase` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM baselancamento WHERE vcodBase = codbaseLancamento;
	if (@contador = 1)
		then 
			DELETE FROM baselancamento WHERE vcodBase = codbaseLancamento;
			SELECT "Base apagada com sucesso!" AS mensagem;
		else
			#SELECT "Marca não encontrada." AS ERRO;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Base não encontrada";
	END if;
SELECT * FROM baselancamento;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_deletaCarga
DROP PROCEDURE IF EXISTS `proc_deletaCarga`;
DELIMITER //
CREATE PROCEDURE `proc_deletaCarga`(
	IN `vcodCarga` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM carga WHERE vcodCarga = codCarga;
	if (@contador = 1)
		then 
			DELETE FROM carga WHERE vcodCarga = codCarga;
			SELECT "Carga apagada com sucesso!" AS mensagem;
		else
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Carga não encontrada";
	END if;
SELECT * FROM carga;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_deletaCargo
DROP PROCEDURE IF EXISTS `proc_deletaCargo`;
DELIMITER //
CREATE PROCEDURE `proc_deletaCargo`(
	IN `vcodCargo` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM cargo WHERE vcodCargo = codCargo;
	if (@contador = 1)
		then 
			DELETE FROM cargo WHERE vcodCargo = codCargo;
			SELECT "Cargo apagada com sucesso!" AS mensagem;
		else
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Cargo não encontrado";
	END if;
SELECT * FROM cargo;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_deletaDestino
DROP PROCEDURE IF EXISTS `proc_deletaDestino`;
DELIMITER //
CREATE PROCEDURE `proc_deletaDestino`(
	IN `vcodDestino` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM destino WHERE vcodDestino = codDestino;
	if (@contador = 1)
		then 
			DELETE FROM destino WHERE vcodDeletaDestino = codDestino;
			SELECT "Destino apagado com sucesso!" AS mensagem;
		else
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Destino não encontrado";
	END if;
SELECT * FROM destino;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaFinanciamento
DROP PROCEDURE IF EXISTS `proc_DeletaFinanciamento`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaFinanciamento`(
	IN `vcodFinanciamento` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM financiamento WHERE vcodFinanciamento = codFinanciamento;
	if (@contador = 1)
	then
	DELETE FROM financiamento WHERE vcodFinanciamento = codFinanciamento;
	SELECT "Financiamento apagado com sucesso!" AS mensagem;
	else
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Financiamento não encontrado";
	END if;
	SELECT * FROM financiamento;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaFoguete
DROP PROCEDURE IF EXISTS `proc_DeletaFoguete`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaFoguete`(
	IN `vcodFoguete` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM foguete WHERE vcodFoguete = codFoguete;
	if (@contador = 1)
	then
	DELETE FROM foguete WHERE vcodFoguete = codFoguete;
	SELECT "Foguete apagado com sucesso!" AS mensagem;
	else
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Foguete não encontrado";
	END if;
	SELECT * FROM Foguete;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaFuncionario
DROP PROCEDURE IF EXISTS `proc_DeletaFuncionario`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaFuncionario`(
	IN `vcodFuncionario` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM funcionario WHERE vcodFuncionario = codFuncionario;
	if (@contador = 1)
	then
	DELETE FROM funcionario WHERE vcodFuncionario = codFuncionario;
	SELECT "Funcionario apagado com sucesso!" AS mensagem;
	else
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Funcionario não encontrado";
	END if;
	SELECT * FROM funcionario;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaLancamentos
DROP PROCEDURE IF EXISTS `proc_DeletaLancamentos`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaLancamentos`(
	IN `vcodLancamentos` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM lancamentos WHERE vcodLancamentos = codLancamentos;
	if (@contador = 1)
	then
	DELETE FROM lancamentos WHERE vcodLancamentos = codLancamentos;
	SELECT "Lancamento apagado com sucesso!" AS mensagem;
	else
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Lancamento não encontrado";
	END if;
	SELECT * FROM lancamentos;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaMissoes
DROP PROCEDURE IF EXISTS `proc_DeletaMissoes`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaMissoes`(
	IN `vcodMissoes` INT
)
BEGIN
SELECT COUNT(*) INTO @contador FROM missoes WHERE vcodMissoes = codMissao;
if (@contador = 1)
then
DELETE FROM missoes WHERE vcodMissoes = codMissao;
SELECT "Missão apagada com sucesso!" AS mensagem;
else
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Missão não encontrada";
END if;
SELECT * FROM missoes;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_DeletaSensores
DROP PROCEDURE IF EXISTS `proc_DeletaSensores`;
DELIMITER //
CREATE PROCEDURE `proc_DeletaSensores`(
	IN `vcodSensores` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM sensores WHERE vcodSensores = codSensores;
	if (@contador = 1)
	then
	DELETE FROM sensores WHERE vcodSensores = codSensores;
	SELECT "Sensor apagado com sucesso!" AS mensagem;
	else
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Sensor não encontrado";
	END if;
	SELECT * FROM sensores;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InserebaseLancamento
DROP PROCEDURE IF EXISTS `proc_InserebaseLancamento`;
DELIMITER //
CREATE PROCEDURE `proc_InserebaseLancamento`(
	IN `vnomebase` VARCHAR(150),
	IN `vpaisbase` VARCHAR(150),
	IN `vprecoConstrucao` DECIMAL(12,2)
)
BEGIN
INSERT INTO baselancamento(nomebase, paisbase, precoConstrucao)
	VALUES(vnomebase, vpaisbase, vprecoConstrucao);
	SELECT * FROM baselancamento;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereCarga
DROP PROCEDURE IF EXISTS `proc_InsereCarga`;
DELIMITER //
CREATE PROCEDURE `proc_InsereCarga`(
	IN `vtipo` VARCHAR(150),
	IN `vpeso` DECIMAL(5,1),
	IN `vdescricao` VARCHAR(150),
	IN `vfoguete_codFoguete` INT
)
BEGIN
INSERT INTO carga(tipo, peso, descricao, foguete_codFoguete)
	VALUES(vtipo, vpeso, vdescricao, vfoguete_codFoguete);
	SELECT * FROM carga;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereCargo
DROP PROCEDURE IF EXISTS `proc_InsereCargo`;
DELIMITER //
CREATE PROCEDURE `proc_InsereCargo`(
	IN `vnomeCargo` VARCHAR(150),
	IN `vsalarioInicial` VARCHAR(150)
)
BEGIN
INSERT INTO cargo(nomeCargo, salarioInicial)
	VALUES(vnomeCargo, vsalarioInicial);
	SELECT * FROM cargo;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereDestino
DROP PROCEDURE IF EXISTS `proc_InsereDestino`;
DELIMITER //
CREATE PROCEDURE `proc_InsereDestino`(
	IN `vnomeLocal` VARCHAR(150),
	IN `vdistancia` FLOAT,
	IN `vpressao` DOUBLE,
	IN `vaceleracaoGravidade` DECIMAL(7,3),
	IN `vtipo` VARCHAR(150)
)
BEGIN
INSERT INTO Destino(nomeLocal, distancia, pressao, aceleracaoGravidade, tipo)
	VALUES(vnomeLocal, vdistancia, vpressao, vaceleracaoGravidade, vtipo);
	SELECT * FROM Destino;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereFinaciamento
DROP PROCEDURE IF EXISTS `proc_InsereFinaciamento`;
DELIMITER //
CREATE PROCEDURE `proc_InsereFinaciamento`(
	IN `vpatrocinador` VARCHAR(100),
	IN `vvalor` DECIMAL(10,2),
	IN `vMissoes_codMissao` INT
)
BEGIN
INSERT INTO Financiamento(patrocinador, valor, Missoes_codMissao)
	VALUES(vpatrocinador, vvalor, vMissoes_codMissao);
	SELECT * FROM Financiamento;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereFoguete
DROP PROCEDURE IF EXISTS `proc_InsereFoguete`;
DELIMITER //
CREATE PROCEDURE `proc_InsereFoguete`(
	IN `vnomeFoguete` VARCHAR(150),
	IN `vmaximoCombustivel` FLOAT,
	IN `vvelocidade` INT,
	IN `vstatus` TINYINT
)
BEGIN
INSERT INTO Foguete(nomeFoguete, maximoCombustivel, velocidade, status)
	VALUES(vnomeFoguete, vmaximoCombustivel, vvelocidade, vstatus);
	SELECT * FROM Foguete;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereFuncionario
DROP PROCEDURE IF EXISTS `proc_InsereFuncionario`;
DELIMITER //
CREATE PROCEDURE `proc_InsereFuncionario`(
	IN `vnomeFuncionario` VARCHAR(150),
	IN `vcpf` VARCHAR(150),
	IN `vsalarioAtual` DECIMAL(8,2),
	IN `vtelefone` VARCHAR(150),
	IN `vdataNascimento` DATE,
	IN `vcargo_codcargo` INT
)
BEGIN
INSERT INTO Funcionario(nomeFuncionario, cpf, salarioAtual, telefone, dataNascimento, cargo_codcargo)
	VALUES(vnomeFuncionario, vcpf, vsalarioAtual, vtelefone, vdataNascimento, vcargo_codcargo);
	SELECT * FROM Funcionario;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_InsereLancamentos
DROP PROCEDURE IF EXISTS `proc_InsereLancamentos`;
DELIMITER //
CREATE PROCEDURE `proc_InsereLancamentos`(
	IN `vdataLancamento` DATE,
	IN `vresultado` VARCHAR(50),
	IN `vFoguete_codFoguete` INT,
	IN `vMissoes_codMissao` INT
)
BEGIN
INSERT INTO Lancamentos(dataLancamento, resultado, Foguete_codFoguete, Missoes_codMissao)
	VALUES(vdataLancamento, vresultado, vFoguete_codFoguete, vMissoes_codMissao);
	SELECT * FROM Lancamentos;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_Inseremissoes
DROP PROCEDURE IF EXISTS `proc_Inseremissoes`;
DELIMITER //
CREATE PROCEDURE `proc_Inseremissoes`(
	IN `vnomeMissao` VARCHAR(150),
	IN `vobjetivoMissao` MEDIUMTEXT,
	IN `vDestino_codDestino` INT
)
BEGIN
INSERT INTO missoes(nomeMissao, objetivoMissao, Destino_codDestino)
	VALUES(vnomeMissao, vobjetivoMissao, vDestino_codDestino);
	SELECT * FROM missoes;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_Inseresensores
DROP PROCEDURE IF EXISTS `proc_Inseresensores`;
DELIMITER //
CREATE PROCEDURE `proc_Inseresensores`(
	IN `vtipo` VARCHAR(50),
	IN `vunidade` VARCHAR(20),
	IN `vposicao` VARCHAR(50),
	IN `vFoguete_codFoguete` INT
)
BEGIN
INSERT INTO sensores(tipo, unidade, posicao, Foguete_codFoguete)
	VALUES(vtipo, vunidade, vposicao, vFoguete_codFoguete);
	SELECT * FROM sensores;
END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_RelatorioFoguete
DROP PROCEDURE IF EXISTS `proc_RelatorioFoguete`;
DELIMITER //
CREATE PROCEDURE `proc_RelatorioFoguete`(
	IN `tipoRelatorio` VARCHAR(150)
)
BEGIN
IF (LOWER(tipoRelatorio) = "simples")
    THEN SELECT f.nomeFoguete, f.velocidade
         FROM foguete AS f;

ELSEIF (LOWER(tipoRelatorio) = "completo")
    THEN SELECT f.codFoguete, f.nomeFoguete, f.maximoCombustivel, 
                f.velocidade, f.status
         FROM foguete AS f;

ELSEIF (LOWER(tipoRelatorio) = "prontos")
    THEN SELECT f.codFoguete, f.nomeFoguete, f.maximoCombustivel
         FROM foguete AS f
         WHERE f.status = 1;

ELSEIF (LOWER(tipoRelatorio) = "naoprontos")
    THEN SELECT f.codFoguete, f.nomeFoguete
         FROM foguete AS f
         WHERE f.status = 0;

ELSEIF (LOWER(tipoRelatorio) = "lancados")
    THEN SELECT f.codFoguete, f.nomeFoguete, f.velocidade
         FROM foguete AS f
         WHERE f.status = 2;

ELSEIF (LOWER(tipoRelatorio) = "total")
    THEN SELECT COUNT(*) AS totalFoguetes
         FROM foguete;

ELSE SIGNAL SQLSTATE "45000"
     SET MESSAGE_TEXT = "Opção inválida!";
END IF;

END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_RelatorioFuncionario
DROP PROCEDURE IF EXISTS `proc_RelatorioFuncionario`;
DELIMITER //
CREATE PROCEDURE `proc_RelatorioFuncionario`(
	IN `tipoRelatorio` VARCHAR(150)
)
BEGIN
IF (LOWER(tipoRelatorio) = "simples")
    THEN SELECT f.nomeFuncionario, f.cpf 
         FROM funcionario AS f;

ELSEIF (LOWER(tipoRelatorio) = "completo")
    THEN SELECT f.codFuncionario, f.nomeFuncionario, f.cpf, f.rg, 
                f.telefone, f.cep, 
                DATE_FORMAT(f.dataNascimento, "%d/%m/%Y") AS dataNascimento,
                f.salarioAtual, f.status, c.nomeCargo
         FROM funcionario AS f
         INNER JOIN cargo AS c ON f.cargo_codCargo = c.codCargo;

ELSEIF (LOWER(tipoRelatorio) = "contato")
    THEN SELECT f.nomeFuncionario, f.telefone, f.cep 
         FROM funcionario AS f;

ELSEIF (LOWER(tipoRelatorio) = "ativos")
    THEN SELECT f.codFuncionario, f.nomeFuncionario, f.salarioAtual
         FROM funcionario AS f
         WHERE f.status = 1;

ELSEIF (LOWER(tipoRelatorio) = "demitidos")
    THEN SELECT f.codFuncionario, f.nomeFuncionario, f.dataNascimento
         FROM funcionario AS f
         WHERE f.status = 0;

ELSEIF (LOWER(tipoRelatorio) = "total")
    THEN SELECT COUNT(*) AS totalFuncionarios 
         FROM funcionario;

ELSE SIGNAL SQLSTATE "45000" 
     SET MESSAGE_TEXT = "Opção inválida!";
END IF;

END//
DELIMITER ;

-- Copiando estrutura para procedure space_ijp.proc_RelatorioMissoes
DROP PROCEDURE IF EXISTS `proc_RelatorioMissoes`;
DELIMITER //
CREATE PROCEDURE `proc_RelatorioMissoes`(
	IN `tipoRelatorio` VARCHAR(150)
)
BEGIN
IF (LOWER(tipoRelatorio) = "simples")
    THEN SELECT m.nomeMissao, m.objetivoMissao
         FROM missoes AS m;

ELSEIF (LOWER(tipoRelatorio) = "completo")
    THEN SELECT m.codMissao, m.nomeMissao, m.objetivoMissao,
                DATE_FORMAT(m.dataInicio, "%d/%m/%Y") AS dataInicio,
                DATE_FORMAT(m.dataFim, "%d/%m/%Y") AS dataFim,
                m.status, d.nomeLocal
         FROM missoes AS m
         INNER JOIN destino AS d ON m.destino_codDestino = d.codDestino;

ELSEIF (LOWER(tipoRelatorio) = "planejadas")
    THEN SELECT m.codMissao, m.nomeMissao, m.objetivoMissao
         FROM missoes AS m
         WHERE LOWER(m.status) = "planejada";

ELSEIF (LOWER(tipoRelatorio) = "andamento")
    THEN SELECT m.codMissao, m.nomeMissao, DATE_FORMAT(m.dataInicio, "%d/%m/%Y") AS dataInicio
         FROM missoes AS m
         WHERE LOWER(m.status) = "em andamento";

ELSEIF (LOWER(tipoRelatorio) = "sucesso")
    THEN SELECT m.codMissao, m.nomeMissao, DATE_FORMAT(m.dataFim, "%d/%m/%Y") AS dataFim
         FROM missoes AS m
         WHERE LOWER(m.status) = "sucesso";

ELSEIF (LOWER(tipoRelatorio) = "falha")
    THEN SELECT m.codMissao, m.nomeMissao, m.objetivoMissao
         FROM missoes AS m
         WHERE LOWER(m.status) = "falha";

ELSEIF (LOWER(tipoRelatorio) = "total")
    THEN SELECT COUNT(*) AS totalMissoes
         FROM missoes;

ELSE SIGNAL SQLSTATE "45000"
     SET MESSAGE_TEXT = "Opção inválida!";
END IF;
END//
DELIMITER ;

-- Copiando estrutura para tabela space_ijp.sensores
DROP TABLE IF EXISTS `sensores`;
CREATE TABLE IF NOT EXISTS `sensores` (
  `codSensores` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `unidade` varchar(20) NOT NULL,
  `position` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `Foguete_codFoguete` int NOT NULL,
  PRIMARY KEY (`codSensores`,`Foguete_codFoguete`),
  KEY `fk_Sensores_Foguete1_idx` (`Foguete_codFoguete`),
  CONSTRAINT `fk_Sensores_Foguete1` FOREIGN KEY (`Foguete_codFoguete`) REFERENCES `foguete` (`codFoguete`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela space_ijp.sensores: ~0 rows (aproximadamente)
INSERT INTO `sensores` (`codSensores`, `tipo`, `unidade`, `position`, `Foguete_codFoguete`) VALUES
	(2, 'Acelerometro', '2000', 'Painel Central', 1);

-- Copiando estrutura para view space_ijp.vi_dadosfoguete
DROP VIEW IF EXISTS `vi_dadosfoguete`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_dadosfoguete` (
	`nomeFoguete` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`status` TINYINT NOT NULL,
	`status_descricao` VARCHAR(1) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`CargasComPeso` TEXT NULL COLLATE 'utf8mb3_general_ci',
	`Sensores` TEXT NULL COLLATE 'utf8mb3_general_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view space_ijp.vi_dadosfuncionario
DROP VIEW IF EXISTS `vi_dadosfuncionario`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_dadosfuncionario` (
	`codFuncionario` INT NOT NULL,
	`nomeFuncionario` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`cpf` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nomeCargo` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`situacao` VARCHAR(1) NOT NULL COLLATE 'utf8mb4_general_ci',
	`email` VARCHAR(1) NULL COLLATE 'utf8mb3_general_ci',
	`telefone` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view space_ijp.vi_dadosmissoes
DROP VIEW IF EXISTS `vi_dadosmissoes`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_dadosmissoes` (
	`codMissao` INT NOT NULL,
	`nomeMissao` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nomeLocal` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`distancia_formatada` VARCHAR(1) NULL COLLATE 'utf8mb4_general_ci',
	`dataInicio` VARCHAR(1) NULL COLLATE 'utf8mb4_general_ci',
	`dataFim` VARCHAR(1) NULL COLLATE 'utf8mb4_general_ci',
	`duracao` VARCHAR(1) NULL COLLATE 'utf8mb4_general_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view space_ijp.vi_financiamentos
DROP VIEW IF EXISTS `vi_financiamentos`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_financiamentos` (
	`codFinanciamento` INT NOT NULL,
	`patrocinador` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`valor` DECIMAL(10,2) NOT NULL,
	`nomeMissao` VARCHAR(1) NULL COLLATE 'utf8mb3_general_ci',
	`dataInicio` DATE NULL,
	`dataFim` DATE NULL,
	`statusMissao` VARCHAR(1) NULL COLLATE 'utf8mb3_general_ci',
	`FoguetesEnvolvidos` TEXT NULL COLLATE 'utf8mb3_general_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view space_ijp.vi_funcionariosnaempresa
DROP VIEW IF EXISTS `vi_funcionariosnaempresa`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_funcionariosnaempresa` (
	`codFuncionario` INT NOT NULL,
	`dataNascimento` VARCHAR(1) NULL COLLATE 'utf8mb4_general_ci',
	`idade` BIGINT NULL,
	`nomeFuncionario` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`salarioAtual` DECIMAL(8,2) NOT NULL,
	`nomeCargo` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`sta tusFuncionario` VARCHAR(1) NOT NULL COLLATE 'utf8mb4_general_ci'
) ENGINE=MyISAM;

-- Copiando estrutura para view space_ijp.vi_lancamentos
DROP VIEW IF EXISTS `vi_lancamentos`;
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `vi_lancamentos` (
	`codLancamentos` INT NOT NULL,
	`codbaseLancamento` INT NOT NULL,
	`dataLancamento` DATE NOT NULL,
	`resultado` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`paisbase` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nomeFoguete` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nomeMissao` VARCHAR(1) NOT NULL COLLATE 'utf8mb3_general_ci'
) ENGINE=MyISAM;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_dadosfoguete`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_dadosfoguete` AS select `f`.`nomeFoguete` AS `nomeFoguete`,`f`.`status` AS `status`,(case `f`.`status` when 0 then 'Não pronto' when 1 then 'Pronto para lançamento' when 2 then 'Já lançado' when 3 then 'Retornando para base' else 'Desconhecido' end) AS `status_descricao`,group_concat(distinct concat(`c`.`tipo`,' (',(`c`.`peso` * `c`.`quant`),' Kg)') separator ', ') AS `CargasComPeso`,group_concat(distinct `s`.`tipo` separator ', ') AS `Sensores` from ((`foguete` `f` join `carga` `c` on((`c`.`Foguete_codFoguete` = `f`.`codFoguete`))) join `sensores` `s` on((`s`.`Foguete_codFoguete` = `f`.`codFoguete`))) group by `f`.`codFoguete`,`f`.`nomeFoguete`,`f`.`status`
;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_dadosfuncionario`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_dadosfuncionario` AS select `funcionario`.`codFuncionario` AS `codFuncionario`,`funcionario`.`nomeFuncionario` AS `nomeFuncionario`,`funcionario`.`cpf` AS `cpf`,`cargo`.`nomeCargo` AS `nomeCargo`,(case when 1 then 'Ativo' when 0 then 'Demitido' else 'Desconhecido' end) AS `situacao`,`funcionario`.`email` AS `email`,`funcionario`.`telefone` AS `telefone` from (`funcionario` join `cargo`) order by `funcionario`.`nomeFuncionario`
;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_dadosmissoes`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_dadosmissoes` AS select `m`.`codMissao` AS `codMissao`,`m`.`nomeMissao` AS `nomeMissao`,`d`.`nomeLocal` AS `nomeLocal`,(case when (`d`.`distancia` >= 1000000000) then concat(format((`d`.`distancia` / 1000000000),1),' bilhões') when (`d`.`distancia` >= 1000000) then concat(format((`d`.`distancia` / 1000000),1),' milhões') when (`d`.`distancia` >= 1000) then concat(format((`d`.`distancia` / 1000),1),' mil') else format(`d`.`distancia`,0) end) AS `distancia_formatada`,date_format(`m`.`dataInicio`,'%d/%m/%Y') AS `dataInicio`,date_format(`m`.`dataFim`,'%d/%m/%Y') AS `dataFim`,(case when ((`m`.`dataInicio` is not null) and (`m`.`dataFim` is not null)) then concat(timestampdiff(YEAR,`m`.`dataInicio`,`m`.`dataFim`),' anos, ',(timestampdiff(MONTH,`m`.`dataInicio`,`m`.`dataFim`) % 12),' meses, ',(timestampdiff(DAY,`m`.`dataInicio`,`m`.`dataFim`) % 30),' dias') else 'Em andamento' end) AS `duracao` from (`missoes` `m` join `destino` `d` on((`m`.`Destino_codDestino` = `d`.`codDestino`)))
;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_financiamentos`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_financiamentos` AS select `f`.`codFinanciamento` AS `codFinanciamento`,`f`.`patrocinador` AS `patrocinador`,`f`.`valor` AS `valor`,`m`.`nomeMissao` AS `nomeMissao`,`m`.`dataInicio` AS `dataInicio`,`m`.`dataFim` AS `dataFim`,`m`.`status` AS `statusMissao`,group_concat(distinct `fo`.`nomeFoguete` separator ', ') AS `FoguetesEnvolvidos` from (((`financiamento` `f` left join `missoes` `m` on((`m`.`codMissao` = `f`.`Missoes_codMissao`))) left join `lancamentos` `l` on((`l`.`Missoes_codMissao` = `m`.`codMissao`))) left join `foguete` `fo` on((`fo`.`codFoguete` = `l`.`Foguete_codFoguete`))) group by `f`.`codFinanciamento`,`f`.`patrocinador`,`f`.`valor`,`m`.`nomeMissao`,`m`.`dataInicio`,`m`.`dataFim`,`m`.`status`
;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_funcionariosnaempresa`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_funcionariosnaempresa` AS select `f`.`codFuncionario` AS `codFuncionario`,date_format(`f`.`dataNascimento`,'%d/%m/%Y') AS `dataNascimento`,timestampdiff(YEAR,`f`.`dataNascimento`,curdate()) AS `idade`,`f`.`nomeFuncionario` AS `nomeFuncionario`,`f`.`salarioAtual` AS `salarioAtual`,`c`.`nomeCargo` AS `nomeCargo`,(case `f`.`status` when 1 then 'Ainda na empresa' when 0 then 'Já demitido' else 'Desconhecido' end) AS `sta tusFuncionario` from (`funcionario` `f` join `cargo` `c` on((`f`.`cargo_codcargo` = `c`.`codcargo`)))
;

-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `vi_lancamentos`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `vi_lancamentos` AS select `l`.`codLancamentos` AS `codLancamentos`,`b`.`codbaseLancamento` AS `codbaseLancamento`,`l`.`dataLancamento` AS `dataLancamento`,`l`.`resultado` AS `resultado`,`b`.`paisbase` AS `paisbase`,`f`.`nomeFoguete` AS `nomeFoguete`,`m`.`nomeMissao` AS `nomeMissao` from (((`lancamentos` `l` join `foguete` `f` on((`l`.`Foguete_codFoguete` = `f`.`codFoguete`))) join `missoes` `m` on((`l`.`Missoes_codMissao` = `m`.`codMissao`))) join `baselancamento` `b`)
;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

-- MySQL Script generated by MySQL Workbench
-- Tue Apr 30 10:34:48 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reconocimientobbdd` DEFAULT CHARACTER SET utf8 ;
USE `reconocimientoBBDD` ;

-- -----------------------------------------------------
-- Table `mydb`.`FichaPersonal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reconocimientobbdd`.`FichaPersonal` (
  `nombre` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `dni` VARCHAR(9) NOT NULL,
  `telefono` INT(9) NOT NULL,
  `direccion` VARCHAR(50),
  `imagen` BLOB,
  `nivelConfidencialidad` INT NOT NULL,
  `email` VARCHAR(50) UNIQUE,
  PRIMARY KEY (`dni`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reconocimientobbdd`.`Usuario` (
  `nivelseguridad` INT NOT NULL,
  `nombreUsuario` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `puesto` VARCHAR(20),
  `FichaPersonal` VARCHAR(9),
  PRIMARY KEY (`nombreUsuario`),
  INDEX `fk_Usuario_FichaPersonal1_idx` (`FichaPersonal` ASC),
  CONSTRAINT `fk_Usuario_FichaPersonal1`
    FOREIGN KEY (`FichaPersonal`)
    REFERENCES `reconocimientobbdd`.`FichaPersonal` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`imagen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reconocimientobbdd`.`imagen` (
  `id` int,
  `imagen` BLOB NOT NULL,
  `cara` tinyint NOT NULL,
  `FichaPersonal` VARCHAR(9),
  PRIMARY KEY (`id`),
  INDEX `fk_imagen_FichaPersonal1_idx` (`FichaPersonal` ASC),
  CONSTRAINT `fk_imagen_FichaPersonal1`
    FOREIGN KEY (`FichaPersonal`)
    REFERENCES `reconocimientobbdd`.`FichaPersonal` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET GLOBAL time_zone='+1:00';

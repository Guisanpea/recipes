-- MySQL Script generated by MySQL Workbench
-- sáb 15 sep 2018 19:14:13 CEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema recipies
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `recipies` ;

-- -----------------------------------------------------
-- Schema recipies
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `recipies` DEFAULT CHARACTER SET utf8 ;
USE `recipies` ;

-- -----------------------------------------------------
-- Table `recipies`.`Level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`Level` ;

CREATE TABLE IF NOT EXISTS `recipies`.`Level` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`Recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`Recipe` ;

CREATE TABLE IF NOT EXISTS `recipies`.`Recipe` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `preparation` VARCHAR(1000) NULL,
  `public` TINYINT(1) NULL,
  `levelId` INT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_Recipe_Level1_idx` (`levelId` ASC),
  CONSTRAINT `fk_Recipe_Level1`
    FOREIGN KEY (`levelId`)
    REFERENCES `recipies`.`Level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`Category` ;

CREATE TABLE IF NOT EXISTS `recipies`.`Category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`Ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`Ingredient` ;

CREATE TABLE IF NOT EXISTS `recipies`.`Ingredient` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`RecipeHasIngredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`RecipeHasIngredient` ;

CREATE TABLE IF NOT EXISTS `recipies`.`RecipeHasIngredient` (
  `recipeId` INT NOT NULL,
  `IngredientId` INT NOT NULL,
  PRIMARY KEY (`recipeId`, `IngredientId`),
  INDEX `fk_Recipe_has_Ingredient_Ingredient1_idx` (`IngredientId` ASC),
  INDEX `fk_Recipe_has_Ingredient_Recipe_idx` (`recipeId` ASC),
  CONSTRAINT `fk_Recipe_has_Ingredient_Recipe`
    FOREIGN KEY (`recipeId`)
    REFERENCES `recipies`.`Recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_has_Ingredient_Ingredient1`
    FOREIGN KEY (`IngredientId`)
    REFERENCES `recipies`.`Ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`Experience`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`Experience` ;

CREATE TABLE IF NOT EXISTS `recipies`.`Experience` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`User` ;

CREATE TABLE IF NOT EXISTS `recipies`.`User` (
  `id` INT NOT NULL,
  `fullName` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `name` VARCHAR(45) NULL,
  `hashedPassword` VARCHAR(64) NULL,
  `lastRecipeAdded` DATETIME NULL,
  `experienceId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Experience1_idx` (`experienceId` ASC),
  CONSTRAINT `fk_User_Experience1`
    FOREIGN KEY (`experienceId`)
    REFERENCES `recipies`.`Experience` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`RecipeHasCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`RecipeHasCategory` ;

CREATE TABLE IF NOT EXISTS `recipies`.`RecipeHasCategory` (
  `recipeId` INT NOT NULL,
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`recipeId`, `categoryId`),
  INDEX `fk_Recipe_has_Category_Category1_idx` (`categoryId` ASC),
  INDEX `fk_Recipe_has_Category_Recipe1_idx` (`recipeId` ASC),
  CONSTRAINT `fk_Recipe_has_Category_Recipe1`
    FOREIGN KEY (`recipeId`)
    REFERENCES `recipies`.`Recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_has_Category_Category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `recipies`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipies`.`UserHasRecipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipies`.`UserHasRecipe` ;

CREATE TABLE IF NOT EXISTS `recipies`.`UserHasRecipe` (
  `userId` INT NOT NULL,
  `recipeId` INT NOT NULL,
  PRIMARY KEY (`userId`, `recipeId`),
  INDEX `fk_User_has_Recipe_Recipe1_idx` (`recipeId` ASC),
  INDEX `fk_User_has_Recipe_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_User_has_Recipe_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `recipies`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Recipe_Recipe1`
    FOREIGN KEY (`recipeId`)
    REFERENCES `recipies`.`Recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

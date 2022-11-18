DROP TABLE IF EXISTS secret_app_notes.secret_password;
DROP TABLE IF EXISTS secret_app_notes.secret_note;

CREATE TABLE IF NOT EXISTS `secret_app_notes`.`secret_password` (
  `secret_password_id` VARCHAR(45) NOT NULL,
  `secret_password_name` VARCHAR(100) NULL,
  `secret_password_username` VARCHAR(100) NULL,
  `secret_password_password` VARCHAR(255) NULL,
  `secret_password_URI` VARCHAR(200) NULL,
  `user_uid_fk` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`secret_password_id`),
  INDEX `fk_secret_password_user_idx` (`user_uid_fk` ASC) VISIBLE,
  CONSTRAINT `fk_secret_password_user`
    FOREIGN KEY (`user_uid_fk`)
    REFERENCES `secret_app_notes`.`user` (`user_uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `secret_app_notes`.`secret_note` (
  `secret_note_id` VARCHAR(45) NOT NULL,
  `secret_note_name` VARCHAR(100) NULL,
  `secret_note_notes` TEXT NULL,
  `user_uid_fk` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`secret_note_id`),
  INDEX `fk_secret_note_user1_idx` (`user_uid_fk` ASC) VISIBLE,
  CONSTRAINT `fk_secret_note_user1`
    FOREIGN KEY (`user_uid_fk`)
    REFERENCES `secret_app_notes`.`user` (`user_uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

DROP PROCEDURE IF EXISTS SP_insert_login;
DROP PROCEDURE IF EXISTS SP_delete_all;
DROP PROCEDURE IF EXISTS SP_delete_login;
DROP PROCEDURE IF EXISTS SP_delete_note;
DROP PROCEDURE IF EXISTS SP_insert_note;
DROP PROCEDURE IF EXISTS SP_select_login_1;
DROP PROCEDURE IF EXISTS SP_select_notes;
DROP PROCEDURE IF EXISTS SP_update_login;
DROP PROCEDURE IF EXISTS SP_update_note;



DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_insert_login`(IN in_secret_password_id VARCHAR(45),
        IN in_secret_password_name VARCHAR(100),
        IN in_secret_password_username VARCHAR(100),
        IN in_secret_password_password VARCHAR(255),
        IN in_secret_password_URI VARCHAR(200),
        IN in_user_uid_fk VARCHAR(45))
    BEGIN
    insert into secret_password(secret_password_id,secret_password_name,secret_password_username,secret_password_password,secret_password_URI,user_uid_fk) 
    values (in_secret_password_id,in_secret_password_name,in_secret_password_username,in_secret_password_password,in_secret_password_URI,in_user_uid_fk);
    END$$
DELIMITER ;


DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_delete_login` ( IN in_secret_password_id VARCHAR(45))
    BEGIN
    DELETE FROM secret_password WHERE secret_password_id=in_secret_password_id;
    END$$
DELIMITER ;


DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_update_login`(IN in_secret_password_id VARCHAR(45),
        IN in_secret_password_name VARCHAR(100),
        IN in_secret_password_username VARCHAR(100),
        IN in_secret_password_password VARCHAR(255),
        IN in_secret_password_URI VARCHAR(200))
    BEGIN
        UPDATE secret_password SET secret_password_name = in_secret_password_name, secret_password_username = in_secret_password_username, 
        secret_password_password = in_secret_password_password, secret_password_URI = in_secret_password_URI
        WHERE secret_password_id = in_secret_password_id;
    END$$
DELIMITER ;


DELIMITER $$
CREATE 
    PROCEDURE `secret_app_notes`.`SP_select_login_1`(IN in_StartIndex INT,IN in_Count INT, IN in_user_uid_fk VARCHAR (45))
    BEGIN
      DECLARE LowerBound INT;
      DECLARE UpperBound INT;
      DECLARE rownum INT;
      SET LowerBound = ((in_StartIndex - 1) * in_Count) + 1;
      SET UpperBound = ((in_StartIndex - 1) * in_Count) + in_Count;
      

      SELECT secret_password_id as id, secret_password_name as name, secret_password_username as username, secret_password_password as password, secret_password_URI as uri,
      (SELECT count(*) FROM secret_password where user_uid_fk = in_user_uid_fk) as contador
        FROM (SELECT *, @rownum := @rownum + 1 AS ran
                FROM (SELECT sc.secret_password_id, sc.secret_password_name, sc.secret_password_username, sc.secret_password_password, sc.secret_password_URI, sc.user_uid_fk
                        FROM secret_password as sc where sc.user_uid_fk = in_user_uid_fk) d, (SELECT @rownum := 0) r ) m
      WHERE ran >= LowerBound and ran <= UpperBound ;
    END$$
DELIMITER ;

------------------- Secret Notes---------------------------

DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_insert_note`(IN in_secret_note_id VARCHAR(45),
        IN in_secret_note_name VARCHAR(100),
        IN in_secret_note_notes TEXT,
        IN in_user_uid_fk VARCHAR(45))
    BEGIN
    insert into secret_note(secret_note_id,secret_note_name,secret_note_notes,user_uid_fk) 
    values (in_secret_note_id,in_secret_note_name,in_secret_note_notes,in_user_uid_fk);
    END$$
DELIMITER ;


DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_delete_note` ( IN in_secret_note_id VARCHAR(45))
    BEGIN
    DELETE FROM secret_note WHERE secret_note_id=in_secret_note_id;
    END$$
DELIMITER ;

CALL SP_delete_note('1');

DELIMITER $$
Create 
    PROCEDURE `secret_app_notes`.`SP_update_note`(IN in_secret_note_id VARCHAR(45),
        IN in_secret_note_name VARCHAR(100),
        IN in_secret_note_notes TEXT)
    BEGIN
        UPDATE secret_note SET secret_note_name = in_secret_note_name, secret_note_notes = in_secret_note_notes
        WHERE secret_note_id = in_secret_note_id;
    END$$
DELIMITER ;


DELIMITER $$
CREATE 
    PROCEDURE `secret_app_notes`.`SP_select_notes`(IN in_StartIndex INT,IN in_Count INT, IN in_user_uid_fk VARCHAR (45))
    BEGIN
      DECLARE LowerBound INT;
      DECLARE UpperBound INT;
      DECLARE rownum INT;
      SET LowerBound = ((in_StartIndex - 1) * in_Count) + 1;
      SET UpperBound = ((in_StartIndex - 1) * in_Count) + in_Count;
      

      SELECT secret_note_id as id, secret_note_name as name, secret_note_notes as notes,
      (SELECT count(*) FROM secret_note where user_uid_fk = in_user_uid_fk) as contador
        FROM (SELECT *, @rownum := @rownum + 1 AS ran
                FROM (SELECT sc.secret_note_id, sc.secret_note_name, sc.secret_note_notes, sc.user_uid_fk
                        FROM secret_note as sc where user_uid_fk = in_user_uid_fk) d, (SELECT @rownum := 0) r ) m
      WHERE ran >= LowerBound and ran <= UpperBound;
    END$$
DELIMITER ;

DELIMITER $$
CREATE
    PROCEDURE `secret_app_notes`.`SP_delete_all`( IN in_secret_user VARCHAR(45))
    BEGIN
        DELETE FROM secret_password WHERE user_uid_fk=in_secret_user;
        DELETE FROM secret_note WHERE user_uid_fk=in_secret_user;
    END$$
DELIMITER ;
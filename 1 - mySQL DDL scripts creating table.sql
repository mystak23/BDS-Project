 CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
 
 CREATE TABLE IF NOT EXISTS `mydb`.`tuning` (
  `tuning_id` INT NOT NULL AUTO_INCREMENT,
  `mm_lowering` SMALLINT NOT NULL,
  `has_sport_intake` TINYINT NOT NULL,
  `has_sport_seats` TINYINT NOT NULL,
  `has_modified_exhaust` TINYINT NOT NULL,
  `has_ceramic_clutch` TINYINT NOT NULL,
  `has_hydro_handbrake` TINYINT NOT NULL,
  `has_sport_suspention` TINYINT NOT NULL,
  `has_wheel_spacers` TINYINT NOT NULL,
  PRIMARY KEY (`tuning_id`),
  UNIQUE(`tuning_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`name` (
  `name_id` INT NOT NULL AUTO_INCREMENT,
  `title_before_name` VARCHAR(30) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `title_after_name` VARCHAR(30) NULL,
  PRIMARY KEY (`name_id`),
  UNIQUE(`tuning_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`insurance` (
  `insurance_id` INT NOT NULL AUTO_INCREMENT,
  `extra_price` INT NOT NULL,
  `theft` TINYINT NOT NULL,
  `accident` TINYINT NOT NULL,
  `rubbing` TINYINT NOT NULL,
  `lost_keys` TINYINT NOT NULL,
  PRIMARY KEY (`insurance_id`),
  UNIQUE (`insurance_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`equipment` (
  `equipment_id` INT NOT NULL AUTO_INCREMENT,
  `number_of_doors` INT NOT NULL,
  `interior_material` VARCHAR(45) NOT NULL,
  `has_automatic_transmition` TINYINT NOT NULL,
  `has_ABS` TINYINT NOT NULL,
  `has_EDS` TINYINT NOT NULL,
  `has_parking_assistent` TINYINT NOT NULL,
  `has_tempomat` TINYINT NOT NULL,
  `has_parking_sensors` TINYINT NOT NULL,
  `has_airbags` TINYINT NOT NULL,
  `has_power_steering_wheel` TINYINT NOT NULL,
  `has_air_conditioning` TINYINT NOT NULL,
  `has_tuning` TINYINT NOT NULL,
  `has_usb` TINYINT NOT NULL,
  PRIMARY KEY (`equipment_id`),
  UNIQUE (`equipment_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`engine` (
  `engine_id` INT NOT NULL AUTO_INCREMENT,
  `ccm_volume` SMALLINT NOT NULL,
  `kW_performance` SMALLINT NOT NULL,
  `Nm_torque` SMALLINT NOT NULL,
  `has_four_wheel_drive` TINYINT NOT NULL,
  `has_turbo` TINYINT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`engine_id`),
  UNIQUE (`engine_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `phone_number` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `data_box` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE (`contact_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`credential` (
  `credential_id` INT NOT NULL AUTO_INCREMENT,
  `ID_card_id` INT NOT NULL,
  `driving_licence_id` INT NOT NULL,
  `passport_id` INT NULL,
  PRIMARY KEY (`credential_id`),
  UNIQUE (`credential_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`brand` (
  `brand_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `year` SMALLINT NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `capacity` SMALLINT NOT NULL,
  `concern` VARCHAR(45) NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`brand_id`),
  UNIQUE (`brand_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(10) NOT NULL,
  `is_our_workplace` TINYINT NOT NULL,
  `is_customers_address` TINYINT NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE (`address_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`bank` (
  `bank_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `residence` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bank_id`),
  UNIQUE (`bank_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `date_of_birth` DATE NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  `credential_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  `name_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `credential_id`, `address_id`, `contact_id`, `name_id`),
  UNIQUE (`customer_id`, `credential_id`, `address_id`, `contact_id`, `name_id`),
  CONSTRAINT `fk_customer_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `mydb`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_credential1`
    FOREIGN KEY (`credential_id`)
    REFERENCES `mydb`.`credential` (`credential_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `mydb`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_name1`
    FOREIGN KEY (`name_id`)
    REFERENCES `mydb`.`name` (`name_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `wage` SMALLINT NOT NULL,
  `employed_since` DATE NOT NULL,
  `place_of_employment` VARCHAR(45) NOT NULL,
  `name_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`, `address_id`, `contact_id`, `name_id`),
  UNIQUE (`employee_id`, `address_id`, `contact_id`, `name_id`),
  CONSTRAINT `fk_employee_name1`
    FOREIGN KEY (`name_id`)
    REFERENCES `mydb`.`name` (`name_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `mydb`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `mydb`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`customer_has_bank` (
  `customer_id` INT NOT NULL,
  `bank_id` INT NOT NULL,
  `credit_card_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bank_id`, `customer_id`),
  UNIQUE (`bank_id`, `customer_id`),
  CONSTRAINT `fk_customer_has_bank1_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_bank1_bank1`
    FOREIGN KEY (`bank_id`)
    REFERENCES `mydb`.`bank` (`bank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `fuel` VARCHAR(45) NOT NULL,
  `bodywork` VARCHAR(45) NOT NULL,
  `mileage` SMALLINT NOT NULL,
  `has_winter_tyres` TINYINT NOT NULL,
  `tuning_id` INT NOT NULL,
  `engine_id` INT NOT NULL,
  PRIMARY KEY (`status_id`, `engine_id`),
  UNIQUE (`status_id`, `engine_id`),
  CONSTRAINT `fk_status_tuning1`
    FOREIGN KEY (`tuning_id`)
    REFERENCES `mydb`.`tuning` (`tuning_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_engine1`
    FOREIGN KEY (`engine_id`)
    REFERENCES `mydb`.`engine` (`engine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`car` (
  `car_id` INT NOT NULL AUTO_INCREMENT,
  `licence_plate` VARCHAR(45) NOT NULL,
  `tank_condition` SMALLINT NOT NULL,
  `is_available` TINYINT NOT NULL,
  `status_id` INT NOT NULL,
  `brand_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  PRIMARY KEY (`car_id`, `status_id`, `brand_id`, `equipment_id`),
  UNIQUE (`car_id`, `status_id`, `brand_id`, `equipment_id`),
  CONSTRAINT `fk_car_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `mydb`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_brand1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `mydb`.`brand` (`brand_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_equipment1`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `mydb`.`equipment` (`equipment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`rent` (
  `rent_id` INT NOT NULL AUTO_INCREMENT,
  `price_of_rent` SMALLINT NOT NULL,
  `date_of_pickup` DATE NOT NULL,
  `date_of_return` DATE NOT NULL,
  `customer_id` INT NOT NULL,
  `pickup_address_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `car_id` INT NOT NULL,
  `insurance_id` INT NOT NULL,
  PRIMARY KEY (`rent_id`, `customer_id`, `employee_id`, `pickup_address_id`, `car_id`),
  UNIQUE (`rent_id`, `customer_id`, `employee_id`, `pickup_address_id`, `car_id`),
  CONSTRAINT `fk_rent_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rent_address1`
    FOREIGN KEY (`pickup_address_id`)
    REFERENCES `mydb`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rent_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mydb`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rent_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `mydb`.`car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rent_insurance1`
    FOREIGN KEY (`insurance_id`)
    REFERENCES `mydb`.`insurance` (`insurance_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
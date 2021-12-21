CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS public.tuning (
  tuning_id BIGSERIAL NOT NULL,
  mm_lowering SMALLINT NOT NULL,
  has_sport_intake BOOLEAN NOT NULL,
  has_sport_seats BOOLEAN NOT NULL,
  has_modified_exhaust BOOLEAN NOT NULL,
  has_ceramic_clutch BOOLEAN NOT NULL,
  has_hydro_handbrake BOOLEAN NOT NULL,
  has_sport_suspention BOOLEAN NOT NULL,
  has_wheel_spacers BOOLEAN NOT NULL,
  PRIMARY KEY (tuning_id));
  
ALTER TABLE public.tuning OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.name (
  name_id BIGSERIAL NOT NULL,
  title_before_name VARCHAR(30) NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  title_after_name VARCHAR(30) NULL,
  PRIMARY KEY (name_id));

ALTER TABLE public.name OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.insurance (
  insurance_id BIGSERIAL NOT NULL,
  extra_price INT NOT NULL,
  theft BOOLEAN NOT NULL,
  accident BOOLEAN NOT NULL,
  rubbing BOOLEAN NOT NULL,
  lost_keys BOOLEAN NOT NULL,
  PRIMARY KEY (insurance_id));
  
ALTER TABLE public.insurance OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.equipment (
  equipment_id BIGSERIAL NOT NULL,
  number_of_doors INT NOT NULL,
  interior_material VARCHAR(45) NOT NULL,
  has_automatic_transmition BOOLEAN NOT NULL,
  has_ABS BOOLEAN NOT NULL,
  has_EDS BOOLEAN NOT NULL,
  has_parking_assistent BOOLEAN NOT NULL,
  has_tempomat BOOLEAN NOT NULL,
  has_parking_sensors BOOLEAN NOT NULL,
  has_airbags BOOLEAN NOT NULL,
  has_power_steering_wheel BOOLEAN NOT NULL,
  has_air_conditioning BOOLEAN NOT NULL,
  has_tuning BOOLEAN NOT NULL,
  has_usb BOOLEAN NOT NULL,
  PRIMARY KEY (equipment_id));
  
ALTER TABLE public.equipment OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.engine (
  engine_id BIGSERIAL NOT NULL,
  ccm_volume SMALLINT NOT NULL,
  kW_performance SMALLINT NOT NULL,
  Nm_torque SMALLINT NOT NULL,
  has_four_wheel_drive BOOLEAN NOT NULL,
  has_turbo BOOLEAN NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (engine_id));

ALTER TABLE public.engine OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.contact (
  contact_id BIGSERIAL NOT NULL,
  phone_number INT NOT NULL,
  email VARCHAR(45) NOT NULL,
  data_box VARCHAR(45) NULL,
  PRIMARY KEY (contact_id));

ALTER TABLE public.contact OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.credential (
  credential_id BIGSERIAL NOT NULL,
  ID_card_id INT NOT NULL,
  driving_licence_id INT NOT NULL,
  passport_id INT NULL,
  PRIMARY KEY (credential_id));

ALTER TABLE public.credential OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.brand (
  brand_id BIGSERIAL NOT NULL,
  name VARCHAR(45) NOT NULL,
  model VARCHAR(45) NOT NULL,
  year SMALLINT NOT NULL,
  color VARCHAR(45) NOT NULL,
  capacity SMALLINT NOT NULL,
  concern VARCHAR(45) NULL,
  country VARCHAR(45) NOT NULL,
  PRIMARY KEY (brand_id));
  
ALTER TABLE public.brand OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.address (
	  address_id BIGSERIAL NOT NULL,
  city VARCHAR(45) NOT NULL,
  street VARCHAR(45) NOT NULL,
  house_number VARCHAR(45) NOT NULL,
  zip_code VARCHAR(10) NOT NULL,
  is_our_workplace BOOLEAN NOT NULL,
  is_customers_address BOOLEAN NOT NULL,
  PRIMARY KEY (address_id));

ALTER TABLE public.address OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.bank(
  bank_id BIGSERIAL NOT NULL,
  name VARCHAR(45) NOT NULL,
  nationality VARCHAR(45) NOT NULL,
  residence VARCHAR(45) NOT NULL,
  PRIMARY KEY (bank_id));
  
ALTER TABLE public.bank OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.customer (
  customer_id BIGSERIAL NOT NULL,
  date_of_birth DATE NOT NULL,
  nationality VARCHAR(45) NOT NULL,
  address_id BIGSERIAL NOT NULL,
  credential_id BIGSERIAL NOT NULL,
  contact_id BIGSERIAL NOT NULL,
  name_id BIGSERIAL NOT NULL,
  PRIMARY KEY (customer_id, credential_id, address_id, contact_id, name_id),
  UNIQUE (customer_id),
  CONSTRAINT fk_customer_address1
    FOREIGN KEY (address_id)
    REFERENCES address (address_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_customer_credential1
    FOREIGN KEY (credential_id)
    REFERENCES credential (credential_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_customer_contact1
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_customer_name1
    FOREIGN KEY (name_id)
    REFERENCES name (name_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE public.customer OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.employee (
  employee_id BIGSERIAL NOT NULL,
  wage SMALLINT NOT NULL,
  employed_since DATE NOT NULL,
  place_of_employment VARCHAR(45) NOT NULL,
  name_id BIGSERIAL NOT NULL,
  contact_id BIGSERIAL NOT NULL,
  address_id BIGSERIAL NOT NULL,
  PRIMARY KEY (employee_id, address_id, contact_id, name_id),
  UNIQUE (employee_id),
  CONSTRAINT fk_employee_name
    FOREIGN KEY (name_id)
    REFERENCES name (name_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_employee_contact1
    FOREIGN KEY (contact_id)
    REFERENCES contact (contact_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_employee_address1
    FOREIGN KEY (address_id)
    REFERENCES address (address_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE public.employee OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.customer_has_bank (
  customer_id BIGSERIAL NOT NULL,
  bank_id BIGSERIAL NOT NULL,
  credit_card_number VARCHAR(45) NOT NULL,
  PRIMARY KEY (bank_id, customer_id),
  CONSTRAINT fk_customer_has_bank1_customer1
    FOREIGN KEY (customer_id)
    REFERENCES customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_customer_has_bank1_bank1
    FOREIGN KEY (bank_id)
    REFERENCES bank (bank_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE public.customer_has_bank OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.status (
  status_id BIGSERIAL NOT NULL,
  fuel VARCHAR(45) NOT NULL,
  bodywork VARCHAR(45) NOT NULL,
  mileage SMALLINT NOT NULL,
  has_winter_tyres BOOLEAN NOT NULL,
  tuning_id BIGSERIAL NOT NULL,
  engine_id BIGSERIAL NOT NULL,
  PRIMARY KEY (status_id, engine_id),
  UNIQUE (status_id),
  CONSTRAINT fk_status_tuning1
    FOREIGN KEY (tuning_id)
    REFERENCES tuning (tuning_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_status_engine1
    FOREIGN KEY (engine_id)
    REFERENCES engine (engine_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
ALTER TABLE public.status OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.car (
	car_id BIGSERIAL NOT NULL,
	licence_plate VARCHAR(45) NOT NULL,
	tank_condition SMALLINT NOT NULL,
	is_available BOOLEAN NOT NULL,
	status_id BIGSERIAL NOT NULL,
	brand_id BIGSERIAL NOT NULL,
	equipment_id BIGSERIAL NOT NULL,
	PRIMARY KEY (car_id, status_id, brand_id, equipment_id),
	UNIQUE (car_id),
	UNIQUE  (licence_plate),
	CONSTRAINT fk_car_status1
		FOREIGN KEY (status_id)
		REFERENCES status (status_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT fk_car_brand1
		FOREIGN KEY (brand_id)
		REFERENCES brand (brand_id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_car_equipment1
		FOREIGN KEY (equipment_id)
		REFERENCES equipment (equipment_id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);
		
ALTER TABLE public.car OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.rent (
  rent_id BIGSERIAL NOT NULL,
  price_of_rent SMALLINT NOT NULL,
  date_of_pickup DATE NOT NULL,
  date_of_return DATE NOT NULL,
  customer_id BIGSERIAL NOT NULL,
  pickup_address_id BIGSERIAL NOT NULL,
  employee_id BIGSERIAL NOT NULL,
  car_id BIGSERIAL NOT NULL,
  insurance_id BIGSERIAL NOT NULL,
  PRIMARY KEY (rent_id, customer_id, employee_id, pickup_address_id, car_id),
  CONSTRAINT fk_rent_customer1
    FOREIGN KEY (customer_id)
    REFERENCES customer (customer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rent_address1
    FOREIGN KEY (pickup_address_id)
    REFERENCES address (address_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rent_employee1
    FOREIGN KEY (employee_id)
    REFERENCES employee (employee_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rent_car1
    FOREIGN KEY (car_id)
    REFERENCES car (car_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rent_insurance1
    FOREIGN KEY (insurance_id)
    REFERENCES insurance (insurance_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE public.rent OWNER TO postgres;

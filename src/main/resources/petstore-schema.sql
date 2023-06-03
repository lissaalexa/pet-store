DROP TABLE IF EXISTS pet_store;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS customer;

CREATE TABLE pet_store (
	pet_store_id INT PRIMARY KEY,
	pet_store_name VARCHAR(255),
	pet_store_address VARCHAR(255),
	pet_store_city VARCHAR(255),
	pet_store_state VARCHAR(60),
	pet_store_zip VARCHAR(20),
	pet_store_phone VARCHAR(20)
);

CREATE TABLE employee (
  employee_id INT PRIMARY KEY,
  pet_store_id INT,
  employee_first_name VARCHAR(255),
  employee_last_name VARCHAR(255),
  employee_phone VARCHAR(20),
  employee_job_title VARCHAR(255),
  FOREIGN KEY (pet_store_id) REFERENCES pet_store(pet_store_id)
);

CREATE TABLE customer (
  customer_id INT PRIMARY KEY,
  customer_first_name VARCHAR(255),
  customer_last_name VARCHAR(255),
  customer_email VARCHAR(255)
);

INSERT INTO pet_store (pet_store_id, pet_store_name, pet_store_address, pet_store_city, pet_store_state, pet_store_zip, pet_store_phone)
	VALUES (1, 'Pet Store', '123 Front St', 'Toronto', 'Ontario', '123', '416-123-4567');
INSERT INTO pet_store (pet_store_id, pet_store_name, pet_store_address, pet_store_city, pet_store_state, pet_store_zip, pet_store_phone)
    VALUES (2, 'Store for Pets', '987 First St', 'Red Deer', 'Alberta', '456', '780-456-7890');
INSERT INTO employee (employee_id, pet_store_id, employee_first_name, employee_last_name, employee_phone, employee_job_title)
	VALUES (1, 2, 'John', 'Smith', '780-456-7890', 'Manager');
INSERT INTO customer (customer_id, customer_first_name, customer_last_name, customer_email)
	VALUES (10, 'Jill', 'Eba', 'jilleba@gmail.com');
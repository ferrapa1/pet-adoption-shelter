-- Create all tables for the application schema

CREATE TABLE address (
    id UUID PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    plz INT NOT NULL,
    city VARCHAR(255) NOT NULL
);

CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    user_type VARCHAR(31) NOT NULL,
    address_id UUID,
    phone_number VARCHAR(16),
    email VARCHAR(255) UNIQUE,
    has_garden BOOLEAN DEFAULT FALSE,
    has_children BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE pet (
    id UUID PRIMARY KEY,
    shelter_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(4000),
    date_of_birth DATE NOT NULL,
    species VARCHAR(255),
    breed_name VARCHAR(255),
    adoption_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (shelter_id) REFERENCES app_user(id)
);

CREATE TABLE pet_picture (
    id UUID PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    pet_id UUID,
    FOREIGN KEY (pet_id) REFERENCES pet(id)
);

CREATE TABLE adoption_request (
  id UUID PRIMARY KEY,
  adopter_id UUID NOT NULL,
  pet_id UUID NOT NULL,
  created_at TIMESTAMP NOT NULL,
  request_status VARCHAR(255) NOT NULL,
  FOREIGN KEY (adopter_id) REFERENCES app_user(id),
  FOREIGN KEY (pet_id) REFERENCES pet(id)
);

CREATE TABLE comment (
    id UUID PRIMARY KEY,
    author_id UUID NOT NULL,
    content VARCHAR(1000) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    parent_id UUID,
    pet_id UUID,
    adoption_id UUID,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (adoption_id) REFERENCES adoption_request(id),
    FOREIGN KEY (author_id) REFERENCES app_user(id),
    FOREIGN KEY (parent_id) REFERENCES comment(id)
);
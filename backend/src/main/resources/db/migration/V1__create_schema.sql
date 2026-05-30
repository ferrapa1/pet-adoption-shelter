-- Create all tables for the application schema

CREATE TABLE address (
    id UUID PRIMARY KEY,
    street VARCHAR(255),
    house_number VARCHAR(255),
    plz INT,
    city VARCHAR(255)
);

CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    user_id VARCHAR(20) UNIQUE NOT NULL,
    user_type VARCHAR(31),
    address_id UUID,
    phone_number VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    has_garden BOOLEAN,
    has_children BOOLEAN,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE pet (
    id UUID PRIMARY KEY,
    shelter_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
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

CREATE TABLE pet_comment (
    id UUID PRIMARY KEY,
    author_id UUID NOT NULL,
    content VARCHAR(1000) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    parent_id UUID,
    pet_id UUID,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (author_id) REFERENCES app_user(id)
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

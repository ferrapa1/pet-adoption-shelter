INSERT INTO ADDRESS(ID, STREET, HOUSE_NUMBER, PLZ, CITY)
VALUES('40000000-0000-0000-0000-000000000001', 'Bahnhofstrasse', '1A', 8001, 'Zürich');

-- Shelter
INSERT INTO APP_USER (ID, USER_TYPE, USER_ID, ADDRESS_ID, PHONE_NUMBER, EMAIL, HAS_GARDEN, HAS_CHILDREN)
VALUES ('10000000-0000-0000-0000-000000000001', 'SHELTER', 'SHELTER_1', '40000000-0000-0000-0000-000000000001', '+41 76 123 45 67', 'shelter_1@mail.com', FALSE, FALSE);

-- Adopter
INSERT INTO APP_USER (ID, USER_TYPE, USER_ID, ADDRESS_ID, PHONE_NUMBER, EMAIL, HAS_GARDEN, HAS_CHILDREN)
VALUES ('20000000-0000-0000-0000-000000000001', 'ADOPTER', 'ADOPTER_1', '40000000-0000-0000-0000-000000000001', '+41 76 123 45 67', 'adopter_1@mail.com', TRUE, TRUE);

-- =================================================================
--  PET 1: Fido (Golden Retriever)
-- =================================================================
-- Pet ID: 00000000-0000-0000-0000-000000000001
-- Shelter ID: 10000000-0000-0000-0000-000000000001 (Assumed Shelter)
INSERT INTO PET (id, shelter_id, name, date_of_birth, species, breed_name, adoption_status)
VALUES ('00000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', 'Fido', '2021-03-15', 'Dog', 'Golden Retriever', 'AVAILABLE');

-- Pictures for Fido
INSERT INTO PET_PICTURE (id, url, pet_id)
VALUES ('20000000-0000-0000-0000-000000000001', 'https://example.com/fido1.jpg', '00000000-0000-0000-0000-000000000001'),
       ('20000000-0000-0000-0000-000000000002', 'https://example.com/fido2.jpg', '00000000-0000-0000-0000-000000000001');

-- Comments for Fido
-- Comment ID: 30000000-0000-0000-0000-000000000001 (Top-level)
-- Author ID: 20000000-0000-0000-0000-000000000001 (Assumed Adopter)
INSERT INTO PET_COMMENT (id, author_id, content, timestamp, pet_id, parent_id)
VALUES ('30000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000001', 'How is he with children?', '2024-05-26 10:00:00', '00000000-0000-0000-0000-000000000001', NULL);

-- Reply to the above comment
-- Comment ID: 30000000-0000-0000-0000-000000000002 (Reply)
-- Author ID: 10000000-0000-0000-0000-000000000001 (Shelter)
INSERT INTO PET_COMMENT (id, author_id, content, timestamp, pet_id, parent_id)
VALUES ('30000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000001', 'He is great with kids! Very gentle.', '2024-05-26 11:30:00', '00000000-0000-0000-0000-000000000001', '30000000-0000-0000-0000-000000000001');


-- =================================================================
--  PET 2: Whiskers (Siamese)
-- =================================================================
-- Pet ID: 00000000-0000-0000-0000-000000000002
-- Shelter ID: 10000000-0000-0000-0000-000000000001 (Assumed Shelter)
INSERT INTO PET (id, shelter_id, name, date_of_birth, species, breed_name, adoption_status)
VALUES ('00000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000001', 'Whiskers', '2022-08-01', 'Cat', 'Siamese', 'AVAILABLE');

-- Pictures for Whiskers
INSERT INTO PET_PICTURE (id, url, pet_id)
VALUES ('20000000-0000-0000-0000-000000000003', 'https://example.com/whiskers1.jpg', '00000000-0000-0000-0000-000000000002');


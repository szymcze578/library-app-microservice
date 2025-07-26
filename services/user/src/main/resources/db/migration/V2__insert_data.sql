-- Insert roles
INSERT INTO roles (type) VALUES
                             ('ADMIN'),
                             ('LIBRARIAN'),
                             ('USER');

-- Insert addresses
INSERT INTO addresses (street, city, home) VALUES
                                               ('Main St', 'Warsaw', '12A'),       -- ID 1
                                               ('Oak Street', 'Kraków', '7B'),     -- ID 2
                                               ('Elm Street', 'Gdańsk', '5C');     -- ID 3

-- Insert users one-by-one using SELECTs to resolve foreign keys
INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'admin1', 'admin1@example.com', 'Anna', 'Nowak', 'hashedpass1',
           true, '1990-01-01',
           (SELECT id FROM roles WHERE type = 'ADMIN'),
           (SELECT id FROM addresses WHERE street = 'Main St' AND city = 'Warsaw' AND home = '12A')
       );

INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'librarian1', 'librarian1@example.com', 'Jan', 'Kowalski', 'hashedpass2',
           true, '1985-06-15',
           (SELECT id FROM roles WHERE type = 'LIBRARIAN'),
           (SELECT id FROM addresses WHERE street = 'Oak Street' AND city = 'Kraków' AND home = '7B')
       );

INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'user1', 'user1@example.com', 'Ewa', 'Wiśniewska', 'hashedpass3',
           true, '2000-12-30',
           (SELECT id FROM roles WHERE type = 'USER'),
           (SELECT id FROM addresses WHERE street = 'Elm Street' AND city = 'Gdańsk' AND home = '5C')
       );

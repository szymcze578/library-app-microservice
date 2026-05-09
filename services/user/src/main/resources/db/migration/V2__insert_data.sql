-- =====================================================
-- V2: Seed test users with roles and addresses
--
-- Test credentials (BCrypt strength 10, $2a$ format):
--   admin1     / Admin123!
--   librarian1 / Librarian123!
--   user1      / User123!
--
-- IMPORTANT: hashes below are placeholders. Generate real ones
-- locally with `new BCryptPasswordEncoder().encode("...")` and
-- replace before running. BCrypt produces a different hash each
-- time but all are valid for the same plaintext.
-- =====================================================

-- Roles
INSERT INTO roles (type) VALUES
                             ('ADMIN'),
                             ('LIBRARIAN'),
                             ('USER');

-- Addresses
INSERT INTO addresses (street, city, home) VALUES
                                               ('Main Street', 'Warsaw', '12A'),
                                               ('Oak Street',  'Kraków', '7B'),
                                               ('Elm Street',  'Gdańsk', '5C');

-- Users — FKs resolved via SELECT to avoid hardcoded IDs
INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'admin1',
           'admin1@example.com',
           'Anna',
           'Nowak',
           -- plaintext: Admin123!
           '$2a$12$5qhaokFrAjHsXIh1iyKSZeBPXe/e25cUmIn8uzJHVxcRUGTHwjFau',
           true,
           '1990-01-01',
           (SELECT id FROM roles     WHERE type = 'ADMIN'),
           (SELECT id FROM addresses WHERE street = 'Main Street' AND city = 'Warsaw' AND home = '12A')
       );

INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'librarian1',
           'librarian1@example.com',
           'Jan',
           'Kowalski',
           -- plaintext: Librarian123!
           '$2a$12$RnARF4BK.AC2VONWs7qCxOFFQ93bhAb29wnJkXGOEVJYGiqaAEFzS',
           true,
           '1985-06-15',
           (SELECT id FROM roles     WHERE type = 'LIBRARIAN'),
           (SELECT id FROM addresses WHERE street = 'Oak Street'  AND city = 'Kraków' AND home = '7B')
       );

INSERT INTO users (
    username, email, first_name, last_name, password,
    is_active, birth_date, role_id, address_id
)
VALUES (
           'user1',
           'user1@example.com',
           'Ewa',
           'Wiśniewska',
           -- plaintext: User123!
           '$2a$12$MmuKlzatkCChBPHJb2d0xuqeyHNG3oHAov2LV0NJsuDBjHEfo4dUK',
           true,
           '2000-12-30',
           (SELECT id FROM roles     WHERE type = 'USER'),
           (SELECT id FROM addresses WHERE street = 'Elm Street'  AND city = 'Gdańsk' AND home = '5C')
       );
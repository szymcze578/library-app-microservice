-- Insert data into publication for books
INSERT INTO publication (title, year, publisher) VALUES
                                                     ('Effective Java', '2018', 'Addison-Wesley'),
                                                     ('Clean Code', '2008', 'Prentice Hall');

-- Insert into book using currval to retrieve publication IDs
INSERT INTO book (id, author, pages, isbn) VALUES
                                               ((SELECT id FROM publication WHERE title = 'Effective Java'), 'Joshua Bloch', 416, '9780134685991'),
                                               ((SELECT id FROM publication WHERE title = 'Clean Code'), 'Robert C. Martin', 464, '9780132350884');

-- Insert data into publication for magazines
INSERT INTO publication (title, year, publisher) VALUES
                                                     ('National Geographic', '2023', 'National Geographic Partners'),
                                                     ('The Economist', '2023', 'The Economist Group');

-- Insert into magazine using matching publication IDs
INSERT INTO magazine (id, month_day, language) VALUES
                                                   ((SELECT id FROM publication WHERE title = 'National Geographic'), 'July 15', 'English'),
                                                   ((SELECT id FROM publication WHERE title = 'The Economist'), 'August 10', 'English');
INSERT INTO publication (
    type, title, publication_year, publisher, language, category, description,
    author, pages, isbn
) VALUES
      ('BOOK', 'The Great Gatsby', 1925, 'Scribner', 'en', 'Fiction',
       'F. Scott Fitzgerald''s masterpiece about the American Dream, set in the Jazz Age on Long Island.',
       'F. Scott Fitzgerald', 180, '9780743273565'),

      ('BOOK', 'Nineteen Eighty-Four', 1949, 'Signet Classic', 'en', 'Dystopian Fiction',
       'George Orwell''s dystopian social science fiction novel depicting a totalitarian regime.',
       'George Orwell', 328, '9780451524935'),

      ('BOOK', 'To Kill a Mockingbird', 1960, 'J.B. Lippincott & Co.', 'en', 'Fiction',
       'Harper Lee''s Pulitzer Prize-winning novel exploring racial injustice in the American South.',
       'Harper Lee', 281, '9780061120084'),

      ('BOOK', 'Pride and Prejudice', 1813, 'T. Egerton', 'en', 'Romance',
       'Jane Austen''s novel of manners following the Bennet sisters in Georgian England.',
       'Jane Austen', 432, '9780141439518'),

      ('BOOK', 'One Hundred Years of Solitude', 1967, 'Harper & Row', 'es', 'Magical Realism',
       'Gabriel García Márquez''s multigenerational saga of the Buendía family in Macondo.',
       'Gabriel García Márquez', 417, '9780060883287'),

      ('BOOK', 'Dune', 1965, 'Chilton Books', 'en', 'Science Fiction',
       'Frank Herbert''s epic of interstellar politics and ecology on the desert planet Arrakis.',
       'Frank Herbert', 688, '9780441172719'),

      ('BOOK', 'The Lord of the Rings', 1954, 'George Allen & Unwin', 'en', 'Fantasy',
       'J.R.R. Tolkien''s epic high fantasy novel of the War of the Ring.',
       'J.R.R. Tolkien', 1178, '9780544003415'),

      ('BOOK', 'Harry Potter and the Philosopher''s Stone', 1997, 'Bloomsbury', 'en', 'Fantasy',
       'J.K. Rowling''s first novel in the Harry Potter series.',
       'J.K. Rowling', 223, '9780747532699'),

      ('BOOK', 'Clean Code: A Handbook of Agile Software Craftsmanship', 2008, 'Prentice Hall', 'en', 'Computer Science',
       'Robert C. Martin''s guide to writing readable, maintainable software.',
       'Robert C. Martin', 464, '9780132350884'),

      ('BOOK', 'Effective Java', 2017, 'Addison-Wesley Professional', 'en', 'Computer Science',
       'Joshua Bloch''s definitive guide to Java best practices, 3rd edition.',
       'Joshua Bloch', 412, '9780134685991'),

      ('BOOK', 'Designing Data-Intensive Applications', 2017, 'O''Reilly Media', 'en', 'Computer Science',
       'Martin Kleppmann''s comprehensive guide to modern data systems architecture.',
       'Martin Kleppmann', 616, '9781449373320'),

      ('BOOK', 'A Brief History of Time', 1988, 'Bantam Books', 'en', 'Science',
       'Stephen Hawking''s landmark popular science book on cosmology.',
       'Stephen Hawking', 256, '9780553380163'),

      ('BOOK', 'Sapiens: A Brief History of Humankind', 2011, 'Harper', 'en', 'History',
       'Yuval Noah Harari''s survey of the history of humankind from the Stone Age to modernity.',
       'Yuval Noah Harari', 443, '9780062316097'),

      ('BOOK', 'Thinking, Fast and Slow', 2011, 'Farrar, Straus and Giroux', 'en', 'Psychology',
       'Daniel Kahneman''s exploration of the two systems that drive the way we think.',
       'Daniel Kahneman', 499, '9780374533557'),

      ('BOOK', 'Pan Tadeusz', 1834, 'Alexander Jełowicki', 'pl', 'Epic Poetry',
       'Adam Mickiewicz''s epic poem, considered the national epic of Poland.',
       'Adam Mickiewicz', 352, '9788373272354');


-- =====================================================
-- MAGAZINES (6 entries)
-- =====================================================
INSERT INTO publication (
    type, title, publication_year, publisher, language, category, description,
    issue_date, issue_number, periodicity, issn
) VALUES
      ('MAGAZINE', 'National Geographic', 2024, 'National Geographic Partners', 'en', 'Science & Nature',
       'The official magazine of the National Geographic Society, covering geography, science, and world cultures.',
       '2024-03-01', 'Vol. 245, No. 3', 'MONTHLY', '0027-9358'),

      ('MAGAZINE', 'The Economist', 2024, 'The Economist Group', 'en', 'News & Politics',
       'Weekly international news and current affairs magazine.',
       '2024-06-15', 'Vol. 451, No. 9401', 'WEEKLY', '0013-0613'),

      ('MAGAZINE', 'Nature', 2024, 'Springer Nature', 'en', 'Science',
       'Weekly scientific journal covering all fields of scientific research.',
       '2024-02-08', 'Vol. 626, No. 7998', 'WEEKLY', '0028-0836'),

      ('MAGAZINE', 'Scientific American', 2024, 'Springer Nature', 'en', 'Science',
       'Popular science magazine featuring articles on recent developments in science and technology.',
       '2024-04-01', 'Vol. 330, No. 4', 'MONTHLY', '0036-8733'),

      ('MAGAZINE', 'Polityka', 2024, 'Polityka Spółdzielnia Pracy', 'pl', 'News & Politics',
       'Polish weekly news magazine covering politics, society, and culture.',
       '2024-05-22', 'Nr. 21 (3464)', 'WEEKLY', '0032-3500'),

      ('MAGAZINE', 'Time', 2024, 'TIME USA, LLC', 'en', 'News & Politics',
       'American news magazine covering politics, culture, business, and world affairs.',
       '2024-07-08', 'Vol. 204, No. 1', 'WEEKLY', '0040-781X');


-- =====================================================
-- CATALOG ENTRIES (physical inventory)
-- =====================================================

INSERT INTO publication_catalog (publication_id, inventory_code, status, condition, shelf_location, acquisition_type)
VALUES
-- The Great Gatsby (3 copies)
((SELECT id FROM publication WHERE isbn = '9780743273565'), 'LIB-000001', 'BORROWED',  'GOOD', 'A-01-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780743273565'), 'LIB-000002', 'AVAILABLE', 'GOOD', 'A-01-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780743273565'), 'LIB-000003', 'AVAILABLE', 'WORN', 'A-01-03', 'BORROW_ONLY'),

-- Nineteen Eighty-Four (4 copies, one damaged)
((SELECT id FROM publication WHERE isbn = '9780451524935'), 'LIB-000004', 'AVAILABLE', 'GOOD',    'A-01-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780451524935'), 'LIB-000005', 'AVAILABLE', 'NEW',     'A-01-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780451524935'), 'LIB-000006', 'AVAILABLE', 'GOOD',    'A-01-06', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780451524935'), 'LIB-000007', 'DAMAGED',   'DAMAGED', 'A-01-07', 'BORROW_ONLY'),

-- To Kill a Mockingbird (2 copies, one borrowed)
((SELECT id FROM publication WHERE isbn = '9780061120084'), 'LIB-000008', 'BORROWED',  'GOOD', 'A-02-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780061120084'), 'LIB-000009', 'AVAILABLE', 'GOOD', 'A-02-02', 'BORROW_ONLY'),

-- Pride and Prejudice (2 copies)
((SELECT id FROM publication WHERE isbn = '9780141439518'), 'LIB-000010', 'AVAILABLE', 'WORN', 'A-02-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780141439518'), 'LIB-000011', 'AVAILABLE', 'GOOD', 'A-02-04', 'BORROW_ONLY'),

-- One Hundred Years of Solitude (2 copies)
((SELECT id FROM publication WHERE isbn = '9780060883287'), 'LIB-000012', 'AVAILABLE', 'NEW',  'A-03-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780060883287'), 'LIB-000013', 'AVAILABLE', 'GOOD', 'A-03-02', 'BORROW_ONLY'),

-- Dune (3 copies)
((SELECT id FROM publication WHERE isbn = '9780441172719'), 'LIB-000014', 'AVAILABLE', 'GOOD', 'B-01-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780441172719'), 'LIB-000015', 'AVAILABLE', 'NEW',  'B-01-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780441172719'), 'LIB-000016', 'AVAILABLE', 'GOOD', 'B-01-03', 'BORROW_ONLY'),

-- Lord of the Rings (2 copies)
((SELECT id FROM publication WHERE isbn = '9780544003415'), 'LIB-000017', 'AVAILABLE', 'GOOD', 'B-01-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780544003415'), 'LIB-000018', 'AVAILABLE', 'WORN', 'B-01-05', 'BORROW_ONLY'),

-- Harry Potter (5 copies — popular!)
((SELECT id FROM publication WHERE isbn = '9780747532699'), 'LIB-000019', 'AVAILABLE', 'GOOD', 'B-02-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780747532699'), 'LIB-000020', 'AVAILABLE', 'NEW',  'B-02-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780747532699'), 'LIB-000021', 'AVAILABLE', 'GOOD', 'B-02-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780747532699'), 'LIB-000022', 'AVAILABLE', 'GOOD', 'B-02-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780747532699'), 'LIB-000023', 'AVAILABLE', 'WORN', 'B-02-05', 'BORROW_ONLY'),

-- Clean Code (3 copies)
((SELECT id FROM publication WHERE isbn = '9780132350884'), 'LIB-000024', 'AVAILABLE', 'NEW',  'C-01-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780132350884'), 'LIB-000025', 'BORROWED',  'GOOD', 'C-01-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780132350884'), 'LIB-000026', 'AVAILABLE', 'GOOD', 'C-01-03', 'BORROW_ONLY'),

-- Effective Java (2 copies)
((SELECT id FROM publication WHERE isbn = '9780134685991'), 'LIB-000027', 'AVAILABLE', 'NEW',  'C-01-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780134685991'), 'LIB-000028', 'AVAILABLE', 'GOOD', 'C-01-05', 'BORROW_ONLY'),

-- Designing Data-Intensive Applications (2 copies)
((SELECT id FROM publication WHERE isbn = '9781449373320'), 'LIB-000029', 'AVAILABLE', 'GOOD', 'C-02-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781449373320'), 'LIB-000030', 'AVAILABLE', 'NEW',  'C-02-02', 'BORROW_ONLY'),

-- A Brief History of Time (1 copy)
((SELECT id FROM publication WHERE isbn = '9780553380163'), 'LIB-000031', 'AVAILABLE', 'GOOD', 'C-02-03', 'BORROW_ONLY'),

-- Sapiens (3 copies)
((SELECT id FROM publication WHERE isbn = '9780062316097'), 'LIB-000032', 'AVAILABLE', 'NEW',  'D-01-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780062316097'), 'LIB-000033', 'AVAILABLE', 'GOOD', 'D-01-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780062316097'), 'LIB-000034', 'AVAILABLE', 'GOOD', 'D-01-03', 'BORROW_ONLY'),

-- Thinking, Fast and Slow (2 copies)
((SELECT id FROM publication WHERE isbn = '9780374533557'), 'LIB-000035', 'AVAILABLE', 'GOOD', 'D-01-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780374533557'), 'LIB-000036', 'AVAILABLE', 'NEW',  'D-01-05', 'BORROW_ONLY'),

-- Pan Tadeusz (2 copies)
((SELECT id FROM publication WHERE isbn = '9788373272354'), 'LIB-000037', 'AVAILABLE', 'GOOD', 'D-02-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788373272354'), 'LIB-000038', 'AVAILABLE', 'GOOD', 'D-02-02', 'BORROW_ONLY'),

-- Magazines (1 copy each)
((SELECT id FROM publication WHERE issn = '0027-9358'), 'LIB-000039', 'AVAILABLE', 'NEW', 'M-01-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0013-0613'), 'LIB-000040', 'AVAILABLE', 'NEW', 'M-01-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0028-0836'), 'LIB-000041', 'AVAILABLE', 'NEW', 'M-01-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0036-8733'), 'LIB-000042', 'AVAILABLE', 'NEW', 'M-02-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0032-3500'), 'LIB-000043', 'AVAILABLE', 'NEW', 'M-02-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0040-781X'), 'LIB-000044', 'AVAILABLE', 'NEW', 'M-02-03', 'BORROW_ONLY');
-- =====================================================
-- V3: Additional 50 publications for search testing
-- =====================================================
INSERT INTO publication (
    type, title, publication_year, publisher, language, category, description,
    author, pages, isbn
) VALUES

-- ========== Classic Literature ==========
('BOOK', 'The Catcher in the Rye', 1951, 'Little, Brown and Company', 'en', 'Fiction',
 'J.D. Salinger''s novel of teenage alienation, narrated by Holden Caulfield over three days in New York City.',
 'J.D. Salinger', 277, '9780316769174'),

('BOOK', 'Brave New World', 1932, 'Chatto & Windus', 'en', 'Dystopian Fiction',
 'Aldous Huxley''s dystopian novel of a genetically engineered, consumerist future society.',
 'Aldous Huxley', 311, '9780060850524'),

('BOOK', 'The Brothers Karamazov', 1880, 'The Russian Messenger', 'en', 'Philosophical Fiction',
 'Fyodor Dostoevsky''s final novel, a philosophical drama of faith, doubt, and parricide.',
 'Fyodor Dostoevsky', 796, '9780374528379'),

('BOOK', 'Crime and Punishment', 1866, 'The Russian Messenger', 'en', 'Philosophical Fiction',
 'Dostoevsky''s psychological study of a destitute student who murders a pawnbroker.',
 'Fyodor Dostoevsky', 671, '9780143058144'),

('BOOK', 'War and Peace', 1869, 'The Russian Messenger', 'en', 'Historical Fiction',
 'Leo Tolstoy''s sprawling epic of five aristocratic families during the Napoleonic Wars.',
 'Leo Tolstoy', 1225, '9781400079988'),

('BOOK', 'Anna Karenina', 1878, 'The Russian Messenger', 'en', 'Fiction',
 'Tolstoy''s tragic novel of love, family, and society in Imperial Russia.',
 'Leo Tolstoy', 864, '9780143035008'),

('BOOK', 'Les Misérables', 1862, 'A. Lacroix, Verboeckhoven & Cie.', 'fr', 'Historical Fiction',
 'Victor Hugo''s novel of redemption set against the backdrop of 19th-century France.',
 'Victor Hugo', 1463, '9780451419439'),

('BOOK', 'The Count of Monte Cristo', 1844, 'Pétion', 'fr', 'Adventure',
 'Alexandre Dumas''s tale of wrongful imprisonment, escape, and meticulous revenge.',
 'Alexandre Dumas', 1276, '9780140449266'),

('BOOK', 'Don Quixote', 1605, 'Francisco de Robles', 'es', 'Fiction',
 'Miguel de Cervantes''s foundational work of modern Western literature, a satirical epic of a delusional knight.',
 'Miguel de Cervantes', 863, '9780060934347'),

('BOOK', 'The Picture of Dorian Gray', 1890, 'Ward, Lock and Company', 'en', 'Gothic Fiction',
 'Oscar Wilde''s philosophical novel of a man whose portrait ages while he remains youthful.',
 'Oscar Wilde', 254, '9780141439570'),

-- ========== Modern Fiction ==========
('BOOK', 'The Road', 2006, 'Alfred A. Knopf', 'en', 'Post-apocalyptic Fiction',
 'Cormac McCarthy''s Pulitzer Prize-winning novel of a father and son crossing a devastated America.',
 'Cormac McCarthy', 287, '9780307387899'),

('BOOK', 'Beloved', 1987, 'Alfred A. Knopf', 'en', 'Fiction',
 'Toni Morrison''s Pulitzer Prize-winning novel of a former slave haunted by her past.',
 'Toni Morrison', 324, '9781400033416'),

('BOOK', 'The Kite Runner', 2003, 'Riverhead Books', 'en', 'Fiction',
 'Khaled Hosseini''s debut novel of friendship, betrayal, and redemption set in Afghanistan.',
 'Khaled Hosseini', 371, '9781594631931'),

('BOOK', 'Life of Pi', 2001, 'Knopf Canada', 'en', 'Adventure',
 'Yann Martel''s Booker Prize-winning novel of a boy stranded at sea with a Bengal tiger.',
 'Yann Martel', 319, '9780156027328'),

('BOOK', 'The Name of the Wind', 2007, 'DAW Books', 'en', 'Fantasy',
 'Patrick Rothfuss''s epic fantasy novel, first in the Kingkiller Chronicle.',
 'Patrick Rothfuss', 662, '9780756404741'),

('BOOK', 'A Game of Thrones', 1996, 'Bantam Spectra', 'en', 'Fantasy',
 'George R.R. Martin''s first novel in A Song of Ice and Fire, a dark epic of medieval power politics.',
 'George R.R. Martin', 694, '9780553103540'),

('BOOK', 'Neuromancer', 1984, 'Ace Books', 'en', 'Science Fiction',
 'William Gibson''s seminal cyberpunk novel that coined the term "cyberspace".',
 'William Gibson', 271, '9780441569595'),

('BOOK', 'Foundation', 1951, 'Gnome Press', 'en', 'Science Fiction',
 'Isaac Asimov''s classic novel of a mathematician-planned galactic empire.',
 'Isaac Asimov', 255, '9780553293357'),

('BOOK', 'Snow Crash', 1992, 'Bantam Books', 'en', 'Science Fiction',
 'Neal Stephenson''s cyberpunk novel that popularized the concept of the "metaverse".',
 'Neal Stephenson', 470, '9780553380958'),

-- ========== Technical / Computer Science ==========
('BOOK', 'The Pragmatic Programmer', 2019, 'Addison-Wesley Professional', 'en', 'Computer Science',
 'Andrew Hunt and David Thomas''s 20th Anniversary Edition of the essential software craftsmanship guide.',
 'David Thomas, Andrew Hunt', 320, '9780135957059'),

('BOOK', 'Domain-Driven Design', 2003, 'Addison-Wesley Professional', 'en', 'Computer Science',
 'Eric Evans''s foundational work on tackling complexity in software through strategic modeling.',
 'Eric Evans', 560, '9780321125217'),

('BOOK', 'Refactoring: Improving the Design of Existing Code', 2018, 'Addison-Wesley Professional', 'en', 'Computer Science',
 'Martin Fowler''s classic, updated 2nd edition, on the mechanics and catalog of refactoring.',
 'Martin Fowler', 448, '9780134757599'),

('BOOK', 'Introduction to Algorithms', 2009, 'MIT Press', 'en', 'Computer Science',
 'Cormen, Leiserson, Rivest, and Stein''s comprehensive textbook, known as CLRS.',
 'Thomas H. Cormen', 1292, '9780262033848'),

('BOOK', 'The Mythical Man-Month', 1995, 'Addison-Wesley', 'en', 'Computer Science',
 'Fred Brooks''s anniversary edition of essays on software engineering and project management.',
 'Frederick P. Brooks Jr.', 322, '9780201835953'),

('BOOK', 'Structure and Interpretation of Computer Programs', 1996, 'MIT Press', 'en', 'Computer Science',
 'Abelson and Sussman''s classic MIT textbook, affectionately known as "the wizard book".',
 'Harold Abelson', 657, '9780262510875'),

('BOOK', 'Spring in Action', 2022, 'Manning Publications', 'en', 'Computer Science',
 'Craig Walls''s 6th edition guide to the Spring Framework and Spring Boot.',
 'Craig Walls', 520, '9781617297571'),

-- ========== Non-fiction & Science ==========
('BOOK', 'The Selfish Gene', 1976, 'Oxford University Press', 'en', 'Science',
 'Richard Dawkins''s influential work on evolutionary theory and gene-centered natural selection.',
 'Richard Dawkins', 360, '9780198788607'),

('BOOK', 'Gödel, Escher, Bach: An Eternal Golden Braid', 1979, 'Basic Books', 'en', 'Philosophy',
 'Douglas Hofstadter''s Pulitzer Prize-winning exploration of consciousness, formal systems, and self-reference.',
 'Douglas Hofstadter', 777, '9780465026562'),

('BOOK', 'Guns, Germs, and Steel', 1997, 'W. W. Norton & Company', 'en', 'History',
 'Jared Diamond''s Pulitzer Prize-winning history of human societies and the origins of inequality.',
 'Jared Diamond', 498, '9780393354324'),

('BOOK', 'Homo Deus: A Brief History of Tomorrow', 2016, 'Harvill Secker', 'en', 'History',
 'Yuval Noah Harari''s follow-up to Sapiens, exploring the future of humanity and technology.',
 'Yuval Noah Harari', 450, '9780062464316'),

('BOOK', 'Man''s Search for Meaning', 1946, 'Beacon Press', 'en', 'Psychology',
 'Viktor Frankl''s memoir of surviving Nazi concentration camps and his theory of logotherapy.',
 'Viktor E. Frankl', 165, '9780807014271'),

-- ========== Polish Literature ==========
('BOOK', 'Lalka', 1890, 'Świat Książki', 'pl', 'Klasyka',
 'Bolesław Prus''s panoramic novel of 19th-century Warsaw society and unrequited love.',
 'Bolesław Prus', 832, '9788368725865'),

('BOOK', 'Quo Vadis', 1896, 'Gebethner i Wolff', 'pl', 'Historical Fiction',
 'Henryk Sienkiewicz''s Nobel Prize-winning historical novel set in Nero''s Rome.',
 'Henryk Sienkiewicz', 608, '9788373271517'),

('BOOK', 'Ferdydurke', 1937, 'Rój', 'pl', 'Modern Literature',
 'Witold Gombrowicz''s satirical novel of immaturity, form, and the struggle against social imposition.',
 'Witold Gombrowicz', 272, '9788308037874'),

('BOOK', 'Solaris', 1961, 'Wydawnictwo MON', 'pl', 'Science Fiction',
 'Stanisław Lem''s philosophical science fiction novel of contact with an alien intelligence.',
 'Stanisław Lem', 216, '9788308042946'),

('BOOK', 'Sklepy cynamonowe', 1934, 'Rój', 'pl', 'Short Stories',
 'Bruno Schulz''s collection of lyrical, dreamlike short stories set in a Galician town.',
 'Bruno Schulz', 176, '9788373278219'),

-- ========== International Fiction ==========
('BOOK', 'The Stranger', 1942, 'Éditions Gallimard', 'fr', 'Philosophical Fiction',
 'Albert Camus''s existentialist novella of a detached man on trial in French Algeria.',
 'Albert Camus', 123, '9780679720201'),

('BOOK', 'The Trial', 1925, 'Verlag Die Schmiede', 'de', 'Fiction',
 'Franz Kafka''s posthumous novel of a man arrested and prosecuted by an unknown authority.',
 'Franz Kafka', 255, '9780805210408'),

('BOOK', 'Norwegian Wood', 1987, 'Kodansha', 'ja', 'Fiction',
 'Haruki Murakami''s coming-of-age novel of love and loss in 1960s Tokyo.',
 'Haruki Murakami', 296, '9780375704024'),

('BOOK', 'The Shadow of the Wind', 2001, 'Editorial Planeta', 'es', 'Mystery',
 'Carlos Ruiz Zafón''s Gothic mystery set in post-war Barcelona, centered on a mysterious forgotten book.',
 'Carlos Ruiz Zafón', 487, '9780143034902');


-- =====================================================
-- MAGAZINES — 10 additional titles
-- =====================================================
INSERT INTO publication (
    type, title, publication_year, publisher, language, category, description,
    issue_date, issue_number, periodicity, issn
) VALUES

      ('MAGAZINE', 'The New Yorker', 2024, 'Condé Nast', 'en', 'Culture & Politics',
       'American weekly magazine featuring journalism, commentary, criticism, essays, fiction, and poetry.',
       '2024-09-23', 'Vol. 100, No. 29', 'WEEKLY', '0028-792X'),

      ('MAGAZINE', 'Wired', 2024, 'Condé Nast', 'en', 'Technology',
       'American monthly magazine focusing on technology, its impact on culture, economy, and politics.',
       '2024-10-01', 'Vol. 32, No. 10', 'MONTHLY', '1059-1028'),

      ('MAGAZINE', 'MIT Technology Review', 2024, 'MIT', 'en', 'Technology',
       'Bimonthly magazine covering emerging technologies and their commercial, social, and political impact.',
       '2024-07-01', 'Vol. 127, No. 4', 'QUARTERLY', '1099-274X'),

      ('MAGAZINE', 'Harvard Business Review', 2024, 'Harvard Business Publishing', 'en', 'Business',
       'General management magazine published by Harvard Business School, for business executives.',
       '2024-05-01', 'Vol. 102, No. 3', 'MONTHLY', '0017-8012'),

      ('MAGAZINE', 'The Atlantic', 2024, 'Atlantic Media', 'en', 'Culture & Politics',
       'American magazine covering news, politics, culture, technology, and the arts.',
       '2024-06-01', 'Vol. 333, No. 5', 'MONTHLY', '1072-7825'),

      ('MAGAZINE', 'Le Monde diplomatique', 2024, 'Le Monde', 'fr', 'Politics',
       'French monthly newspaper offering analysis and opinion on international politics and economics.',
       '2024-04-01', 'No. 841', 'MONTHLY', '0026-9395'),

      ('MAGAZINE', 'Der Spiegel', 2024, 'Spiegel-Verlag', 'de', 'News & Politics',
       'German weekly news magazine, one of Europe''s largest publications of its kind.',
       '2024-08-10', 'Nr. 33/2024', 'WEEKLY', '0038-7452'),

      ('MAGAZINE', 'Gazeta Wyborcza Magazyn', 2024, 'Agora SA', 'pl', 'News & Politics',
       'Weekly supplement to the leading Polish daily Gazeta Wyborcza, featuring long-form journalism.',
       '2024-06-07', 'Nr. 23/2024', 'WEEKLY', '0860-908X'),

      ('MAGAZINE', 'Tygodnik Powszechny', 2024, 'Tygodnik Powszechny Sp. z o.o.', 'pl', 'Culture & Politics',
       'Polish Catholic weekly magazine covering social, cultural, and religious topics.',
       '2024-07-14', 'Nr. 28/2024', 'WEEKLY', '0041-4808'),

      ('MAGAZINE', 'New Scientist', 2024, 'New Scientist Ltd', 'en', 'Science',
       'Weekly science and technology magazine covering recent developments in scientific research.',
       '2024-09-07', 'Vol. 263, No. 3506', 'WEEKLY', '0262-4079');


-- =====================================================
-- CATALOG ENTRIES for the new publications
-- Conservative inventory: 1-3 copies per title.
-- Mix of statuses and conditions for realistic search/filter results.
-- =====================================================

INSERT INTO publication_catalog (publication_id, inventory_code, status, condition, shelf_location, acquisition_type)
VALUES
-- Classic literature (1-3 copies each)
((SELECT id FROM publication WHERE isbn = '9780316769174'), 'LIB-000045', 'AVAILABLE', 'GOOD', 'A-04-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780316769174'), 'LIB-000046', 'AVAILABLE', 'WORN', 'A-04-02', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780060850524'), 'LIB-000047', 'AVAILABLE', 'GOOD', 'A-04-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780060850524'), 'LIB-000048', 'BORROWED',  'GOOD', 'A-04-04', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780374528379'), 'LIB-000049', 'AVAILABLE', 'GOOD', 'A-05-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780143058144'), 'LIB-000050', 'AVAILABLE', 'GOOD', 'A-05-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780143058144'), 'LIB-000051', 'AVAILABLE', 'NEW',  'A-05-03', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9781400079988'), 'LIB-000052', 'AVAILABLE', 'GOOD', 'A-05-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780143035008'), 'LIB-000053', 'AVAILABLE', 'GOOD', 'A-05-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780143035008'), 'LIB-000054', 'BORROWED',  'WORN', 'A-05-06', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780451419439'), 'LIB-000055', 'AVAILABLE', 'GOOD', 'A-06-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780140449266'), 'LIB-000056', 'AVAILABLE', 'WORN', 'A-06-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780140449266'), 'LIB-000057', 'AVAILABLE', 'GOOD', 'A-06-03', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780060934347'), 'LIB-000058', 'AVAILABLE', 'GOOD', 'A-06-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780141439570'), 'LIB-000059', 'AVAILABLE', 'GOOD', 'A-06-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780141439570'), 'LIB-000060', 'AVAILABLE', 'NEW',  'A-06-06', 'BORROW_ONLY'),

-- Modern Fiction
((SELECT id FROM publication WHERE isbn = '9780307387899'), 'LIB-000061', 'AVAILABLE', 'GOOD', 'A-07-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780307387899'), 'LIB-000062', 'AVAILABLE', 'NEW',  'A-07-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781400033416'), 'LIB-000063', 'AVAILABLE', 'GOOD', 'A-07-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781594631931'), 'LIB-000064', 'BORROWED',  'GOOD', 'A-07-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781594631931'), 'LIB-000065', 'AVAILABLE', 'GOOD', 'A-07-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780156027328'), 'LIB-000066', 'AVAILABLE', 'GOOD', 'A-07-06', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780756404741'), 'LIB-000067', 'AVAILABLE', 'NEW',  'B-03-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780756404741'), 'LIB-000068', 'AVAILABLE', 'GOOD', 'B-03-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780553103540'), 'LIB-000069', 'BORROWED',  'GOOD', 'B-03-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780553103540'), 'LIB-000070', 'AVAILABLE', 'NEW',  'B-03-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780553103540'), 'LIB-000071', 'AVAILABLE', 'GOOD', 'B-03-05', 'BORROW_ONLY'),

((SELECT id FROM publication WHERE isbn = '9780441569595'), 'LIB-000072', 'AVAILABLE', 'WORN', 'B-04-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780553293357'), 'LIB-000073', 'AVAILABLE', 'GOOD', 'B-04-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780553380958'), 'LIB-000074', 'AVAILABLE', 'GOOD', 'B-04-03', 'BORROW_ONLY'),

-- Technical books (higher availability, fewer copies each)
((SELECT id FROM publication WHERE isbn = '9780135957059'), 'LIB-000075', 'AVAILABLE', 'NEW',  'C-03-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780135957059'), 'LIB-000076', 'BORROWED',  'GOOD', 'C-03-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780321125217'), 'LIB-000077', 'AVAILABLE', 'GOOD', 'C-03-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780134757599'), 'LIB-000078', 'AVAILABLE', 'NEW',  'C-03-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780262033848'), 'LIB-000079', 'AVAILABLE', 'GOOD', 'C-04-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780201835953'), 'LIB-000080', 'AVAILABLE', 'WORN', 'C-04-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780262510875'), 'LIB-000081', 'AVAILABLE', 'GOOD', 'C-04-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781617297571'), 'LIB-000082', 'AVAILABLE', 'NEW',  'C-04-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9781617297571'), 'LIB-000083', 'AVAILABLE', 'NEW',  'C-04-05', 'BORROW_ONLY'),

-- Non-fiction
((SELECT id FROM publication WHERE isbn = '9780198788607'), 'LIB-000084', 'AVAILABLE', 'GOOD', 'D-03-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780465026562'), 'LIB-000085', 'AVAILABLE', 'GOOD', 'D-03-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780393354324'), 'LIB-000086', 'AVAILABLE', 'GOOD', 'D-03-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780062464316'), 'LIB-000087', 'BORROWED',  'NEW',  'D-03-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780062464316'), 'LIB-000088', 'AVAILABLE', 'GOOD', 'D-03-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780807014271'), 'LIB-000089', 'AVAILABLE', 'GOOD', 'D-03-06', 'BORROW_ONLY'),

-- Polish literature (multiple copies — locally relevant)
((SELECT id FROM publication WHERE isbn = '9788368725865'), 'LIB-000090', 'AVAILABLE', 'NEW',  'D-04-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788368725865'), 'LIB-000091', 'AVAILABLE', 'GOOD', 'D-04-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788373271517'), 'LIB-000092', 'AVAILABLE', 'GOOD', 'D-04-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788308037874'), 'LIB-000093', 'AVAILABLE', 'GOOD', 'D-04-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788308042946'), 'LIB-000094', 'BORROWED',  'GOOD', 'D-04-05', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788308042946'), 'LIB-000095', 'AVAILABLE', 'GOOD', 'D-04-06', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9788373278219'), 'LIB-000096', 'AVAILABLE', 'GOOD', 'D-04-07', 'BORROW_ONLY'),

-- International fiction
((SELECT id FROM publication WHERE isbn = '9780679720201'), 'LIB-000097', 'AVAILABLE', 'GOOD', 'A-08-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780805210408'), 'LIB-000098', 'AVAILABLE', 'GOOD', 'A-08-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780375704024'), 'LIB-000099', 'AVAILABLE', 'NEW',  'A-08-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780375704024'), 'LIB-000100', 'AVAILABLE', 'GOOD', 'A-08-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE isbn = '9780143034902'), 'LIB-000101', 'AVAILABLE', 'GOOD', 'A-08-05', 'BORROW_ONLY'),

-- Magazines (1 copy each)
((SELECT id FROM publication WHERE issn = '0028-792X'), 'LIB-000102', 'AVAILABLE', 'NEW', 'M-03-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '1059-1028'), 'LIB-000103', 'AVAILABLE', 'NEW', 'M-03-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '1099-274X'), 'LIB-000104', 'AVAILABLE', 'NEW', 'M-03-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0017-8012'), 'LIB-000105', 'AVAILABLE', 'NEW', 'M-03-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '1072-7825'), 'LIB-000106', 'AVAILABLE', 'NEW', 'M-04-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0026-9395'), 'LIB-000107', 'AVAILABLE', 'NEW', 'M-04-02', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0038-7452'), 'LIB-000108', 'AVAILABLE', 'NEW', 'M-04-03', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0860-908X'), 'LIB-000109', 'AVAILABLE', 'NEW', 'M-04-04', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0041-4808'), 'LIB-000110', 'AVAILABLE', 'NEW', 'M-05-01', 'BORROW_ONLY'),
((SELECT id FROM publication WHERE issn = '0262-4079'), 'LIB-000111', 'AVAILABLE', 'NEW', 'M-05-02', 'BORROW_ONLY');
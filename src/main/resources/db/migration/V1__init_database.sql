create table if not exists publication (
                             id BIGSERIAL PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             year VARCHAR(255) NOT NULL,
                             publisher VARCHAR(255) NOT NULL
);

create table if not exists book (
                      id BIGINT PRIMARY KEY,
                      author VARCHAR(255),
                      pages INTEGER,
                      isbn VARCHAR(255),
                      CONSTRAINT fk_book_publication FOREIGN KEY (id) REFERENCES publication(id)
);

create table if not exists magazine (
                          id BIGINT PRIMARY KEY,
                          month_day VARCHAR(255) NOT NULL,
                          language VARCHAR(255) NOT NULL,
                          CONSTRAINT fk_magazine_publication FOREIGN KEY (id) REFERENCES publication(id)
);
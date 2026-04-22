CREATE TABLE publication (
                             id              BIGSERIAL PRIMARY KEY,
                             type            VARCHAR(20)  NOT NULL,

    -- Common fields (on abstract Publication)
                             title           VARCHAR(255) NOT NULL,
                             publication_year INTEGER     NOT NULL,
                             publisher       VARCHAR(150) NOT NULL,
                             language        VARCHAR(10)  NOT NULL,
                             category        VARCHAR(100),
                             description     TEXT,
                             cover_image_key VARCHAR(255),

    -- Book-specific fields (NULL for Magazine rows)
                             author          VARCHAR(200),
                             pages           INTEGER,
                             isbn            VARCHAR(13),

    -- Magazine-specific fields (NULL for Book rows)
                             issue_date      DATE,
                             issue_number    VARCHAR(50),
                             periodicity     VARCHAR(20),
                             issn            VARCHAR(9),

    -- Auditing
                             created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
                             updated_at      TIMESTAMP    NOT NULL DEFAULT NOW(),

                             CONSTRAINT chk_publication_type CHECK (type IN ('BOOK', 'MAGAZINE')),
                             CONSTRAINT chk_publication_year CHECK (publication_year >= 1000),
                             CONSTRAINT chk_book_pages_positive CHECK (pages IS NULL OR pages > 0),
                             CONSTRAINT chk_magazine_periodicity CHECK (
                                 periodicity IS NULL OR periodicity IN ('WEEKLY', 'MONTHLY', 'QUARTERLY', 'ANNUAL')
                                 )
);

-- Partial unique indexes: allow many NULLs, enforce uniqueness when present
CREATE UNIQUE INDEX idx_publication_isbn ON publication(isbn) WHERE isbn IS NOT NULL;
CREATE UNIQUE INDEX idx_publication_issn ON publication(issn) WHERE issn IS NOT NULL;

-- Query indexes
CREATE INDEX idx_publication_title ON publication(title);
CREATE INDEX idx_publication_type  ON publication(type);

-- =====================================================
-- PublicationCopy table
-- Represents physical/logical inventory items.
-- =====================================================
CREATE TABLE publication_catalog (
                                  id               BIGSERIAL PRIMARY KEY,
                                  publication_id   BIGINT       NOT NULL REFERENCES publication(id) ON DELETE RESTRICT,
                                  inventory_code   VARCHAR(30)  NOT NULL UNIQUE,
                                  status           VARCHAR(20)  NOT NULL DEFAULT 'AVAILABLE',
                                  condition        VARCHAR(20)  NOT NULL DEFAULT 'GOOD',
                                  shelf_location   VARCHAR(50),
                                  acquisition_type VARCHAR(20)  NOT NULL DEFAULT 'BORROW_ONLY',
                                  price            NUMERIC(10, 2),
                                  version          BIGINT       NOT NULL DEFAULT 0,
                                  created_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
                                  updated_at       TIMESTAMP    NOT NULL DEFAULT NOW(),

                                  CONSTRAINT chk_copy_status CHECK (
                                      status IN ('AVAILABLE', 'RESERVED', 'BORROWED', 'LOST', 'DAMAGED')
                                      ),
                                  CONSTRAINT chk_copy_condition CHECK (
                                      condition IN ('NEW', 'GOOD', 'WORN', 'DAMAGED')
                                      ),
                                  CONSTRAINT chk_copy_acquisition CHECK (
                                      acquisition_type IN ('BORROW_ONLY', 'SELL_ONLY', 'BORROW_OR_SELL')
                                      ),
                                  CONSTRAINT chk_copy_price_positive CHECK (price IS NULL OR price > 0)
);

CREATE INDEX idx_copy_publication ON publication_catalog(publication_id);
CREATE INDEX idx_copy_status      ON publication_catalog(status);
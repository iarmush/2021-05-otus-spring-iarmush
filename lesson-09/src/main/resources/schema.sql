DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR
(
    ID        BIGINT IDENTITY PRIMARY KEY,
    FULL_NAME VARCHAR(255) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE
(
    ID   BIGINT IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK
(
    ID        BIGINT IDENTITY PRIMARY KEY,
    TITLE     VARCHAR(255) UNIQUE NOT NULL,
    AUTHOR_ID BIGINT              NOT NULL REFERENCES AUTHOR (ID),
    GENRE_ID  BIGINT              NOT NULL REFERENCES GENRE (ID)
);

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT
(
    ID      BIGINT IDENTITY PRIMARY KEY,
    TEXT    VARCHAR(255) NOT NULL,
    BOOK_ID BIGINT       NOT NULL REFERENCES BOOK (ID)
);
CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255),
                      author VARCHAR(255),
                      publication_year DATE,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

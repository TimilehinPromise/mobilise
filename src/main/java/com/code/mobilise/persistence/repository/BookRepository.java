package com.code.mobilise.persistence.repository;

import com.code.mobilise.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> , JpaSpecificationExecutor<Book> {

    boolean existsBookByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);
}

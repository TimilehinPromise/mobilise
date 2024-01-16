package com.code.mobilise.service.concrete;


import com.code.mobilise.persistence.entity.Book;
import com.code.mobilise.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.runAsync;


@Slf4j
@Service
@RequiredArgsConstructor
public class StartUpService {

    private final BookRepository bookRepository;


    public CompletableFuture<Void> loadDefaultData() {
        return runAsync(() -> {
        // ADD DEFAULT BOOKS ON STARTUP
            if (bookRepository.findAll().isEmpty()){
             Book book1 = Book.builder()
                     .author("Craig Walls")
                     .title("Spring in Action")
                     .publicationYear(LocalDate.of(2005,11,16))
                     .build();

                Book book2 = Book.builder()
                        .author("Pro Java Clustering and Scalability")
                        .title("Jorge Acetozi")
                        .publicationYear(LocalDate.of(2017,01,16))
                        .build();

                Book book3 = Book.builder()
                        .author("Clean Code")
                        .title("Robert Cecil Martin")
                        .publicationYear(LocalDate.of(2008,04,16))
                        .build();

                List<Book> bookList =  List.of(book1,book2,book3);

                bookRepository.saveAll(bookList);
            }
        });
    }

}

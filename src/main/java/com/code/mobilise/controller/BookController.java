package com.code.mobilise.controller;


import com.code.mobilise.models.BookResponse;
import com.code.mobilise.models.ResponseMessage;
import com.code.mobilise.models.dto.AllBooksDTO;
import com.code.mobilise.models.dto.BookDTO;
import com.code.mobilise.service.abstracts.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(value = "v1/api/book", produces = APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseMessage> createBook(@RequestBody @Valid BookDTO bookDTO) {
        ResponseMessage response = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateBook(@RequestBody @Valid BookDTO bookDTO) {
        ResponseMessage response = bookService.updateBook(bookDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<BookResponse>> getAllBook(@ModelAttribute @Valid AllBooksDTO bookDTO) {
        Page<BookResponse> response = bookService.getAllBooks(bookDTO.getSize(), bookDTO.getIndex());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byId/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId ) {
        BookResponse response = bookService.getBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<ResponseMessage> deleteBook(@PathVariable Long bookId) {
        ResponseMessage response = bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> searchBook(
            @RequestParam String keyword,
            @ModelAttribute @Valid AllBooksDTO bookDTO) {

        Page<BookResponse> response = bookService.searchBooks(keyword, bookDTO.getIndex(), bookDTO.getSize());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}

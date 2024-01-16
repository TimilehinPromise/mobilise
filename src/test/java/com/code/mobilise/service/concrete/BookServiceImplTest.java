package com.code.mobilise.service.concrete;

import com.code.mobilise.exception.AlreadyExistException;
import com.code.mobilise.exception.NotFoundException;
import com.code.mobilise.models.BookResponse;
import com.code.mobilise.models.ResponseMessage;
import com.code.mobilise.models.dto.BookDTO;
import com.code.mobilise.persistence.entity.Book;
import com.code.mobilise.persistence.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.code.mobilise.persistence.specification.BookSpecification.searchInBookWithKeyword;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;


    @Test
    void testCreateBookSuccess() {
        BookDTO bookDTO = new BookDTO(null, "Test Title", "Test Author", LocalDate.now());
        when(bookRepository.existsBookByTitle("Test Title")).thenReturn(false);

        ResponseMessage response = bookService.createBook(bookDTO);

        verify(bookRepository).save(any(Book.class));
        assertEquals("Book Created Successfully", response.getMessage());
    }



    @Test
    void testCreateBookAlreadyExists() {
        BookDTO bookDTO = new BookDTO(null, "Existing Title", "Test Author", LocalDate.now());
        when(bookRepository.existsBookByTitle("Existing Title")).thenReturn(true);

        assertThrows(AlreadyExistException.class, () -> bookService.createBook(bookDTO));
    }

    @Test
    void testUpdateBookSuccess() {
        BookDTO bookDTO = new BookDTO(1L, "Updated Title", "Updated Author", LocalDate.now());
        Book existingBook = new Book(1L, "Original Title", "Original Author", LocalDate.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.existsByTitleAndIdNot("Updated Title", 1L)).thenReturn(false);

        ResponseMessage response = bookService.updateBook(bookDTO);

        verify(bookRepository).save(any(Book.class));
        assertEquals("Book Updated Successfully", response.getMessage());
    }

    @Test
    void testUpdateNonExistentBook() {
        BookDTO bookDTO = new BookDTO(1L, "Updated Title", "Updated Author", LocalDate.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookService.updateBook(bookDTO));
    }

    @Test
    void testDeleteBookSuccess() {
        Book existingBook = new Book(1L, "Test Title", "Test Author", LocalDate.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));

        ResponseMessage response = bookService.deleteBook(1L);

        verify(bookRepository).delete(existingBook);
        assertEquals("Book Deleted Successfully", response.getMessage());
    }

    @Test
    void testDeleteNonExistentBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookService.deleteBook(1L));
    }


    @Test
    void testGetBookByIdSuccess() {
        Book book = new Book(1L, "Test Title", "Test Author", LocalDate.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponse bookResponse = bookService.getBookById(1L);

        assertNotNull(bookResponse);
        assertEquals(book.getTitle(), bookResponse.getTitle());
    }

    @Test
    void testGetBookByNonExistentId() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void testGetAllBooks() {
        Page<Book> booksPage = new PageImpl<>(Collections.singletonList(
                new Book(1L, "Test Title", "Test Author", LocalDate.now())
        ));

        when(bookRepository.findAll(any(Pageable.class))).thenReturn(booksPage);

        Page<BookResponse> response = bookService.getAllBooks(10, 0);

        assertNotNull(response);
        assertEquals(1, response.getContent().size());
    }

    @Test
    void testSearchBooks() {
        Specification<Book> spec = searchInBookWithKeyword("Test");
        Page<Book> booksPage = new PageImpl<>(Collections.singletonList(
                new Book(1L, "Test Title", "Test Author", LocalDate.now())
        ));

        when(bookRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(booksPage);

        Page<BookResponse> response = bookService.searchBooks("Test Title", 0, 10);

        assertNotNull(response);
        assertEquals(1, response.getContent().size());
    }





    @BeforeEach
    void setUp() {
    }
}
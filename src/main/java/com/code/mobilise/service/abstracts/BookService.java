package com.code.mobilise.service.abstracts;

import com.code.mobilise.models.BookResponse;
import com.code.mobilise.models.ResponseMessage;
import com.code.mobilise.models.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    ResponseMessage createBook(BookDTO dto);

    ResponseMessage updateBook(BookDTO dto);

    Page<BookResponse> getAllBooks(int size, int pageIndex);

    BookResponse getBookById(Long bookId);

    ResponseMessage deleteBook(Long bookId);

    Page<BookResponse> searchBooks(String keyword,
                                   int index, int size);
}

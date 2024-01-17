package com.code.mobilise.service.concrete;


import com.code.mobilise.exception.AlreadyExistException;
import com.code.mobilise.exception.NotFoundException;
import com.code.mobilise.models.BookResponse;
import com.code.mobilise.models.ResponseMessage;
import com.code.mobilise.models.dto.BookDTO;
import com.code.mobilise.persistence.entity.Book;
import com.code.mobilise.persistence.repository.BookRepository;
import com.code.mobilise.service.abstracts.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.code.mobilise.persistence.specification.BookSpecification.searchInBookWithKeyword;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final String BOOK_ALREADY_EXIST = "BOOK WITH TITLE ALREADY EXIST";


    @Override
    public ResponseMessage createBook(BookDTO dto){

        if (bookRepository.existsBookByTitle(dto.getTitle())){
            throw new AlreadyExistException(BOOK_ALREADY_EXIST);
        }

        bookRepository.save(Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publicationYear(dto.getPublicationYear())
                .build());

        return createResponseMessage("Book Created Successfully");
    }

    @Override
    public ResponseMessage updateBook(BookDTO dto) {
        Optional<Book> existingBook = bookRepository.findById(dto.getBookId());
        if (existingBook.isEmpty()) {
            throw new NotFoundException();
        }

        if (bookRepository.existsByTitleAndIdNot(dto.getTitle(), dto.getBookId())) {
            throw new AlreadyExistException("A book with the title '" + dto.getTitle() + "' already exists.");
        }
        // update existing book
        Book bookToUpdate = existingBook.get();
        bookToUpdate.setTitle(dto.getTitle());
        bookToUpdate.setAuthor(dto.getAuthor());
        bookToUpdate.setPublicationYear(dto.getPublicationYear());

        bookRepository.save(bookToUpdate);

        return createResponseMessage("Book Updated Successfully");
    }

    @Override
    public Page<BookResponse> getAllBooks(int size, int pageIndex){

        return bookRepository.findAll(PageRequest.of(pageIndex,size)).map(Book::toModel);
    }

    @Override
    public BookResponse getBookById(Long bookId){
        return bookRepository.findById(bookId)
                .map(Book::toModel)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public ResponseMessage deleteBook(Long bookId){
        Optional<Book> existingBook = bookRepository.findById(bookId);
        if (existingBook.isEmpty()) {
            throw new NotFoundException();
        }
        bookRepository.delete(existingBook.get());

        return createResponseMessage("Book Deleted Successfully");
    }

    @Override
    public Page<BookResponse> searchBooks(String keyword,
                                          int index, int size){
        //get Spec
        Specification<Book> spec =  searchInBookWithKeyword(keyword);

        return bookRepository.findAll(Specification.where(spec), PageRequest.of(index,size)).map(Book::toModel);

    }

    private ResponseMessage createResponseMessage(String message) {
        return ResponseMessage.builder().message(message).build();
    }


}

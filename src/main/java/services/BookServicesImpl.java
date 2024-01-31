package services;

import data.model.Books;
import data.repository.BookRepository;
import dtos.AddBookRequest;
import dtos.BorrowBookRequest;
import dtos.RemoveBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static utils.Mapper.map;

    @Service
    public class BookServicesImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServicesImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Books addBook(AddBookRequest addBookRequest) {
        Optional<Books> book = getBook(addBookRequest.getBookName(), addBookRequest.getAuthorName());
        return book.map(value -> updateBook(value, addBookRequest)).orElseGet(() -> createNewBook(addBookRequest));

    }

    private Optional<Books> getBook(String bookName, String authorName) {
        return bookRepository.findBookByBookNameAndAuthorName(bookName, authorName);
    }

    @Override
    public void remove(RemoveBookRequest removeBookRequest) {
        Optional<Books> book = getBook(removeBookRequest.getBookName(), removeBookRequest.getBookAuthor());
        if(book.isPresent()) bookRepository.delete(book.get());
        else throw new CustomException("Book Not Found");

    }

    @Override
    public Books findBookToBorrow(BorrowBookRequest borrowBookRequest) {
        Optional<Books> book = getBook(borrowBookRequest.getBookName(), borrowBookRequest.getBookAuthor());
        if(book.isEmpty()) throwNotFoundException();
        return reduceBookBorrowed(book.get());
    }


    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books findBook(String bookName, String authorName) {
        Optional<Books> book = getBook(bookName, authorName);
        if(book.isEmpty()) throwNotFoundException();
        return book.get();
    }

    private Books addToBookReturned(Books book) {
        book.setNumberAvailable(book.getNumberAvailable() + 1);
        bookRepository.save(book);
        return book;
    }

    private void throwNotFoundException() {
        throw new CustomException("Book Not Found");
    }

    private Books reduceBookBorrowed(Books book) {
        book.setNumberAvailable(book.getNumberAvailable() - 1);
        bookRepository.save(book);
        return book;
    }

    private Books updateBook(Books book, AddBookRequest addBookRequest) {
        book.setNumberAvailable(book.getNumberAvailable() + addBookRequest.getNumberAvailable());
        bookRepository.save(book);
        return book;
    }

    private Books createNewBook(AddBookRequest addBookRequest) {
        Books book = map(addBookRequest);
        bookRepository.save(book);
        return book;
    }

}
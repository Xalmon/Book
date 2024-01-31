package utils;

import data.model.Books;
import data.model.BorrowedBooks;
import data.model.Users;
import dtos.AddBookRequest;
import dtos.BorrowBookRequest;

import java.time.LocalDate;

public class Mapper {

    public static Books map(AddBookRequest addBookRequest) {
        Books book = new Books();
        book.setBookName(addBookRequest.getBookName());
        book.setAuthorName(addBookRequest.getAuthorName());
        book.setBorrowPrice(addBookRequest.getBorrowPrice());
        book.setShelveNumber(addBookRequest.getShelveNumber());
        book.setNumberAvailable(addBookRequest.getNumberAvailable());
        return book;
    }

    public static BorrowedBooks map(BorrowBookRequest borrowBookRequest, Users user, Books book) {
        BorrowedBooks borrowedBook = new BorrowedBooks();
        borrowedBook.setBorrower(user);
        borrowedBook.setBookName(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setDueDate(borrowBookRequest.getDueDate());
        return borrowedBook;
    }
}
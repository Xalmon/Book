package services;

import data.model.BorrowedBooks;
import data.model.Users;
import dtos.BorrowBookRequest;


import java.util.List;

public interface BorrowedBookService {
    void borrowBook(BorrowBookRequest borrowBookRequest);


    List<BorrowedBooks> findBookBorrowedBy(Users user);
}
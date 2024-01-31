package services;


import data.model.Books;
import data.model.BorrowedBooks;
import data.model.Users;
import dtos.UserLoginRequest;
import dtos.RegisterUserRequest;

import java.util.List;

public interface UserServices {
    void registerUser(RegisterUserRequest registerUserRequest);

    Users login(UserLoginRequest loginRequest);

    Users findUser(String borrower);

    List<Books> checkListOfBook();

    Books searchBook(String bookName,String authorName);

    List<BorrowedBooks> checkListOfBorrowedBooks(String username);
}
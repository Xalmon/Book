package services;


import data.model.Books;
import dtos.AddBookRequest;
import dtos.BorrowBookRequest;
import dtos.RemoveBookRequest;


import java.util.List;

public interface BookService {
    Books addBook(AddBookRequest addBookRequest);

    void remove(RemoveBookRequest removeBookRequest);

    Books findBookToBorrow(BorrowBookRequest borrowBookRequest);

    List<Books> getAllBooks();

    Books findBook(String bookName, String authorName);
}
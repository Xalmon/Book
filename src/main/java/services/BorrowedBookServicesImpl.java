package services;

import data.model.Books;
import data.model.BorrowedBooks;
import data.model.Users;
import data.repository.BorrowedBookRepository;
import dtos.BorrowBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static utils.Mapper.map;

@Service
public class BorrowedBookServicesImpl implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookService bookService;
    private final UserServices userService;

    @Autowired
    public BorrowedBookServicesImpl(
            BorrowedBookRepository borrowedBookRepository,
            BookService bookService,
            UserServices userService) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void borrowBook(BorrowBookRequest borrowBookRequest) {
        Users user = userService.findUser(borrowBookRequest.getBorrower());
        Books book = bookService.findBookToBorrow(borrowBookRequest);

        BorrowedBooks borrowedBook = map(borrowBookRequest, user, book);
        borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public List<BorrowedBooks> findBookBorrowedBy(Users user) {
        return borrowedBookRepository.findBookByBorrower(user);
    }
}
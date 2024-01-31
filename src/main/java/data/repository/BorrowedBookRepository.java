package data.repository;

import data.model.Books;
import data.model.BorrowedBooks;
import data.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedBookRepository extends MongoRepository<BorrowedBooks, String> {
    Optional<BorrowedBooks> findBookByBookNameAndBorrower(Books book, Users user);

    List<BorrowedBooks> findBookByBorrower(Users user);
}
package data.repository;

import data.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Books, String> {
    Optional<Books> findBookByBookNameAndAuthorName(String bookName, String authorName);
}

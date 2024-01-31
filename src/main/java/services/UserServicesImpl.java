package services;

import data.model.Books;
import data.model.BorrowedBooks;
import data.model.Users;
import data.repository.UserRepository;
import dtos.UserLoginRequest;
import dtos.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static utils.UserMapper.map;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowedBookService borrowedBookService;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        checkIfUserAlreadyExists(registerUserRequest.getUsername());
        Users user = map(registerUserRequest);
        userRepository.save(user);
    }

    @Override
    public Users login(UserLoginRequest loginRequest) {
        Optional<Users> user = getUser(loginRequest.getUsername());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if(!user.get().getPassword().equals(loginRequest.getPassword()))
            throw new RuntimeException("Incorrect Password");

        user.get().setLoggedIn(true);
        userRepository.save(user.get());
        return user.get();
    }

    @Override
    public Users findUser(String borrower) {
        Optional<Users> user = getUser(borrower);
        if (user.isEmpty()) throw new RuntimeException("User not found");
        return user.get();
    }

    @Override
    public List<Books> checkListOfBook() {
        return bookService.getAllBooks();
    }

    @Override
    public Books searchBook(String bookName, String authorName) {
        Books book = bookService.findBook(bookName, authorName);
        return book;
    }

    @Override
    public List<BorrowedBooks> checkListOfBorrowedBooks(String username) {
        return borrowedBookService.findBookBorrowedBy(findUser(username));
    }

    private Optional<Users> getUser(String username) {
        Optional<Users> user =  userRepository.findByUsername(username);
        return user;
    }

    private void checkIfUserAlreadyExists(String username) throws CustomException {
        Optional<Users> user = getUser(username);
        if(user.isPresent()) throw new RuntimeException("Username Already Exist, Try Another Username");
    }


}
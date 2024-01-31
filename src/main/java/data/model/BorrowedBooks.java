package data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document("BorrowedBooks")
public class BorrowedBooks {

    private String id;
    @DBRef
    private Books bookName;
    @DBRef
    private Users borrower;
    private LocalDate borrowDate;
    private LocalDate dueDate;

}

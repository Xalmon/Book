package data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Book Users")
public class Users {

    @Id
    private String id;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isLoggedIn;

}
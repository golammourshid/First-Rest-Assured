package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String email;
    private String password;

    public UserModel() {

    }
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

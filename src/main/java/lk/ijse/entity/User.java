package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private  String userName;
    private  String name;
    private  String password;
    private  String userLevel;


    public User(String userName, String password, String userLevel) {
        this.userName = userName;
        this.password = password;
        this.userLevel = userLevel;
    }
}

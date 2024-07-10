package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface RegisterUserBO  extends SuperBO {
    public  String GiveAccesToCreateAccount(UserDTO user ) throws SQLException, ClassNotFoundException;

    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}

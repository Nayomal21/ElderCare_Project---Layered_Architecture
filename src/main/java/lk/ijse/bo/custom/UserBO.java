package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    public  Boolean checkCrendials(UserDTO user ) throws SQLException, ClassNotFoundException ;

    }

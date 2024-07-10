package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemAddBO  extends SuperBO {
    public boolean save(ItemDTO item) throws SQLException, ClassNotFoundException ;
    public String nextId() throws SQLException, ClassNotFoundException ;


}

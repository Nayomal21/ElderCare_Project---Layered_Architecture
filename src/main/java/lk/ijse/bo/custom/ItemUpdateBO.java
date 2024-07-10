package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface ItemUpdateBO extends SuperBO {

    public ItemDTO searchById(String text) throws SQLException, ClassNotFoundException ;
    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException ;

}

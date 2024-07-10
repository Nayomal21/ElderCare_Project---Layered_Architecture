package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ElderDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface ElderBO extends SuperBO {


    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException ;
    public ObservableList<ElderDTO> getTableData() throws SQLException, ClassNotFoundException ;

}

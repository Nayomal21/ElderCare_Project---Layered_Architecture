package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface EmployeeBo extends SuperBO {
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException ;
    public ObservableList<EmployeeDTO> getTableData() throws SQLException, ClassNotFoundException ;

}

package lk.ijse.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Payment;
import lk.ijse.model.DbConnection;
import lk.ijse.model.tm.Saltm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public  lk.ijse.entity.Employee searchByName(String text) throws SQLException, ClassNotFoundException ;
    public  ObservableList<Payment> getPreviousSalary(String empId) throws SQLException, ClassNotFoundException;
public HashSet<String>  getEmployeeNames() throws SQLException, ClassNotFoundException;
    public String getSavedEmployeeIdId() ;
    public  Integer getCount() throws SQLException, ClassNotFoundException;

}

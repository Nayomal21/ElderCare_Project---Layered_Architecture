package lk.ijse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Bill;
import lk.ijse.model.DbConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;

public interface BillDAO extends CrudDAO<Bill> {

    public ObservableList<Bill> getTableData(String elderName) throws SQLException, ClassNotFoundException;
    public BigDecimal getMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException ;
    public  BigDecimal getItemMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException;

}

package lk.ijse.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;
import lk.ijse.model.DbConnection;
import lk.ijse.model.tm.DashBoardtm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {

    public ObservableList<lk.ijse.entity.Payment> getPreviousPayments(int[] feeId) throws SQLException, ClassNotFoundException;

    public boolean saveEmployeePayment(Payment dto) throws SQLException, ClassNotFoundException;

    public ArrayList<BigDecimal> getIncomeAndExpence(Year year, Month month) throws SQLException, ClassNotFoundException;

}
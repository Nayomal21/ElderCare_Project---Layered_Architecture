package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.model.tm.DashBoardtm;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public interface DashBoardTaskBO extends SuperBO {
    public  ObservableList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException;
    public    ObservableList<DashBoardtm> getUpComingPayment(LocalDate date) throws SQLException, ClassNotFoundException;
    public  ObservableList<ItemDTO> getBarChartData() throws SQLException, ClassNotFoundException;
    public  Integer getElderCount() throws SQLException, ClassNotFoundException;

    public  Integer getEmployeeCount() throws SQLException, ClassNotFoundException;

    public  BigDecimal getMonthTotalElderBills(Year year, Month month) throws SQLException, ClassNotFoundException;

    public  BigDecimal getItemMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException;
    public  ArrayList<BigDecimal> getIncomeAndExpence(Year year, Month month) throws SQLException, ClassNotFoundException;




    }

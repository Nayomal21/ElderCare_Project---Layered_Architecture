package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public interface ReportBO extends SuperBO {

    public BigDecimal getMonthTotalBiil(Year year, Month month) throws SQLException, ClassNotFoundException ;
    public ArrayList<BigDecimal> getIncomeAndExpenceOfPayments(Year year, Month month) throws SQLException, ClassNotFoundException ;

    }

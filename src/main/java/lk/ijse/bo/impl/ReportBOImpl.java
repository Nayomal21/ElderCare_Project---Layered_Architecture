package lk.ijse.bo.impl;

import lk.ijse.bo.custom.ReportBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BillDAO;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.impl.BillDAOImpl;
import lk.ijse.dao.impl.PaymentDAOImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class ReportBOImpl implements ReportBO {
    BillDAO billDAO = (BillDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.BILL);

    PaymentDAO paymentBo = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.PAYMENT);

    @Override
    public BigDecimal getMonthTotalBiil(Year year, Month month) throws SQLException, ClassNotFoundException {
    return  billDAO.getMonthTotal(year,month);
    }

    public  ArrayList<BigDecimal> getIncomeAndExpenceOfPayments(Year year, Month month) throws SQLException, ClassNotFoundException {
      return    paymentBo.getIncomeAndExpence(year,month);
    }

    }

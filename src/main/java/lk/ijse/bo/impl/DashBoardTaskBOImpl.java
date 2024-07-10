package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import lk.ijse.bo.custom.DashBoardTaskBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.impl.*;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Fee;
import lk.ijse.entity.Item;
import lk.ijse.entity.Payment;
import lk.ijse.model.tm.DashBoardtm;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class DashBoardTaskBOImpl  implements DashBoardTaskBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.PAYMENT);
    ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);
    FeeDAO feeDAO = (FeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.FEE);
    BillDAO billDAO = (BillDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.BILL);

    @Override
    public ObservableList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException {
        ObservableList<Payment> tableData = paymentDAO.getTableData();
        ObservableList<PaymentDTO> list = FXCollections.observableArrayList();

        for (Payment payment : tableData){
            list.add(new PaymentDTO(payment.getDate(),payment.getAmount()));



        }
        return  list;
    }

    @Override
    public ObservableList<DashBoardtm> getUpComingPayment(LocalDate date) throws SQLException, ClassNotFoundException {

        ObservableList<DashBoardtm> list = FXCollections.observableArrayList();
        ObservableList<Fee> upComingPayment = feeDAO.getUpComingPayment(date);
       for (Fee fee : upComingPayment){

           list.add(new DashBoardtm(elderDAO.searchById(fee.getElderId()).getLastName(),
                   fee.getMonth().toString(), fee.getTotal().toString(), new Button("pay") ));
       }

       return  list;
    }

    @Override
    public ObservableList<ItemDTO> getBarChartData() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        ObservableList<Item> barChartData = itemDAO.getBarChartData();
        ObservableList<ItemDTO> list = FXCollections.observableArrayList();
        for (Item item : barChartData){
            list .add(new ItemDTO(item.getItemId(),
                    item.getDescription(),
                    item.getType(),
                    item.getQtyOnHand(),
                    item.getUnitPrice()));


        }

        return  list;
    }

    @Override
    public Integer getElderCount() throws SQLException, ClassNotFoundException {
      return   elderDAO.getCount();
    }

    @Override
    public Integer getEmployeeCount() throws SQLException, ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
     return    employeeDAO.getCount();
    }

    @Override
    public BigDecimal getMonthTotalElderBills(Year year, Month month) throws SQLException, ClassNotFoundException {
      return   billDAO.getMonthTotal(year, month);
    }

    @Override
    public BigDecimal getItemMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException {
       return  billDAO.getItemMonthTotal(year,month);
    }

    @Override
    public ArrayList<BigDecimal> getIncomeAndExpence(Year year, Month month) throws SQLException, ClassNotFoundException {
        return  paymentDAO.getIncomeAndExpence(year,month);
    }
}

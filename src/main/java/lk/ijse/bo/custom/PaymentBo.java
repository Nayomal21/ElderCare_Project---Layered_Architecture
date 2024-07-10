package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.*;
import lk.ijse.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface PaymentBo extends SuperBO {

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException ;

    public EmployeeDTO searchEmployeeName(String text) throws SQLException, ClassNotFoundException ;

    public void loadElderData() throws SQLException, ClassNotFoundException ;

    public void loadEmployeeData() throws SQLException, ClassNotFoundException ;
    public ElderDTO searchElderName(String text) throws SQLException, ClassNotFoundException ;
    public  ObservableList<FeeDTO> getPreviousFees(String elderId) throws SQLException, ClassNotFoundException;

    public ObservableList<PaymentDTO> getPreviousPayments(int[] feeId) throws SQLException, ClassNotFoundException ;
    public  ObservableList<PaymentDTO> getPreviousSalary(String empId) throws SQLException, ClassNotFoundException ;

    public  int getFeeIdByMonth(int value, String elderId) throws SQLException, ClassNotFoundException ;
    public boolean saveEmployeePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException ;


   public    HashSet<String> getElderNames() throws SQLException, ClassNotFoundException;
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException ;

    }

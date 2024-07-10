package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.PaymentBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.FeeDAO;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.impl.ElderDAOImpl;
import lk.ijse.dao.impl.EmployeeDAOImpl;
import lk.ijse.dao.impl.FeeDAOImpl;
import lk.ijse.dao.impl.PaymentDAOImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Elder;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Fee;
import lk.ijse.entity.Payment;

import java.sql.SQLException;
import java.util.HashSet;

public class PaymentBOImpl implements PaymentBo {
            ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);
         PaymentDAO  paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.PAYMENT);
    FeeDAO feeDAO = (FeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.FEE);

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.EMPLOYEE);

    @Override
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
        return    employeeDAO.getEmployeeNames();
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {

        return  paymentDAO.save(new lk.ijse.entity.Payment(
                dto.getPaymentId(),
                dto.getDate(),
                dto.getDescription(),
                dto.getEmpld(),
                dto.getAmount(),
                dto.getResponsiblePerson(),
                dto.getMonth()

        ));




    }
    public boolean saveEmployeePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {

        return  paymentDAO.saveEmployeePayment(new lk.ijse.entity.Payment(
                dto.getPaymentId(),
                dto.getDate(),
                dto.getDescription(),
                dto.getEmpld(),
                dto.getAmount(),
                dto.getResponsiblePerson(),
                dto.getMonth()

        ));




    }

    @Override
    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException {
        return  elderDAO.getElderNameList();
    }

    @Override
    public EmployeeDTO searchEmployeeName(String text) throws SQLException, ClassNotFoundException {
        EmployeeDAO   employeeDAO = new EmployeeDAOImpl();
        Employee employee =  employeeDAO.searchByName(text);
        System.out.println(employee.toString());
        return new lk.ijse.dto.EmployeeDTO(
                employee.getEmpId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSurName(),
                employee.getNic(),
                employee.getPastWorkingdetails(),
                employee.getDob(),
                employee.getAddress(),
                employee.getTel(),
                employee.getAdmitDate(),
                employee.getSalary(),
                employee.getType()
        );



    }


    @Override
    public void loadElderData() throws SQLException, ClassNotFoundException {
        elderDAO.getTableData();
    }

    @Override
    public void loadEmployeeData() throws SQLException, ClassNotFoundException {


        employeeDAO.getTableData();
    }

    @Override
    public ElderDTO searchElderName(String text) throws SQLException, ClassNotFoundException {
        Elder elder =  elderDAO.searchByName(text);
        System.out.println(elder.toString());
        return new ElderDTO(
                elder.getElderId(),
                elder.getFirstName(),
                elder.getLastName(),
                elder.getSurName(),
                elder.getNic(),
                elder.getAddress(),
                elder.getTel(),
                elder.getMedicalHistory(),
                elder.getMonthlyFee(),
                elder.getAdvanceFee(),
                elder.getAdmitDate(),
                elder.getDob(),
                elder.getPaymentDate(),
                elder.getEmail()
        );

    }

    @Override
    public ObservableList<FeeDTO> getPreviousFees(String elderId) throws SQLException, ClassNotFoundException {
        ObservableList<Fee> list =     feeDAO.getPreviousFees(elderId);
        ObservableList<FeeDTO> oblist = FXCollections.observableArrayList();
        for (Fee fee : list){

            oblist.add(new FeeDTO(fee.getFeeId(),fee.getMonth(), fee.getTotal()));

        }
        return  oblist;
    }

    @Override
    public ObservableList<PaymentDTO> getPreviousPayments(int[] feeId) throws SQLException, ClassNotFoundException {
        ObservableList<Payment> list = paymentDAO.getPreviousPayments(feeId);
        ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList();

        for (Payment payment : list){

            observableList.add(new PaymentDTO((payment.getPaymentId()),payment.getAmount()));


        }
        return  observableList;
    }

    @Override
    public ObservableList<PaymentDTO> getPreviousSalary(String empId) throws SQLException, ClassNotFoundException {
        ObservableList<Payment> list = employeeDAO.getPreviousSalary(empId);
        ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList();

        for (Payment payment : list){
            observableList.add(new PaymentDTO((payment.getMonth()),payment.getAmount()));


        }
        return  observableList;  
        
        
        
    }

    @Override
    public int getFeeIdByMonth(int value, String elderId) throws SQLException, ClassNotFoundException {
        return feeDAO.getFeeIdByMonth(value,elderId);
    }

}

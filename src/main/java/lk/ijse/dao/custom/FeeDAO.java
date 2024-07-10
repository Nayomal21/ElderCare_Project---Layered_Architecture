package lk.ijse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Fee;

import java.sql.SQLException;
import java.time.LocalDate;

public interface FeeDAO extends CrudDAO<Fee> {
    public boolean updateMedicalFees(Fee fee) throws SQLException, ClassNotFoundException;
    public  ObservableList<lk.ijse.entity.Fee> getPreviousFees(String elderId) throws SQLException, ClassNotFoundException;
    public  int getFeeIdByMonth(int value, String elderId) throws SQLException, ClassNotFoundException;
    public ObservableList<Fee> getUpComingPayment(LocalDate date) throws SQLException, ClassNotFoundException;



}

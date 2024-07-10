package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.FeeDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Fee;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FeeDAOImpl  implements FeeDAO {

    @Override
    public boolean update(Fee fee) throws SQLException, ClassNotFoundException {
return  SQLUtil.execute("UPDATE Fee SET chargesForGoods = ?+chargesForGoods WHERE elderId= ? order by  yearMonth desc limit 1 ",
        fee.getTotal(),
        fee.getElderId());


    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Fee> getTableData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Fee fee) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute( "INSERT INTO Fee ( elderId, yearMonth,emailStatus)  VALUES ( ?,? ,'notSent' )",
        fee.getElderId(),fee.getMonth());

    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Fee searchById(String text) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateMedicalFees(Fee fee) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE Fee SET medicalFees = ?+medicalFees WHERE elderId= ? order by  yearMonth desc limit 1 ",
                fee.getTotal(),
                fee.getElderId());
    }

    @Override
    public ObservableList<Fee> getPreviousFees(String elderId) throws SQLException, ClassNotFoundException {
        ObservableList<lk.ijse.entity.Fee> oblist = FXCollections.observableArrayList();

         ResultSet resultSet  = SQLUtil.execute("SELECT  feeId,yearMonth,totalAmount FROM Fee WHERE elderId = ? order by yearMonth  limit 5",elderId);

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            Date date = resultSet.getDate(2);
            Double amount =  resultSet.getDouble(3);

            oblist.add(new lk.ijse.entity.Fee(id, date.toLocalDate(),amount));


        }
        return oblist;
    }

    @Override
    public  int getFeeIdByMonth(int value, String elderId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT feeId FROM Fee WHERE EXTRACT(MONTH FROM yearMonth) = ? AND elderId = ?",
                value,elderId);

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return  0;
    }

    @Override
    public ObservableList<Fee> getUpComingPayment(LocalDate date) throws SQLException, ClassNotFoundException {
        ObservableList<Fee> observableList = FXCollections.observableArrayList();
        System.out.println(date.minusMonths(1)+"==========="+ Date.valueOf(date.plusDays(5)));
        System.out.println(observableList);
        ResultSet resultSet  =  SQLUtil.execute("SELECT * from Fee where DAY(yearMonth) between ? and ?",


              date.getDayOfMonth(),
       (String.valueOf(date.plusDays(3).getDayOfMonth()))


        );





        while (resultSet.next()){

            String elderID =resultSet.getNString("elderId");
            Date yearMonth = resultSet.getDate("yearMonth");
            yearMonth.setMonth(LocalDate.now().getMonthValue()-1);
            Double totalAmount = resultSet.getDouble("totalAmount");



            Fee dtm = new Fee(elderID, yearMonth.toLocalDate(), totalAmount);
            observableList.add(dtm);

        }

        return  observableList;
    }
}

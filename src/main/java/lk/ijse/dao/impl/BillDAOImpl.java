package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.BillDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Bill;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;

public class BillDAOImpl implements BillDAO {
    @Override
    public boolean update(Bill entity) throws SQLException {
        return false;
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<lk.ijse.entity.Bill> getTableData() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =   SQLUtil.execute("SELECT description,date,total,location FROM Bill WHERE type = 'Item'");

        ObservableList<Bill> obList = FXCollections.observableArrayList();


        while (resultSet.next()) {

            String description = resultSet.getString(1);
            LocalDate date = resultSet.getDate(2).toLocalDate();
            Double total = resultSet.getDouble(3);
            String location = resultSet.getString(4);




           obList.add( new Bill(Date.valueOf(date),total,description,location));


        }
        return obList;


    }

    @Override
    public boolean save(Bill entity) throws SQLException, ClassNotFoundException {
        return     SQLUtil.execute( "INSERT INTO Bill VALUES (?, ?, ?, ?, ?, ?, ?)",entity.getId(),
                entity.getDate(),
                entity.getElderId(),
                entity.getTotal(),
                entity.getDescription(),
                entity.getType(),
                entity.getLocation());
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT billId FROM Bill order by billId desc limit 1");

             resultSet.next();



    String currentId =   resultSet.getString(1);
        if (currentId != null) {
            String[] split = currentId.split("B");  //" ", "2"
            System.out.println(Arrays.toString(split));
            int idNum = Integer.parseInt(split[1]);
            return "B" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);
            // return "B"+ ++idNum;
        }
        return "B001";

    }

    @Override
    public Bill searchById(String text) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ObservableList<Bill> getTableData(String elderName) throws SQLException, ClassNotFoundException {
        ObservableList<Bill> obList = FXCollections.observableArrayList();



        ResultSet resultSet  = SQLUtil.execute("SELECT description,date,total,location FROM Bill  WHERE elderId = ?", elderName);
        while (resultSet.next()) {

            String description = resultSet.getString(1);
            LocalDate date = resultSet.getDate(2).toLocalDate();
            Double total = resultSet.getDouble(3);
            String location = resultSet.getString(4);



           Bill bill = new Bill(Date.valueOf(date),total,description,location);
            obList.add(bill);
        }
        return obList;



    }
@Override
    public  BigDecimal getMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException {

    int  monthNum = month.getValue();
    int yearNum = year.getValue();
    String sql = "SELECT    SUM(total)  FROM    Bill  WHERE    MONTH(date) = ?   AND YEAR(date) =? and type = 'Elder' ";

    ResultSet resultSet =SQLUtil.execute(sql,monthNum,yearNum);

    if(resultSet.next()) {
        double aDouble = resultSet.getDouble(1);

        return BigDecimal.valueOf(aDouble);
    }
    return  null;

}

    @Override
    public BigDecimal getItemMonthTotal(Year year, Month month) throws SQLException, ClassNotFoundException {

            int  monthNum = month.getValue();
            int yearNum = year.getValue();
            String sql = "SELECT    SUM(total)  FROM    Bill  WHERE    MONTH(date) = ?   AND YEAR(date) =? and type = 'Item' ";


            ResultSet resultSet = SQLUtil.execute(sql,monthNum,yearNum);

            if(resultSet.next()) {
                double aDouble = resultSet.getDouble(1);

                return BigDecimal.valueOf(aDouble);
            }
            return  null;
        }




}

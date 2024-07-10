package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Payment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class PaymentDAOImpl  implements PaymentDAO {

    @Override
    public boolean update(Payment eto) throws SQLException {
        return false;
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Payment> getTableData() throws SQLException, ClassNotFoundException {
        ObservableList<lk.ijse.entity.Payment> oblist = FXCollections.observableArrayList();

        String sql = "SELECT date,amount FROM Payment WHERE feeId is not null and  EXTRACT(MONTH FROM date) = ? order by date;";


        ResultSet resultSet = SQLUtil.execute(sql,4);

        while (resultSet.next()){

            Date date = resultSet.getDate(1);
            Double amount = resultSet.getDouble(2);

            oblist.add(new lk.ijse.entity.Payment(date,amount));




        }

        return  oblist;    }

    @Override
    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment VALUES  (?,?,?,?,?,?,?,?)",nextId(),
              payment.getDate(),
                payment.getDescription(),
                payment.getEmpld(),
                payment.getFeeld(),
                payment.getAmount(),
                payment.getResponsiblePerson(),
                payment.getMonth());
    }



    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT payId FROM Payment order by payId desc limit 1");

        resultSet.next();



        String currentId =   resultSet.getString(1);
        if (currentId != null) {
            String[] split = currentId.split("P");  //" ", "2"
            System.out.println(Arrays.toString(split));
            int idNum = Integer.parseInt(split[1]);
            return "P" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);
            // return "P"+ ++idNum;
        }
        return "P001";

    }


    @Override
    public Payment searchById(String text) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Payment> getPreviousPayments(int[] feeId) throws SQLException, ClassNotFoundException {

            ObservableList<lk.ijse.entity.Payment> oblist = FXCollections.observableArrayList();

            for (int i :feeId){
ResultSet resultSet = SQLUtil.execute("SELECT feeId,amount FROM Payment WHERE feeId = ?",i);

                int id=0;
                Double amount=0.0;
                while (resultSet.next()) {
                    amount +=  resultSet.getDouble(2);
                    id = resultSet.getInt(1);



                }oblist.add(new lk.ijse.entity.Payment(id, amount));
            } return oblist;
        }

    @Override
    public boolean saveEmployeePayment(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment(payId,date,description,empId,amount,responsiblePerson,month) VALUES  (?,?,?,?,?,?,?)",nextId(),
                payment.getDate(),
                payment.getDescription(),
                payment.getEmpld(),
                payment.getAmount(),
                payment.getResponsiblePerson(),
                payment.getMonth());    }

    @Override
    public ArrayList<BigDecimal> getIncomeAndExpence(Year year, Month month) throws SQLException, ClassNotFoundException {
        ArrayList<BigDecimal> list = new ArrayList<>();
        int years = year.getValue();
        int months = month.getValue();

        String sql = "SELECT \n" +
                "  MONTH(date) AS month, \n" +
                "  SUM(CASE WHEN empId IS NULL THEN amount ELSE 0 END) AS income,\n" +
                "  SUM(CASE WHEN empId IS NOT NULL THEN amount ELSE 0 END) AS expense\n" +
                "FROM \n" +
                "  Payment \n" +
                "WHERE \n" +
                "  MONTH(date) = ?\n" +
                "and YEAR(date)=? \n" +
                "GROUP BY \n" +
                "  MONTH(date) \n" +
                "ORDER BY \n" +
                "  month;";


        ResultSet resultSet = SQLUtil.execute(sql,months,years);

        if (resultSet.next()){

            Double income = resultSet.getDouble("income");
            Double expense = resultSet.getDouble("expense");

            System.out.println(income+"+"+expense);

            BigDecimal income2 = BigDecimal.valueOf(income);
            BigDecimal expense2= BigDecimal.valueOf(expense);
            list.add(income2);
            list.add(expense2);

        }

        return list;
    }


}

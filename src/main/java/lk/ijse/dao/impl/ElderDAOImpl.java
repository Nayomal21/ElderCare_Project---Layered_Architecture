package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Elder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class ElderDAOImpl implements ElderDAO {
    public  HashSet<String> names ;
private  static  String savedElderId;
    @Override
    public boolean update(Elder elder) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE  Elder  SET firstName = ?, lastName= ?,surname=?" +
                ",nic=?,address=?,tel = ?" +
                ",dob=?,admitDate=?,paymentDate=?" +
                ",advanceFee = ?,monthlyFee=?,medicalHistory=? ,emailAddress = ? WHERE  elderId = ?";
        return  SQLUtil.execute(sql,
                elder.getFirstName(),
                elder.getLastName(),
                elder.getSurName(),
                elder.getNic(),
                elder.getAddress(),
                elder.getTel(),
                elder.getDob(),
                elder.getAdmitDate(),
                elder.getPaymentDate(),
                elder.getAdvanceFee(),
                elder.getMonthlyFee(),
                elder.getMedicalHistory(),
                elder.getEmail(),
                elder.getElderId()

                );
    }

    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE  Elder SET  status = 'deactive' WHERE  elderId= ?",id);
    }

    @Override
    public ObservableList<Elder> getTableData() throws SQLException, ClassNotFoundException {
        names= new HashSet<>();
        ResultSet resultSet = SQLUtil.execute("SELECT firstName,lastName,surname,dob,monthlyFee,elderId FROM Elder WHERE  status = 'active'");
        ObservableList<Elder> obList = FXCollections.observableArrayList();


        while (resultSet.next()) {

            String firstName = resultSet.getString(1);
            String lastName = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String elderId =resultSet.getString(6);
            names.add(firstName + "  " + lastName + " " + (surname != null ? surname : ""));
            Date dob = resultSet.getDate("dob");
            Double fee = resultSet.getDouble("monthlyFee");



            obList.add(new Elder(elderId,firstName,lastName,surname,fee,dob));

    }
    return obList;
    }

    @Override
    public boolean save(Elder elder) throws SQLException, ClassNotFoundException {
       return  SQLUtil.execute( "INSERT INTO Elder VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'active')",

         nextId(),
        elder.getFirstName(),
        elder.getLastName(),
        elder.getSurName(),
        elder.getNic(),
        elder.getAddress(),
        elder.getTel(),
        elder.getDob(),
        elder.getAdmitDate(),
         elder.getPaymentDate(),
         elder.getAdvanceFee(),
         elder.getMonthlyFee(),
         elder.getMedicalHistory(),
         elder.getEmail());




    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute(("SELECT elderId FROM Elder order by elderId desc limit 1"));

        if (resultSet.next()) {

                String currentId =resultSet.getString(1);
                String[] split = currentId.split("E");  //" ", "2"
                System.out.println(Arrays.toString(split));
                int idNum = Integer.parseInt(split[1]);

                savedElderId =  "E" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);
              return savedElderId;
                // return "E"+ ++idNum;
            }


        else {
            savedElderId = "E001";
            return savedElderId;
        }
    }

    @Override
    public Elder searchById(String text) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Elder WHERE  status = 'active' and elderId = ?",text);

        if (resultSet.next()) {
            System.out.println(resultSet.getString("elderId"));
            lk.ijse.entity.Elder elder = new lk.ijse.entity.Elder
                    (resultSet.getString("elderId"),resultSet.getNString("firstName"), resultSet.getNString("lastName"), resultSet.getNString("surname"),
                            resultSet.getNString("nic"), resultSet.getNString("address"),
                            resultSet.getNString("tel"),
                            resultSet.getNString("medicalHistory"),
                            resultSet.getDouble("monthlyFee"), resultSet.getDouble("advanceFee"),
                            resultSet.getDate("admitDate"),resultSet.getDate("dob"),resultSet.getDate("paymentDate"),resultSet.getNString("emailAddress"));


            return elder;

        }
return  null;
    }
     @Override
    public  lk.ijse.entity.Elder searchByName(String text) throws SQLException, ClassNotFoundException {



        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Elder WHERE  status = 'active'");

        while (resultSet.next()) {
            String name = resultSet.getString(2) + "  " + resultSet.getString(3) + " " + (resultSet.getString(4) != null ? resultSet.getString(4) : "");
            System.out.println(name +"-"+text);
            if (text.equals(name)) {
                System.out.println(resultSet.getString("elderId"));
                lk.ijse.entity.Elder elder = new lk.ijse.entity.Elder
                        (resultSet.getString("elderId"),resultSet.getNString("firstName"), resultSet.getNString("lastName"), resultSet.getNString("surname"),
                                resultSet.getNString("nic"), resultSet.getNString("address"),
                                resultSet.getNString("tel"),
                                resultSet.getNString("medicalHistory"),
                                resultSet.getDouble("monthlyFee"), resultSet.getDouble("advanceFee"),
                                resultSet.getDate("admitDate"),resultSet.getDate("dob"),resultSet.getDate("paymentDate"),resultSet.getNString("emailAddress"));


                return elder;

            }
        }
        return  null;
    }

    @Override
    public HashSet<String> getElderNameList() throws SQLException, ClassNotFoundException {
        getTableData();
        return names;
    }

    @Override
    public String getEdlerId() {
        return savedElderId;
    }

    @Override
    public Integer getCount() throws SQLException, ClassNotFoundException {


            ResultSet resultSet = SQLUtil.execute("SELECT count(*) from Elder WHERE  status = 'active'");
            if (resultSet.next())  return  resultSet.getInt(1);
            return  null;

    }
}

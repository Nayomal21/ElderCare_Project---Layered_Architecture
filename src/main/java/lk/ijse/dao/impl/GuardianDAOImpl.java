package lk.ijse.dao.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.custom.GuardianDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Guardian;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class GuardianDAOImpl  implements GuardianDAO {

    @Override
    public boolean update(Guardian guardian) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE  Guardian SET  firstName=?,lastName=?,surname=?,relation=?,nic=?,address=?,tel=? WHERE elderId = ?   OR empId = ?",

                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getSurName(),
                guardian.getRelation(),
                guardian.getNIC(),
                guardian.getAddress(),
                guardian.getTel(),
                guardian.getElderId(),
                guardian.getEmpId());
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Guardian> getTableData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Guardian guardian) throws SQLException, ClassNotFoundException {
      return SQLUtil.execute( "INSERT INTO Guardian VALUES (?,?,?,?,?,?,?,?,?,?)",
        nextId(),
       guardian.getFirstName(),
       guardian.getLastName(),
       guardian.getSurName(),
       guardian.getRelation(),
       guardian.getElderId(),
      guardian.getEmpId(),
       guardian.getNIC(),
       guardian.getAddress(),
        guardian.getTel());

    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(("SELECT guardianId FROM Guardian order by guardianId desc limit 1"));

        if (resultSet.next()) {

            String currentId =resultSet.getString(1);
            String[] split = currentId.split("G");  //" ", "2"
            System.out.println(Arrays.toString(split));
            int idNum = Integer.parseInt(split[1]);

            return    "G" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);

            // return "E"+ ++idNum;
        }


        else {
            return  "G001";

        }
    }

    @Override
    public Guardian searchById(String text) throws SQLException, ClassNotFoundException {



        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guardian WHERE  elderId = ?  or empId=?",text,text);
        if (resultSet.next()) {

           Guardian guardian = new Guardian(
                    resultSet.getNString("firstName"),
                    resultSet.getNString("lastName"),
                    resultSet.getNString("surname"),
                    resultSet.getNString("relation"),
                    resultSet.getNString("nic"),
                    resultSet.getNString("address"),
                    resultSet.getNString("tel"));


            return guardian;

        }

        return null;
    }
}

package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class EmployeeDAOImpl implements EmployeeDAO {
    private   HashSet<String> names ;
    private  String savedEmployeeId;

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
       return  SQLUtil.execute("UPDATE  Employee  SET firstName = ?, lastName= ?,surname=?" +
                ",nic=?,address=?,tel = ?" +
                ",dob=?,admitDate=?,pastWorkingdetails=?" +
                ",salary = ?,type=? WHERE  empId = ?",

        employee.getFirstName(),
        employee.getLastName(),
        employee.getSurName(),
        employee.getNic(),
        employee.getAddress(),
        employee.getTel(),
        employee.getDob(),
        employee.getAdmitDate(),
        employee.getPastWorkingdetails(),
         employee.getSalary(),
        employee.getType(),
        employee.getEmpId());


    }

    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE  Employee SET  status = 'deactive' WHERE  empId= ?",id);
    }

    @Override
    public ObservableList<Employee> getTableData() throws SQLException, ClassNotFoundException {

        names= new HashSet<>();


        ResultSet resultSet = SQLUtil.execute("SELECT empId, firstName,lastName,surname,salary FROM Employee WHERE  status = 'active'");

        ObservableList<Employee> obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            String empId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String surname = resultSet.getString(4);
            Double salary = resultSet.getDouble(5);
           String fullName = firstName + "  " + lastName + " " + (surname != null ? surname : "");


            obList.add ( new Employee(empId,firstName,lastName,surname , salary));

            names.add(fullName);



        }

    return  obList;
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute( "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,'active')",

       nextId(),
        employee.getFirstName(), employee.getLastName(),
        employee.getSurName(),
         employee.getNic(),
         employee.getPastWorkingdetails(),
          employee.getDob(),
         employee.getAddress(),
        employee.getTel(),
        employee.getAdmitDate(),
        employee.getSalary(),
        employee.getType());


    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(("SELECT empId FROM Employee order by empId desc limit 1"));

        if (resultSet.next()) {

            String currentId =resultSet.getString(1);
            String[] split = currentId.split("Emp");  //" ", "2"
            System.out.println(Arrays.toString(split));
            int idNum = Integer.parseInt(split[1]);

            savedEmployeeId =  "Emp" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);
            return savedEmployeeId;
            // return "E"+ ++idNum;
        }


        else {
            savedEmployeeId = "Emp001";
            return savedEmployeeId;
        }
    }

    @Override
    public Employee searchById(String text) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public  lk.ijse.entity.Employee searchByName(String text) throws SQLException, ClassNotFoundException {



        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE  status = 'active'");

         while (resultSet.next()) {
            String name = resultSet.getString(2) + "  " + resultSet.getString(3) + " " + (resultSet.getString(4) != null ? resultSet.getString(4) : "");
            System.out.println(name +"-"+text);
            if (text.equals(name)) {
                System.out.println(resultSet.getString("empId"));
                lk.ijse.entity.Employee employee = new lk.ijse.entity.Employee
                        (      resultSet.getString("empId"),
                                resultSet.getNString("firstName"),
                                resultSet.getNString("lastName"),
                                resultSet.getNString("surname"),
                                resultSet.getNString("nic"),
                                resultSet.getNString("pastWorkingdetails")
                                ,resultSet.getDate("dob")
                                ,resultSet.getNString("address"),
                                resultSet.getNString("tel")
                                ,resultSet.getDate("admitDate"),
                                resultSet.getDouble("salary")
                                ,resultSet.getNString("type")
                                ,resultSet.getNString("status")
                        );


                return employee;

            }
        }
        return  null;

    }

    @Override
    public  ObservableList<Payment> getPreviousSalary(String empId) throws SQLException, ClassNotFoundException {

        ObservableList<Payment> oblist = FXCollections.observableArrayList();


       ResultSet resultSet =  SQLUtil.execute("SELECT amount,month FROM Payment WHERE empId = ?",empId);


        String id=null;
        while (resultSet.next()) {
            String month = resultSet.getString(2);

            oblist.add(new Payment(month,resultSet.getDouble(1)));



        }
        return oblist;
    }

    @Override
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
        getTableData();
        return  names;
    }
    @Override
    public String getSavedEmployeeIdId() {
        return savedEmployeeId;
    }

    @Override
    public Integer getCount() throws SQLException, ClassNotFoundException {

            ResultSet resultSet = SQLUtil.execute("SELECT count(*) from Employee WHERE  status = 'active'");
            if (resultSet.next())  return  resultSet.getInt(1);
            return  null;

    }

}

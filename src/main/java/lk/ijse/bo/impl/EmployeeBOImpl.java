package lk.ijse.bo.impl;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.EmployeeBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.impl.EmployeeDAOImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;
import lk.ijse.model.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EmployeeBOImpl implements EmployeeBo {
        EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.EMPLOYEE);
    @Override
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
     return    employeeDAO.getEmployeeNames();
    }

    @Override
    public ObservableList<EmployeeDTO> getTableData() throws SQLException, ClassNotFoundException {
        ObservableList<Employee> list = employeeDAO.getTableData();
        ObservableList<EmployeeDTO> obList = FXCollections.observableArrayList();

        for (Employee employee : list){

            String empId = employee.getEmpId();
            String firstName = employee.getFirstName();
            String lastName = employee.getLastName();
            String surname = employee.getSurName();
            Double salary = employee.getSalary();

            JFXButton view = new JFXButton("View");
            view.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(20);
            x.setFitWidth(20);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderAdd_pic/view2.png"));
            view.setGraphic(x);
            view.setCursor(Cursor.HAND);
            view.setOnAction(actionEvent -> {
                System.out.println("clicked view thred");

                try {
                    JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/EmployeeDetails.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                    Map<String,Object> data = new HashMap<>();
                    System.out.println(empId);
                    data.put("getEmployeeId",empId);

                    JasperPrint jasperPrint =
                            null;

                    jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);


                } catch (JRException e) {
                    throw new RuntimeException(e);
                }












            });
            //print on action set printable details of this

            obList.add ( new EmployeeDTO(firstName,lastName,surname , salary,view));






        }
        return obList;

    }
    }


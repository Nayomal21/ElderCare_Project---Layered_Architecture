package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.impl.EmployeeBOImpl;
import lk.ijse.bo.custom.EmployeeBo;

import lk.ijse.dto.EmployeeDTO;
import lk.ijse.model.DbConnection;

import lk.ijse.model.tm.Employeetm;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

import java.util.HashMap;

public class EmployeeSearchFormController {

    private  static ObservableList<Employeetm> obList;
    @FXML
    private JFXButton btnButton;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrint;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<Employeetm> tblEmployee;

    @FXML
    private TextField txtName;
        EmployeeBo employeeBo = (EmployeeBo) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.EMPLOYEE);
    public  void  initialize(){

        try {
            setCellValueFactory();
            loadEmployeeData();
           TextFields.bindAutoCompletion(txtName,employeeBo.getEmployeeNames());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void loadEmployeeData() {

         obList = FXCollections.observableArrayList();
        try {


            obList= getTableType(employeeBo.getTableData());

            tblEmployee.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private ObservableList<Employeetm> getTableType(ObservableList<EmployeeDTO> tableData) {


        ObservableList<Employeetm> observableList = FXCollections.observableArrayList();
        for (EmployeeDTO employeeDTO : tableData){
            observableList.add(new Employeetm(employeeDTO.getFirstName() + "  " + employeeDTO.getLastName() + " " + (employeeDTO.getSurName())
                  ,employeeDTO.getSalary(),employeeDTO.getView()));
        }
        return  observableList;
    }

    @FXML
    void btnButtonOnAction(ActionEvent event) {
        int rawnum = 0;

        for (int i = 0; i <obList.size(); i++) {
            System.out.println( obList.get(i).getName());

          if ( obList.get(i).getName().equals(txtName.getText())){
              System.out.println("in");

              rawnum = i;
              break;

          }

        }
        System.out.println(rawnum);
        tblEmployee.getSelectionModel().select(rawnum);


    }

    private void setCellValueFactory() {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSalary.setCellValueFactory(new  PropertyValueFactory<>("salary"));
        colPrint.setCellValueFactory(new PropertyValueFactory<>("Print"));

    }


    public void btnReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/EmployeeList.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);





            JasperPrint jasperPrint =
                    null;

            jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap<>(), DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException e) {
            throw new RuntimeException(e);
        }




    }
}

package lk.ijse.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeUpdateBO;
import lk.ijse.bo.impl.EmployeeUpdateBOImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.GuardianDTO;


import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeUpdateFormController {
    @FXML
    private DatePicker DpAdmitDate;

    @FXML
    private DatePicker DpDOB;

    @FXML
    private TextField txtAddress;

    @FXML
    private JFXTextArea txtArea;

    @FXML
    private TextField txtFFirstName;

    @FXML
    private TextField txtFLastName;

    @FXML
    private TextField txtFNIC;

    @FXML
    private TextField txtFSurName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtFtel;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRelation;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSurName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtType;

    @FXML
    private JFXTextArea txtworkingDetails;

        EmployeeUpdateBO employeeUpdateBO = (EmployeeUpdateBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.EMPLOYEEUPDATE);
    public void initialize(){


        try {
            TextFields.bindAutoCompletion(txtName, employeeUpdateBO.getEmployeeNames());

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    @FXML
    void BtnAttrachOnAction(ActionEvent event)  {


                String location = getEmployeeFolderLocation(txtName.getText());





                    new Thread(() -> {
                        String command = "open " + location;
                        try {
                            Runtime.getRuntime().exec(command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();



                }


    private String getEmployeeFolderLocation(String text)  {

        try {
            return  "/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Employee/" + employeeUpdateBO.searchByEmployeeName(text).getEmpId() +"/";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void bntSearchOnAction(ActionEvent event) {

        try {
            EmployeeDTO employee = employeeUpdateBO.searchByEmployeeName(txtName.getText());
            GuardianDTO guardian = employeeUpdateBO.getGuardianDetails(employee.getEmpId());

            if (employee != null){
                // elderId = elder.getElderId();
                txtFirstName.setText(employee.getFirstName());
                txtLastName.setText(employee.getLastName());
                txtSurName.setText(employee.getSurName());
                txtAddress.setText(employee.getAddress());
                DpDOB.setValue(employee.getDob().toLocalDate());
                DpAdmitDate.setValue(employee.getAdmitDate().toLocalDate());
                txtNIC.setText(employee.getNic());
                txtTel.setText(employee.getTel());
                txtSalary.setText(String.valueOf(employee.getSalary()));
                txtworkingDetails.setText(employee.getPastWorkingdetails());
                txtType.setText(employee.getType());
            }

            if (guardian != null){
                txtFFirstName.setText(guardian.getFirstName());
                txtFLastName.setText(guardian.getLastName());
                txtFSurName.setText(guardian.getSurName());
                txtArea.setText(guardian.getAddress());
                txtRelation.setText(guardian.getRelation());
                txtFNIC.setText(guardian.getNIC());
                txtFtel.setText(guardian.getTel());
            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnElderDeleteOnAction(ActionEvent event)  {

        String name = txtName.getText();
        System.out.println(name);

        if (name != null){
            String empId = null;
            try {
                empId = employeeUpdateBO.searchByEmployeeName(name).getEmpId();
                if (employeeUpdateBO.deleteEmployee(empId)){

                    new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted succcessfuly!").show();
                clearTextFields();
                }


            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }else {

            new Alert(Alert.AlertType.INFORMATION, "Please fill name .").show();

        }




    }

    @FXML
    void btnElderUpdateOnAction(ActionEvent event){

        String empId= null;
        try {
            empId = employeeUpdateBO.searchByEmployeeName(txtName.getText()).getEmpId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Date admitDate = Date.valueOf(DpAdmitDate.getValue());

        Date dob = Date.valueOf(DpDOB.getValue());

        Double salary = Double.valueOf(txtSalary.getText());

        String advanceFee = txtSalary.getText();

        String area = txtArea.getText();

        String firstName = txtFirstName.getText();

        String lastName = txtLastName.getText();
        String address = txtAddress.getText();

        String nic = txtNIC.getText();

        String relation = txtRelation.getText();

        String surName = txtSurName.getText();

        String tel = txtTel.getText();

        String workingDetails = txtworkingDetails.getText();

        String fFirstName = txtFFirstName.getText();

        String fLastName = txtFLastName.getText();

        String fNIC = txtFNIC.getText();

        String fSurName = txtFSurName.getText();

        String ftel = txtFtel.getText();

        String type = txtType.getText();


        Boolean isSavedI;
        try {

            EmployeeDTO employee = new EmployeeDTO(empId, firstName, lastName, surName, nic, workingDetails, dob, address, tel,admitDate, salary, type);
            GuardianDTO guardian = new GuardianDTO(fFirstName, fLastName, fSurName, relation, fNIC, area, ftel);
            if (isvalid()){
                Boolean isSaved = employeeUpdateBO.updateEmployee(employee, guardian);
                if(isSaved) {
                    clearTextFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "  Employee updates successfully !").show();
                }
                else  new Alert(Alert.AlertType.ERROR,"Employee not saved .").show();


            }else               new Alert(Alert.AlertType.INFORMATION,"Incorrect data  type inserted.Please Check again").show();






        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }

    @FXML
    private void clearTextFields() {
        ArrayList<TextField> txFieldVars = new ArrayList<>();
        txFieldVars.add(txtAddress);
        txFieldVars.add(txtSalary);
        txFieldVars.add(txtFirstName);
        txtArea.setText("");
        txtworkingDetails.setText("");
        txFieldVars.add(txtFFirstName);
        txFieldVars.add(txtFLastName);
        txFieldVars.add(txtFNIC);
        txFieldVars.add(txtFSurName);
        txFieldVars.add(txtFtel);
        txFieldVars.add(txtLastName);
        txFieldVars.add(txtNIC);
        txFieldVars.add(txtType);
        txFieldVars.add(txtRelation);
        txFieldVars.add(txtSurName);
        txFieldVars.add(txtTel);
        ArrayList<DatePicker> dpVars = new ArrayList<>();

        dpVars.add(DpAdmitDate);

        dpVars.add(DpDOB);



        for (DatePicker datePicker : dpVars) {

            datePicker.setValue(null);

        }

        for (TextField textField : txFieldVars) {
            textField.clear();
        }
    }

    private boolean isvalid() {

        boolean check = true;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFirstName)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFFirstName)) check=false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFFirstName)) check=false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtFtel)) check=false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtTel)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpAdmitDate)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpDOB)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtFNIC) ) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtNIC) )check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS,txtArea) )check= false;


        return check;


    }
}

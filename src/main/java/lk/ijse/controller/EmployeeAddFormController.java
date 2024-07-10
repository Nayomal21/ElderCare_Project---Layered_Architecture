package lk.ijse.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeAddBO;
import lk.ijse.bo.impl.EmployeeAddBOImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.GuardianDTO;



import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAddFormController {
    private List<File> selectedFiles;
    EmployeeAddBO employeeAddBO = (EmployeeAddBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.EMPLOYEEADD);

    @FXML
    private DatePicker DpAdmitDate;

    @FXML
    private DatePicker DpDOB;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

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
    private TextField txtRelation;

    @FXML
    private TextField txtSurName;

    @FXML
    private TextField txtTel;

    @FXML
    private JFXTextArea txtworkingDetails;

    @FXML
    void btnSaveOnAction(ActionEvent event) {

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

            EmployeeDTO employee = new EmployeeDTO(null, firstName, lastName, surName, nic, workingDetails, dob, address, tel,admitDate, salary, type);
            GuardianDTO guardian = new GuardianDTO(fFirstName, fLastName, fSurName, relation, fNIC, area, ftel);
            if (isvalid()){
                Boolean isSaved = employeeAddBO.saveEmployee(employee, guardian, selectedFiles);
                if (isSaved) {
                    clearTextFields();
                    new Alert(Alert.AlertType.CONFIRMATION, " New Employee saved successfully !").show();
                } else new Alert(Alert.AlertType.ERROR, "Employee not saved .").show();
            }else       new Alert(Alert.AlertType.INFORMATION,"Incorrect data  type inserted.Please Check again").show();



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

    @FXML
    void BtnAttrachOnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/nayomal/Downloads"));

        selectedFiles = fileChooser.showOpenMultipleDialog(null);}


    private boolean isvalid() {

        boolean check = true;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFirstName)) check= false;
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


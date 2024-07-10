package lk.ijse.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;

import java.awt.TextArea;
import java.io.File;


import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.TextField;

import javafx.stage.FileChooser;

import lk.ijse.Util.Regex;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ElderAddBO;
import lk.ijse.bo.impl.ElderAddBOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElderAddFormController {


    public TextField txtEmail;
    private List<File> selectedFiles;
    public TextField txtAddressG;
    public JFXTextArea txtArea;
    @FXML
    private DatePicker DpAdmitDate;

    @FXML
    private DatePicker DpDOB;

    @FXML
    private DatePicker DpPaymentDate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAdvanceFee;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextArea txtGAddress;

    @FXML
    private TextField txtGFirstName;

    @FXML
    private TextField txtGLastName;

    @FXML
    private TextField txtGNIC;

    @FXML
    private TextField txtGSurName;

    @FXML
    private TextField txtGtel;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMedicalHistory;

    @FXML
    private TextField txtMonthlyFee;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtRelation;

    @FXML
    private TextField txtSurName;

    @FXML
    private TextField txtTel;
    ElderAddBO elderAddBO = (ElderAddBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ELDERADD);

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String firstName = txtFirstName.getText();

        String lastName = txtLastName.getText();

        String surName = txtSurName.getText();

        String nic = txtNIC.getText();

        String address = txtAddress.getText();

        String tel = txtTel.getText();

        String medicalHistory = txtMedicalHistory.getText();

        Double monthlyFee = Double.valueOf(txtMonthlyFee.getText());

        Double advanceFee = Double.valueOf(txtAdvanceFee.getText());

        String relation = txtRelation.getText();

        String gFirstName = txtGFirstName.getText();

        String gLastName = txtGLastName.getText();

        String gSurName = txtGSurName.getText();

        String gNIC = txtGNIC.getText();

        String gAddress = txtArea.getText();

        String gTel = txtGtel.getText();

        Date admitDate = Date.valueOf(DpAdmitDate.getValue());

        Date dob = Date.valueOf(DpDOB.getValue());

        Date paymentDate = Date.valueOf(DpPaymentDate.getValue());

        String email = txtEmail.getText();



        ElderDTO elder = new ElderDTO(firstName, lastName, surName, nic, address, tel, medicalHistory, monthlyFee, advanceFee, admitDate, dob, paymentDate,email);

        GuardianDTO guardian = new GuardianDTO(gFirstName, gLastName, gSurName, relation, gNIC, gAddress, gTel);


        if (isvalid()){
            Boolean isSaved = elderAddBO.saveElder(elder, guardian, selectedFiles);

            if (isSaved) {
                clearTextFields();

                new Alert(Alert.AlertType.CONFIRMATION, "New Resident saved succesfully!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }
        }else {

            new Alert(Alert.AlertType.INFORMATION,"Inocrrect data inserted.pLease Check again").show();
        }

    }

    private boolean isvalid() {

        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFirstName)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtGFirstName)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtGtel)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtTel)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpPaymentDate)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpAdmitDate)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpDOB)) return false;
        if(!Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtGNIC) ) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtNIC) ) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS,txtArea) ) return false;


        return true;


    }

    public void btnAttrachOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/nayomal/Downloads"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        selectedFiles = fileChooser.showOpenMultipleDialog(null);


    }

    @FXML
    private void clearTextFields() {
        ArrayList<TextField> txFieldVars = new ArrayList<>();
        txFieldVars.add(txtAddressG);
        txFieldVars.add(txtAdvanceFee);
        txFieldVars.add(txtFirstName);
        txtArea.setText("");
        txFieldVars.add(txtGFirstName);
        txFieldVars.add(txtGLastName);
        txFieldVars.add(txtGNIC);
        txFieldVars.add(txtGSurName);
        txFieldVars.add(txtGtel);
        txFieldVars.add(txtLastName);
        txFieldVars.add(txtMedicalHistory);
        txFieldVars.add(txtMonthlyFee);
        txFieldVars.add(txtNIC);
        txFieldVars.add(txtRelation);
        txFieldVars.add(txtSurName);
        txFieldVars.add(txtTel);
        txFieldVars.add(txtEmail);
        ArrayList<DatePicker> dpVars = new ArrayList<>();

        dpVars.add(DpAdmitDate);

        dpVars.add(DpDOB);

        dpVars.add(DpPaymentDate);


        for (DatePicker datePicker : dpVars) {

            datePicker.setValue(null);

        }

        for (TextField textField : txFieldVars) {
          if (textField != null) textField.clear();
        }
    }
}




package lk.ijse.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ElderUpdateBO;
import lk.ijse.bo.impl.ElderUpdateBOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;

import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ElderUpdateFormController  {
    private String elderId;
    @FXML
    public JFXTextArea txtArea;
    @FXML
    public TextField txtName;
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
    private TextField txtEmail;
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
ElderUpdateBO elderUpdateBO = (ElderUpdateBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ELDERUPDATE);
    public void initialize() {
        try {
            TextFields.bindAutoCompletion(txtName, elderUpdateBO.getElderNames());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }



    }

    private String getElderFolderLocation(String text) throws SQLException, ClassNotFoundException {

        return  "/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/" + elderUpdateBO.searchByElderName(text).getElderId() +"/";
    }

 @FXML

    public void btnElderUpdateOnAction(javafx.event.ActionEvent actionEvent){
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

    String email = txtEmail.getText();

    Date paymentDate = Date.valueOf(DpPaymentDate.getValue());

    ElderDTO elder = new ElderDTO(elderId,firstName, lastName, surName, nic, address, tel, medicalHistory, monthlyFee, advanceFee, admitDate, dob, paymentDate,email);
    GuardianDTO guardian = new GuardianDTO( gFirstName, gLastName, gSurName,relation, gNIC, gAddress, gTel );

     String isUpdatedtE = null;
     try {

         if (isvalid()){
             Boolean isupdated = elderUpdateBO.updateElder(elder, guardian);

             if (isupdated) {
                 clearFields();
                 new Alert(Alert.AlertType.CONFIRMATION, " Resident updated successfully!").show();
             } else new Alert(Alert.AlertType.ERROR, "error").show();
         }else {

             new Alert(Alert.AlertType.INFORMATION,"Incorrect data  type inserted.pLease Check again").show();

         }

     } catch (SQLException e) {
         new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
     }



}


    public void bntSearchOnAction(javafx.event.ActionEvent actionEvent) {

        if (txtName.getText() != null ){
            try {
                ElderDTO elder = elderUpdateBO.searchByElderName(txtName.getText());
                GuardianDTO guardian = elderUpdateBO.getGuardianDetails(elder.getElderId());

                if (elder != null) {
                    elderId = elder.getElderId();
                    txtFirstName.setText(elder.getFirstName());
                    txtLastName.setText(elder.getLastName());
                    txtSurName.setText(elder.getSurName());
                    txtAddress.setText(elder.getAddress());
                    DpDOB.setValue(elder.getDob().toLocalDate());
                    DpAdmitDate.setValue(elder.getAdmitDate().toLocalDate());
                    DpPaymentDate.setValue(elder.getPaymentDate().toLocalDate());
                    txtNIC.setText(elder.getNic());
                    txtTel.setText(elder.getTel());
                    txtAdvanceFee.setText(String.valueOf(elder.getAdvanceFee()));
                    txtMedicalHistory.setText(elder.getMedicalHistory());
                    txtMonthlyFee.setText(String.valueOf(elder.getMonthlyFee()));
                    txtEmail.setText(elder.getEmail());
                }

                if (guardian != null) {
                    txtGFirstName.setText(guardian.getFirstName());
                    txtGLastName.setText(guardian.getLastName());
                    txtGSurName.setText(guardian.getSurName());
                    txtArea.setText(guardian.getAddress());
                    txtRelation.setText(guardian.getRelation());
                    txtGNIC.setText(guardian.getNIC());
                    txtGtel.setText(guardian.getTel());
                }


            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION,"Elder cannot found. Please check name again.\n\n\n"+e.getMessage()).show();


            }
        }else {


            new Alert(Alert.AlertType.INFORMATION,"Elder cannot found. Please check name again.").show();
        }

    }


    @FXML
    void btnElderDeleteOnAction(javafx.event.ActionEvent event) {

        try {
            if (elderUpdateBO.deleteELder(elderId)){
                new Alert(Alert.AlertType.CONFIRMATION,"Resident deleted successfully!").show();
                clearFields();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"error-delete").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {

        txtName.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtSurName.setText("");
        txtAddress.setText("");
        DpDOB.setValue(null);
        DpAdmitDate.setValue(null);
        DpPaymentDate.setValue(null);
        txtNIC.setText("");
        txtTel.setText("");
        txtAdvanceFee.setText("");
        txtMedicalHistory.setText("");
        txtMonthlyFee.setText("");
        txtGFirstName.setText("");
        txtGLastName.setText("");
        txtGSurName.setText("");
        txtArea.setText("");
        txtRelation.setText("");
        txtGNIC.setText("");
        txtGtel.setText("");
        txtEmail.setText("");
        txtEmail.setStyle("-fx-border-color: black;");

        txtName.setStyle("-fx-border-color: black;");
        txtFirstName.setStyle("-fx-border-color: black;");
        txtLastName.setStyle("-fx-border-color: black;");
        txtSurName.setStyle("-fx-border-color: black;");
        txtAddress.setStyle("-fx-border-color: black;");
        txtNIC.setStyle("-fx-border-color: black;");
        txtTel.setStyle("-fx-border-color: black;");
        txtAdvanceFee.setStyle("-fx-border-color: black;");
        txtMedicalHistory.setStyle("-fx-border-color: black;");
        txtMonthlyFee.setStyle("-fx-border-color: black;");
        txtGFirstName.setStyle("-fx-border-color: black;");
        txtGLastName.setStyle("-fx-border-color: black;");
        txtGSurName.setStyle("-fx-border-color: black;");
        txtArea.setUnFocusColor(Paint.valueOf("black"));
        DpPaymentDate.setStyle("-fx-border-color: black;");
        DpAdmitDate.setStyle("-fx-border-color: black;");
        DpDOB.setStyle("-fx-border-color: black;");
        txtRelation.setStyle("-fx-border-color: black;");
        txtGNIC.setStyle("-fx-border-color: black;");
        txtGtel.setStyle("-fx-border-color: black;");
        txtEmail.setStyle("-fx-border-color: black;");
    }

    @FXML
    void txtNameIOnAction(javafx.event.ActionEvent  event) {
            bntSearchOnAction(event);
    }
    private boolean isvalid() {

        boolean check = true;
    /*    if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtFirstName)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtGFirstName)) check=false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtGFirstName)) check=false;
       if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtGtel)) check=false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtTel)) check= false;
       if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpPaymentDate)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpAdmitDate)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpDOB)) check= false;
       if(!Regex.setTextColor(lk.ijse.Util.TextField.EMAIL,txtEmail)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtGNIC) ) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NIC,txtNIC) )check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS,txtArea) )check= false;

*/

        return check;


    }

    public void btnAttrachOnAction(javafx.event.ActionEvent actionEvent) {

        String location = null;
        try {
            try {
                location = getElderFolderLocation(txtName.getText());
            }catch (NullPointerException e ){
                new Alert(Alert.AlertType.ERROR,"Please insert Elder name .").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String finalLocation = location;
            new Thread(() -> {
                String command = "open " + finalLocation;
                try {
                    Runtime.getRuntime().exec(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"File not found -"+e.getMessage()).show();
        }




    }
}

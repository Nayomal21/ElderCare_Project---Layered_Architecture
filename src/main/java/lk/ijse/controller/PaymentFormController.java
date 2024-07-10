package lk.ijse.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.impl.PaymentBOImpl;
import lk.ijse.bo.custom.PaymentBo;
import lk.ijse.dto.FeeDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.model.tm.FeePaytm;
import lk.ijse.model.tm.Saltm;


import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

public class PaymentFormController {


    public Label lblAmount;
    public Label lblAlert;
    public ImageView imgAlert;

    @FXML
    private TableColumn<?, ?> colEMonth;

    @FXML
    private TableColumn<?, ?> colEsalary;

    @FXML
    private TableView<Saltm> tblEsalary;
    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colPaidAmount;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTotal;


    @FXML
    private TableView<FeePaytm> tblfee;
    @FXML
    private DatePicker DpDate;
    @FXML
    private ChoiceBox<String> chbMonth;

    @FXML
    private Pane elderPane;

    @FXML
    private Pane employeePane;

    @FXML
    private JFXRadioButton pickElder;

    @FXML
    private JFXRadioButton pickEmployee;

    @FXML
    private TextField txtAmount;

    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private ImageView imgIllustration;
    @FXML
    private TextField txtElderName;

    @FXML
    private ImageView imPayment;
    @FXML
    private TextField txtEmployeeName;
    private    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
               PaymentBo paymentBo = (PaymentBo) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.PAYMENT);

    public void initialize() {
        chbMonth.getItems().addAll(monthNames);


        setCellValueFactory();
        System.out.println("in");
        employeePane.setVisible(false);
        elderPane.setVisible(false);

        System.out.println("out");



    }


    private void setCellValueFactory() {

        colMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("PaidAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colEMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        colEsalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));


    }




    @FXML
    void KeyElderNamePressed(KeyEvent event) throws SQLException, ClassNotFoundException {

        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("pressed");
            tblfee.setVisible(true);
            imgAlert.setVisible(true);
            lblAlert.setVisible(true);
            loadAllFees();

        }



    }

    private void loadAllFees()  {

        try {
            String name = txtElderName.getText();
            ObservableList<FeeDTO> Feeoblist = paymentBo.getPreviousFees(paymentBo.searchElderName(name).getElderId());


            int[] arr = new int[Feeoblist.size()];
            for (int i = 0; i <Feeoblist.size();i++){
                arr[i]=  Feeoblist.get(i).getFeeId();

            }
            ObservableList<PaymentDTO> PayOblist = null;
            PayOblist = paymentBo.getPreviousPayments(arr);
            ObservableList<FeePaytm> finalList =getTableType(Feeoblist,PayOblist);
            tblfee.setItems(finalList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private ObservableList<FeePaytm> getTableType(ObservableList<FeeDTO> feeoblist, ObservableList<PaymentDTO> payOblist) {

        ObservableList<FeePaytm> oblist = FXCollections.observableArrayList();
        for (int i = 0; i < feeoblist.size() ; i++) {

            Month month = feeoblist.get(i).getMonth().getMonth();
            Double total = feeoblist.get(i).getTotal();
            Double paidamount;
            if(payOblist.get(i).getFeeld()==(feeoblist.get(i).getFeeId()))
                 paidamount = payOblist.get(i).getAmount();
            else paidamount = 0.0;
            String status;
            System.out.println(total+"-"+paidamount);
            if (total <= paidamount)  status = "Complete";
            else {

                status = "Incomplete";
            }
            oblist.add(new FeePaytm(month.name(), total,paidamount,status));


        }
        
        return  oblist;
    }

    @FXML
    void KeyEmployeeNamePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            imgIllustration.setVisible(false);

            System.out.println("pressed");
            try {
                tblEsalary.setVisible(true);
                loadAllSalary();
            } catch (Exception e) {
new Alert(Alert.AlertType.INFORMATION,"Can't find Employee name in the list.please check name again")    .show();        }

        }

    }

    private void loadAllSalary() throws SQLException, ClassNotFoundException {
       String name = txtEmployeeName.getText();
      ObservableList<PaymentDTO> oblist = paymentBo.getPreviousSalary(paymentBo.searchEmployeeName(name).getEmpId());
        ObservableList<Saltm> observableList = FXCollections.observableArrayList();
        for (PaymentDTO payment : oblist){
            observableList.add(new Saltm(payment.getMonth(),payment.getAmount()));


        }

        tblEsalary.setItems(observableList);
    }

    @FXML
    void rbtnElderPickOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        imPayment.setVisible(false);
        lblAmount.setText("Amount :");
        pickEmployee.setSelected(false);
        employeePane.setVisible(false);
        elderPane.setVisible(true);
        tblEsalary.setVisible(false);
        tblfee.setVisible(false);
       txtElderName.requestFocus();
      txtElderName.setText("");

        paymentBo.loadElderData();
        TextFields.bindAutoCompletion(txtElderName, paymentBo.getElderNames());
    }

    @FXML
    void rbtnEmployeePickOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        imPayment.setVisible(false);
        lblAmount.setText("Salary :");
        pickElder.setSelected(false);
        elderPane.setVisible(false);
        employeePane.setVisible(true);
        tblfee.setVisible(false);
        tblEsalary.setVisible(false);
        txtEmployeeName.requestFocus();
        txtEmployeeName.setText("");
        paymentBo.loadEmployeeData();
        System.out.println(paymentBo.getEmployeeNames());
        TextFields.bindAutoCompletion(txtEmployeeName, paymentBo.getEmployeeNames());

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (isValid() ){
            Double amount = Double.valueOf(txtAmount.getText());
            LocalDate date = LocalDate.parse(DpDate.getValue().toString());
            String description = txtDescription.getText();


            Boolean pickElderValue = pickElder.isSelected();

            boolean pickEmployeeValue = pickEmployee.isSelected();

            String type = pickElderValue ? txtElderName.getText(): pickEmployeeValue ? txtEmployeeName.getText() : null;

            if (type == null) new Alert(Alert.AlertType.ERROR, "Please select Payment Type.").show();
            String elderName = null;

            if (pickElderValue) {
                elderName = paymentBo.searchElderName(txtElderName.getText()).getElderId();
                try {
                    Month month = Month.valueOf(tblfee.selectionModelProperty().getValue().getSelectedItem().getMonth());
                    System.out.println(month);
                    int FeeId = paymentBo.getFeeIdByMonth(month.getValue(),paymentBo.searchElderName(txtElderName.getText()).getElderId());

                    try {
                        Boolean isSaved = paymentBo.savePayment(new PaymentDTO(null,date,description,null,FeeId,amount,LoginFormController.user,null));
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                            txtElderName.setText("");
                            txtAmount.setText("");
                            txtDescription.setText("");
                            DpDate.setValue(null);
                            tblfee.setVisible(false);

                        }
                        else new Alert(Alert.AlertType.ERROR, "error").show();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }

                }catch (Exception e) {

                    new Alert(Alert.AlertType.INFORMATION,"Please Select  Payment month from table.").show();
                }



            }else {



                String employeeId = paymentBo.searchEmployeeName(txtEmployeeName.getText()).getEmpId();
                try {
                    Boolean isSaved = paymentBo.saveEmployeePayment(new lk.ijse.dto.PaymentDTO(null,date,description,employeeId,amount,LoginFormController.user,chbMonth.getValue()) );
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                        txtEmployeeName.setText("");
                        txtAmount.setText("");
                        txtDescription.setText("");
                        chbMonth.valueProperty().set(null);
                        DpDate.setValue(null);
                        tblEsalary.setVisible(false);
                    }
                    else new Alert(Alert.AlertType.ERROR, "error").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "its me "+e.getMessage()).show();
                }

            }

        }else             new Alert(Alert.AlertType.ERROR, "Please insert data in correct type!").show();

    }

    private boolean isValid() {
boolean check = true;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.UNITPRICE,txtAmount)) check= false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE,DpDate)) check= false;

        return  check;
    }
}

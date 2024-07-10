package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ElderBO;
import lk.ijse.bo.impl.ElderBOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.model.DbConnection;
import lk.ijse.model.tm.Eldertm;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class ElderFormController implements Initializable {
    public TextField txtName;
    public TableView<Eldertm> tblElder;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colFee;
    public TableColumn<?,?> colAge;
    public TableColumn<?,?> colPrint;
    private  AutoCompletionBinding<String> autoCompletionBinding;
private  static   ObservableList<Eldertm>  obList;

  ElderBO elderBO = (ElderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ELDER);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllElders();

       setCellValueFactory();

        try {
            TextFields.bindAutoCompletion(txtName, elderBO.getElderNames());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new  PropertyValueFactory<>("age"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colPrint.setCellValueFactory(new PropertyValueFactory<>("Print"));

    }

    private void loadAllElders() {
          obList = FXCollections.observableArrayList();
        try {
             obList=  getTMType(elderBO.getTableData());

            tblElder.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private ObservableList<Eldertm> getTMType(ObservableList<ElderDTO> tableData) {

        ObservableList<Eldertm> observableList = FXCollections.observableArrayList();
        for (ElderDTO elderDTO : tableData){
            observableList.add(new Eldertm(elderDTO.getFirstName() + "  " + elderDTO.getLastName() + " " + (elderDTO.getSurName()),
                    elderDTO.getAge(),elderDTO.getMonthlyFee(),elderDTO.getView()));
        }
        return  observableList;
    }

    public boolean isValied() throws SQLException, ClassNotFoundException {
        for (String x : elderBO.getElderNames()){

            if (x.equals(txtName.getText())) {

                return  true;

            }


        }
        new Alert(Alert.AlertType.INFORMATION,txtName.getText()+"  is not  in Elder List").show();
        return false;
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            if (isValied()){
                int rawnum = 0;

                for (int i = 0; i < obList.size(); i++) {
                    System.out.println(obList.get(i).getName());

                    if (obList.get(i).getName().equals(txtName.getText())) {
                        System.out.println("in");

                        rawnum = i;
                        break;

                    }

                }
                System.out.println(rawnum);
                tblElder.getSelectionModel().select(rawnum);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        btnSearchOnAction(event);

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/ElderList.jrxml");
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

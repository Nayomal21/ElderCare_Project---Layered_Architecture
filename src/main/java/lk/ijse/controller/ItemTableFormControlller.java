package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardTaskBO;
import lk.ijse.bo.impl.DashBoardTaskBOImpl;
import lk.ijse.model.tm.DashBoardtm;


import lombok.Data;

import java.sql.SQLException;
import java.time.LocalDate;

@Data
public class ItemTableFormControlller {

    public  static  int turn=0;

    @FXML
    private Button btnActive;

    @FXML
    private HBox itemC;

    @FXML
    private Label lblAmount;

    @FXML
    private  Label lblElderName;

    @FXML
    private Label lblPaymentDate;

    public  static   ObservableList<DashBoardtm> upComingPayment;
         DashBoardTaskBO dashBoardBO = (DashBoardTaskBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.DASHBOARDTASK);
    public void  initialize(){

        try {
             upComingPayment = dashBoardBO.getUpComingPayment(LocalDate.now());
             lblElderName.setText(upComingPayment.get(turn).getLblElderName());
            lblPaymentDate.setText(upComingPayment.get(turn).getLblPaymentDate());
            lblAmount.setText(upComingPayment.get(turn).getLblAmount());
            btnActive= upComingPayment.get(turn).getBtnActive();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPayOnaAction(ActionEvent event) {

        try {



            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));

            Pane form = loader.load();





        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }

    }

}


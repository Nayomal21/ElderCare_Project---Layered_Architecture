package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.Launcher;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemAddBO;
import lk.ijse.bo.impl.ItemAddBOImpl;

import lk.ijse.dto.ItemDTO;



import java.sql.SQLException;

public class ItemAddFormController {

    @FXML
    private ChoiceBox<String> chbType;

    @FXML
    private Label lblCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;
    ItemAddBO itemAddBO = (ItemAddBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ITEMADD);
    public   void initialize(){

        loadId();
        loadTypes();

    }

    private void loadId() {

        try {
            lblCode.setText(itemAddBO.nextId());

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadTypes() {

       final  String[] arv = {"Kg","l","Packet"};
    chbType.getItems().addAll(arv);

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
        chbType.setValue("");


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (isValid()){
            Double unitPrice = Double.valueOf(txtUnitPrice.getText());

            int qty = Integer.parseInt(txtQtyOnHand.getText());


                boolean save = itemAddBO.save(new ItemDTO(lblCode.getText(), txtDescription.getText(), chbType.getValue(), qty, unitPrice));
               if (save) {

                   ItemFormController.stage.close();

                   new Alert(Alert.AlertType.CONFIRMATION, "Item saved successfully!").show();
                   loadId();
               } else {
                   new Alert(Alert.AlertType.ERROR, "Item  not saved successfully!").show();


               }



          }else{

        Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data in correct type!");
        alert.initOwner(Launcher.stage);
        Launcher.stage.setAlwaysOnTop(false);

        alert.showAndWait();
        Launcher.stage.setAlwaysOnTop(true);

    }

    }
    private boolean isValid() {
boolean check = true;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.UNITPRICE,txtUnitPrice)) check = false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.QTY,txtQtyOnHand)) check = false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtDescription)) check = false;

        return  check;
    }
}

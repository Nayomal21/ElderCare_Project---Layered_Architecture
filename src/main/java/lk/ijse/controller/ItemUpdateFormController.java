package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.Launcher;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemUpdateBO;
import lk.ijse.bo.impl.ItemUpdateBOImpl;
import lk.ijse.dto.ItemDTO;


import java.sql.SQLException;

public class ItemUpdateFormController {

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
ItemUpdateBO itemUpdateBO = (ItemUpdateBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ITEMUPDATE);

    public  void initialize(){

        try {
            loadData();
            loadTypes();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }
    private void loadTypes() {

        final  String[] arv = {"Kg","l","Packet"};
        chbType.getItems().addAll(arv);

    }


    private void loadData() throws SQLException, ClassNotFoundException {

        ItemDTO item = itemUpdateBO.searchById(ItemFormController.updateCode);

        lblCode.setText(item.getCode());
        txtDescription.setText(item.getDescription());
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        chbType.setValue(item.getType());
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
        chbType.setValue("");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        try{
            Double unitPrice = Double.valueOf(txtUnitPrice.getText());

            int qty = Integer.parseInt(txtQtyOnHand.getText());

            boolean save = false;
            try {
                if (isValid()){
                    save = itemUpdateBO.update(new ItemDTO(lblCode.getText(), txtDescription.getText(), chbType.getValue(), qty, unitPrice));
                }        } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }

            if (save) {
                btnClearOnAction(event);
                ItemFormController.stage.close();
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully!").show();


            }else {
                new Alert(Alert.AlertType.ERROR, "Item  not updates successfully!").show();


            }
        }catch (Exception e ){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert data in correct type!");
            alert.initOwner(Launcher.stage);
            Launcher.stage.setAlwaysOnTop(false);

            alert.show();
            Launcher.stage.setAlwaysOnTop(true);

        }





    }

    private boolean isValid() {

        if (!Regex.setTextColor(lk.ijse.Util.TextField.UNITPRICE,txtUnitPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.QTY,txtQtyOnHand)) return false;

        return  true;
    }


}

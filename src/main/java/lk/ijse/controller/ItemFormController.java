package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import lk.ijse.Launcher;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.impl.ItemBOImpl;

import lk.ijse.dto.ItemDTO;
import lk.ijse.model.tm.Itemtm;

import org.controlsfx.control.textfield.TextFields;

import java.util.Optional;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class ItemFormController {

public static  Stage stage;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<Itemtm, String> colDescription;

    @FXML
    private TableColumn<Itemtm, String> colQtyOnHand;

    @FXML
    private TableColumn<Itemtm, Double> colUnitPrice;

    @FXML
    private TableView<Itemtm> tblItem;

    public  static  String updateCode;
    @FXML
    private TextField txtName;
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.ITEM);
    public  void initialize(){
        setCellValueFactory();
        loadItems();
        TextFields.bindAutoCompletion(txtName, itemBO.getItemNames());
        tblItem.setEditable(false);


    }

    public void loadItems() {

        ObservableList obList = FXCollections.observableArrayList();
        try {
            obList=  getTableTypeData(itemBO.getTableData());
            System.out.println(obList);
            tblItem.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private ObservableList getTableTypeData(ObservableList<ItemDTO> tableData) {

        ObservableList<Itemtm> oblist = FXCollections.observableArrayList();

        for (ItemDTO  item :tableData){
            JFXButton delete = new JFXButton("delete");
            delete.setOnAction(event -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> Deletetype = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete this item?", yes, no).showAndWait();

                if (Deletetype.orElse(no) == yes) {
                    try {
                        boolean isDeleted = itemBO.delete(item.getCode());
                        if (isDeleted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Item deleted successfully").show();

                           loadItems();
                        }

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                }

            });
            delete.setButtonType(JFXButton.ButtonType.RAISED);
            delete.setStyle("-fx-background-color: #f368e0; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(15);
            x.setFitWidth(15);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderUpdate_pic/delete.png"));
            delete.setGraphic(x);
            delete.applyCss();
            JFXButton update = new JFXButton("Update");
            update.setButtonType(JFXButton.ButtonType.RAISED);
            update.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x1 =new ImageView();
            x1.setFitHeight(15);
            x1.setFitWidth(15);
            x1.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderUpdate_pic/update.png"));
            update.setGraphic(x1);

            update.setOnAction(event -> {

              loadUpdateForm(item.getCode());
            });
            update.applyCss();


            HBox hbox = new HBox(update, delete);

            hbox.setSpacing(5);

            hbox.setAlignment(Pos.CENTER);


            oblist.add(new Itemtm(item.getCode(), item.getDescription(), item.getUnitPrice(),
                    item.getQtyOnHand() + " " + item.getType(), hbox));
        }

        return  oblist;
    }

    private void setCellValueFactory() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(new  PropertyValueFactory<Itemtm,String>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("edit"));

        colDescription.setCellFactory(forTableColumn());
        colDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Itemtm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Itemtm, String> event) {
                Itemtm person = event.getRowValue();
                person.setDescription(event.getNewValue());

            }
        });

        colQtyOnHand.setCellFactory(TextFieldTableCell.forTableColumn());
        colQtyOnHand.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Itemtm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Itemtm, String> event) {
                Itemtm person = event.getRowValue();
                person.setQty(event.getNewValue());

            }
        });
        colUnitPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colUnitPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Itemtm, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Itemtm, Double> event) {
                Itemtm person = event.getRowValue();
                person.setUnitPrice(Double.valueOf(event.getNewValue()));
            }
        });





    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {


            stage  = new Stage();

            stage.setScene(new Scene(FXMLLoader
                    .load(this.getClass().getResource("/view/item_add_form.fxml"))));
            stage.setTitle("Item add Form");
            stage.centerOnScreen();
          //  stage.show();


// Set stage1 as the owner of stage2
            stage.initOwner(Launcher.stage);

// Set stage2 as a modal window with window modality
            stage.initModality(Modality.WINDOW_MODAL);
            Launcher.stage.setAlwaysOnTop(true);
// Show stage2
            stage.showAndWait();

// Stage2 is now closed, so enable stage1
            Launcher.stage.setAlwaysOnTop(false);
            loadItems();
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        if(isValied()){
            String name = txtName.getText();

            int row = itemBO.getTableRow(name);
            System.out.println(row);
            tblItem.getSelectionModel().select(--row);
        }

    }
    public void loadUpdateForm(String code) {
        try {
                updateCode=code;

             stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader
                    .load(this.getClass().getResource("/view/item_update_form.fxml"))));
            stage.setTitle("Item update Form");
            stage.centerOnScreen();
            //stage.showAndWait();

            // Set stage1 as the owner of stage2
            stage.initOwner(Launcher.stage);

// Set stage2 as a modal window with window modality
            stage.initModality(Modality.WINDOW_MODAL);
            Launcher.stage.setAlwaysOnTop(true);
// Show stage2
            stage.showAndWait();

// Stage2 is now closed, so enable stage1
            Launcher.stage.setAlwaysOnTop(false);
            loadItems();




        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

        btnSearchOnAction(event);

    }
    public boolean isValied(){
      for (String x :itemBO.getItemNames()){

          if (x.equals(txtName.getText())) {

              return  true;

          }


      }
      new Alert(Alert.AlertType.INFORMATION," Item is not in Item List").show();
      return false;
    }


}







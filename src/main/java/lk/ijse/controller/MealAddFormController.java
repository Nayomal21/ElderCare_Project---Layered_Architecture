package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MealBO;
import lk.ijse.bo.impl.MealBOImpl;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;
import lk.ijse.model.tm.MealItemtm;



import org.controlsfx.control.textfield.TextFields;

import java.time.LocalDate;
import java.util.Optional;

public class MealAddFormController {
    private ObservableList<MealItemtm> obList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXTextArea txtBreakFast;

    @FXML
    private JFXTextArea txtDinner;


    @FXML
    private JFXTextArea txtLunch;


    @FXML
    private JFXButton btnAddToCart;
    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<MealItemtm> tblItem;

    @FXML
    private TextField txtItem;


  public   static  boolean alert;
    @FXML
    private Label txtDate;
        MealBO mealBO = (MealBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.MEAL);
    public  void initialize(){

        try {
            txtDate.setText(LocalDate.now().toString());
            setCellValueFactory();
            mealBO.getTableData();
            TextFields.bindAutoCompletion(txtItem,mealBO.getItemNames());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("buttons"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }





    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        String item = txtItem.getText();
        int qty =  Integer.parseInt(txtQty.getText());


        JFXButton remove = new JFXButton();

        ImageView x2 =new ImageView();
        x2.setImage(new Image
                ("file:src/main/resources/icon/icon/mealAdd_icon/remove.png"));
        remove.setGraphic(x2);
        remove.setCursor(Cursor.HAND);
        remove.setOnAction((e) -> {

            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblItem.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblItem.refresh();

            }
        });

        JFXButton plus = new JFXButton();

        ImageView x =new ImageView();
        x.setImage(new Image
                ("file:src/main/resources/icon/icon/mealAdd_icon/add.png"));
        plus.setGraphic(x);
        plus.setCursor(Cursor.HAND);
        plus.setOnAction((e) -> {
                int selectedIndex = tblItem.getSelectionModel().getFocusedIndex();
            System.out.println(selectedIndex+"tn");
                MealItemtm changed = obList.get(selectedIndex);
                changed.setQty(changed.getQty()+1);

                obList.set(selectedIndex,changed );

                tblItem.refresh();


        });

        JFXButton minus = new JFXButton();

        ImageView x3 =new ImageView();
        x3.setImage(new Image
                ("file:src/main/resources/icon/icon/mealAdd_icon/minus.png"));
        minus.setGraphic(x3);
        minus.setCursor(Cursor.HAND);

        minus.setOnAction((e) -> {
            int selectedIndex = tblItem.getSelectionModel().getFocusedIndex();
            System.out.println(selectedIndex+"tn");
            MealItemtm changed = obList.get(selectedIndex);
            changed.setQty(changed.getQty()-1);

            obList.set(selectedIndex,changed );

            tblItem.refresh();


        });




        HBox hbox = new HBox(plus,minus,remove);

        hbox.setSpacing(10);

        hbox.setAlignment(Pos.CENTER);

        MealItemtm tm = new MealItemtm(item, qty,hbox );

        obList.add(tm);

        tblItem.setItems(obList);

        txtItem.clear();
        txtQty.clear();

        txtItem.requestFocus();

    }


    @FXML
    void btnItemOnAction(ActionEvent event) {

        txtQty.requestFocus();

    }



    @FXML
    void btnQtyOnAction(ActionEvent event) {

        btnAddToCart.requestFocus();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String breakfast = txtBreakFast.getText();

        String lunch = txtLunch.getText();

        String dinner = txtDinner.getText();


        MealDTO meal = new MealDTO(LocalDate.now(), breakfast, lunch, dinner);

        try {
            boolean isComplete = mealBO.saveMealDetail(meal, getMealItemsList());

            if (isComplete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Details saved successfully!").show();
         btnClearOnAction(event);

            }

            else  { if (alert)new Alert(Alert.AlertType.ERROR,"not saved").show();}
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    private ObservableList<MealItemDTO> getMealItemsList() {

        ObservableList<MealItemDTO> list = FXCollections.observableArrayList();

        for (MealItemtm tm : obList){
            list.add( new MealItemDTO(tm.getName(), (int) tm.getQty()));
        }
        return list;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtItem.clear();
        txtQty.clear();
        txtBreakFast.clear();
        txtDinner.clear();
        txtLunch.clear();
        tblItem.getItems().clear();
    }

}

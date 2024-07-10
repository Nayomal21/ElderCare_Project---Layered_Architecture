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
import lk.ijse.bo.custom.MealUpdateBO;
import lk.ijse.bo.impl.MealUpdateBOImpl;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;
import lk.ijse.model.tm.MealItemtm;



import org.controlsfx.control.textfield.TextFields;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class MealUpdateFormController {

    private ObservableList<MealItemtm> obList = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAddToCart;
    public   static  boolean dalert;
    public   static  boolean ualert;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableView<MealItemtm> tblItem;

    @FXML
    private JFXTextArea txtBreakFast;

    @FXML
    private TextField txtDay;

    @FXML
    private JFXTextArea txtDinner;

    @FXML
    private TextField txtItem;

    @FXML
    private JFXTextArea txtLunch;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtYear;
    private  static LocalDate date;
    private   static ObservableList<MealItemtm>  storePreviousData;
    MealUpdateBO mealUpdateBO = (MealUpdateBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.MELAUPDATE);
    public  void initialize(){

        try {
            txtDay.requestFocus();
            setCellValueFactory();
            mealUpdateBO.getTableData();
            TextFields.bindAutoCompletion(txtItem,mealUpdateBO.getItemNames());


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
    void txtDayOnAction(ActionEvent event) {
txtMonth.requestFocus();
    }

    @FXML
    void txtMonthOnAction(ActionEvent event) {
txtYear.requestFocus();
    }

    @FXML
    void txtYearOnAction(ActionEvent event) {
        loadTableAndTextFeilds();
    }

    private void loadTableAndTextFeilds() {
        int year = Integer.parseInt(txtYear.getText());
        String month= (txtMonth.getText());
        String day =(txtDay.getText());

String  date = year+"-"+month+"-"+day;

        System.out.println(date);
       loadMEaldetails(date);




    }

    private void loadMEaldetails(String d) {

        try {
            MealDTO mealDetails = mealUpdateBO.getMealDetails(d);
            date= mealDetails.getDate();
            txtBreakFast.setText(mealDetails.getBreakfast());
            txtLunch.setText(mealDetails.getLunch());
            txtDinner.setText(mealDetails.getDinner());
           ObservableList<MealItemtm> tableType = getTableTypes(mealUpdateBO.getMealItemDetails(Date.valueOf(mealDetails.getDate())));


          //   storePreviousData = MealItemRepo.getMealItemDetails(Date.valueOf(mealDetails.getDate()));
            storePreviousData = getTableTypes(mealUpdateBO.getMealItemDetails(Date.valueOf(mealDetails.getDate())));

           obList= tableType;
            tblItem.setItems(tableType);

        } catch (Exception e) {
            System.out.println(e.getMessage());
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private ObservableList<MealItemtm> getTableTypes(ObservableList<MealItemDTO> mealItemDetails) {
        System.out.println(mealItemDetails+"06  07 ");
        ObservableList<MealItemtm> observableList = FXCollections.observableArrayList();
        for (MealItemDTO tm :mealItemDetails)
       {
            JFXButton remove = new JFXButton();

            ImageView x2 = new ImageView();
            x2.setImage(new Image
                    ("file:src/main/resources/icon/icon/mealAdd_icon/remove.png"));
            remove.setGraphic(x2);
            remove.setCursor(Cursor.HAND);
            remove.setOnAction((e) -> {

                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int selectedIndex = tblItem.getSelectionModel().getSelectedIndex();
                    obList.remove(selectedIndex);

                    tblItem.refresh();

                }
            });

            JFXButton plus = new JFXButton();

            ImageView x = new ImageView();
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/mealAdd_icon/add.png"));
            plus.setGraphic(x);
            plus.setCursor(Cursor.HAND);
            plus.setOnAction((e) -> {
                int selectedIndex = tblItem.getSelectionModel().getFocusedIndex();
                System.out.println(selectedIndex + "tn");
                MealItemtm changed = obList.get(selectedIndex);
                changed.setQty(changed.getQty() + 1);

                obList.set(selectedIndex, changed);

                tblItem.refresh();


            });

            JFXButton minus = new JFXButton();

            ImageView x3 = new ImageView();
            x3.setImage(new Image
                    ("file:src/main/resources/icon/icon/mealAdd_icon/minus.png"));
            minus.setGraphic(x3);
            minus.setCursor(Cursor.HAND);

            minus.setOnAction((e) -> {
                int selectedIndex = tblItem.getSelectionModel().getFocusedIndex();
                System.out.println(selectedIndex + "tn");
                MealItemtm changed = obList.get(selectedIndex);
                changed.setQty(changed.getQty() - 1);

                obList.set(selectedIndex, changed);

                tblItem.refresh();


            });


            HBox hbox = new HBox(plus, minus, remove);

            hbox.setSpacing(10);

            hbox.setAlignment(Pos.CENTER);
           MealItemtm tm2 = new MealItemtm(tm.getName(), tm.getQty(),hbox);

           observableList.add(tm2);
        }
       return observableList;

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
    void btnClearOnAction(ActionEvent event)

    {
        txtItem.clear();
        txtQty.clear();
        txtBreakFast.clear();
        txtDinner.clear();
        txtLunch.clear();
        tblItem.getItems().clear();
        txtDay.clear();
        txtMonth.clear();
        txtYear.clear();
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
    void btnUpdateOnAction(ActionEvent event)
    {


        String breakfast = txtBreakFast.getText();

        String lunch = txtLunch.getText();

        String dinner = txtDinner.getText();


        MealDTO meal = new MealDTO(date, breakfast, lunch, dinner);

        try {
            boolean isComplete = mealUpdateBO.updateMealDetail(meal, getTableType(obList),getTableType(storePreviousData));

            if (isComplete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Details updated successfully!").show();
                btnClearOnAction(event);
            }

            else  { if (ualert)new Alert(Alert.AlertType.ERROR,"not saved").show();}
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }





    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {

            try {
                boolean isComplete = mealUpdateBO.deleteMealDetail(date,getTableType(storePreviousData));

                if (isComplete) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Meal Details deleted successfully!").show();
                    btnClearOnAction(actionEvent);
                }

                else  { if (dalert)new Alert(Alert.AlertType.ERROR,"not deleted").show();}
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }




    }

    private ObservableList<MealItemDTO> getTableType(ObservableList<MealItemtm> mealItemDetails) {
        System.out.println(mealItemDetails);
        ObservableList<MealItemDTO> observableList = FXCollections.observableArrayList();
        for (MealItemtm tm :mealItemDetails)
        {
              observableList.add(new MealItemDTO(tm.getName(), tm.getQty()))  ;



        }
        return observableList;

    }

}

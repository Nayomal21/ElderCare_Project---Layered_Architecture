package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.impl.BIllBOImpl;
import lk.ijse.bo.custom.BillBO;
import lk.ijse.dto.BillDTO;
import lk.ijse.model.tm.Billtm;

import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class BillAddFormController {


    public Pane elderPane;
    public Label lblSelectImage;

    private File selectedFiles;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;


    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colView;

    @FXML
    private TableView<Billtm> tblBill;


    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private TextField txtElderName;

    @FXML
    private TextField txtTotal;

    @FXML
    private DatePicker DpDate;

    @FXML
    private ImageView IllBill;
    @FXML
    private JFXRadioButton pickElder;

    @FXML
    private JFXRadioButton pickItem;

    @FXML
    private JFXRadioButton pickGoods;
    @FXML
    private JFXRadioButton pickMedicine;


    @FXML
    private ImageView IllHuman;


    BillBO billBO = (BillBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.BILL);
    public void initialize() {
        System.out.println("in");
        setCellValueFactory();
        elderPane.setVisible(false);
        IllBill.setVisible(true);
        tblBill.setVisible(false);
        IllHuman.setVisible(true);

        System.out.println("out");

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/home/nayomal/Downloads"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Images", "*.png")
        );

        selectedFiles = fileChooser.showOpenDialog(null);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        TableView.TableViewSelectionModel<Billtm> selectionModel = tblBill.getSelectionModel();
        ObservableList<Billtm> selectedColumns = selectionModel.getSelectedItems();

        if (!selectedColumns.isEmpty()) {
            Billtm selectedColumn = selectedColumns.get(0);
            System.out.println(selectedColumn.getDescription());
            // Do something with the selected column
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        if (isvalid()){
            Double total = Double.valueOf(txtTotal.getText());

            String description = txtDescription.getText();

            LocalDate date = LocalDate.parse(DpDate.getValue().toString());

            Boolean pickElderValue = pickElder.isSelected();

            Boolean pickItemValue = pickItem.isSelected();

            String type = pickElderValue ? pickElder.getText() : pickItemValue ? pickItem.getText() : null;

            if (type == null) {
                new Alert(Alert.AlertType.ERROR, "Please select Bill Type.").show();
                return;
            }
            String elderName = null;

            if (pickElderValue) {
                String Feetype = pickMedicine.isSelected() ? pickMedicine.getText() : pickGoods.isSelected() ? pickGoods.getText() : null;
                if (Feetype == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select Bill's  category.").show();
                    return;
                }
                elderName = billBO.searchElderName(txtElderName.getText()).getElderId();
                billBO.SetFee(Feetype, elderName, total);

            }
            if (selectedFiles == null) {
                lblSelectImage.setTextFill(Color.RED);
                new Alert(Alert.AlertType.ERROR, "Please Insert  a Image of Bill").show();
                return;
            }

            try {

                Boolean isSaved= billBO.save(new BillDTO(description, date, total, elderName, type, selectedFiles));


                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Bill saved successfully!").show();
                    lblSelectImage.setTextFill(Color.BLACK);
                    selectedFiles = null;
                    if (type.equals("Item")) loadAllBills();
                    else loadAllBills(txtElderName.getText());

                } else new Alert(Alert.AlertType.ERROR, "error").show();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else        new Alert(Alert.AlertType.INFORMATION,"Incorrect data  type inserted.Please Check again").show();




    }



    @FXML
    void rbtnElderPickOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        tblBill.setVisible(true);
        tblBill.getItems().clear();
        pickItem.setSelected(false);
        IllBill.setVisible(false);
        elderPane.setVisible(true);
        txtElderName.requestFocus();
        txtElderName.setText("");


        billBO.loadElderData();
        TextFields.bindAutoCompletion(txtElderName, billBO.getElderNames());


    }

    public void rbtnItemPickOnAction(ActionEvent actionEvent) {
        tblBill.setVisible(true);
        IllBill.setVisible(true);
        pickElder.setSelected(false);
        elderPane.setVisible(false);
        loadAllBills();

    }

    public void rbtnMediicalOnAction(ActionEvent actionEvent) {
        pickGoods.setSelected(false);

    }

    public void rbtnGoodOnAction(ActionEvent actionEvent) {
        pickMedicine.setSelected(false);


    }


    private void loadAllBills() {
        ObservableList obList = FXCollections.observableArrayList();
        try {
            obList = billBO.getTableData();

            tblBill.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void setCellValueFactory() {

        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colView.setCellValueFactory(new PropertyValueFactory<>("View"));

    }

    @FXML
    void KeyNamePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("pressed");
            loadAllBills(txtElderName.getText());

        }
    }

    private void loadAllBills(String ElderName) {

        ObservableList<Billtm> obList = FXCollections.observableArrayList();
        try {
            obList = (billBO.getTableData(ElderName));

            tblBill.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private ObservableList getTableType(ObservableList<BillDTO> tableData) {

        ObservableList<Billtm> oblist = FXCollections.observableArrayList();

        for (BillDTO bill :tableData){
            String location = bill.getSelectedFiles().getAbsolutePath();
            System.out.println(location);

            JFXButton view = new JFXButton("view");
            view.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(20);
            x.setFitWidth(20);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderAdd_pic/view2.png"));
            view.setGraphic(x);
            view.setOnAction(actionEvent -> {
                System.out.println("clicked view thred");

                System.out.println(location);
                if (location != null){
                    new Thread(() -> {
                        String command = "open " + location;
                        try {
                            Runtime.getRuntime().exec(command);
                            System.out.println("call");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }else  new Alert(Alert.AlertType.INFORMATION,"No Images saved for this bill !").show();


            });

            view.setCursor(Cursor.HAND);
            // print.setFont("Roboto");

            oblist.add(new Billtm(bill.getDescription(),bill.getDate(),bill.getTotal(),view ));
        }
        return  oblist;
    }
    private boolean isvalid() {

        boolean check = true;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.UNITPRICE, txtTotal)) check = false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DATE, DpDate)) check = false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtDescription)) check = false;



        return check; }
}

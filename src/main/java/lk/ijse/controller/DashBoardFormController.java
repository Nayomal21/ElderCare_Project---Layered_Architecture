package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lk.ijse.Launcher;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardFormBO;

import lk.ijse.dto.UserDTO;


import java.io.IOException;
import java.sql.SQLException;

public class DashBoardFormController {
    @FXML
    private Circle cirImage;
    @FXML
    private MenuItem BtnAddElder;

    public    AnchorPane leafNode;

    @FXML
    private MenuButton mbtnElder;

    @FXML
    private MenuItem mbtnSearch;

    @FXML
    private ImageView ivUser;

    @FXML
    private Label lblUserName;
    DashBoardFormBO dashBordForm = (DashBoardFormBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.DASHBOARD);

    public  void  initialize (){
        Launcher.stage.setWidth(1130);
        lblUserName.setText(LoginFormController.userLevel);
        loadDashBoardPanel();

    }


    // display image in a JPanel popup


    @FXML
    void btnLogoutOnAction(ActionEvent event) {
            Stage stage = Launcher.stage;
            stage.centerOnScreen();
            stage.setWidth(641);
        try {


            try {
                stage.setScene(new Scene(FXMLLoader
                        .load(this.getClass().getResource("/view/login_form2.fxml"))));
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,"form error - "+ e.getMessage()).show();
            }




        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }

//

//        try {
//Launcher.stage.close();
//            // Load the registration_form.fxml file
//            Stage stage =new Stage();
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_form2.fxml"));
//            Scene scene = new Scene(loader.load());
//            stage.setScene(scene);
//                stage.show();
//
//        } catch (Exception e) {
//
//
//        }

    }

    private void loadDashBoardPanel() {


      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoardTask.fxml"));

        Pane form = null;
        try {
            form = loader.load();
        } catch (IOException e) {
            System.out.println("yut");
            throw new RuntimeException(e);

        }
       leafNode.getChildren().clear();

       leafNode.getChildren().add(form);


    }


    public void btnDashboardOnAction(ActionEvent actionEvent) {
        initialize ();


    }
    public void BtnSearchOnAction(ActionEvent actionEvent) {
        try {

            // Load the registration_form.fxml file

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/elder_search_form.fxml"));

            Pane registrationForm = loader.load();


            // Set the registration form as the child of the leafNode

            leafNode.getChildren().clear();

            leafNode.getChildren().add(registrationForm);


            // Get the controller of the registration form and set the root node

            RegistrationFormController registrationFormController = loader.getController();

            registrationFormController.setRootNode(leafNode);


        } catch (Exception e) {


        }

    }




    public void btnAddOnAction(ActionEvent actionEvent) {
        if (LoginFormController.userLevel.equals("Manager") || LoginFormController.userLevel.equals("Owner") ){
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/elder_add_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }else{
          createDialog();
        }


    }

    private void createDialog() {
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setContent(createLogForm(dialog));
        dialog.setTitle("Access Denied as a  "+LoginFormController.userLevel);
        dialog.setHeaderText("You have no access to use this form.\nif want to use this login as Manager ");


        Image image = new Image("file:/home/nayomal/Desktop/projects/Eldercare/src/main/resources/icon/icon/ElderAdd_pic/alert.png");

        ImageView imageView = new ImageView(image);

        dialog.setGraphic(imageView);

        dialog.show();

    }

    private Node createLogForm(Dialog dialog) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7);
        gridPane.setHgap(5);
       // gridPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FDA7DF"), CornerRadii.EMPTY, Insets.EMPTY)));

        Stop stop1 = new Stop(0, Color.valueOf("#25CCF7"));

        Stop stop2 = new Stop(1, Color.valueOf("#3B3B98"));

        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop1, stop2);

        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);


        // Set the background of the GridPane

        gridPane.setBackground(new Background(backgroundFill));

        Label lblUserName = new Label("Username:");
        lblUserName.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        gridPane.add((lblUserName),0,0);
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        gridPane.add((username),1,0);
        Label lblPassword = new Label("Password:");
        lblPassword.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        username.setFont(Font.font("DejaVu Serif Condensed",13));

        gridPane.add(lblPassword,0,3);
        gridPane.add(password,1,3);
        Button loginAsManager = new Button("Login as Manager");
        Button access = new Button("Access Only This Form");
        access.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        loginAsManager.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        loginAsManager.setStyle("-fx-background-color: #5dea3b; -fx-text-fill: #0a0a0a;");
        access.setStyle("-fx-background-color: #e1bd08; -fx-text-fill: #0a0a0a;");

        access.setOnAction(event -> {
            String userLevelex = LoginFormController.userLevel;

            try {
                Boolean result = dashBordForm.checkCrendials(new UserDTO(username.getText(),password.getText()));
                if (result){
                    System.out.println(userLevelex);
                    btnAddOnAction(event);
                    LoginFormController.userLevel= userLevelex;
                    dialog.close();

                }

            } catch (SQLException e) {
                System.out.println("op");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        });
        loginAsManager.setOnAction(event ->
        {
            try {
                Boolean result = dashBordForm.checkCrendials(new UserDTO(username.getText(),password.getText()));
                if (result){
                    LoginFormController.user = username.getText();
                    btnAddOnAction(event);
                    dialog.close();
                }

            } catch (SQLException e) {
                System.out.println("op");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        });

        gridPane.add((loginAsManager),0,4);
        gridPane.add((access),1,4);
        return gridPane;

    }


    public void btnElderUpdateOnAction(ActionEvent actionEvent) {

        if (LoginFormController.userLevel.equals("Manager")){
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/elder_update_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                System.out.println(e.getLocalizedMessage() + e.getMessage());
            }
        }else {
            createDialog();


        }

    }


    @FXML
    private void btnItemOnAction(ActionEvent event) {

        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/item_form.fxml"));

            Pane form = loader.load();

            leafNode.getChildren().clear();

            leafNode.getChildren().add(form);


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }


    }



    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        if (LoginFormController.userLevel.equals("Manager") || LoginFormController.userLevel.equals("Owner") ){
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }else {

            createDialog();

        }


    }

    @FXML
    void btnBillsOnAction(ActionEvent event) {

        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bill_add_form.fxml"));

            Pane form = loader.load();

            leafNode.getChildren().clear();

            leafNode.getChildren().add(form);


        } catch (Exception e) {

            //new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }

    }


    @FXML
    void btnEmpAddOnAction(ActionEvent event) {

        if (LoginFormController.userLevel.equals("Manager") || LoginFormController.userLevel.equals("Owner") ){
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_add_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                System.out.println(e.getLocalizedMessage() + e.getMessage());
            }
        }else  createDialog();

    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {
        if (LoginFormController.userLevel.equals("Manager") || LoginFormController.userLevel.equals("Owner") ){
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_update_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                System.out.println(e.getLocalizedMessage() + e.getMessage());
            }
        }else createDialog();


    }
    @FXML
    void BtnEmpSearchOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_search_form.fxml"));

            Pane form = loader.load();

            leafNode.getChildren().clear();

            leafNode.getChildren().add(form);


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }
    }

    @FXML
    void BtnMealAddOnAction(ActionEvent event) {



        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/meal_add_form.fxml"));

            Pane form = loader.load();

            leafNode.getChildren().clear();

            leafNode.getChildren().add(form);


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }

    }


    @FXML
    void BtnMealUpdateOnAction(ActionEvent event) {




        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/meal_update_form.fxml"));

            Pane form = loader.load();

            leafNode.getChildren().clear();

            leafNode.getChildren().add(form);


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getLocalizedMessage()+e.getMessage());
        }
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {

        if (LoginFormController.userLevel.equals("Manager") || LoginFormController.userLevel.equals("Owner") ){

            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report_form.fxml"));

                Pane form = loader.load();

                leafNode.getChildren().clear();

                leafNode.getChildren().add(form);


            } catch (Exception e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                System.out.println(e.getLocalizedMessage() + e.getMessage());
            }
        }else  createDialog();

    }
private  void  refreshUserName(){

        lblUserName.setText( LoginFormController.userLevel);
}
}

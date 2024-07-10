package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashBoardTaskBO;
import lk.ijse.bo.impl.DashBoardTaskBOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.PaymentDTO;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashBoardTaskController implements  Initializable{


    @FXML
    private VBox pnItems;

    @FXML
    private VBox Vbox;

@FXML
    private CategoryAxis XAxis;

    @FXML
    private NumberAxis YAxis;

    @FXML
    private BarChart<String, Double> bcItems;

    @FXML
    private Label lblCount;

    @FXML
    private Label lblEmployeeCount;


    @FXML
    private AreaChart<String, Double> bcProfit;

    @FXML
    private PieChart pieChart;
        DashBoardTaskBO dashBoardBO = (DashBoardTaskBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.DASHBOARDTASK);
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       initComponents();
        try {
            loadBarChart();

            loadTable();
           loadAreaChart();
loadPieChart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAreaChart() {

       XYChart.Series<String,Double> series1 = new XYChart.Series();


        try {

            ObservableList<PaymentDTO> tableData = dashBoardBO.getPaymentData();
            System.out.println("hi"+tableData);

            for (PaymentDTO item :tableData) {
                series1.getData().add(new XYChart.Data(String.valueOf(item.getDate().getDayOfMonth()), item.getAmount()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        bcProfit.getData().addAll(series1);

    }

    private void loadTable() throws SQLException, ClassNotFoundException {

        Node[] nodes = new Node[dashBoardBO.getUpComingPayment(LocalDate.now()).size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                ItemTableFormControlller.turn= i;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Item.fxml"));

                nodes[i] = loader.load();




                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #719fe5");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #ffffff");
                });
                pnItems.getChildren().add(nodes[i]);

            } catch (IOException e) {


                e.printStackTrace();
            }
        }
    }

    private void loadBarChart() {


        XYChart.Series<String,Double> series1 = new XYChart.Series();


        try {
            ObservableList<ItemDTO> tableData = dashBoardBO.getBarChartData();


            for (ItemDTO item :tableData) {
                series1.getData().add(new XYChart.Data(item.getDescription(), item.getQtyOnHand()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        bcItems.getData().addAll(series1);

    }

    public  void initComponents()  {



       try {
           lblCount.setText(String.valueOf(dashBoardBO.getElderCount()));
           lblEmployeeCount.setText(String.valueOf(dashBoardBO.getEmployeeCount()));


       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }


   }
  public  void  loadPieChart() throws SQLException, ClassNotFoundException {


      Year year = Year.of(LocalDate.now().getYear());
      Month month =  Month.APRIL;
              //LocalDate.now().getMonth();
      pieChart.setLegendVisible(true);

      ArrayList<BigDecimal> list = dashBoardBO.getIncomeAndExpence(year,month);

      Double elPayment = list.get(0).doubleValue();
      Double salary = list.get(1).doubleValue();
Double elexpence = dashBoardBO.getMonthTotalElderBills(year,month).doubleValue();
Double itemExpense = dashBoardBO.getItemMonthTotal(year,month).doubleValue();
      ObservableList<PieChart.Data> pieChartData =
              FXCollections.observableArrayList(
                      new PieChart.Data("Item Expenses ",itemExpense/elPayment ),
                      new PieChart.Data("Elder Expenses", elexpence/elPayment),
                      new PieChart.Data("Salary Payments",salary/elPayment ));


      pieChartData.forEach(data -> {


          String labelText = String.format("%s: %.2f%%", data.getName(), data.getPieValue() * 100);


          data.setName(labelText);


      });

      pieChart.setLabelsVisible(true);
      pieChart.setData(pieChartData);


  }


}

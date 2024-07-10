package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReportBO;
import lk.ijse.bo.impl.ReportBOImpl;
import lk.ijse.model.DbConnection;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReportFormController {
    ReportBO reportBO = (ReportBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.REPORT);

    private final Month[] monthNames = {
            Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE,
            Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER
    };

    private   String[] years = new String[10];
    @FXML
    private ChoiceBox<Month> chbMonth;

    @FXML
    private ChoiceBox<String> chbYear;
    @FXML
    private DatePicker dpfromDate;

    @FXML
    private DatePicker dptoDate;
    @FXML
    private DatePicker dpfromDateMeal;
    @FXML
    private DatePicker dptoDateMeal;
    @FXML
    private DatePicker dpfromDate1;

    @FXML
    private DatePicker dptoDate1;

    public  void initialize(){
        getYears();

chbYear.getItems().addAll(years);
chbMonth.getItems().addAll(monthNames);



    }

    private void getYears() {

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            years[i]= String.valueOf(LocalDate.now().getYear()-i);

        }

        System.out.println(Arrays.toString(years));
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/FeePayments.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String,Object> data = new HashMap<>();


            Date date1= Date.valueOf(dpfromDate.getValue());
            Date date2= Date.valueOf(dptoDate.getValue());


            data.put("getDate1", date1);
            data.put("getDate2",date2);

            JasperPrint jasperPrint =
                    null;

            jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Couldn't generate Report.Please check date Duration").show();
        }



    }

@FXML
    void btnProfitOnAction(ActionEvent event){


    try {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/profit.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();


        Month month = chbMonth.getValue();
       // Year  year = Year.of(Integer.parseInt((chbYear.getValue())));

       Year year = Year.of(Integer.parseInt(chbYear.getValue()));
        BigDecimal expence = reportBO.getMonthTotalBiil(year,month);
        ArrayList<BigDecimal> list = reportBO.getIncomeAndExpenceOfPayments(year, month);
        System.out.println("me"+list);
        BigDecimal income = list.get(0);
        BigDecimal salary = list.get(1);

        BigDecimal profit = income.subtract(expence.add(salary));



        data.put("month", month.toString());
        data.put("expence",expence);
        data.put("income", income);
        data.put("salary", salary);
        data.put("profit",profit);

        JasperPrint jasperPrint =
                null;

        jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    } catch (Exception e) {
new Alert(Alert.AlertType.ERROR,"Couldn't generate Report.\n "+e.getMessage()).show();
    }


}

    @FXML
    void btnSalaryOnAction(ActionEvent event) {

        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/salaryPayments.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String,Object> data = new HashMap<>();


            Date date1= Date.valueOf(dpfromDate1.getValue());
            Date date2= Date.valueOf(dptoDate1.getValue());


            data.put("getDate1", date1);
            data.put("getDate2",date2);

            JasperPrint jasperPrint =
                    null;

            jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Couldn't generate Report.\n "+e.getMessage()).show();
        }

    }

    public void btnViewMealOnAction(ActionEvent actionEvent) {


        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/Meal_report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String,Object> data = new HashMap<>();


            Date date1= Date.valueOf(dpfromDateMeal.getValue());
            Date date2= Date.valueOf(dptoDateMeal.getValue());


            data.put("getdate1", date1);
            data.put("getDate2",date2);

            JasperPrint jasperPrint =
                    null;

            jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Couldn't generate Report.\n "+e.getMessage()).show();
        }




    }
}

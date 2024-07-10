package lk.ijse.Util;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public   static boolean isTextFieldValid(TextField textField  , String text){

        String filed = "";

        switch (textField){
            case ID:
                filed = "^([A-Z0-9])$";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case EMAIL:
                filed="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
                break;
            case ELDERNAME:
                filed="^\\\\D*$";
                break;
            case TEL:
                filed = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$";
                break;
            case DATE:
                filed= "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
                break;
            case  NIC:
                filed="^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
                break;
            case ADDRESS:
                filed ="^[a-zA-Z0-9_]+$";
                break;
            case UNITPRICE:
                filed= "^\\d+(\\.\\d+)?$";
                break;

            case QTY:
                filed = "^\\d+$";
                break;






        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;



    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {

        if (Regex.isTextFieldValid(location, textField.getText())) {

            System.out.println("correct");
            textField.setStyle("-fx-border-color: Green;");

            //            textField.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        } else {
            System.out.println("wrong");
            //textField.getStyleClass().add("InvaldiColor.css");
            textField.setStyle("-fx-border-color: rgba(255,0,0,0.96);");


            return false;
        }
    }
    public static boolean setTextColor(TextField location, JFXTextArea textField) {

        if (Regex.isTextFieldValid(location, textField.getText())) {

            System.out.println("correct - textArea");

            textField.setStyle("-fx-border-color: rgb(0,128,0);");
            return true;
        } else {
            System.out.println("wrong");
            textField.setStyle("-fx-border-color: rgba(255,0,0,0.96);");


            return false;
        }
    }
    public static boolean setTextColor(TextField location, javafx.scene.control.DatePicker datePicker) {
        if ( datePicker.getValue() != null  && Regex.isTextFieldValid(location,datePicker.getValue().toString())) {

            System.out.println("correct");
            datePicker.setStyle("-fx-border-color: Green;");

            //            textField.setUnFocusColor(Paint.valueOf("Green"));
            return true;
        } else {
            System.out.println("wrong");
            //textField.getStyleClass().add("InvaldiColor.css");
            datePicker.setStyle("-fx-border-color: rgba(255,0,0,0.96);");


            return false;
        }
    }
}

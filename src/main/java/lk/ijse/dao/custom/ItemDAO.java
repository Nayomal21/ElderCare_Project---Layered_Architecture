package lk.ijse.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Item;
import lk.ijse.entity.Meal_Item;
import lk.ijse.model.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public interface ItemDAO extends CrudDAO<Item> {

    public ObservableList<Item> getItemList( ) ;
    public HashSet<String> getItemNames( ) ;
    public  String getCodeByItemName(String name) throws SQLException, ClassNotFoundException;
    public  int getTableRow(String name) ;
    public  boolean updateMealItem(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException ;
    public  boolean updateMealItem2ndTime(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException ;
    public  ObservableList<lk.ijse.entity.Item> getBarChartData() throws SQLException, ClassNotFoundException;

    }

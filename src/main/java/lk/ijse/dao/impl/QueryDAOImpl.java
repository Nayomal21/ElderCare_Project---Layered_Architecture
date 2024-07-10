package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Meal_Item;
import lk.ijse.model.DbConnection;
import lk.ijse.model.tm.MealItemtm;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {



    public  ObservableList<Meal_Item> getMealItemDetails(Date date) throws SQLException, ClassNotFoundException {

        ObservableList<Meal_Item> oblist = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute("SELECT i.name,imd.Qty from Item i join date_meal_item_details imd on i.itemId=imd.itemId where imd.date = ?",date);

        while (resultSet.next()){
            String name = resultSet.getNString(1);
            int qty = resultSet.getInt(2);

            Meal_Item mealItem = new Meal_Item(name, qty);

            oblist.add(mealItem);

        }
        return  oblist;

    }

}

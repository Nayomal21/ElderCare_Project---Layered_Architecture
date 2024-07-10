package lk.ijse.dao.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.MealItemDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Meal_Item;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MealItemDAOImpl  implements MealItemDAO {
ItemDAO itemDAO = new ItemDAOImpl();
    @Override
    public boolean update(Meal_Item mealItem) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE  date_meal_item_details SET  Qty = ? WHERE  itemId = ? and Date = ?",
              mealItem.getQty(),
                mealItem.getItemId(),
                mealItem.getDate());
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Meal_Item> getTableData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Meal_Item mealItem) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO date_meal_item_details VALUES (?,?,?)", LocalDate.now(),

                      mealItem.getItemId(),
                mealItem.getQty());


    }
        @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Meal_Item searchById(String text) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean MealItemAlreadyIsExists(Meal_Item mealItem) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from  date_meal_item_details where itemId =? and Date =?",
                mealItem.getItemId(),mealItem.getDate());

        return resultSet.next();
    }
}

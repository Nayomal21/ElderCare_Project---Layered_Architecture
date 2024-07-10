package lk.ijse.dao.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Meal_Item;

import java.sql.Date;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {

    public ObservableList<Meal_Item> getMealItemDetails(Date date) throws SQLException, ClassNotFoundException ;

}

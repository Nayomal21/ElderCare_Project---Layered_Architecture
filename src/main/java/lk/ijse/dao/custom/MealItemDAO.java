package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Meal_Item;

import java.sql.SQLException;
import java.time.LocalDate;

public interface MealItemDAO extends CrudDAO<Meal_Item> {

    public   boolean MealItemAlreadyIsExists(Meal_Item mealItem) throws SQLException, ClassNotFoundException;



    }

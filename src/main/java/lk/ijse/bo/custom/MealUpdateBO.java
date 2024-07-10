package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;
import lk.ijse.entity.Meal_Item;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;

public interface MealUpdateBO  extends SuperBO {

    public ObservableList<ItemDTO> getTableData() throws SQLException, ClassNotFoundException ;

    public HashSet<String> getItemNames() ;
    public MealDTO getMealDetails(String date) throws SQLException, ClassNotFoundException;
    public  ObservableList<MealItemDTO> getMealItemDetails(Date date) throws SQLException, ClassNotFoundException ;
    public  boolean deleteMealDetail(LocalDate date, ObservableList<MealItemDTO> storePreviousData) throws SQLException ;
    public  boolean updateMealItem(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException ;
    public  boolean updateSavedMeal(LocalDate date, ObservableList<MealItemDTO> obList) throws SQLException, ClassNotFoundException ;
    public  boolean updateMealDetail(lk.ijse.dto.MealDTO meal, ObservableList<MealItemDTO> obList, ObservableList<MealItemDTO> storePreviousData) throws SQLException ;

    }

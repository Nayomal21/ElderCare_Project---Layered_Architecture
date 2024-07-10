package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface MealBO  extends SuperBO {


    public ObservableList<ItemDTO> getTableData() throws SQLException, ClassNotFoundException;

    public HashSet<String> getItemNames();

    public boolean saveMealDetail(MealDTO meal, ObservableList<MealItemDTO> obList) throws SQLException, ClassNotFoundException;
}

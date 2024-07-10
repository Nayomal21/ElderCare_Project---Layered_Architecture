package lk.ijse.dao.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.custom.MealDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Meal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDAOImpl  implements MealDAO {

    @Override
    public boolean update(Meal meal) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE  Meal  SET  BreakFast=?,Lunch=?,Dinner=? WHERE  Date = ?",

                meal.getBreakfast(),
                meal.getLunch(),
                meal.getDinner(),
                meal.getDate());
    }

    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("Delete from   Meal Where Date = ?");
    }

    @Override
    public ObservableList<Meal> getTableData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Meal meal) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Meal VALUES (?,?,?,?)",meal.getDate(),
                meal.getBreakfast(),
                meal.getLunch(),
                meal.getDinner());

    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Meal searchById(String text) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute("SELECT *  FROM Meal  WHERE Date = ?",text);

        if (resultSet.next()){
            Date date1 = resultSet.getDate(1);
            String Breakfast = resultSet.getNString(2);
            String Lunch = resultSet.getNString(3);
            String Dinner = resultSet.getNString(4);

            return new lk.ijse.entity.Meal(date1.toLocalDate(),Breakfast,Lunch,Dinner);



        }
        return  null;
    }
}

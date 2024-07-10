package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.bo.custom.MealBO;
import lk.ijse.controller.MealAddFormController;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.MealDAO;
import lk.ijse.dao.custom.MealItemDAO;
import lk.ijse.dao.impl.ItemDAOImpl;
import lk.ijse.dao.impl.MealDAOImpl;
import lk.ijse.dao.impl.MealItemDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.Meal;
import lk.ijse.entity.Meal_Item;
import lk.ijse.model.DbConnection;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public class MealBOImpl implements MealBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ITEM);
    MealDAO mealDAO = (MealDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.MEAL);
    MealItemDAO mealItemDAO = (MealItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.MEALITEM);


    @Override
    public ObservableList<ItemDTO> getTableData() throws SQLException, ClassNotFoundException {
        ObservableList<Item> list = itemDAO.getTableData();

        ObservableList<ItemDTO> oblist = FXCollections.observableArrayList();

        for (Item item : list){

            oblist.add(new ItemDTO(item.getItemId(),
                    item.getDescription(),
                    item.getType(),
                    item.getQtyOnHand(),
                    item.getUnitPrice()
            ));


        } return  oblist;
    }
    @Override
    public HashSet<String> getItemNames() {
        return itemDAO.getItemNames();
    }
    public  boolean saveMealDetail(MealDTO meal, ObservableList<MealItemDTO> obList) throws SQLException, ClassNotFoundException {

        ObservableList<Meal_Item> list = FXCollections.observableArrayList();

        for (MealItemDTO mealItem : obList){
            list.add(new Meal_Item(itemDAO.getCodeByItemName(mealItem.getName()),mealItem.getQty()));


        }

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean saved = mealDAO.save(new Meal(meal.getDate(), meal.getBreakfast(), meal.getLunch(),meal.getDinner()));
          //  System.out.println(saved+"- Mealrepo");

            if ( saved) {

                try {
                    if ((saveMealItems(list))) {
                       // System.out.println(true + "- MealItemRepo" + "---" + ItemRepo.updateMealItem(list));
                        if (itemDAO.updateMealItem(list)) {
                            System.out.println(true + "- ItemRepo");
                            connection.commit();
                            return true;

                        } else {
                            System.out.println("These Items Out of Stock");
                            connection.rollback();
                            return false;
                        }
                    }
                }catch (SQLException e ){
                    MealAddFormController.alert = false;
                    new Alert(Alert.AlertType.INFORMATION, " These Items Out of Stock").show();

                }
            }

            connection.rollback();
            return false;
        }catch (Exception e ){
            MealAddFormController.alert = false;
            new Alert(Alert.AlertType.INFORMATION," Today Meal Details Already saved").show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    private boolean saveMealItems(ObservableList<Meal_Item> list) throws SQLException, ClassNotFoundException {

        for (Meal_Item mealItem : list){
            if(!mealItemDAO.save(mealItem)) return  false;


        }
        return  true;
    }


}

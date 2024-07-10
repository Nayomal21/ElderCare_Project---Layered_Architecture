package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.bo.custom.MealUpdateBO;
import lk.ijse.controller.MealUpdateFormController;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.MealDAO;
import lk.ijse.dao.custom.MealItemDAO;
import lk.ijse.dao.impl.ItemDAOImpl;
import lk.ijse.dao.impl.MealDAOImpl;
import lk.ijse.dao.impl.MealItemDAOImpl;
import lk.ijse.dao.impl.QueryDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.MealDTO;
import lk.ijse.dto.MealItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.Meal;
import lk.ijse.entity.Meal_Item;
import lk.ijse.model.DbConnection;




import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;

public class MealUpdateBOImpl  implements MealUpdateBO {
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

    @Override
    public MealDTO getMealDetails(String date) throws SQLException, ClassNotFoundException {
        Meal meal =  mealDAO.searchById(date);
        return  new MealDTO(meal.getDate(),
                meal.getBreakfast(), meal.getLunch(), meal.getDinner());
    }
@Override
    public  ObservableList<MealItemDTO> getMealItemDetails(Date date) throws SQLException, ClassNotFoundException {
        QueryDAOImpl queryDAO = (QueryDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.QUERY);
        ObservableList<Meal_Item> mealItemDetails = queryDAO.getMealItemDetails(date);
        ObservableList<MealItemDTO> observableList= FXCollections.observableArrayList();

        for (Meal_Item item : mealItemDetails){
            System.out.println(item.getItemId()+"eeeeee");
            observableList.add(new MealItemDTO(item.getItemId(),item.getQty()));

        }
        return observableList;

    }
@Override
public  boolean deleteMealDetail(LocalDate date, ObservableList<MealItemDTO> storePreviousData) throws SQLException {

    Connection connection = DbConnection.getInstance().getConnection();
    connection.setAutoCommit(false);
    try {
        boolean delete = mealDAO.delete(String.valueOf(date));
        System.out.println(delete+"- Mealrepo");

        if ( delete) {
            if ((itemDAO.updateMealItem(getType(storePreviousData)))){





                connection.commit();

                return true;


            } else {


                connection.rollback();

                return false;

            }




        }

        connection.rollback();
        return false;
    }catch (Exception e ){
        MealUpdateFormController.dalert = false;
        connection.rollback();
        return false;
    } finally {
        connection.setAutoCommit(true);
    }

}

    @Override
    public boolean updateMealItem(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException {
        return  itemDAO.updateMealItem2ndTime(obList);
    }

    private ObservableList<Meal_Item> getType(ObservableList<MealItemDTO> storePreviousData) {


        ObservableList<Meal_Item> list = FXCollections.observableArrayList();
        for (MealItemDTO dto : storePreviousData){
            list.add(new Meal_Item(dto.getName(),dto.getQty()));


        }
        return list;
    }
    @Override
    public  boolean updateMealDetail(lk.ijse.dto.MealDTO meal, ObservableList<MealItemDTO> obList, ObservableList<MealItemDTO> storePreviousData) throws SQLException {


        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean saved = mealDAO.update(new Meal(meal.getDate(),meal.getBreakfast(), meal.getLunch(), meal.getDinner()));
            System.out.println(saved+"- Mealrepo");

            if ( saved) {

                try {
                    if (updateSavedMeal(meal.getDate(), obList)) {
                        System.out.println(true + "- MealItemRepo" + "---" );
                        if (itemDAO.updateMealItem2ndTime(getType(storePreviousData))){

                            System.out.println("ItemRepo.updateMealItem2ndTime in ");
                            if (itemDAO.updateMealItem(getType(obList))) {

                                System.out.println(true + "- ItemRepo");

                                connection.commit();

                                return true;


                            } else {


                                connection.rollback();

                                return false;

                            }


                        }else {

                            connection.rollback();
                            return false;

                        }
                    }
                }catch (SQLException e ){
                    MealUpdateFormController.ualert = false;
                    new Alert(Alert.AlertType.INFORMATION, " These Items Out of Stock").show();

                    connection.rollback();
                    return false;
                }
            }

            connection.rollback();
            return false;
        }catch (Exception e ){

            MealUpdateFormController.ualert = false;
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }



    }

    private  boolean itemIsExists(String itemId, LocalDate date) throws SQLException, ClassNotFoundException {
      return   mealItemDAO.MealItemAlreadyIsExists(new Meal_Item(itemId,Date.valueOf(date)));
    }
@Override
    public  boolean updateSavedMeal(LocalDate date, ObservableList<MealItemDTO> obList) throws SQLException, ClassNotFoundException {

        int items = obList.size();
        int changed = 0;

        for (MealItemDTO item :obList) {

            if (itemIsExists(itemDAO.getCodeByItemName(item.getName()),date)) {

                  if (  mealItemDAO.update(new Meal_Item(itemDAO.getCodeByItemName(item.getName()),
                        Date.valueOf(date), item.getQty()))) changed++;

            }else {


                if (mealItemDAO.save(new Meal_Item(itemDAO.getCodeByItemName(item.getName()),
                        Date.valueOf(date), item.getQty()))) changed++;


            }
        }
        if (changed == items) return  true;
        return false;
    }


}

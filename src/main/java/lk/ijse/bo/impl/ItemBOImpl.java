package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.impl.ItemDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.HashSet;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ITEM);

    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
return  itemDAO.delete(id);
    }

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
    public ItemDTO searchById(String text) throws SQLException, ClassNotFoundException {
      Item item = itemDAO.searchById(text);
      return  new ItemDTO(item.getItemId(),item.getDescription(),item.getType(),item.getQtyOnHand(),item.getUnitPrice());
    }

    @Override
    public ObservableList<ItemDTO> getItemList() {
        ObservableList<Item>  list =    itemDAO.getItemList();
        ObservableList<ItemDTO>  oblist = FXCollections.observableArrayList();

        for (Item item : list){

            oblist.add(new ItemDTO(item.getItemId(),item.getDescription(),item.getType(),item.getQtyOnHand(),item.getUnitPrice()));

        }
        return  oblist;
    }

    @Override
    public HashSet<String> getItemNames() {
        return itemDAO.getItemNames();
    }

    @Override
    public  String getCodeByItemName(String name) throws SQLException, ClassNotFoundException {
       return  itemDAO.getCodeByItemName(name);

}

    @Override
    public int getTableRow(String name) {
        return itemDAO.getTableRow(name);
    }
}

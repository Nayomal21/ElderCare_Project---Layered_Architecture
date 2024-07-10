package lk.ijse.bo.impl;

import lk.ijse.bo.custom.ItemUpdateBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.impl.ItemDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;

import java.sql.SQLException;

public class ItemUpdateBOImpl implements ItemUpdateBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ITEM);

    @Override
    public ItemDTO searchById(String text) throws SQLException, ClassNotFoundException {
        Item item =  itemDAO.searchById(text);

        return  new ItemDTO(item.getItemId(),
                item.getDescription(),item.getType(),item.getQtyOnHand(),item.getUnitPrice());
    }

    @Override
    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getType(),item.getQtyOnHand(),item.getUnitPrice()));
    }

}

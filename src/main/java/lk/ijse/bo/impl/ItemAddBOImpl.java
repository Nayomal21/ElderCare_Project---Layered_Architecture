package lk.ijse.bo.impl;

import lk.ijse.bo.custom.ItemAddBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.impl.ItemDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;

import java.sql.SQLException;

public class ItemAddBOImpl implements ItemAddBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ITEM);

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return  itemDAO.nextId();

    }
    @Override
    public boolean save(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(item.getCode(),item.getDescription(),item.getType(),item.getQtyOnHand(),item.getUnitPrice()));



    }
}

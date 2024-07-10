package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface ItemBO  extends SuperBO {
    

    
    public Boolean delete(String id) throws SQLException, ClassNotFoundException ;

    
    public ObservableList<ItemDTO> getTableData() throws SQLException, ClassNotFoundException ;

    


    public ItemDTO searchById(String text) throws SQLException, ClassNotFoundException ;

    public ObservableList<ItemDTO> getItemList() ;
    
    public HashSet<String> getItemNames() ;

    
    public  String getCodeByItemName(String name) throws SQLException, ClassNotFoundException;
    public  int getTableRow(String name) ;
}

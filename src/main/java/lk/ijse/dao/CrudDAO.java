package lk.ijse.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudDAO <T> extends  SuperDAO{



    public  boolean update(T eto) throws SQLException, ClassNotFoundException;

    public  Boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  ObservableList<T> getTableData() throws SQLException, ClassNotFoundException;

    public boolean save(T dto) throws SQLException, ClassNotFoundException;

    public  String nextId() throws SQLException, ClassNotFoundException;

    public  T searchById(String text) throws SQLException, ClassNotFoundException;
}

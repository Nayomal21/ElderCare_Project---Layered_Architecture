package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.entity.Elder;
import lk.ijse.model.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public interface ElderDAO extends CrudDAO<Elder> {

    public  Elder searchByName(String text) throws SQLException, ClassNotFoundException;

    HashSet<String> getElderNameList() throws SQLException, ClassNotFoundException;

    String getEdlerId();
    public  Integer getCount() throws SQLException, ClassNotFoundException;
}

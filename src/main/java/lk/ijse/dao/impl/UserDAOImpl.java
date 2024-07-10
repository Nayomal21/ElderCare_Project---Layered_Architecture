package lk.ijse.dao.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl  implements UserDAO {

    @Override
    public boolean update(User eto) throws SQLException {
        return false;
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<User> getTableData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute("INSERT INTO User VALUES(?,?,?,?)",dto.getUserName(),
        dto.getName(),
        dto.getPassword(),
        dto.getUserLevel());

    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User searchById(String text) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT userName, password,userLevel FROM User WHERE userName = ?",text);
if (resultSet.next()) {
    return new User(resultSet.getNString(1),
            resultSet.getNString(2),
            resultSet.getNString(3));
}else {

    return  null;
}

    }
}

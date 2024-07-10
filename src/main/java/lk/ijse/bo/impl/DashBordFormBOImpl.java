package lk.ijse.bo.impl;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.DashBoardFormBO;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.impl.UserDAOImpl;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;

public class DashBordFormBOImpl  implements DashBoardFormBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.USER);
    public  Boolean checkCrendials(UserDTO user ) throws SQLException, ClassNotFoundException {


        lk.ijse.entity.User user1  =  userDAO.searchById(user.getUserName());

        if(user1!=null) {
            String dbPw = user1.getPassword();

            if(user.getPassword().equals(dbPw)) {
                String userLevel = user1.getUserLevel();
                LoginFormController.userLevel= userLevel;
                return true;
            } else {

                Image image = new Image("file:/home/nayomal/Desktop/projects/Eldercare/src/main/resources/icon/icon/ElderAdd_pic/alert.png");

                ImageView imageView = new ImageView(image);

                Alert alert = new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!");
                alert.setGraphic(imageView);
                alert.show();
            }

        } else {


            Image image = new Image("file:/home/nayomal/Desktop/projects/Eldercare/src/main/resources/icon/icon/ElderAdd_pic/alert.png");



            Alert alert = new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!");
            alert.show();
        }
        return  false;
    }}



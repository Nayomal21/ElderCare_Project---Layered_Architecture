package lk.ijse.bo.impl;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.ElderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.impl.ElderDAOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.entity.Elder;
import lk.ijse.model.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ElderBOImpl  implements ElderBO {
   ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);

    public ObservableList<ElderDTO> getTableData() throws SQLException, ClassNotFoundException {
        ObservableList<Elder> resultSet = elderDAO.getTableData();
        ObservableList<ElderDTO> obList = FXCollections.observableArrayList();

       for (Elder elder : resultSet){

            String firstName = elder.getFirstName();
            String lastName = elder.getLastName();
            String surname = elder.getSurName();
            String elderId =elder.getElderId();
            Date dob = elder.getDob();
            Double fee = elder.getMonthlyFee();

            Date now = Date.valueOf(LocalDate.now());
            JFXButton view = new JFXButton("View" );
            view.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(20);
            x.setFitWidth(20);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderAdd_pic/view2.png"));
            view.setGraphic(x);
//            Image image = new Image("icon/icon/ElderAdd_pic/view.png", 32, 32, false, false);
//
//
//            view.setGraphic(image);
            view.setCursor(Cursor.HAND);

            view.setOnAction(event -> {
                System.out.println("clicked view thred");

                try {
                    JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/Elder_Guardian_details_report.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                    Map<String,Object> data = new HashMap<>();
                    System.out.println(elderId);
                    data.put("ElderId",elderId);

                    JasperPrint jasperPrint =
                            null;

                    jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);


                } catch (JRException e) {
                    throw new RuntimeException(e);
                }

            });
            //print.setFont("Roboto");

            ElderDTO elder2 = new ElderDTO(firstName , lastName , (surname != null ? surname : "")
                    , Period.between(dob.toLocalDate(), now.toLocalDate()).getYears()
                    , fee, view);

            obList.add(elder2);
        }
        return obList;



    }

    @Override
    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException {
        return  elderDAO.getElderNameList();
    }


}

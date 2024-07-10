package lk.ijse.bo.impl;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.BillBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BillDAO;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.custom.FeeDAO;
import lk.ijse.dao.impl.BillDAOImpl;
import lk.ijse.dao.impl.ElderDAOImpl;
import lk.ijse.dao.impl.FeeDAOImpl;
import lk.ijse.dto.BillDTO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.entity.Elder;
import lk.ijse.entity.Fee;


import lk.ijse.model.tm.Billtm;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;

public class BIllBOImpl implements BillBO {

    BillDAO billDAO = (BillDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.BILL);
   ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);

    @Override
    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException {
        return  elderDAO.getElderNameList();
    }

    public boolean save(BillDTO dto) throws SQLException, ClassNotFoundException {

        String billId = nextId();

        lk.ijse.entity.Bill bill = new lk.ijse.entity.Bill(
                billId,
                Date.valueOf(dto.getDate()),
                dto.getElderName(),
                dto.getTotal(),
                dto.getDescription(),
                dto.getType(),
                getLocation(dto.getSelectedFiles(),dto.getElderName(),billId)

        );



        return     billDAO.save(bill);
    }

    public   String getLocation(File selectedFiles, String type, String billId ) {


        File outputDirectory;

        if (type.equals("item")){
            outputDirectory = new File("/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Bill");
        }  else {
            outputDirectory = new File("/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/" + type + "/Bills");

        }

        System.out.println(selectedFiles);

        if (selectedFiles != null) {

            Image image = new Image(selectedFiles.toURI().toString());

            try {
                File outputFile = new File(outputDirectory, billId  + ".png");
                System.out.println("chekccc");
                boolean png = ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputFile);

                System.out.println("Image saved to " + outputFile.getAbsolutePath());
                return  outputFile.getAbsolutePath();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return  "nullvalue";

    }


    public ElderDTO searchElderId(String text) throws SQLException, ClassNotFoundException {
        Elder elder =  elderDAO.searchById(text);
        System.out.println(elder.toString());
        return new ElderDTO(
                elder.getElderId(),
                elder.getFirstName(),
                elder.getLastName(),
                elder.getSurName(),
                elder.getNic(),
                elder.getAddress(),
                elder.getTel(),
                elder.getMedicalHistory(),
                elder.getMonthlyFee(),
                elder.getAdvanceFee(),
                elder.getAdmitDate(),
                elder.getDob(),
                elder.getPaymentDate(),
                elder.getEmail()
        );
    }
    public ElderDTO searchElderName(String text) throws SQLException, ClassNotFoundException {
        Elder elder =  elderDAO.searchByName(text);
        System.out.println(elder.toString());
        return new ElderDTO(
                elder.getElderId(),
                elder.getFirstName(),
                elder.getLastName(),
                elder.getSurName(),
                elder.getNic(),
                elder.getAddress(),
                elder.getTel(),
                elder.getMedicalHistory(),
                elder.getMonthlyFee(),
                elder.getAdvanceFee(),
                elder.getAdmitDate(),
                elder.getDob(),
                elder.getPaymentDate(),
                elder.getEmail()
        );
    }
    public String nextId() throws SQLException, ClassNotFoundException {
        return billDAO.nextId();
    }

    @Override
    public ObservableList<Billtm> getTableData() throws SQLException, ClassNotFoundException {
        ObservableList<lk.ijse.entity.Bill> list =   billDAO.getTableData();

        ObservableList<Billtm> obList = FXCollections.observableArrayList();


        for (lk.ijse.entity.Bill bill : list){
            String description = bill.getDescription();
            LocalDate date = bill.getDate().toLocalDate();
            Double total = bill.getTotal();
            String location = bill.getLocation();

            JFXButton view = new JFXButton("view") ;
            view.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(20);
            x.setFitWidth(20);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderAdd_pic/view2.png"));
            view.setGraphic(x);
            view.setCursor(Cursor.HAND);

            view.setOnAction(event -> {
                        System.out.println("clicked view thred");

                        System.out.println(location);
                        if (location != null){
                            new Thread(() -> {
                                String command = "open " + location;
                                try {
                                    Runtime.getRuntime().exec(command);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }else  new Alert(Alert.AlertType.INFORMATION,"No Images saved for this bill !").show();
                    }
            );
            // print.setFont("Roboto");

            Billtm billtm = new Billtm(description,date,total,view);

            obList.add(billtm);
        }
        return obList;





    }

    @Override
    public ObservableList<Billtm> getTableData(String elderName) throws SQLException, ClassNotFoundException {
        String id = elderDAO.searchByName(elderName).getElderId();
       ObservableList<lk.ijse.entity.Bill> list =   billDAO.getTableData(id);

        ObservableList<Billtm> obList = FXCollections.observableArrayList();


        for (lk.ijse.entity.Bill bill :list){
            String location = bill.getLocation();
            System.out.println(location);

            JFXButton view = new JFXButton("view");
            view.setStyle("-fx-background-color: #69e74c; -fx-text-fill: #182f11; -fx-background-radius: 15px;");
            javafx.scene.image.ImageView x =new ImageView();
            x.setFitHeight(20);
            x.setFitWidth(20);
            x.setImage(new Image
                    ("file:src/main/resources/icon/icon/ElderAdd_pic/view2.png"));
            view.setGraphic(x);
            view.setOnAction(actionEvent -> {
                System.out.println("clicked view thred");

                System.out.println(location);
                if (location != null){
                    new Thread(() -> {
                        String command = "open " + location;
                        try {
                            Runtime.getRuntime().exec(command);
                            System.out.println("call");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }else  new Alert(Alert.AlertType.INFORMATION,"No Images saved for this bill !").show();


            });

            view.setCursor(Cursor.HAND);
            // print.setFont("Roboto");

            obList.add(new Billtm(bill.getDescription(),bill.getDate().toLocalDate(),bill.getTotal(),view ));
        }

        return  obList;

    }

    @Override
    public void loadElderData() throws SQLException, ClassNotFoundException {
        elderDAO.getTableData();
    }
    @Override
    public  boolean SetFee(String feetype, String elderId, Double total) throws SQLException, ClassNotFoundException {
        FeeDAO feeDAO = new FeeDAOImpl();

        if (feetype.equals("Medical")){

            return  feeDAO.updateMedicalFees(new Fee(elderId,total));

        }else
        return  feeDAO.update(new Fee(elderId,total));
    }


}



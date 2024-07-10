package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BillDTO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.model.tm.Billtm;

import java.io.File;
import java.sql.SQLException;
import java.util.HashSet;

public interface BillBO  extends SuperBO {


    public boolean save(BillDTO dto) throws SQLException, ClassNotFoundException ;

    public   String getLocation(File selectedFiles, String type, String billId ) ;

    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException ;

    public ElderDTO searchElderName(String text) throws SQLException, ClassNotFoundException ;
    public String nextId() throws SQLException, ClassNotFoundException ;
    public ObservableList<Billtm> getTableData() throws SQLException, ClassNotFoundException ;

    ObservableList<Billtm> getTableData(String elderName) throws SQLException, ClassNotFoundException;

    void loadElderData() throws SQLException, ClassNotFoundException;
    public  boolean SetFee(String feetype, String elderId, Double total) throws SQLException, ClassNotFoundException;
}

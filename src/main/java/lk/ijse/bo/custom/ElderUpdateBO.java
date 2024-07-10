package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public interface ElderUpdateBO  extends SuperBO {


    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException ;
    

    ElderDTO searchByElderName(String text) throws SQLException, ClassNotFoundException;

    boolean deleteELder(String elderId) throws SQLException, ClassNotFoundException;

    GuardianDTO getGuardianDetails(String elderId) throws SQLException, ClassNotFoundException;
    public  Boolean updateElder(ElderDTO elder, GuardianDTO guardian) throws SQLException ;


}

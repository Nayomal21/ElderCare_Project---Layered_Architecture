package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public interface ElderAddBO extends SuperBO {
    public  Boolean saveElder(ElderDTO elder, GuardianDTO guardian, List<File> selectedFiles) throws SQLException ;

    public   Boolean makeFile(String id) ;
}

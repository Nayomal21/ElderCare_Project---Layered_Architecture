package lk.ijse.bo.impl;

import lk.ijse.bo.custom.ElderAddBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.custom.FeeDAO;
import lk.ijse.dao.custom.GuardianDAO;
import lk.ijse.dao.impl.ElderDAOImpl;
import lk.ijse.dao.impl.FeeDAOImpl;
import lk.ijse.dao.impl.GuardianDAOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;
import lk.ijse.entity.Elder;
import lk.ijse.entity.Fee;
import lk.ijse.entity.Guardian;
import lk.ijse.model.DbConnection;

import lk.ijse.repository.MedicalReportSave;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ElderAddBOImpl  implements ElderAddBO {
    ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);
   GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.GUARDIAN);

    public  Boolean saveElder(ElderDTO elder, GuardianDTO guardian, List<File> selectedFiles) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean saved = elderDAO.save(new Elder(
                    null,
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
                    elder.getEmail()));


            if ( saved) {
                makeFile(elderDAO.getEdlerId());
                if(guardianDAO.save(new Guardian(
                        null,
                        guardian.getFirstName(),
                        guardian.getLastName(),
                        guardian.getSurName(),
                        guardian.getRelation(),
                        elderDAO.getEdlerId(),
                        null,
                        guardian.getNIC(),
                        guardian.getAddress(),
                        guardian.getTel()))

                ){
                    if (selectedFiles != null) {
                        Boolean isSavedI = MedicalReportSave.add(selectedFiles, elderDAO.getEdlerId());

                        if (isSavedI) {
                            FeeDAO feeDAO = new FeeDAOImpl();
                            if (feeDAO.save(new Fee(elderDAO.getEdlerId(), elder.getPaymentDate().toLocalDate())))
                            {
                                connection.commit();
                                return true;
                            }else{

                                connection.rollback();
                                return false;
                            }
                        }
                    }else {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e ){
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public   Boolean makeFile(String id) {
        File directory = new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/"+id);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/"+id+"/medicalReports").mkdir();
                new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/"+id+"/Bills").mkdir();


                System.out.println("Directory created");
                return  true;
            } else {
                System.out.println("Failed to create directory");
            }
        }
        return  false;

    }


}

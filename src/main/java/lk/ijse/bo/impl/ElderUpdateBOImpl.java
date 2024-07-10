package lk.ijse.bo.impl;

import lk.ijse.bo.custom.ElderUpdateBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ElderDAO;
import lk.ijse.dao.custom.GuardianDAO;
import lk.ijse.dao.impl.ElderDAOImpl;
import lk.ijse.dao.impl.GuardianDAOImpl;
import lk.ijse.dto.ElderDTO;
import lk.ijse.dto.GuardianDTO;
import lk.ijse.entity.Elder;
import lk.ijse.entity.Guardian;
import lk.ijse.model.DbConnection;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public class ElderUpdateBOImpl implements ElderUpdateBO {
    ElderDAO elderDAO = (ElderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.ELDER);
            String elderId ;

    @Override
    public ElderDTO searchByElderName(String text) throws SQLException, ClassNotFoundException {
        Elder elder =  elderDAO.searchByName(text);
        elderId= elder.getElderId();
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

    @Override
    public boolean deleteELder(String elderId) throws SQLException, ClassNotFoundException {
        return elderDAO.delete(elderId);
    }

    @Override
    public GuardianDTO getGuardianDetails(String elderId) throws SQLException, ClassNotFoundException {
        GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.GUARDIAN);
        Guardian guardianDTO =  guardianDAO.searchById(elderId);
        return new GuardianDTO(
                guardianDTO.getFirstName(),
                guardianDTO.getLastName(),
                guardianDTO.getSurName(),
                guardianDTO.getRelation(),

                guardianDTO.getNIC(),
                guardianDTO.getAddress(),
                guardianDTO.getTel()

        );
    }

    @Override
    public HashSet<String> getElderNames() throws SQLException, ClassNotFoundException {
        return  elderDAO.getElderNameList();
    }

    public  Boolean updateElder(ElderDTO elder, GuardianDTO guardian) throws SQLException {


        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {

           //String elderId2 = elderId;
            boolean  updated =(elderDAO.update(new Elder(

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

            )));
            System.out.println(updated+"hello its me");
            if ( updated) {
                GuardianDAO guardianDAO = new GuardianDAOImpl();
                if(guardianDAO.update(new Guardian(

                        null,
                        guardian.getFirstName(),
                        guardian.getLastName(),
                        guardian.getSurName(),
                        guardian.getRelation(),
                        elder.getElderId(),
                        null,
                        guardian.getNIC(),
                        guardian.getAddress(),
                        guardian.getTel()))



                ){

                    connection.commit();
                    return  true;
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


}

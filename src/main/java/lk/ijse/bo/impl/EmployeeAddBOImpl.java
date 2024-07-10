package lk.ijse.bo.impl;

import lk.ijse.bo.custom.EmployeeAddBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.GuardianDAO;
import lk.ijse.dao.impl.EmployeeDAOImpl;
import lk.ijse.dao.impl.GuardianDAOImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.GuardianDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Guardian;
import lk.ijse.model.DbConnection;
import lk.ijse.repository.MedicalReportSave;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeAddBOImpl implements EmployeeAddBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.EMPLOYEE);
    GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.GUARDIAN);

    public  Boolean saveEmployee(EmployeeDTO employeeDTO, GuardianDTO guardian, List<File> selectedFiles) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean saved = employeeDAO.save(
                    new Employee(
                    null,
                    employeeDTO.getFirstName(),
                    employeeDTO.getLastName(),
                    employeeDTO.getSurName(),
                    employeeDTO.getNic(),
                    employeeDTO.getPastWorkingdetails(),
                    employeeDTO.getDob(),
                    employeeDTO.getAddress(),
                    employeeDTO.getTel(),
                    employeeDTO.getAdmitDate(),
                    employeeDTO.getSalary(),
                    employeeDTO.getType(),
                    "active"));








            if ( saved) {
                makeFile(employeeDAO.getSavedEmployeeIdId());

                if(guardianDAO.save(new Guardian(
                        null,
                        guardian.getFirstName(),
                        guardian.getLastName(),
                        guardian.getSurName(),
                        guardian.getRelation(),
                        null,
                        employeeDAO.getSavedEmployeeIdId(),
                        guardian.getNIC(),
                        guardian.getAddress(),
                        guardian.getTel()))

                ){
                    if (selectedFiles != null) {
                        Boolean isSavedI = MedicalReportSave.add(selectedFiles, employeeDAO.getSavedEmployeeIdId(), 0);

                        if (isSavedI) {
                            connection.commit();
                            return true;
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

            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }


    }

    public   Boolean makeFile(String id) {
        File directory = new File("/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Employee/"+id);

        if (!directory.exists()) {
            if (directory.mkdirs()) {



                System.out.println("Directory created");
            } else {
                System.out.println("Failed to create directory");
            }
        }
        return  true;

    }
}

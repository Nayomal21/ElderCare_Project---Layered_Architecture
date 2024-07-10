package lk.ijse.bo.impl;

import lk.ijse.bo.custom.EmployeeUpdateBO;
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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public class EmployeeUpdateBOImpl implements EmployeeUpdateBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.EMPLOYEE);
    GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTYPES.GUARDIAN);

    String employeeId ;
    @Override
    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
      return employeeDAO.getEmployeeNames();
    }

    @Override
    public EmployeeDTO searchByEmployeeName(String text) throws SQLException, ClassNotFoundException {
     Employee employee = employeeDAO.searchByName(text);
     return  new EmployeeDTO(
             employee.getEmpId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getSurName(),
             employee.getNic(),
             employee.getPastWorkingdetails(),
             employee.getDob(),
             employee.getAddress(),
             employee.getTel(),
             employee.getAdmitDate(),
             employee.getSalary(),
             employee.getType()

     );

    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
      return   employeeDAO.delete(employeeId);
    }

    @Override
    public GuardianDTO getGuardianDetails(String employeeId) throws SQLException, ClassNotFoundException {
        Guardian guardian = guardianDAO.searchById(employeeId);

        return  new GuardianDTO(
               guardian.getFirstName(),
               guardian.getLastName(),
               guardian.getSurName(),
               guardian. getRelation(),
                guardian.getNIC(),
                guardian.getAddress(),
                guardian.getTel()
        );
    }

    @Override
    public Boolean updateEmployee(EmployeeDTO employee, GuardianDTO guardian) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            Boolean updated = employeeDAO.update(new Employee(
                    employee.getEmpId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSurName(),
                    employee.getNic(),
                    employee.getPastWorkingdetails(),
                    employee.getDob(),
                    employee.getAddress(),
                    employee.getTel(),
                    employee.getAdmitDate(),
                    employee.getSalary(),
                    employee.getType(),
                   " active"








            ));
            System.out.println(updated);
            if ( updated) {
                if(guardianDAO.update(new Guardian(
                        null,
                        guardian.getFirstName(),
                        guardian.getLastName(),
                        guardian.getSurName(),
                        guardian.getRelation(),
                        null,
                        employee.getEmpId(),
                        guardian.getNIC(),
                        guardian.getAddress(),
                        guardian.getTel())

                )){
                    connection.commit();
                    return  true;
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
}

package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.GuardianDTO;

import java.sql.SQLException;
import java.util.HashSet;

public interface EmployeeUpdateBO extends SuperBO {

    public HashSet<String> getEmployeeNames() throws SQLException, ClassNotFoundException ;


    EmployeeDTO searchByEmployeeName(String text) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

    GuardianDTO getGuardianDetails(String employeeId) throws SQLException, ClassNotFoundException;
    public  Boolean updateEmployee(EmployeeDTO employeeDTO, GuardianDTO guardian) throws SQLException ;


}

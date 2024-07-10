package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private  String empId;

    private String firstName;

    private String lastName;

    private String surName;

    private String nic;

    private  String pastWorkingdetails;

    private Date dob;
    private String address;

    private String tel;

    private Date admitDate;

    private  double salary;
    private  String type;

    private JFXButton view;

    public EmployeeDTO(String empId, String firstName, String lastName, String surName, String nic, String pastWorkingdetails, Date dob, String address, String tel, Date admitDate, double salary, String type) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.nic = nic;
        this.pastWorkingdetails = pastWorkingdetails;
        this.dob = dob;
        this.address = address;
        this.tel = tel;
        this.admitDate = admitDate;
        this.salary = salary;
        this.type = type;
    }

    public EmployeeDTO(String firstName, String lastName, String surName, double salary, JFXButton view) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.salary = salary;
        this.view = view;
    }
}

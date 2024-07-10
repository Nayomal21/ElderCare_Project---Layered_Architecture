package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

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
    private  String status;


    public Employee(String empId,String firstName, String lastName, String surName, double salary) {
      this.empId= empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.salary = salary;
    }

}

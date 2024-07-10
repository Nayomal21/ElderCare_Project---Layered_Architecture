package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Guardian {


private  String guardianId;
    private String FirstName;

    private String LastName;

    private String SurName;
    private String relation;

    private  String elderId;
    private  String empId;

    private String NIC;

    private String Address;

    private String Tel;

    public Guardian(String firstName, String lastName, String surName, String relation, String NIC, String address, String tel) {
        FirstName = firstName;
        LastName = lastName;
        SurName = surName;
        this.relation = relation;
        this.NIC = NIC;
        Address = address;
        Tel = tel;
    }
}

package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Elder {
    private  String elderId;

    private String firstName;

    private String lastName;

    private String surName;

    private String nic;

    private String address;

    private String tel;

    private String medicalHistory;

    private Double monthlyFee;

    private Double advanceFee;

    private Date admitDate;

    private Date dob;

    private Date paymentDate;

    private  String email;

    public Elder(String elderId, String firstName, String lastName, String surName, Double monthlyFee, Date dob) {
        this.elderId = elderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.monthlyFee = monthlyFee;
        this.dob = dob;
    }
/*    public String getElderId() {
        return elderId;
    }

    public Elder(String firstName, String lastName, String surName, String nic, String address, String tel, String medicalHistory, Double monthlyFee, Double advanceFee, Date admitDate, Date dob, Date paymentDate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.nic = nic;
        this.address = address;
        this.tel = tel;
        this.medicalHistory = medicalHistory;
        this.monthlyFee = monthlyFee;
        this.advanceFee = advanceFee;
        this.admitDate = admitDate;
        this.dob = dob;
        this.paymentDate = paymentDate;
        this.email = email;
    }*/
}

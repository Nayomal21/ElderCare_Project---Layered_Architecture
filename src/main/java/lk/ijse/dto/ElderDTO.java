package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElderDTO {
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
    private  JFXButton view ;
    private  int age ;
    public ElderDTO(String firstName, String lastName, String s, int years, Double fee, JFXButton view) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = s;
        this.age= years;
        this.monthlyFee = fee;
        this.view= view;

    }

    public String getElderId() {
        return elderId;
    }

    public ElderDTO(String firstName, String lastName, String surName, String nic, String address, String tel, String medicalHistory, Double monthlyFee, Double advanceFee, Date admitDate, Date dob, Date paymentDate, String email) {
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
    }

    public ElderDTO(String elderId, String firstName, String lastName, String surName, String nic, String address, String tel, String medicalHistory, Double monthlyFee, Double advanceFee, Date admitDate, Date dob, Date paymentDate, String email) {
        this.elderId = elderId;
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
    }
}

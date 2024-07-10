package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    private String paymentId;

    private LocalDate date;

    private String description;

    private String empld;

    private int feeld;

    private Double amount;

    private String responsiblePerson;

    private String month;

    public Payment(int feeld, Double amount) {
        this.amount= amount;
        this.feeld= feeld;

    }

    public Payment(String month,Double amount) {
        this.amount = amount;
        this.month = month;
    }

    public Payment(Date date, Double amount, String empId) {
        this.amount= amount;
        this.date= date.toLocalDate();
        this.empld= empId;

    }

    public Payment(String paymentId, LocalDate date, String description, String empld, int feeld, Double amount, String responsiblePerson) {
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.empld = empld;
        this.feeld = feeld;
        this.amount = amount;
        this.responsiblePerson = responsiblePerson;


    }
    public Payment(String paymentId, LocalDate date, String description, String empld, Double amount, String responsiblePerson,String  month) {
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.empld = empld;
        this.amount = amount;
        this.responsiblePerson = responsiblePerson;
        this.month = month;

    }

    public Payment(Date date, Double amount) {
        this.amount= amount;
        this.date= date.toLocalDate();


    }

}

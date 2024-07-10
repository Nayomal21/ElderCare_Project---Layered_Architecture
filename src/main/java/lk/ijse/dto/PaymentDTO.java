package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private String paymentId;

    private LocalDate date;

    private String description;

    private String empld;

    private int feeld;

    private Double amount;

    private String responsiblePerson;

    private String month;

    public PaymentDTO(int feeld, Double amount) {
        this.amount= amount;
        this.feeld= feeld;

    }



    public PaymentDTO(Date date, Double amount, String empId) {
        this.amount= amount;
        this.date= date.toLocalDate();
        this.empld= empId;

    }

    public PaymentDTO(String paymentId, LocalDate date, String description, String empld, int feeld, Double amount, String responsiblePerson) {
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.empld = empld;
        this.feeld = feeld;
        this.amount = amount;
        this.responsiblePerson = responsiblePerson;


    }
    public PaymentDTO(String paymentId, LocalDate date, String description, String empld, Double amount, String responsiblePerson, String  month) {
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.empld = empld;
        this.amount = amount;
        this.responsiblePerson = responsiblePerson;
        this.month = month;

    }

    public PaymentDTO(String month, Double amount) {
        this.amount= amount;
        this.month= month;


    }

    public PaymentDTO(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
    }
}

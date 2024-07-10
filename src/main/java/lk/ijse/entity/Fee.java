package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fee {

    private int feeId;
   private LocalDate Month;
   private String elderId;
   private Double medicalFees;

   private Double empCharges;
    private Double chargesForGoods;
   private Double total;
   private String emailStatus;

    public Fee(String elderId, Double total) {
       this.elderId= elderId;
        this.total = total;
    }

    public Fee(int feeId, LocalDate month, Double total) {
        this.feeId = feeId;
        this.Month = month;
        this.total = total;
    }

    public Fee(String edlerId, LocalDate localDate) {
        this.elderId= edlerId;
        this.Month = localDate;


    }

    public Fee( String elderId,LocalDate month, Double total) {
        Month = month;
        this.elderId = elderId;
        this.total = total;
    }
}
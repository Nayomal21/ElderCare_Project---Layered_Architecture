package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeeDTO {

    int feeId;
    LocalDate Month;
    String elderId;
    Double medicalFees;

    Double empCharges;
    Double chargesForGoods;
    Double total;

    public FeeDTO(int feeId, LocalDate month, Double total) {
        this.feeId = feeId;
        Month = month;
        this.total = total;
    }


}

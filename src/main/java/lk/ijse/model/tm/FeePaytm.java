package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeePaytm {
    String month;
    Double total;
    Double paidAmount;
    String status;
}

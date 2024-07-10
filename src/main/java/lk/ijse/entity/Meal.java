package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal {

    private LocalDate date;
    private String breakfast;
    private String lunch;
    private String dinner;
}

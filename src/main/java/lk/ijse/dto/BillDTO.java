package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDTO {

    private String description;

    private LocalDate date;

    private Double total;

    private String elderName;

    private String type;

    private File selectedFiles;


}

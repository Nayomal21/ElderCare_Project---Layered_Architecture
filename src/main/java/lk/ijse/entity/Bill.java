package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

    private String id;

    private Date date;

    private String elderId;

    private Double total;

    private String description;

    private String type;
    private String location;


    public Bill(Date date, Double total, String description,String location) {
        this.date = date;
        this.total = total;
        this.description = description;
        this.location= location;
    }


}

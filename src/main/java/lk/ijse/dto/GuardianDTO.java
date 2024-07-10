package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuardianDTO {



    private String FirstName;

    private String LastName;

    private String SurName;
    private String relation;

    private String NIC;

    private String Address;

    private String Tel;

}

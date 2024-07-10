package lk.ijse.model.tm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashBoardtm {

    @FXML
    private String lblElderName;
    @FXML
    private String lblPaymentDate;


    @FXML
    private String lblAmount;


    @FXML
    private Button btnActive;



}

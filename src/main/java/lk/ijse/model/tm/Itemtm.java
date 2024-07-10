package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Itemtm {


        private String code;
        private String description;
        private Double unitPrice;
        private String qty;
        private HBox edit;
    }
package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String itemId;
    private String description;
    private  String type;
    private int qtyOnHand;
    private double unitPrice;
    private String status;

    public Item(String itemId, String description, String type, int qtyOnHand, double unitPrice) {
        this.itemId = itemId;
        this.description = description;
        this.type = type;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }
}

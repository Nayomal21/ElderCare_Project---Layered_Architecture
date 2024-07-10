package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal_Item {
private  String itemId;
private Date date;
    private  int qty;

    public Meal_Item(String itemId, int qty) {
        this.itemId = itemId;
        this.qty = qty;
    }

    public Meal_Item(String itemId, Date date) {
        this.itemId = itemId;
        this.date = date;
    }
}

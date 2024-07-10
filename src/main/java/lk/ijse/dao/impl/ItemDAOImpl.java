package lk.ijse.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Item;
import lk.ijse.entity.Meal_Item;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.HashSet;

public class ItemDAOImpl implements ItemDAO {
    public static HashSet<String> names;
    private static ObservableList<lk.ijse.entity.Item> obList;
    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE    Item  SET  name = ?, qtyOnHand=?,type=?,unitPrice=? WHERE  itemId = ?",

                item.getDescription(),
                item.getQtyOnHand(),
                item.getType(),
                item.getUnitPrice(),
                item.getItemId());

    }

    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute( "UPDATE Item SET status = 'deactive' WHERE  itemId = ?",id);

    }

    @Override
    public ObservableList<Item> getTableData() throws SQLException, ClassNotFoundException {
        names = new HashSet<>();

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Item  WHERE  status = 'active'");

        obList = FXCollections.observableArrayList();



        while (resultSet.next()) {

            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            String type = resultSet.getNString(4);
            Double unitPrice = resultSet.getDouble(5);

            obList.add(new lk.ijse.entity.Item(code,name,type,qty,unitPrice));
            names.add(name);


        }
        return obList;
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO   Item Values  (?,?,?,?,?,'active')",
                 item.getItemId(),
                item.getDescription(),
                item.getQtyOnHand(),
                item.getType(),
                item.getUnitPrice());


    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT itemId FROM Item order by itemId desc limit 1");



        String id = "I001";

        if (resultSet.next()) {

            id = resultSet.getString(1);

            if (id != null) {
                String[] split = id.split("I");  //" ", "2"
                System.out.println(Arrays.toString(split));
                int idNum = Integer.parseInt(split[1]);
                return "I" + (idNum + 1 < 10 ? "00" + (++idNum) : idNum + 1 < 100 ? "0" + (++idNum) : ++idNum);
                // return "E"+ ++idNum;
            }


        }

        return id;

    }

    @Override
    public Item searchById(String text) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Item  WHERE  itemId = ? and status = 'active'",text);


        if (resultSet.next()) {

            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            String type = resultSet.getNString(4);
            Double unitPrice = resultSet.getDouble(5);

            return new lk.ijse.entity.Item(code, name, type, qty, unitPrice);

        }
        return null;
    }

    @Override
    public ObservableList<Item> getItemList() {
        return obList;
    }

    @Override
    public HashSet<String> getItemNames() {
        return names;
    }

    @Override
    public  String getCodeByItemName(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet  = SQLUtil.execute("SELECT itemId FROM Item  WHERE  name = ? and status = 'active'",name);


        if (resultSet.next()) {

            String code = resultSet.getString(1);

            return  code;

        }
        return null;
    }
@Override
    public  int getTableRow(String name) {

        int x = 0;

        for (lk.ijse.entity.Item item : getItemList()) {
            x++;
            if (item.getDescription().equals(name))
                return x;


        }

        return x;
    }
    public  boolean updateMealItem(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException {


        for (Meal_Item  item :obList){
            boolean saved =   SQLUtil.execute("UPDATE  Item SET  qtyOnHand = qtyOnHand - ? WHERE  itemId = ?",item.getQty(),
                    (item.getItemId().contains("I0") ? item.getItemId() :getCodeByItemName(item.getItemId())) );

            System.out.println("save      "+ item+"gggggg"+saved);

            if (!saved)
 return  false;
        }

        return true;

    }
@Override
    public  boolean updateMealItem2ndTime(ObservableList<Meal_Item> obList) throws SQLException, ClassNotFoundException {
        System.out.println(obList);

        for (Meal_Item  item :obList){

            boolean updated  = SQLUtil.execute("UPDATE  Item SET  qtyOnHand = qtyOnHand + ? WHERE  itemId = ?",item.getQty(),getCodeByItemName(item.getItemId()));
            System.out.println("update    "+ item+"gggggg"+updated);

            if (!updated) return  false;

        }

        return true;

    }

    @Override
    public ObservableList<Item> getBarChartData() throws SQLException, ClassNotFoundException {

          names = new HashSet<>();

            String sql = "SELECT * FROM Item  WHERE  status = 'active' and qtyOnHand < 10 ";

            obList = FXCollections.observableArrayList();

            ResultSet resultSet = SQLUtil.execute(sql);

            while (resultSet.next()) {

                String code = resultSet.getString(1);
                String name = resultSet.getString(2);
                int qty = resultSet.getInt(3);
                String type = resultSet.getNString(4);
                Double unitPrice = resultSet.getDouble(5);

                obList.add(new lk.ijse.entity.Item(code,name,type,qty,unitPrice));
                names.add(name);


            }
            return obList;

    }


}

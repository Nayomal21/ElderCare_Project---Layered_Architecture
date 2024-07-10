package lk.ijse.dao;

import lk.ijse.model.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {



    public  static <T > T execute(String sql , Object... obj) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pvsm = connection.prepareStatement(sql);
        int i=0;
        for (Object o : obj){

            pvsm.setObject(i+1,o);


            System.out.println(i+""+o);
            i++;

        }


        if (sql.contains("SELECT")){
            System.out.println( "yes");


            return (T)pvsm.executeQuery();


        }else {

            return (T)(Boolean)( pvsm.executeUpdate()>0);
        }
    }



}
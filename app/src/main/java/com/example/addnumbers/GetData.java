package com.example.addnumbers;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    Connection connect;
    String ConnectionResult;
    Boolean isSuccess=false;

    public List<Map<String,String>> getData(){
    List<Map<String,String>>data=null;
    data=new ArrayList<Map<String,String>>();
    try {

        ConnectionDb connectionDb = new ConnectionDb();
        connect = connectionDb.CONN();
        if (connect == null) {
            ConnectionResult = "Check your internet";
        } else {
            String query = "select id,username,password from tbl_login";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("ID", rs.getString("id"));
                datanum.put("USERNAME", rs.getString("username"));
                datanum.put("PASSWORD", rs.getString("password"));
                data.add(datanum);
            }
            ConnectionResult = "Successful";
            isSuccess = true;
            connect.close();
        }
    }
    catch(Exception e)
    {
        isSuccess=false;
        ConnectionResult=e.getMessage();
    }
    return data;
    }
}

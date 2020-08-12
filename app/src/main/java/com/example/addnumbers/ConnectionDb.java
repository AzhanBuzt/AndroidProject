package com.example.addnumbers;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDb {
    String ip = "192.168.0.109";
    String port="1433";
    String classs="net.sourceforge.jtds.jdbc.Driver";
    String db="apis";
    String un="aaisha";
    String pas="aa123";

    @SuppressLint("New Api")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String ConnURL=null;
        try {
            Class.forName(classs);
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           //ConnURL = "jdbc:jtds:sqlserver://" + ip +";"+ "databaseName=" + db + ";user=" + un + ";password="+ pas +";ssl=request;";

           ConnURL="jdbc:jtds:sqlserver://192.168.0.109:1433;databaseName=apis;user=aaisha;password=aa123;";
            conn= DriverManager.getConnection(ConnURL);
            Log.e("Error",conn.toString());
        }
        catch (SQLException se)
        {
             Log.e("Error",se.getMessage());
        }
        catch (ClassNotFoundException se)
        {
            Log.e("Error",se.getMessage());
        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage());
        }
        return conn;
    }


}

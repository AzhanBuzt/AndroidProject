package com.example.addnumbers;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDb {
    String ip = "192.168.0.103";
    String classs="net.sourceforge.jtds.jdbc.driver";
    String db="apis";
    String un="DESKTOP-OMRPIQM";
    String pas="";

    @SuppressLint("New Api")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String ConnURL=null;
        try {
            Class.forName(classs).newInstance();
            ConnURL="jdbc:jtds:sqlserver://"+ip+"/instance=SQLEXPRESS;databaseName=apis;username=DESKTOP-OMRPIQM";
            conn= DriverManager.getConnection(ConnURL);
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

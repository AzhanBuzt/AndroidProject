package com.example.addnumbers;

import android.util.Log;

public class Seperator {
    String SepData,UserName,Status,Pass,Id,Role,ApiStatus,Login,ErrorMessage,Password;
    public Seperator(String toString) {
        SepData=toString;
        String[] strs = SepData.split(",");

        String[] Id_Semi= strs[0].split(":");
        Id=Id_Semi[1].toString();

        String[] ErrorMessage_Semi= strs[1].split(":");
        ErrorMessage= ErrorMessage_Semi[1].toString();

        String[] ApiStatus_Semi= strs[2].split(":");
        ApiStatus= ApiStatus_Semi[1].toString();

        String[] Login_Semi= strs[3].split(":");
        Login = Login_Semi[1].toString();

        String[] UserName_Semi= strs[4].split(":");
        UserName = UserName_Semi[1].toString();

        String[] Role_Semi= strs[5].split(":");
        Role = Role_Semi[1].toString();

        String[] Status_Semi= strs[6].split(":");
        Status = Status_Semi[1].toString();

        String[] Password_Semi= strs[7].split(":");
        Password=Password_Semi[1].toString();
    }

}

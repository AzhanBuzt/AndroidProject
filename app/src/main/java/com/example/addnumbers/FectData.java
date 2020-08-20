package com.example.addnumbers;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FectData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataParsed="";
    String singleParsed="";
    String fectdata="";
    ArrayList<HashMap<String,String >> userList=new ArrayList<>();

    public FectData(String toString) {
        fectdata=toString;
    }


    @Override
    protected  Void doInBackground(Void... voids)
    {
        try {

        URL ulr = new URL("https://webapi-pg8.conveyor.cloud/api/values/"+fectdata);
            HttpURLConnection httpurlcon= (HttpURLConnection) ulr.openConnection();
            InputStream inputStream=httpurlcon.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null)
            {
                line=bufferedReader.readLine();
                data=data +line;
            }

            JSONArray JA= new JSONArray(data);
            for(int i=0;i<JA.length();i++)
            {
                JSONObject jo= (JSONObject) JA.get(i);
                singleParsed="Id:" + jo.get("id")+","+ "ErrorMessage:" + jo.get("errorMessage")+","+ "ApiStatus:" + jo.get("ApiStatus")+","+ "login:" + jo.get("login")+","+"Name:" + jo.get("username")+","+ "Role:" + jo.get("role")+","+ "Status:" + jo.get("status")+","+ "Password:" + jo.get("password");
                dataParsed=dataParsed+singleParsed;
               AppLoginActivity.data.setText( this.dataParsed);
            }

    }
        catch(MalformedURLException e)
        {e.printStackTrace();} catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);

    }


}

package com.example.addnumbers;

import android.os.AsyncTask;

import com.google.gson.JsonArray;

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
import java.nio.charset.MalformedInputException;

public class FectData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataParsed="";
    String singleParsed="";

    @Override
    protected  Void doInBackground(Void... voids)
    {
        try {
        URL ulr = new URL("https://webapi-pg8.conveyor.cloud/api/values");
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
                singleParsed="Name:" + jo.get("username")+"\n"+
                        "role:" + jo.get("role")+"\n";

                dataParsed=dataParsed+singleParsed;
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
        MainActivity.data.setText(this.dataParsed);
    }


}

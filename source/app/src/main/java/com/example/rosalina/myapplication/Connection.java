package com.example.rosalina.myapplication;

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

public class Connection  extends AsyncTask<Void, Void, Void>{
    String data = "";
    String dataParsed ="";
    String singleParsed ="";
    String points="";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/1cbyk3");
            //connetion
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //read the data
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;

            }

            JSONArray JA = new JSONArray(data);
            for (int i = 0; i<JA.length();i++){
                JSONObject JO= (JSONObject) JA.get(i);
                singleParsed ="Nome: "+ JO.get("nome")+"\n";
                points = JO.get("local")+"%7C";
                dataParsed = dataParsed + singleParsed;
            }
            MapsActivity.waypoints = points;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MapsActivity.data.setText(this.dataParsed);

    }

}
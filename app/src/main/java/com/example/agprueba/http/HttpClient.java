package com.example.agprueba.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpClient {

    public Boolean verifyConnection(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    public String getDate(Context context) throws IOException {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String lat = sharedPref.getString("latitud", "5.9566095");
        String lon = sharedPref.getString("longitud", "-72.9533289");

        URL url = new URL("http://api.geonames.org/timezoneJSON?formatted=true&lat="+lat+"&lng="+lon+"&username=qa_mobile_easy&style=full");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String date = "";
        try {
            BufferedReader bufferedReaderUsu = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilderUs = new StringBuilder();
            String lineUs = null;
            while((lineUs = bufferedReaderUsu.readLine()) != null){
                stringBuilderUs.append(lineUs);
            }

            String response = stringBuilderUs.toString();
            JSONObject jsonObject = new JSONObject(response);
            date = (String) jsonObject.get("time");

        }catch (IOException | JSONException e) {
            e.printStackTrace();
            urlConnection.disconnect();
        }
        finally{
            urlConnection.disconnect();
        }
        return date;
    }
}

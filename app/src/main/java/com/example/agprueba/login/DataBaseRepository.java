package com.example.agprueba.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.agprueba.database.DatabaseManager;
import com.example.agprueba.database.Intent;
import com.example.agprueba.database.IntentEntity;
import com.example.agprueba.database.User;
import com.example.agprueba.database.UserEntity;
import com.example.agprueba.http.HttpClient;
import com.example.agprueba.root.App;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataBaseRepository implements LoginRepository {

    private DatabaseManager dbManager;
    private SQLiteDatabase dbLogin;

    private Context context;

    @Inject
    HttpClient httpClient;

    @Inject
    public DataBaseRepository(Context context) {
        this.context = context;
        dbManager = DatabaseManager.getInstanceDatabaseManager(context);
        ((App) context).getComponent().inject(this);
    }

    @Override
    public void saveUser(User user) {
        dbLogin = dbManager.abrirBD();
        UserEntity userEntity = new UserEntity(dbLogin);
        try {
            dbLogin.beginTransaction();
            userEntity.crearUsuario(user);
            dbLogin.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        }finally{
            dbLogin.endTransaction();
            dbManager.cerrarBD();
        }
    }




    private class getDateAsynctask extends AsyncTask<Void,Void,String> {
        String username;
        String result;

        public getDateAsynctask(String username, String result) {
            this.username = username;
            this.result = result;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Boolean connection = httpClient.verifyConnection(context);
            String date = "";
            if(connection){
                try {
                    date = httpClient.getDate(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return date;
        }

        protected void onPostExecute(String dateHttp) {
            String date = "";
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            if(!result.equals("")){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm");
                try {
                    Date newDate = format.parse(dateHttp);
                    date = df.format(newDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                date = df.format(Calendar.getInstance().getTime());
            }
            saveIntenInBD(username, result, date);
        }
    }

    @Override
    public void saveIntent(String username, String result) {
        Boolean connection = httpClient.verifyConnection(context);
        if(connection){
            new getDateAsynctask(username, result).execute();
        }else{
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            String date = df.format(Calendar.getInstance().getTime());
            saveIntenInBD(username, result, date);
        }
    }


    public void saveIntenInBD(String username, String result, String date){
        dbLogin = dbManager.abrirBD();
        IntentEntity intentEntity = new IntentEntity(dbLogin);
        try {
            dbLogin.beginTransaction();
            intentEntity.crearIntent(username, result, date);
            dbLogin.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        }finally{
            dbLogin.endTransaction();
            dbManager.cerrarBD();
        }
    }

    @Override
    public User findUser(String username, String password) {

        User user = null;

        dbLogin = dbManager.abrirBD();
        UserEntity userEntity = new UserEntity(dbLogin);

        try {
            dbLogin.beginTransaction();
            user = userEntity.findUser(username, password);
            dbLogin.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        }finally{
            dbLogin.endTransaction();
            dbManager.cerrarBD();
        }

        return user;
    }

    @Override
    public ArrayList<Intent> findIntents(String username)
    {
        ArrayList<Intent> intentsList = new ArrayList<>();

        dbLogin = dbManager.abrirBD();
        IntentEntity intentEntity = new IntentEntity(dbLogin);

        try {
            dbLogin.beginTransaction();
            intentsList = intentEntity.findIntents(username);
            dbLogin.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        }finally{
            dbLogin.endTransaction();
            dbManager.cerrarBD();
        }

        return intentsList;
    }
}

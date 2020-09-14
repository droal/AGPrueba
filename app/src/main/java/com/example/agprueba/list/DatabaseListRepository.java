package com.example.agprueba.list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.agprueba.database.DatabaseManager;
import com.example.agprueba.database.Intent;
import com.example.agprueba.database.IntentEntity;
import com.example.agprueba.database.User;
import com.example.agprueba.database.UserEntity;
import com.example.agprueba.login.LoginRepository;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseListRepository implements ListRepository {

    private DatabaseManager dbManager;
    private SQLiteDatabase dbLogin;

    @Inject
    public DatabaseListRepository(Context context) {
        dbManager = DatabaseManager.getInstanceDatabaseManager(context);

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

package com.example.agprueba.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.agprueba.database.DatabaseManager;
import com.example.agprueba.database.Intent;
import com.example.agprueba.database.IntentEntity;
import com.example.agprueba.database.User;
import com.example.agprueba.database.UserEntity;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataBaseRepository implements LoginRepository {

    private DatabaseManager dbManager;
    private SQLiteDatabase dbLogin;

    @Inject
    public DataBaseRepository(Context context) {
        dbManager = DatabaseManager.getInstanceDatabaseManager(context);

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

    @Override
    public void saveIntent(String username, String result) {
        dbLogin = dbManager.abrirBD();
        IntentEntity intentEntity = new IntentEntity(dbLogin);
        try {
            dbLogin.beginTransaction();
            intentEntity.crearIntent(username, result);
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

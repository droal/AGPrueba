package com.example.agprueba.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {

    private SQLiteDatabase dataBase;
    private SQLiteOpenHelper openHelper;
    private static DatabaseManager singletonDatabaseManager = null;


    public DatabaseManager(Context context){
        openHelper= new DataBaseLoginHelper(context);
    }

    public static DatabaseManager getInstanceDatabaseManager(Context context)
    {
        if(singletonDatabaseManager == null)
        {
            singletonDatabaseManager = new DatabaseManager(context);
        }
        return singletonDatabaseManager;
    }

    public void inicializarBD() throws Exception {
        dataBase=abrirBD();

        try{
            dataBase.beginTransaction();
            dataBase.setTransactionSuccessful();

        }catch(Exception ex){
            throw ex;
        }
        finally{
            dataBase.endTransaction();
            cerrarBD();
        }
    }//public void inicializarBD() throws Exception


    public SQLiteDatabase abrirBD()
    {
        try {
            dataBase = openHelper.getWritableDatabase();
            return dataBase;
        } catch (Exception e) {
            return null;
        }
    }//public SQLiteDatabase abrirBD()

    public boolean cerrarBD()
    {
        try
        {
            openHelper.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

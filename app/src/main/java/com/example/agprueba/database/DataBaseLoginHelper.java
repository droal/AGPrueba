package com.example.agprueba.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseLoginHelper extends SQLiteOpenHelper {

	public static final String DATABASENAME = "LoginDB";
	public static final int DATAVERSION = 1;

	/*Nombres de las tablas*/
	public static final String T_USER="T_USER";
	public static final String T_INTENT = "T_INTENT";

	/*Campos de la tabla USER*/
	public static final String USER_ID = "ID";
	public static final String USER_NAME = "USERNAME";
	public static final String USER_PSW = "PASSWORD";

	/*Campos de la tabla INTENT*/
	public static final String INTENT_ID = "ID";
	public static final String INTENT_DATE = "DATE";
	public static final String INTENT_RESULT ="RESULT";
	public static final String FK_ID_USER ="FK_ID_USER";

	public static final String CREATE_T_USER = "CREATE TABLE "+T_USER+
			" ("+
			USER_ID+" INTEGER UNIQUE NOT NULL,"+
			USER_NAME+" TEXT NOT NULL,"+
			USER_PSW+" TEXT NOT NULL,"+
			"CONSTRAINT PK_"+T_USER+" PRIMARY KEY ("+USER_ID+")"+
			");";

	public static final String CREATE_T_INTENT = "CREATE TABLE "+T_INTENT+" ("+
			INTENT_ID+" INTEGER UNIQUE NOT NULL,"+
			INTENT_DATE+" TEXT NOT NULL,"+
			INTENT_RESULT+" TEXT NOT NULL,"+
			USER_NAME+" TEXT NOT NULL,"+
			//FK_ID_USER+" INTEGER NOT NULL,"+

			"CONSTRAINT PK_"+T_INTENT+" PRIMARY KEY ("+INTENT_ID+")"+
			//"FOREIGN KEY ("+FK_ID_USER+") REFERENCES "+T_USER+" ("+INTENT_ID+")"+
			");";


	public DataBaseLoginHelper(Context context)
	{
		super(context, DATABASENAME, null, DATAVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_T_USER);
		db.execSQL(CREATE_T_INTENT);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+T_USER);
		db.execSQL("DROP TABLE IF EXISTS "+T_INTENT);
		onCreate(db);
	}

}



package com.example.agprueba.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class IntentEntity {
   private static SQLiteDatabase dbLogin;

	public IntentEntity(SQLiteDatabase dbLogin) {
		super();
		this.dbLogin = dbLogin;
	}

	public int crearIntent(String username, String result){

		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
		String date = df.format(Calendar.getInstance().getTime());

		ContentValues intentValues = new ContentValues();

		intentValues.put(DataBaseLoginHelper.USER_NAME, username);
		intentValues.put(DataBaseLoginHelper.INTENT_DATE, date);
		intentValues.put(DataBaseLoginHelper.INTENT_RESULT, result);

		int id = (int)dbLogin.insert(DataBaseLoginHelper.T_INTENT, null, intentValues);

		return id;
	}

	public ArrayList<Intent> findIntents(String username){

		Cursor intentRes = dbLogin.rawQuery(
				"SELECT "+ DataBaseLoginHelper.T_INTENT +"."+DataBaseLoginHelper.INTENT_DATE
						+"," +DataBaseLoginHelper.T_INTENT +"."+DataBaseLoginHelper.INTENT_RESULT
						+" FROM "
						+DataBaseLoginHelper.T_INTENT
						+" WHERE "
						+DataBaseLoginHelper.T_INTENT+"."+DataBaseLoginHelper.USER_NAME+" = "+"'"+username+"'"
						+";", null
		);

		ArrayList<Intent> intents = new ArrayList<>();

		if(intentRes.moveToFirst()){
			do{
				Intent intent = new Intent();
				intent.setDate(intentRes.getString(0));
				intent.setEstate(intentRes.getString(1));
				intents.add(intent);
			}while(intentRes.moveToNext());
		}

		return intents;
	}



}

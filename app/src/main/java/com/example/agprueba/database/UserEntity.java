package com.example.agprueba.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserEntity {
   private static SQLiteDatabase dbLogin;

	public UserEntity(SQLiteDatabase dbLogin) {
		super();
		this.dbLogin = dbLogin;
	}

	public int crearUsuario(User user){

		ContentValues userValues = new ContentValues();

		userValues.put(DataBaseLoginHelper.USER_NAME, user.getUsername());
		userValues.put(DataBaseLoginHelper.USER_PSW, user.getPassword());

		int idUsuario = (int)dbLogin.insert(DataBaseLoginHelper.T_USER, null, userValues);

		return idUsuario;
	}

	public User findUser(String username, String password){

		Cursor userResult = dbLogin.rawQuery(
				"SELECT "+ DataBaseLoginHelper.T_USER +"."+DataBaseLoginHelper.USER_NAME
						+"," +DataBaseLoginHelper.T_USER +"."+DataBaseLoginHelper.USER_PSW
						+" FROM "
						+DataBaseLoginHelper.T_USER
						+" WHERE "
						+DataBaseLoginHelper.T_USER+"."+DataBaseLoginHelper.USER_NAME+" = "+"'"+username+"'"
						//+" AND "
						//+DataBaseLoginHelper.T_USER+"."+DataBaseLoginHelper.USER_PSW+" = "+"'"+password+"'"
						+";", null
		);


		if (userResult.moveToFirst()){
			String nam = userResult.getString(0).toString();
			String pass = userResult.getString(1).toString();

			User user = new User(nam, pass);
			String n = user.getUsername();
			return user;
		}
		else{
			return null;
		}
	}



}

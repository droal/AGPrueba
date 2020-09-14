package com.example.agprueba.root;


import android.app.Application;

import com.example.agprueba.database.DatabaseManager;
import com.example.agprueba.list.ListModule;
import com.example.agprueba.login.LoginModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .listModule(new ListModule())
                .build();

        DatabaseManager inicializarBD = DatabaseManager.getInstanceDatabaseManager(this);
        try {
            inicializarBD.inicializarBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}

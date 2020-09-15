package com.example.agprueba.root;


import com.example.agprueba.http.HttpClient;
import com.example.agprueba.http.HttpModule;
import com.example.agprueba.list.ListActivity;
import com.example.agprueba.list.ListModule;
import com.example.agprueba.login.DataBaseRepository;
import com.example.agprueba.login.LoginActivity;
import com.example.agprueba.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class
        , LoginModule.class
        , ListModule.class
        , HttpModule.class
})
public interface ApplicationComponent {

    //Método de inyección
    void inject(LoginActivity target);
    void inject(ListActivity listActivity);
    void inject(DataBaseRepository repo);
}

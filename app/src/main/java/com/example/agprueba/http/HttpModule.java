package com.example.agprueba.http;

import android.content.Context;

import com.example.agprueba.login.DataBaseRepository;
import com.example.agprueba.login.LoginRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {

    @Provides
    public HttpClient provideHttpClient(Context context){
        return new HttpClient();
    }
}

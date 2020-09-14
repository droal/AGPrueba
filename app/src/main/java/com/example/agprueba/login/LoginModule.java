package com.example.agprueba.login;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository){
        return  new LoginActivtyModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository(Context context){
        return new DataBaseRepository(context);
    }
}

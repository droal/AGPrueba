package com.example.agprueba.login;

import com.example.agprueba.database.User;


import java.io.IOException;

import dagger.Module;

@Module
public class LoginActivtyModel implements LoginActivityMVP.Model{

    private LoginRepository repository;


    public LoginActivtyModel(LoginRepository repository){
        this.repository = repository;
    }


    @Override
    public void createUser(String username, String password) {
        repository.saveUser(new User(username, password));
    }

    @Override
    public void createIntent(String userName, String result) {
        try {
            repository.saveIntent(userName, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(String userName, String password) {
        User user = repository.findUser(userName, password);

        return user;
    }




}

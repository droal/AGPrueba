package com.example.agprueba.login;

import com.example.agprueba.database.User;


public interface LoginActivityMVP {

    interface View{
        String getUserName();
        String getPassword();

        void showUserNotAvailable();
        void showInputUSerNameError();
        void showInputPasswordError();
        void showInputEmptyError();
        void showUserSaved();

        void setUserName(String userName);
        void setPassword(String password);

        void passToListActivity(String userName);
    }

    interface Presenter{
        void setView(LoginActivityMVP.View view);
        void loginButtonClicked();
        void signUpButtonClicked();
    }

    interface Model{
        void createUser(String userName, String password);
        void createIntent(String userName, String result);
        User findUser(String userName, String password);
    }
}

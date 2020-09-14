package com.example.agprueba.login;

import androidx.annotation.Nullable;
import com.example.agprueba.database.User;


public class LoginActivityPresenter implements LoginActivityMVP.Presenter{

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if(view != null) {
            String username = view.getUserName().trim();
            String password = view.getPassword().trim();

            if(username.equals("") || password.equals("")){
                view.showInputEmptyError();
            }else{
                User user = model.findUser(username, password);
                if(user != null){
                    String pass = user.getPassword();

                    if(pass.equals(password)){
                        model.createIntent(username, "succes");
                        view.passToListActivity(username);
                    }else{
                        model.createIntent(username, "fail");
                        view.showUserNotAvailable();
                    }
                }
                else{
                    view.showUserNotAvailable();
                }
            }

        }

    }

    @Override
    public void signUpButtonClicked() {

        if(view != null){
            String username = view.getUserName().trim();
            String password = view.getPassword().trim();

            if(validateInput(username, password)){
                model.createUser(username, password);
                view.showUserSaved();
                view.setPassword("");
                view.setUserName("");
            }
        }
    }


    private boolean validateInput(String username, String password){
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
        String mailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

        if(username.equals("") || password.equals("")){
            view.showInputEmptyError();
            return false;
        }
        else if(!username.matches(mailRegex)){
            view.showInputUSerNameError();
            return false;
        }
        else if(!password.matches(passRegex)){
            view.showInputPasswordError();
            return false;
        }
        else{
            return true;
        }
    }


}

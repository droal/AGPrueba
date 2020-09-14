package com.example.agprueba.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agprueba.R;
import com.example.agprueba.databinding.ActivityLoginBinding;
import com.example.agprueba.list.ListActivity;
import com.example.agprueba.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject
    LoginActivityMVP.Presenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getComponent().inject(this);

        //Eventos botones
        funcButtons();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }


    private void funcButtons() {

        //Ingresar
        binding.loginBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });


        //Registrar usuario
        binding.loginBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signUpButtonClicked();
            }
        });
    }


    @Override
    public void passToListActivity(String userName) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }



    @Override
    public String getUserName() {
        return this.binding.loginEtEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return this.binding.loginEtPw.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, getString(R.string.login_user_not_found), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputEmptyError() {
        Toast.makeText(this, getString(R.string.login_input_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputUSerNameError() {
        Toast.makeText(this, getString(R.string.login_username_err), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputPasswordError() {
        Toast.makeText(this, getString(R.string.login_password_err), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, getString(R.string.login_user_created), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserName(String userName) {
        binding.loginEtEmail.setText(userName);
    }

    @Override
    public void setPassword(String password) {
        binding.loginEtPw.setText(password);
    }


}

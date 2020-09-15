package com.example.agprueba.login;

import com.example.agprueba.database.Intent;
import com.example.agprueba.database.User;

import java.io.IOException;
import java.util.ArrayList;

public interface LoginRepository {
    void saveUser(User user);
    void saveIntent(String username, String result) throws IOException;

    User findUser(String username, String password);
    ArrayList<Intent> findIntents(String username);
}

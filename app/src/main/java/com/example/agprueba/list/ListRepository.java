package com.example.agprueba.list;

import com.example.agprueba.database.Intent;
import com.example.agprueba.database.User;

import java.util.ArrayList;

public interface ListRepository {

    ArrayList<Intent> findIntents(String username);
}

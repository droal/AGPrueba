package com.example.agprueba.list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.agprueba.database.Intent;

import java.util.ArrayList;
import java.util.List;


public class ListPresenter implements ListMVP.Presenter{

    @Nullable
    private ListMVP.View view;
    private ListMVP.Model model;

    public ListPresenter(ListMVP.Model  model) {
        this.model = model;

    }

    @Override
    public void setView(ListMVP.View view) {
        this.view = view;
    }

    @Override
    public void requestList(String username) {
        ArrayList<Intent> intents = model.findIntents(username);
        view.showIntentsList(intents);
    }


}

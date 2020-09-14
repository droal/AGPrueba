package com.example.agprueba.list;

import androidx.lifecycle.LiveData;

import com.example.agprueba.database.Intent;

import java.util.ArrayList;
import java.util.List;


public interface ListMVP {

    interface View{
        void showIntentsList(List<Intent> intents);
        void showError();
    }

    interface Presenter{
        void setView(ListMVP.View view);
        void requestList(String userName);
    }

    interface Model{
        LiveData<List<Intent>> getObservable();
        ArrayList<Intent> findIntents(String userName);
    }

}


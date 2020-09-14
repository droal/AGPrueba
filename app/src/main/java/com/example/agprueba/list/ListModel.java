package com.example.agprueba.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.agprueba.database.Intent;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

@Module
public class ListModel implements ListMVP.Model{

    private ListRepository repository;

    private MutableLiveData<List<Intent>> intentList = null;

    public ListModel(ListRepository repository){
        this.repository = repository;
    }


    @Override
    public LiveData<List<Intent>> getObservable(){
        if(intentList == null){
            intentList = new MutableLiveData<>();
        }
        return intentList;
    }


    @Override
    public ArrayList<Intent> findIntents(String userName) {
        ArrayList<Intent> intents = repository.findIntents(userName);

        //intentList.setValue(intents);

        return intents;
    }

}

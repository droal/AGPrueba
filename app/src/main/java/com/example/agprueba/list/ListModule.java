package com.example.agprueba.list;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {

    @Provides
    public ListMVP.Presenter provideListPresenter(ListMVP.Model model){
        return new ListPresenter(model);
    }

    @Provides
    public ListMVP.Model provideListModel(ListRepository repository){
        return  new ListModel(repository);
    }

    @Provides
    public ListRepository provideListRepository(Context context){
        return new DatabaseListRepository(context);
    }


}

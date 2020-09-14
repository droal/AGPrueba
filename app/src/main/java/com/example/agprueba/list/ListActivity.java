package com.example.agprueba.list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agprueba.R;
import com.example.agprueba.database.Intent;
import com.example.agprueba.databinding.ActivityIntentsListBinding;
import com.example.agprueba.login.LoginActivityMVP;
import com.example.agprueba.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListActivity extends AppCompatActivity implements ListMVP.View{

    @Inject
    ListMVP.Presenter presenter;

    private ActivityIntentsListBinding binding;

    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntentsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getComponent().inject(this);

        username = getIntent().getExtras().getString("username");
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.requestList(username);
    }

    @Override
    public void showIntentsList(List<Intent> intents) {

        List<String> intentsList = new ArrayList<>();
        for (Intent intent  : intents)
            intentsList.add("Intent date: "+intent.getDate()+"   Result:"+intent.getEstate());

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, intentsList);

        binding.lvIntentsList.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.list_err), Toast.LENGTH_LONG).show();
    }

}


package com.example.agprueba.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private LocationManager locationManager = null;
    private static final int idRequestPermisionGPS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getComponent().inject(this);

        //Get location
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        requestLocation();

        //Eventos botones
        funcButtons();
    }

    private void requestLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, idRequestPermisionGPS);
                return;
            }
            else {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(Criteria.POWER_LOW);

                String bestProvider = locationManager.getBestProvider(criteria, true);

                if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                }

                locationManager.requestLocationUpdates(bestProvider, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("longitud", String.valueOf(location.getLongitude()));
                        editor.putString("latitud", String.valueOf(location.getLatitude()));
                        editor.commit();

                        locationManager.removeUpdates(this);
                        locationManager = null;

                    }

                    @Override public void onStatusChanged(String s, int i, Bundle bundle) {}
                    @Override public void onProviderEnabled(String s) {}
                    @Override public void onProviderDisabled(String s) {}
                });
            }
        }
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

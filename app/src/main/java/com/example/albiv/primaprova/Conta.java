package com.example.albiv.primaprova;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

public class Conta extends AppCompatActivity {

    protected ConstraintLayout sfondo;
    protected TextView testo;
    protected int i = 0;
    private LocationManager posizionemanager;
    private LocationListener posizioneascoltatore;
    private Switch gps,net;
    private Button mappa;
    private String coordinate;
    Double Altitudine,Longitudine,Latitudine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);

        testo = findViewById(R.id.testo1);
        sfondo = findViewById(R.id.sfondo);
        sfondo.setBackgroundResource(R.drawable.sfondo);
        gps = findViewById(R.id.switch1);
        net = findViewById(R.id.switch2);
        coordinate = "https://www.google.it/maps/search/40.8630+14.2767+@40.8630,14.2767,17z";
        mappa = findViewById(R.id.web);

        //Cancella la barra superiore e la torna a nascondere quando ho tirato giù la tendina di notifica
        // Chissà se funzia
        Layout struttura = new Layout();
        struttura.notifiche();
        struttura.actionbarHide();

        ////LINK BOTTONE /////
        mappa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(coordinate);
            }
        });
        ////POSIZIONE////
        posizionemanager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        posizioneascoltatore = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                testo.setText("Longitudine:"+location.getLongitude()+"\nLatitudine: "+location.getLatitude()+"\nAltitudine: "+location.getAltitude());
                testo.setTextColor(Color.GREEN);
                Altitudine = location.getAltitude();
                Longitudine = location.getLongitude();
                Latitudine = location.getLatitude();
                coordinate = "https://www.google.it/maps/search/"+Latitudine+"+"+Longitudine+"+/@"+Latitudine+","+Longitudine+",17z";
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},10);
        }else{
            configureSwitch();
        }
        ///// FINE POSIZIONE /////

    }
    ///// CHIAMATA PERMESSI ///
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    configureSwitch();
                    return;
                }
        }

    }
    /////// COSA FARE ALLA CAMBIAMENTO DEGLI SWITCH ///////
    private void configureSwitch() {
        gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    //posizionemanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, posizioneascoltatore);
                    posizionemanager.requestLocationUpdates("gps", 0, 0, posizioneascoltatore);
                }
                else
                {
                    posizionemanager.removeUpdates(posizioneascoltatore);
                    testo.setText("Longitudine: "+Longitudine+"\nLatitudine: "+Latitudine+"\nAltitudine: "+Altitudine);
                    testo.setTextColor(Color.RED);
                }
            }
        });
        net.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @SuppressLint("MissingPermission")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    posizionemanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, posizioneascoltatore);
                }
                else
                {
                    posizionemanager.removeUpdates(posizioneascoltatore);
                    testo.setText("Longitudine: "+Longitudine+"\nLatitudine: "+Latitudine+"\nAltitudine: "+Altitudine);
                    testo.setTextColor(Color.RED);
                }
            }
        });
    }
    //// APERTURA PAGINA WEB ////
    public void openWebPage(String coordinate) {
        Uri webpage = Uri.parse(coordinate);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

package pnj.uts.aplikasigps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        TextView koordinat;
        TextView koordinat2;
        TextView koordinat3;
        Button btnShowMap, btnMap;
        FusedLocationProviderClient fusedLocationProviderClient;

        btnShowMap = findViewById(R.id.btnShowMap);
        btnMap = findViewById(R.id.btnPeta);
        koordinat = findViewById(R.id.koordinat);
        koordinat2 = findViewById(R.id.koordinat2);
        koordinat3 = findViewById(R.id.koordinat3);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED){
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        if (location != null){
                                            Double ltd=location.getLatitude();
                                            Double lgt=location.getLongitude();
                                            Double alt=location.getAltitude();

                                            koordinat.setText(ltd+" ");
                                            koordinat2.setText(lgt+" ");
                                            koordinat3.setText(alt+" ");
                                            Toast.makeText(MainActivity.this, "Posisi Anda Berhasil Ditemukan", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
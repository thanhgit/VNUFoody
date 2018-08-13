package com.phuocthang.vnufoody;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.phuocthang.database.DBHelper;
import com.phuocthang.util.GPSUtilities;

public class MainActivity extends AppCompatActivity {
    /*private static final int ACCESS_FINE_LOCATION_CODE = 1;
    private LocationManager locationManager;
    private GPSUtilities gpsUtilities;
    double longitude;
    double latitude;*/

    Toolbar myToobar;
    /*Button btn_func_1;
    Button btn_func_2;*/
    private int func_code = 0;
    FloatingActionButton fla_function_1,fla_function_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewControls();
        ViewEvents();
       /* gpsUtilities = new GPSUtilities(MainActivity.this);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        //permission gps
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_CODE);

        } else {

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                    longitude = gpsUtilities.getLongitude();
                    latitude = gpsUtilities .getLatitude();

                    Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();

            } else {
                 gpsUtilities.showSettingsAlert();
            }
        }*/
    }


    private void ViewControls() {
        /*btn_func_1 = (Button) findViewById(R.id.btn_func_1);
        btn_func_2 = (Button) findViewById(R.id.btn_func_2);*/

        fla_function_1 = (FloatingActionButton) findViewById(R.id.function_1);
        fla_function_2 = (FloatingActionButton) findViewById(R.id.function_2);

        myToobar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToobar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void ViewEvents() {
        /*btn_func_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_code = 1;
                Intent mapsView = new Intent(MainActivity.this, MapsActivity.class);

                Bundle func_1_bundle = new Bundle();
                func_1_bundle.putInt("FUNCTION_CODE", func_code);
                mapsView.putExtra("FUNCTION_BUNDLE",func_1_bundle);

                startActivity(mapsView);
            }
        });

        btn_func_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                func_code = 2;
                Intent mapsView = new Intent(MainActivity.this, MapsActivity.class);

                Bundle func_2_bundle = new Bundle();
                func_2_bundle.putInt("FUNCTION_CODE", func_code);
                mapsView.putExtra("FUNCTION_BUNDLE",func_2_bundle);


                startActivity(mapsView);
            }
        });*/


        fla_function_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_code = 1;
                Intent mapsView = new Intent(MainActivity.this, MapsActivity.class);

                Bundle func_1_bundle = new Bundle();
                func_1_bundle.putInt("FUNCTION_CODE", func_code);
                mapsView.putExtra("FUNCTION_BUNDLE",func_1_bundle);

                startActivity(mapsView);
            }
        });
        fla_function_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_code = 2;
                Intent mapsView = new Intent(MainActivity.this, MapsActivity.class);

                Bundle func_2_bundle = new Bundle();
                func_2_bundle.putInt("FUNCTION_CODE", func_code);
                mapsView.putExtra("FUNCTION_BUNDLE",func_2_bundle);


                startActivity(mapsView);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       /* switch (requestCode) {
            case ACCESS_FINE_LOCATION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Cấp quyền thành công", Toast.LENGTH_SHORT).show();
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                        longitude = gpsUtilities.getLongitude();
                        latitude = gpsUtilities .getLatitude();

                        Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();

                    } else {
                        gpsUtilities.showSettingsAlert();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Cấp quyền thất bại", Toast.LENGTH_SHORT).show();
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1);
                }
                 }
                return;
            }
        }*/
    }


}

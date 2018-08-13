package com.phuocthang.vnufoody;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.phuocthang.database.DBHelper;
import com.phuocthang.model.Place;
import com.phuocthang.util.GPSUtilities;

import java.util.ArrayList;
import java.util.Collection;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private GPSUtilities gpsUtilities;
    double longitude;
    double latitude;

    Toolbar mapsToolbar;
    int func_code=0;

    // THANH_UIT change in 4/25/2017 6:19 AM
    DBHelper db;
    ArrayList<Place> places=new ArrayList<Place>();
    // End change THANH_UIT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //gpsUtilities = new GPSUtilities(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        addViewsControls();

        Intent mapsIntent = getIntent();
        Bundle mapsBundle = mapsIntent.getBundleExtra("FUNCTION_BUNDLE");
        func_code = mapsBundle.getInt("FUNCTION_CODE");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void addViewsControls() {
        mapsToolbar = (Toolbar) findViewById(R.id.mapsToolbar);
        setSupportActionBar(mapsToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // THANH_UIT change in 4/25/2017 7:56 AM
      /*  db=new DBHelper(this);
        db.insertPlace(new Place(0,db.getIdByName("ate"),"BoBaPop - Làng Đại Học",10.874732, 106.799426,"\n" +
                "Đông Hòa, Tx. Dĩ An, Bình Dương, Việt Nam","null"));
        db.insertPlace(new Place(0,db.getIdByName("others"),"Chợ làng đại học",10.876283, 106.801965,"Linh Trung, Thủ Đức, Hồ Chí Minh, Việt Nam","null"));
        db.insertPlace(new Place(0,db.getIdByName("others"),"Ký túc xá khu A ĐH Quốc gia Tp. Hồ Chí Minh",10.878251, 106.806158,"Linh Trung Ward Đông Hòa, dĩ an,bình dương, Thu Duc District, Linh Trung, Thủ Đức, Ho Chi Minh, Việt Nam","null"));
        db.insertPlace(new Place(0,db.getIdByName("drink"),"Feel Coffee & Tea Express,Thủ Đức",10.875888, 106.802110,"A2, Đông Hòa, Hồ Chí Minh, Bình Dương, Việt Nam","null"));
        db.insertPlace(new Place(0,db.getIdByName("others"),"Trường Đại Học Bách Khoa TPHCM Cơ Sở 2",10.880425, 106.806133,"Đông Hòa, Tx. Dĩ An, Bình Dương, Việt Nam","null"));*/
        places.addAll(db.getAllPlaces());
        //Log.d("MapsActivity","Result"+places.get(0).getPlaceName());
        // End change THANH_UIT
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private LatLngBounds ADELAIDE = new LatLngBounds(
            new LatLng(10.870274, 106.778160), new LatLng(10.889082, 106.809662));
            //new LatLng(10.871969, 106.783875), new LatLng(10.885366, 106.804647));

            //new LatLng(-35.0, 138.58), new LatLng(-34.9, 138.61));
    private LatLng DHGQ = new LatLng(10.875340, 106.803102);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);



        if(func_code==1) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();

            mMap.setMinZoomPreference(13.5f);
            mMap.setMaxZoomPreference(20.0f);
            mMap.addMarker(new MarkerOptions().position(ADELAIDE.northeast).title("Marker in Sydney"));
            mMap.addMarker(new MarkerOptions().position(ADELAIDE.southwest).title("Marker in Sydney"));

            // THANH_UIT change in 4/25/2017 8:40 AM
            for(Place place:places){
                Log.d("MapsActivity",place.getPlaceName());
                mMap.addMarker(new MarkerOptions().position(new LatLng(place.getLatitude(),place.getLongitude())).title(place.getPlaceName())).setTag(place.getId());
            }
            // End change THANH_UIT

            mMap.setLatLngBoundsForCameraTarget(ADELAIDE);

           // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ADELAIDE.getCenter(), 15));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DHGQ, 15));
            DrawArea();

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    mMap.clear();
                    DrawArea();
                    Toast.makeText(MapsActivity.this,latLng.latitude+"\n"+latLng.longitude,Toast.LENGTH_SHORT).show();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
                    DrawAreaOnTouch(latLng);
                }
            });

            // THANH_UIT change in 4/25/2017 10:35 AM
            mMap.setOnMarkerClickListener(MapsActivity.this);
            // End change THANH_UIT
        }
        else
            Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();


        /*locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


            longitude = gpsUtilities.getLongitude();
            latitude = gpsUtilities .getLatitude();

            //Toast.makeText(this,latitude + longitude+"",Toast.LENGTH_LONG).show();
        } else {
            gpsUtilities.showSettingsAlert();
        }*/


        /*LatLng nhakhachdhqg = new LatLng( 10.879714, 106.795152);
        LatLng cucbac = new LatLng(10.894636, 106.795368);
        LatLng cucnam = new LatLng(10.863023, 106.795924);*/
        /*LatLngBounds DHGQ = new LatLngBounds(
                cucbac, cucnam);*/
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        /*mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(ADELAIDE, 400,800,5));
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);*/
        //mMap.setOnCameraChangeListener(this);

       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nhakhachdhqg, 12.0f));
    }

    public void DrawArea()
    {
        LatLng nhakhachdhqg = new LatLng( 10.879714, 106.795152);
        mMap.addCircle(new CircleOptions()
                .center(nhakhachdhqg)
                .radius(2000)
                .strokeWidth(0)
                .fillColor(Color.argb(50,23, 103, 52)));

    }

    public void DrawAreaOnTouch(LatLng latlng)
    {

        LatLng nhakhachdhqg = new LatLng( latlng.latitude, latlng.longitude);
        mMap.addCircle(new CircleOptions()
                .center(nhakhachdhqg)
                .radius(500)
                .strokeWidth(0)
                .fillColor(Color.argb(50,241, 117, 37)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuMapsItem) {
        if(menuMapsItem.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(menuMapsItem);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Bundle bundle=new Bundle();
        bundle.putString("id_place",marker.getTag().toString());
        FragmentManager fragmentManager=getFragmentManager();
        DetailPlaceFragmentDialog dialog=new DetailPlaceFragmentDialog();
        dialog.setArguments(bundle);
        dialog.show(fragmentManager,"fragment_detail_Place");
        return false;
    }

}

class getData  extends AsyncTask<Void,ArrayList<Place>,Void>{

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(ArrayList<Place>... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}


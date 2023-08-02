package com.example.Start_Screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FoodMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmap);

        fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location1 = new LatLng(37.5913, 127.0221);
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.title("성신여대");
        markerOptions1.snippet("학교");
        markerOptions1.position(location1);
        googleMap.addMarker(markerOptions1);

        LatLng location2 = new LatLng( 37.59180699674069, 127.01848170470035);
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.title("제순식당");
        markerOptions2.snippet("한식");
        markerOptions2.position(location2);
        googleMap.addMarker(markerOptions2);

        LatLng centerLocation = new LatLng((location1.latitude + location2.latitude) / 2,
                (location1.longitude + location2.longitude) / 2);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 14));
    }
}

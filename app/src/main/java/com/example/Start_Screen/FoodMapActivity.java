package com.example.Start_Screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.EditText;
import android.location.Geocoder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class FoodMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private FragmentManager fragmentManager;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private EditText etSearch;

    private boolean isInitialLocation = true;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmap);

        etSearch = findViewById(R.id.etSearch);
        fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // onCreate 메서드에서 권한을 체크하고 요청합니다.
        // 1. 사용자의 현재 위치 권한이 허용되어 있는지 확인합니다.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // 2. 권한이 허용되어 있다면, 지도에 현재 위치를 표시하는 기능을 활성화합니다.
            googleMap.setMyLocationEnabled(true);
        } else {
            // 3. 권한이 허용되어 있지 않다면, 사용자에게 권한을 요청합니다.
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST_CODE);
        }
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // 10초마다 위치 업데이트
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1);
            return;
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    currentLocation = location;
                    if (isInitialLocation) {
                        updateMapWithCurrentLocation();
                        isInitialLocation = false;
                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용된 경우, 다시 지도를 초기화하고 현재 위치를 표시합니다.
                mapFragment.getMapAsync(this);
            } else {
                // 권한이 거부된 경우 사용자에게 알림을 띄울 수 있습니다.
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng location1 = new LatLng(37.5913, 127.0221);
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.title("성신여대");
        markerOptions1.snippet("학교");
        markerOptions1.position(location1);
        googleMap.addMarker(markerOptions1);

        LatLng location2 = new LatLng(37.59180699674069, 127.01848170470035);
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.title("제순식당");
        markerOptions2.snippet("한식");
        markerOptions2.position(location2);
        googleMap.addMarker(markerOptions2);

        googleMap.setOnMarkerClickListener(this);

        LatLng centerLocation = new LatLng((location1.latitude + location2.latitude) / 2,
                (location1.longitude + location2.longitude) / 2);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 14));

        addZoomControls(); // 지도 확대 및 축소 버튼 및 현재 위치 버튼 추가

        // 검색 기능을 추가합니다.
        // ...

        // 현재 위치 버튼 클릭 이벤트 처리
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                // 현재 위치로 이동합니다.
                updateMapWithCurrentLocation();
                return true;
            }
        });

        // 지도 패딩을 설정하여 "내 위치" 버튼이 확대/축소 버튼 위에 겹치지 않도록 합니다.
        // (left, top, right, bottom)
        googleMap.setPadding(0, 0, 20, 200);
    }

    // 검색 기능을 구현합니다.
    // ...

    // AlertDialog로 장소 선택 리스트 보여주기
    // ...

    private void addZoomControls() {
        if (googleMap != null) {
            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);
        }
    }

    private void updateMapWithCurrentLocation() {
        if (googleMap != null && currentLocation != null) {
            LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (currentLocation != null) {
            LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            LatLng markerLatLng = marker.getPosition();

            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(currentLatLng)
                    .add(markerLatLng);
            Polyline polyline = googleMap.addPolyline(polylineOptions);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }
}
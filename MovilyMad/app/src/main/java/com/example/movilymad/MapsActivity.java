package com.example.movilymad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, RoutingListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    ArrayList<MarkerOptions> zityMarkers;
    ArrayList<MarkerOptions> biciMadMarkers;
    ArrayList<MarkerOptions> eMovMarkers;
    LatLng latlng = new LatLng(40.46407, -3.69276);
    MarkerOptions me;
    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.colorPrimary};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        polylines = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 177);
            }
            return;
        } else {
            mMap.setMyLocationEnabled(true);
            me = new MarkerOptions().position(latlng).title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            mMap.addMarker(me);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(me.getPosition(), 15));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (!marker.getTitle().equals("Me"))
                        getRouteToMarker(marker.getPosition());
                    return false;
                }
            });
            setMarkers();
            for (MarkerOptions marker : zityMarkers)
                mMap.addMarker(marker);
            for (MarkerOptions marker : biciMadMarkers)
                mMap.addMarker(marker);
            for (MarkerOptions marker : eMovMarkers)
                mMap.addMarker(marker);

        }
    }

    private void getRouteToMarker(LatLng destination) {
        Routing routing = new Routing.Builder()
                .key("AIzaSyD-8g2SQvSFc6eXBunDbrrrsf7qTrI42e8")
                .travelMode(AbstractRouting.TravelMode.WALKING)
                .withListener(this)
                .alternativeRoutes(false)
                .waypoints(me.getPosition(), destination)
                .build();
        routing.execute();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 8);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 8);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void setMarkers() {
        zityMarkers = new ArrayList<>();
        biciMadMarkers = new ArrayList<>();
        eMovMarkers = new ArrayList<>();

        MarkerOptions zity_1 = new MarkerOptions()
                .position(new LatLng(40.46759298106364, -3.693600498355295))
                .title("Zity 1").snippet("Matricula: 1234AAA").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        MarkerOptions zity_2 = new MarkerOptions()
                .position(new LatLng(40.467429736651184, -3.697012268222238))
                .title("Zity 2").snippet("Matricula: 2222BBB").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        MarkerOptions zity_3 = new MarkerOptions()
                .position(new LatLng(40.46279342968996, -3.6999948846467987))
                .title("Zity 3").snippet("Matricula: 4567CCC").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        zityMarkers.add(zity_1);
        zityMarkers.add(zity_2);
        zityMarkers.add(zity_3);

        MarkerOptions bici_1 = new MarkerOptions()
                .position(new LatLng(40.464295366988466, -3.6900599824556366))
                .title("BiciMad 1").snippet("Matricula: 7891DDD").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        MarkerOptions bici_2 = new MarkerOptions()
                .position(new LatLng(40.45756903840472, -3.68297895065632))
                .title("BiciMad 2").snippet("Matricula: 3216EEE").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        MarkerOptions bici_3 = new MarkerOptions()
                .position(new LatLng(40.46103024305326, -3.695746265567209))
                .title("BiciMad 3").snippet("Matricula: 7895FFF").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        biciMadMarkers.add(bici_1);
        biciMadMarkers.add(bici_2);
        biciMadMarkers.add(bici_3);

        MarkerOptions emov_1 = new MarkerOptions()
                .position(new LatLng(40.463111259517575, -3.698122501373291))
                .title("Emov 1").snippet("Matricula: 6724CCD").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        MarkerOptions emov_2 = new MarkerOptions()
                .position(new LatLng(40.46951055485471, -3.690762519836426))
                .title("Emov 2").snippet("Matricula: HU4187P").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        MarkerOptions emov_3 = new MarkerOptions()
                .position(new LatLng(40.46569064068097, -3.680140972137451))
                .title("Emov 3").snippet("Matricula: 9865BCA").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

        eMovMarkers.add(emov_1);
        eMovMarkers.add(emov_2);
        eMovMarkers.add(emov_3);
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        if (e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "Error: " + e.getMessage());
        } else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(), "Distancia: " + route.get(i).getDistanceText() + ": Tiempo: " + route.get(i).getDurationText(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRoutingCancelled() {

    }

    private void erasePolylines() {
        for (Polyline line : polylines)
            line.remove();

        polylines.clear();
    }
}

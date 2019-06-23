package com.example.movilymad;


import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

class APIRequests {

    List getBiciMadStations(){
        ArrayList<MarkerOptions> biciMadMarkers = new ArrayList<>();
        MarkerOptions bici_1 = new MarkerOptions()
                .position(new LatLng(40.464295366988466, -3.6900599824556366))
                .title("BiciMad 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        MarkerOptions bici_2 = new MarkerOptions()
                .position(new LatLng(40.45756903840472, -3.68297895065632))
                .title("BiciMad 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        MarkerOptions bici_3 = new MarkerOptions()
                .position(new LatLng(40.46103024305326, -3.695746265567209))
                .title("BiciMad 3").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        biciMadMarkers.add(bici_1);
        biciMadMarkers.add(bici_2);
        biciMadMarkers.add(bici_3);

        return biciMadMarkers;
    }

    List getZityVehicles(){
        ArrayList<MarkerOptions> zityMarkers = new ArrayList<>();
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

        return zityMarkers;
    }

    List getEmovVehicles(){
        ArrayList<MarkerOptions> eMovMarkers = new ArrayList<>();
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

        return eMovMarkers;
    }
}

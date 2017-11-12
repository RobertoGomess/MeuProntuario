package br.com.meuprontuario.meuprontuario;



import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.meuprontuario.meuprontuario.PacoteMenu.MenuHomeActivity;
import br.com.meuprontuario.meuprontuario.Uteis.UteisJava;


/**
 * Created by betoj on 13/04/2017.
 */

public class EmergenciaFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMarkerClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private FloatingActionButton btnLigar;
    private Context context;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        View view = inflater.inflate(R.layout.fragment_emergencia, container, false);
        btnLigar = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonLigar);
        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("tel:192");
                    Intent i = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Não foi possível realizar a ligação. " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        UteisJava.enableMyLocation(context,(MenuHomeActivity) getActivity(),mMap);


        //click no button minha localização
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                marcaMyPosition(googleMap,new MarkerOptions()
                        .title("Minha localização")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_emergency_ambulance))
                );

                return false;
            }
        });

    }


    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void marcaMyPosition(GoogleMap googleMap , MarkerOptions markerOptions ){

        if (UteisJava.isLocationEnabled(context) == true){

            Location location = UteisJava.getMyPosition(context,(MenuHomeActivity) getActivity(),googleMap);
            if (location != null){
                markerOptions.position(new LatLng(location.getLatitude(),location.getLongitude()));
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            }

        }
    };
}


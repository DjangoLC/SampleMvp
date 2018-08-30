package com.example.joseenrique.myapplication.views.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseenrique.myapplication.R;
import com.example.joseenrique.myapplication.dagger.AndroidApplication;
import com.example.joseenrique.myapplication.dagger.AndroidComponent;
import com.example.joseenrique.myapplication.dagger.DependencyInjector;
import com.example.joseenrique.myapplication.interfaces.MapPresenter;
import com.example.joseenrique.myapplication.presenters.MapPresenterImpl;
import com.example.joseenrique.myapplication.tasks.LocationProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class thirdFragment extends Fragment implements com.example.joseenrique.myapplication.interfaces.MapView,View.OnClickListener
                            {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.btn_get_location)
    Button btn_get_location;

    @BindView(R.id.tv_info_map)
    TextView tv_info_map;

    @BindView(R.id.barMap)
    ProgressBar barMap;

    private LocationProvider mLocationProvider;

    private String mParam1;
    private String mParam2;

    MapView mMapView;
    private GoogleMap googleMap;

    private OnThirdFragmentInteractionListener mListener;

    private MapPresenter presenter;

    private View rootView;

    @Inject
    Context context;
    @Inject
    LocationManager manager;

    public thirdFragment() {
        // Required empty public constructor
    }

    public static thirdFragment newInstance(String param1, String param2) {
        thirdFragment fragment = new thirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == rootView){
            rootView = inflater.inflate(R.layout.third_fragment, container, false);
            ButterKnife.bind(this,rootView);
            btn_get_location.setOnClickListener(this);
            presenter = new MapPresenterImpl(this);
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(context);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mMapView.getMapAsync(mMap -> {
                googleMap = mMap;
 
                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            });

        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((AndroidApplication)getActivity().getApplication()).getComponent().inject(this);
        if (context instanceof OnThirdFragmentInteractionListener) {
            mListener = (OnThirdFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_location:
                presenter.setInfo(manager,context);
                break;


                default:
                    Toast.makeText(context, "Action unknown!", Toast.LENGTH_SHORT).show();
                    break;
        }
    }


    public interface OnThirdFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void setInfo(Location location){
        tv_info_map.setText(String.format("Current Location info: \n " +
                                            "Latitude: %s \n Longitude: %s \n accurrecy: %s"
                ,location.getLatitude(),location.getLongitude(),location.getAccuracy()));
        currentMark(location);

    }

    @Override
    public void showProgress(boolean isShow) {
        if (isShow){
            barMap.setVisibility(View.VISIBLE);
            Toast.makeText(context, "We are looking you, wait a moment please!", Toast.LENGTH_SHORT).show();
        }
        else
            barMap.setVisibility(View.GONE);
    }

    public void currentMark(Location l){
        LatLng current = new LatLng(l.getLatitude(), l.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(current).title("yu are here!").snippet("Some awesome title"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(current).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

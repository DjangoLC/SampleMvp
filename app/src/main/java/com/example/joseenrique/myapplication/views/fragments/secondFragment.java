package com.example.joseenrique.myapplication.views.fragments;

import android.annotation.TargetApi;
import android.arch.persistence.room.Entity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.joseenrique.myapplication.R;
import com.example.joseenrique.myapplication.interfaces.SecondPresenter;
import com.example.joseenrique.myapplication.interfaces.SecondView;
import com.example.joseenrique.myapplication.presenters.SecondPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class secondFragment extends Fragment implements SecondView,View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.btn_download_images)
    Button btn_download_images;
    @BindView(R.id.bar_image)
    ProgressBar bar;
    @BindView(R.id.imageView)
    ImageView imageView;
    private String mParam1;
    private String mParam2;

    private OnSecondFragmentInteractionListener mListener;

    private SecondPresenter presenter;

    private View v;

    public secondFragment() {
        // Required empty public constructor
    }

    public static secondFragment newInstance(String param1, String param2) {
        secondFragment fragment = new secondFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v==null){
            v = inflater.inflate(R.layout.second_fragment, container, false);
            ButterKnife.bind(this, v);
            presenter = new SecondPresenterImpl(this);
            btn_download_images.setOnClickListener(this);

        }

        return v;

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
        if (context instanceof OnSecondFragmentInteractionListener) {
            mListener = (OnSecondFragmentInteractionListener) context;
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
    public void showProgress() {
        bar.setVisibility(View.VISIBLE);
        bar.setIndeterminate(true);

    }

    @Override
    public void hideProgress() {
        bar.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showImage(String src) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(BitmapFactory.decodeFile(src));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_download_images:
                presenter.DownloadImage();
                break;
        }
    }

    public interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

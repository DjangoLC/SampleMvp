package com.example.joseenrique.myapplication.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.joseenrique.myapplication.Adapter.AdapterProductos;
import com.example.joseenrique.myapplication.R;
import com.example.joseenrique.myapplication.dagger.AndroidApplication;
import com.example.joseenrique.myapplication.interfaces.MainPresenter;
import com.example.joseenrique.myapplication.interfaces.MainView;
import com.example.joseenrique.myapplication.models.ModelsBD.AppDatabase;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.example.joseenrique.myapplication.presenters.MainPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class firstFragment extends Fragment implements View.OnClickListener,MainView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.mRecyclerItems)
    RecyclerView mRecyclerItems;
    @BindView(R.id.btn_download)
    Button btn_download;

    @BindView(R.id.progress)
    ProgressBar progress;

    private AdapterProductos adapterProductos;
    private LinearLayoutManager llm;

    private String mParam1;
    private String mParam2;

    private View v;

    private OnFirstFragmentInteractionListener mListener;
    private Context context;

    private MainPresenter presenter;

    @Inject
    AppDatabase db;

    public firstFragment() {
        // Required empty public constructor
    }

    public static firstFragment newInstance(String param1, String param2) {
        firstFragment fragment = new firstFragment();
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

            presenter = new MainPresenterImpl(this);
            context = getActivity();
            llm= new LinearLayoutManager(context);
            v = inflater.inflate(R.layout.first_fragment, container, false);
            ButterKnife.bind(this, v);
            btn_download.setOnClickListener(this);

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
        ((AndroidApplication)getActivity().getApplication()).getComponent().inject(this);
        if (context instanceof OnFirstFragmentInteractionListener) {
            mListener = (OnFirstFragmentInteractionListener) context;
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
            case R.id.btn_download:
                    presenter.downloadClubs(db);
                //Toast.makeText(context, "Click downloads Button", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void showProgress() {
        Toast.makeText(context,  "Starting sync data ...", Toast.LENGTH_SHORT).show();
        progress.setVisibility(View.VISIBLE);
        progress.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        Toast.makeText(context,  "Data sync !", Toast.LENGTH_SHORT).show();
        progress.setVisibility(View.GONE);
    }

    @Override
    public void loaditems(List<ModelProductoBD> objects) {
        buildList(objects);
        btn_download.setText("DOWNLOAD ITEMS ("+objects.size()+")");
    }

    public interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void buildList(List<ModelProductoBD> data){
        adapterProductos = new AdapterProductos(data,context);
        mRecyclerItems.setHasFixedSize(true);
        mRecyclerItems.setLayoutManager(llm);
        mRecyclerItems.setAdapter(adapterProductos);
    }

}

package com.example.joseenrique.myapplication.interactors;

import com.example.joseenrique.myapplication.Retrofit.Controller;
import com.example.joseenrique.myapplication.interfaces.MainInteractor;
import com.example.joseenrique.myapplication.models.Credencials;
import com.example.joseenrique.myapplication.models.ModelsBD.AppDatabase;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.example.joseenrique.myapplication.tasks.GenericTask;

import java.util.List;

public class MainInteractorImpl implements MainInteractor, GenericTask.listener,Controller.onFinishDownload{

    private Credencials credencials;
    private allDone listener;

    private AppDatabase db;

    public MainInteractorImpl(allDone listener){
        this.listener = listener;
    }

    @Override
    public void  downloadData(AppDatabase db) {

        Controller controller = new Controller( this);
        credencials = new Credencials();
        credencials.setDevice("F78370B1A950F2553B8129619965EC80795E4A41C2E80C491348058571839DFA");
        credencials.setuser("2807704F6664C76A7855C384AA5AF09F2364DB8E432F21B34442B2A620D9777F");

        controller.start(credencials);

        this.db=db;
    }

    @Override
    public void onFinish(List<ModelStockBD> stock, List<ModelProductoBD> product) {
        listener.done(product);
    }


    @Override
    public void data(String dir) {
        if (!dir.isEmpty()){
            new GenericTask(db.productsDao(),
                    db.stockDao(), dir,this).execute();
        }
    }

    @Override
    public void finshDownloader(String dir) {

    }

    public interface allDone{
        void done(List<ModelProductoBD> data);
    }
}

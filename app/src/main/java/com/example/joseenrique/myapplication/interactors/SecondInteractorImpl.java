package com.example.joseenrique.myapplication.interactors;

import com.example.joseenrique.myapplication.Retrofit.Controller;
import com.example.joseenrique.myapplication.interfaces.SecondInteractor;
import com.example.joseenrique.myapplication.models.ProductImage;

public class SecondInteractorImpl implements SecondInteractor,Controller.onFinishDownload{


    private listener listener;
    private ProductImage productImage;

    public SecondInteractorImpl(listener listener){
        this.listener = listener;
    }


    @Override
    public void downloadImage() {
        productImage = new ProductImage();
        productImage.setUser("1FC3BE2CD5EBD3FC1C71AA57AB592D1E5EF46B6826356301471CAD6787A5F2D6");
        productImage.setDevice("7EDF8234C46214EC60D34A191FA5F629B108BF555CF814C730CD02BB9C853A12");

        new Controller(this).StartPOstImage(productImage);
    }

    @Override
    public void data(String dir) {

    }

    @Override
    public void finshDownloader(String dir) {
        listener.done(dir);
    }

    public interface listener{
        void done(String src);
    }
}

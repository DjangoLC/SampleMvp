package com.example.joseenrique.myapplication.presenters;

import com.example.joseenrique.myapplication.interactors.SecondInteractorImpl;
import com.example.joseenrique.myapplication.interfaces.SecondInteractor;
import com.example.joseenrique.myapplication.interfaces.SecondPresenter;
import com.example.joseenrique.myapplication.interfaces.SecondView;

public class SecondPresenterImpl implements SecondPresenter,SecondInteractorImpl.listener{


    private SecondView view;
    private SecondInteractor interactor;

    public SecondPresenterImpl(SecondView view){
        this.view = view;
        interactor = new SecondInteractorImpl(this);
    }

    @Override
    public void DownloadImage() {
        if (view!=null){
            view.showProgress();
            interactor.downloadImage();
        }
    }

    @Override
    public void setImage() {

    }

    @Override
    public void done(String src) {
        view.showImage(src);
    }
}

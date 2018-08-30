package com.example.joseenrique.myapplication.dagger;

import android.app.Application;

public class AndroidApplication extends Application {

    private AndroidComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

       component = DaggerAndroidComponent.builder().androidModule(new AndroidModule(this)).build();
        //injectDependencies();

    }

    public AndroidComponent getComponent() {
        return component;
    }

    private void injectDependencies() {
        DependencyInjector.initialize(this);
        DependencyInjector.applicationComponent().inject(this);
    }
}

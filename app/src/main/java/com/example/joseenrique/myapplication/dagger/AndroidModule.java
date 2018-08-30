package com.example.joseenrique.myapplication.dagger;

import android.content.Context;
import android.location.LocationManager;

import com.example.joseenrique.myapplication.models.ModelsBD.AppDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    @Inject
    AndroidApplication application;

    public AndroidModule(AndroidApplication application){
        this.application = application;
    }

    @Provides @Singleton
    public LocationManager provideLocationmanager(){
        return (LocationManager)application.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides @Singleton
    public Context provideContext(){
        return application;
    }


    @Provides @Singleton
    public AppDatabase provideAppDataBase(){
        return AppDatabase.getAppDatabase(application);
    }

    public AndroidApplication getApplication(){
        return application;
    }

}

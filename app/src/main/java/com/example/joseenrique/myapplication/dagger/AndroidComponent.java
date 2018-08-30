package com.example.joseenrique.myapplication.dagger;

import com.example.joseenrique.myapplication.views.fragments.activities.MainActivity;
import com.example.joseenrique.myapplication.views.fragments.firstFragment;
import com.example.joseenrique.myapplication.views.fragments.thirdFragment;

import javax.inject.Singleton;

import dagger.Component;

@peerActivity
@Singleton
@Component(modules = {AndroidModule.class})
public interface AndroidComponent {
    void inject(thirdFragment thirdFragment);
    void inject(MainActivity activity);
    void inject(AndroidApplication application);
    void inject(firstFragment firstFragment);

}

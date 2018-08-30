package com.example.joseenrique.myapplication.dagger;

public class DependencyInjector {

    private static AndroidComponent applicationComponent;

    public static void initialize(AndroidApplication application) {
        applicationComponent = DaggerAndroidComponent.builder()
                .androidModule(new AndroidModule(application))
                .build();
    }

    public static AndroidComponent applicationComponent() {
        return applicationComponent;
    }

    private DependencyInjector(){}

}

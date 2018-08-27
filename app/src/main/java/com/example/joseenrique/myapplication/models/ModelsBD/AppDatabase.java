package com.example.joseenrique.myapplication.models.ModelsBD;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.joseenrique.myapplication.db.ProductsDao;
import com.example.joseenrique.myapplication.db.StockDao;

@Database(entities = {ModelProductoBD.class,ModelPropsBD.class,ModelStockBD.class,
        ModelGroupsBD.class}, version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ProductsDao productsDao();

    public abstract StockDao stockDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "test")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            //.allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

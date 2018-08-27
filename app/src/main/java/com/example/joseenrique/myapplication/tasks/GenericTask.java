package com.example.joseenrique.myapplication.tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.joseenrique.myapplication.ModelsGson.ModelProductosGson;
import com.example.joseenrique.myapplication.ModelsGson.ProductsGson;
import com.example.joseenrique.myapplication.ModelsGson.StocksGson;
import com.example.joseenrique.myapplication.Utils.TimeElapsed;
import com.example.joseenrique.myapplication.db.ProductsDao;
import com.example.joseenrique.myapplication.db.StockDao;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GenericTask extends AsyncTask<Void,Void,Boolean> {



    private ProductsDao productsDao;
    private StockDao stockDao;
    private String dir;
    private  static List<ModelStockBD> allData;
    private static List<ModelProductoBD> allData2;
    private List<ModelStockBD> currentData;
    private List<ModelProductoBD> currentData2;

    public listener listener;


    private final String TAG = getClass().getSimpleName();

    public GenericTask(ProductsDao productsDao, StockDao stockDao, String dir,listener listener){
        this.productsDao = productsDao;
        this.stockDao= stockDao;
        this.listener = listener;
        this.dir= dir;

        currentData = new ArrayList<>();
        currentData2 = new ArrayList<>();
    }

    public interface listener{
         void onFinish(List<ModelStockBD> stock, List<ModelProductoBD> product);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean con=true;

        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new FileReader(dir)) {

            // Convert JSON to Java Object
            TimeElapsed.StarTime();
            ModelProductosGson productosGson = gson.fromJson(reader, ModelProductosGson.class);
            TimeElapsed.EndTime();
            Log.i(TAG, "Time after to convert JSOn to Objects!" + TimeElapsed.getDuration());
            sb.append("\n"+"Time in convert JSON to Object: "+TimeElapsed.getDuration()+"\n");

            for (StocksGson current : productosGson.getContentGson().getStocks()) {
                ModelStockBD stockBD = new ModelStockBD();
                stockBD.setProduct(current.getP());
                Log.i("product: ",""+current.getP());
                stockBD.setStock(current.getS());
                stockBD.setWareHouse(current.getW());
                currentData.add(stockBD);
            }

            for (ProductsGson current : productosGson.getContentGson().getContent()) {
                ModelProductoBD productoBD = new ModelProductoBD();
                productoBD.setId(current.getId());
                productoBD.setA(current.getA());
                productoBD.setC(current.getC());
                productoBD.setCu(current.getCu());
                productoBD.setFn(current.getFn());
                productoBD.setG(current.getG());
                productoBD.setGu(current.getGu());
                productoBD.setI(current.getI());
                productoBD.setIbu(current.getIbu());
                productoBD.setIsu(current.getIsu());
                productoBD.setM(current.getM());
                productoBD.setNs(current.getNs());
                productoBD.setSu(current.getSu());
                productoBD.setU(current.getU());
                productoBD.setIu(current.getIu());
                productoBD.setS(current.getS());
                productoBD.setT(current.getT());

                currentData2.add(productoBD);
            }

        } catch (Exception e){
            con=false;
            e.getStackTrace();
        }

        if (stockDao instanceof StockDao){
            TimeElapsed.StarTime();
            stockDao.deleteAll();
            TimeElapsed.EndTime();
            sb.append("Time while delete Stock"+TimeElapsed.getDuration()+"\n");

            TimeElapsed.StarTime();
            stockDao.insertStock(currentData);
            TimeElapsed.EndTime();
            Log.i(TAG,"Time after to starting insert records!"+TimeElapsed.getDuration());
            sb.append("Time in insert records Stock: "+TimeElapsed.getDuration()+"\n");

            TimeElapsed.StarTime();
            allData=stockDao.getAllStock();
            TimeElapsed.EndTime();
            //mAllData.add(allData);
            Log.i(TAG,"Time after to get all records!"+TimeElapsed.getDuration());
            sb.append("Time in get all records Stock: "+TimeElapsed.getDuration()+"\n");

        } else
            throw new IllegalArgumentException("THe object not is intstance of StockDao");

        if (productsDao instanceof ProductsDao){
            TimeElapsed.StarTime();
            productsDao.deleteAll();
            TimeElapsed.EndTime();
            sb.append("Time while delete Products"+TimeElapsed.getDuration()+"\n");

            TimeElapsed.StarTime();
            productsDao.insertProducts(currentData2);
            TimeElapsed.EndTime();
            sb.append("Time in insert records Products: "+TimeElapsed.getDuration()+"\n");

            TimeElapsed.StarTime();
            allData2 =productsDao.getAllProducts();
            TimeElapsed.EndTime();
            sb.append("Time in getAll records Products: "+TimeElapsed.getDuration()+"\n");

            System.out.println(sb.toString());

        } else
            throw new IllegalArgumentException("THe object not is intstance of ProductsDao");

        return con;

    }

    @Override
    protected void onPostExecute(Boolean objects) {
        if (objects){

                if (listener!=null){
                    listener.onFinish(allData,allData2);
                } else{
                    Log.i(TAG,"The current listener s null");
                }


        } else{
            Log.i(TAG,"DoInBackGround is false");
        }

    }

}

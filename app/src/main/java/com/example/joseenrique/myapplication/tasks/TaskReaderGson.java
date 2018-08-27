package com.example.joseenrique.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.joseenrique.myapplication.Utils.TimeElapsed;
import com.example.joseenrique.myapplication.Utils.Utilities;
import com.example.joseenrique.myapplication.db.ProductsDao;
import com.example.joseenrique.myapplication.db.StockDao;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.example.joseenrique.myapplication.parser.ProductParser;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TaskReaderGson extends AsyncTask<Void,Void,Boolean> {

    private String dir;
    private listener listener;
    private List<ModelProductoBD> modelProductoBDS;
    private List<ModelStockBD> modelStockBDS;

    private ProductsDao productsDao;
    private StockDao stockDao;

    private ProgressBar bar;

    private final String TAG = getClass().getSimpleName();

    public TaskReaderGson(String dir, ProgressBar bar, ProductsDao productsDao, StockDao stockDao, listener listener){
        this.dir = dir;
        this.bar = bar;
        this.stockDao = stockDao;
        this.productsDao = productsDao;
        this.listener = listener;
    }

    public interface listener{
       void onFinish(List<ModelProductoBD> productoBDS, List<ModelStockBD> stockBDS);
    }

    @Override
    protected void onPreExecute() {
        bar.setVisibility(View.VISIBLE);
        bar.setIndeterminate(true);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        StringBuilder sb = new StringBuilder();
        try{
            ProductParser productParser = new ProductParser();

            TimeElapsed.StarTime();
            initCatalog(productParser,dir);
            TimeElapsed.EndTime();
            sb.append("Time to convert JSON to objects: "+TimeElapsed.getDuration());

            TimeElapsed.StarTime();
            productsDao.insertProducts(productParser.getPs());
            TimeElapsed.EndTime();
            sb.append("Time to insert Prdoducts in BD : "+TimeElapsed.getDuration());

            TimeElapsed.StarTime();
            stockDao.insertStock(productParser.getOs());
            TimeElapsed.EndTime();
            sb.append("Time to insert Stock in BD: "+TimeElapsed.getDuration());

            Log.i(TAG,sb.toString());
            System.out.println(sb.toString());

            modelProductoBDS = productsDao.getAllProducts();
            modelStockBDS    = stockDao.getAllStock();

            return true;
        } catch (Exception e){

            e.getMessage();

            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean){
            if (listener!=null)
                listener.onFinish(modelProductoBDS,modelStockBDS);
        }
        bar.setVisibility(View.GONE);
    }


    private void initCatalog(ProductParser parser,String s) {
        JsonReader jsonReader = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        long init = System.currentTimeMillis();
        boolean exception;
        Log.d("CATALOG, INIT", init + " ms");
        try {
            //ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes());
            FileInputStream inputStream = new FileInputStream(s);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            jsonReader = new JsonReader(bufferedReader);
            jsonReader.beginObject();
            String name = "";
            String toDelete = null;
            int size = 0;
            while ( jsonReader.hasNext() ) {
                name = jsonReader.nextName();
                //Log.e("NAME CATALOG", "=> " + name);
                if ( Utilities.isEmpty(name) ) {
                    continue;
                }
                if ( name.equals("dateTime") ) {
                    //date = DateUtilities.getDateFromDB(jsonReader.nextString());
                    jsonReader.skipValue();
                } else if ( name.equals("size") ) {
                    size = jsonReader.nextInt();
                } else if ( name.equals("toDelete") ) {
                    toDelete = jsonReader.nextString();
                } else if ( name.equals("content") ) {
                    exception = false;

                    parser.process(jsonReader);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;

        } finally {
            if ( jsonReader != null ) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

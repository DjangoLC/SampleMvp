package com.example.joseenrique.myapplication.Retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.joseenrique.myapplication.BuildConfig;
import com.example.joseenrique.myapplication.Utils.Base64Coder;
import com.example.joseenrique.myapplication.Utils.TimeElapsed;
import com.example.joseenrique.myapplication.Utils.Utilities;
import com.example.joseenrique.myapplication.models.Credencials;
import com.example.joseenrique.myapplication.models.ProductImage;
import com.google.gson.JsonSyntaxException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Controller {

    private onFinishDownload listener;
    private final String TAG = getClass().getSimpleName();

    private String dir;

    public Controller( onFinishDownload listener){
        this.listener = listener;
    }

    static final String BASE_URL = "http://red-sinergia-mhgpjzrzgh.dynamic-m.com:10002/RSGMOBSYS/ws/newCatalog/";
    static final String BASE_URL2 = "http://187.237.253.244:8080/RSGMOBSYS/ws/newCatalog/";

    public void start(Credencials credencials) {

        try{


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if(BuildConfig.DEBUG){
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            } else{
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();

            RsgAPI rsgAPI = retrofit.create(RsgAPI.class);

            Call<String> call = rsgAPI.getDataPost(credencials.getDevice(),credencials.getuser(),"");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()) {
                        String DatasList = response.body();
                        if (DatasList!=null){
                            byte[] zip = Base64Coder.decode(DatasList);
                            try {
                                TimeElapsed.StarTime();
                                dir = Utilities.decompressAndSave("1.data", zip);
                                TimeElapsed.EndTime();
                                Log.i("TAG","Time in covert and save data: "+TimeElapsed.getDuration());
                                Thread.sleep(50);
                                System.gc();
                                listener.data(dir);
                                Thread.sleep(50);
                                Thread.sleep(50);// TODO uncomment:
                                System.gc();
                                Log.i("Here",dir);
                                System.out.println(dir);


                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        } else{
                            //Toast.makeText(context, "Data is null", Toast.LENGTH_SHORT).show();
                        }




                    } else {
                        System.out.println(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    //Toast.makeText(context, "onFailure()", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });

        } catch (IllegalStateException | JsonSyntaxException exception){
            exception.getMessage();
        }

    }

    public void StartPOstImage(ProductImage productImage){
        try{


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if(BuildConfig.DEBUG){
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            } else{
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();

            RsgAPI rsgAPI = retrofit.create(RsgAPI.class);

            Call<ResponseBody> call = rsgAPI.postImage(productImage.getDevice(),productImage.getUser(),"F465D230A45E496A04AFFCE855D64F17A746D8F864E7864AACCCC5103F5274B89E9743EA94FD66C898B41894E4B4818F");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ResponseBody body = response.body();
                    String b = Utilities.saveFileTo("image.jpg", body.byteStream());
                        if (b!=null) {
                            Log.i(TAG, "Image saved correctly :D");
                            listener.finshDownloader(b);
                        }
                        else{
                            Log.i(TAG,"Something was wrong");
                            listener.finshDownloader(b);
                        }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //Toast.makeText(context, "onFailure() imagePost", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });

        } catch (IllegalStateException | JsonSyntaxException exception){
            exception.getMessage();
        }

    }

    public interface onFinishDownload{
        public void data(String dir);
        public void finshDownloader(String dir);
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}

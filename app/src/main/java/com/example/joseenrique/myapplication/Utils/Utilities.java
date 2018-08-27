package com.example.joseenrique.myapplication.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPInputStream;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Utilities {

    private static File productsFolder;

    private final int MY_PERMISSIONS_REQUEST_READ_INTERNAL_STORAGE=1;
    private final int MY_PERMISSIONS_REQUEST_INTERNET=2;
    private final int MY_PERMISSIONS_REQUEST_WRITE_INTERNAL_STORAGE=3;

    private Context context;
    private Activity act;

    public Utilities(){

    }

    public Utilities(Context context,Activity act){
        this.context = context;
        this.act = act;
    }

    public static boolean isEmpty(String str){
        if (str.isEmpty())
            return true;
        else
            return false;
    }

    public static String decompressAndSave(String fileName, byte[] zip) throws IOException {
        final int BUFFER_SIZE = 1024;
        fileName = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath()+ File.separator+fileName;
        Log.i("Decompress and Save", "=> " + fileName);
        /*FileInputStream fis = new FileInputStream(fileName);*/
        FileOutputStream fos = new FileOutputStream(fileName);
        ByteArrayInputStream bais = new ByteArrayInputStream(zip);// TODO Windows WS, 4, zip.length - 4);
        PrintWriter pw = new PrintWriter(fos);
        GZIPInputStream gis = new GZIPInputStream(bais, BUFFER_SIZE);
        byte[] data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(data)) != -1) {
            pw.print(new String(data, 0, bytesRead, "UTF-8"));
        }
        gis.close();
        pw.close();
        fos.close();
        zip = null;
        return fileName;
    }

    public static String saveFileTo(String fileName, InputStream is) {
        try {
            final int BUFFER_SIZE = 1024;
            byte[] data = new byte[BUFFER_SIZE];
            int bytesRead;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bytesRead = is.read(data);
            while ( bytesRead != -1 ) {
                baos.write(data, 0, bytesRead);
                bytesRead = is.read(data);
            }
            //baos.close();
            return Utilities.saveFileTo(fileName, baos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "some was wrong";
        }
    }

    public static String saveFileTo(String fileName, byte[] zipBytes) {
        try {
            File folder = getProductsFolder();
            folder.mkdirs();
            if ( folder.exists() ) {
                File imageFile = new File(folder, fileName);
                FileOutputStream fos = new FileOutputStream(imageFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                //fos.write(zipBytes);
                bos.write(zipBytes);
                //fos.close();
                bos.flush();
                bos.close();
                return imageFile.getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "image not saved";
    }

    public static File getProductsFolder() {
        if ( productsFolder == null ) {
            productsFolder = new File(Environment.getExternalStorageDirectory(), "rsmobile/products");
        }
        return productsFolder;
    }

    public void checkPermissions(){

        boolean hasPermissionReadInternal = (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissionReadInternal) {
            ActivityCompat.requestPermissions(act,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_INTERNAL_STORAGE);
        }

        boolean hasPermissioninternet = (ContextCompat.checkSelfPermission(context,
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissioninternet) {
            ActivityCompat.requestPermissions(act,
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_INTERNET);
        }

        boolean hasPermissionWrite = (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissionWrite) {
            ActivityCompat.requestPermissions(act,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_INTERNAL_STORAGE);
        }

    }

}

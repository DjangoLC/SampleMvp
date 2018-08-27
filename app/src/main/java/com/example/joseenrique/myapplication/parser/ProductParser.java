package com.example.joseenrique.myapplication.parser;

import android.util.Log;

import com.example.joseenrique.myapplication.Utils.Utilities;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProductParser {

    public ProductParser(){

    }

    private List<ModelProductoBD> ps = null;
    private List<ModelStockBD> os = new LinkedList<>();

    public void process(JsonReader jr) throws Exception {
        String name = null;
        jr.beginObject();
        while (jr.hasNext()) {
            name = jr.nextName();
            //Log.e("NAME", "=> " + name);
            if (Utilities.isEmpty(name)) {
                continue;
            } else if (name.equals("products")) {
                jr.beginArray();
                ps = new LinkedList<>();
                processProductLines(jr, name, ps);
                jr.endArray();
            } else if (name.equals("stocks")) {
                jr.beginArray();
                processStock(jr, name);
                jr.endArray();
            } else {
                jr.skipValue();
            }
        }
        jr.endObject();

    }


    private void processStock(JsonReader jr, String name) throws IOException {
        ModelStockBD p = null;
        int i = 0;
        boolean deleted = false;
        while (jr.hasNext()) {
            jr.beginObject();
            p = new ModelStockBD();
            while (jr.hasNext()) {
                name = jr.nextName();
                //Log.e("NAME LINES", "=> " + name);
                if (Utilities.isEmpty(name)) {
                    continue;
                }
                if (name.equals("p")) {
                    p.setProduct(jr.nextInt());
                } else if (name.equals("w")) {
                    p.setWareHouse(jr.nextInt());
                } else if (name.equals("s")) {
                    p.setStock(jr.nextDouble());
                } else {
                    jr.skipValue();
                }
            }
            jr.endObject();
            os.add(p);
            i++;

        }
        if (i > 0) {
            //insert(new DAOStock(getRsgActivity()), os, deleted);
            Log.d("> 0", "LIMPIANDO...");
            System.gc();
        }
    }

    private void processProductLines(JsonReader jr, String name, List<ModelProductoBD> ps) throws IOException {
        boolean deleted = false;
        boolean deletedPps = false;
        int i = 0;
        ModelProductoBD p = null;
        while (jr.hasNext()) {
            jr.beginObject();
            p = new ModelProductoBD();
            //boolean salable = false;
            //boolean inventory = false;
            while (jr.hasNext()) {
                //salable = false;
                //inventory = false;
                name = jr.nextName();
                //Log.e("NAME LINES", "=> " + name);
                if (Utilities.isEmpty(name)) {
                    continue;
                }
                if (name.equals("id")) {
                    p.setId(jr.nextInt());
                } else if (name.equals("c")) {
                    p.setC(jr.nextString());
                } else if (name.equals("n")) {
                    p.setNs(jr.nextString());
                } else if (name.equals("g")) {
                    p.setG(String.valueOf(jr.nextInt()));
                } else if (name.equals("u")) {
                    p.setU(String.valueOf(jr.nextInt()));
                } else if (name.equals("gu")) {
                    p.setGu(String.valueOf(jr.nextInt()));
                } else if (name.equals("su")) {
                    p.setSu(jr.nextString());
                } else if (name.equals("iu")) {
                    p.setIu(jr.nextString());
                } else if (name.equals("isu")) {
                    p.setIsu(String.valueOf(jr.nextInt()));
                } else if (name.equals("ibu")) {
                    p.setIbu(String.valueOf(jr.nextInt()));
                } else if (name.equals("cu")) {
                    p.setCu(String.valueOf(jr.nextBoolean()));
                } else if (name.equals("a")) {
                    p.setA(String.valueOf(jr.nextBoolean()));
                } else if (name.equals("s")) {
                    //salable = true;
                    p.setS(String.valueOf(jr.nextBoolean()));
                } else if (name.equals("i")) {
                    //inventory = true;
                    p.setI(String.valueOf(jr.nextBoolean()));
                } else if (name.equals("ns")) {
                    p.setNs(jr.nextDouble() + "");
                } else if (name.equals("f")) {
                    p.setFn(jr.nextString());
                } else if (name.equals("m")) {
                    p.setM(String.valueOf(jr.nextLong()));
                } else if (name.equals("fn")) {
                    p.setFn(jr.nextString());
                } else if (name.equals("t")) {
                    p.setT(jr.nextString());
                } else if (name.equals("ps")) {
                    jr.beginArray();

                    jr.endArray();

                } else {
                    Log.e("PRODUCTOS, SKIPPING", "=> " + name);
                    jr.skipValue();
                }
            }
            /*if ( !salable ) { // TODO Parche asqueroso
                p.setSalable(true);
            }
            if ( !inventory ) { // TODO Parche asqueroso
                p.setInventory(true);
            }*/
            jr.endObject();
            ps.add(p);
            i++;

        }
    }

    public List<ModelProductoBD> getPs() {
        return ps;
    }

    public List<ModelStockBD> getOs() {
        return os;
    }
}

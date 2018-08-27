package com.example.joseenrique.myapplication.ModelsGson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enrique on 21/08/2018.
 */

public class ProductsGson {

    @SerializedName("id")
    private int id;

    @SerializedName("c")
    private String c;

    @SerializedName("n")
    private String g;

    @SerializedName("u")
    private String u;

    @SerializedName("gu")
    private String gu;

    @SerializedName("su")
    private String su;

    @SerializedName("iu")
    private String iu;

    @SerializedName("isu")
    private String isu;

    @SerializedName("ibu")
    private String ibu;

    @SerializedName("cu")
    private String cu;

    @SerializedName("a")
    private String a;

    @SerializedName("s")
    private String s;

    @SerializedName("i")
    private String i;

    @SerializedName("ns")
    private String ns;

    @SerializedName("m")
    private String m;

    @SerializedName("fn")
    private String fn;

    @SerializedName("t")
    private String t;


    public ProductsGson() {
    }

    public ProductsGson(int id, String c, String g, String u, String gu, String su, String iu,
                        String isu, String ibu, String cu, String a, String s, String i, String ns,
                        String m, String fn, String t) {
        this.id = id;
        this.c = c;
        this.g = g;
        this.u = u;
        this.gu = gu;
        this.su = su;
        this.iu = iu;
        this.isu = isu;
        this.ibu = ibu;
        this.cu = cu;
        this.a = a;
        this.s = s;
        this.i = i;
        this.ns = ns;
        this.m = m;
        this.fn = fn;
        this.t = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getIu() {
        return iu;
    }

    public void setIu(String iu) {
        this.iu = iu;
    }

    public String getIsu() {
        return isu;
    }

    public void setIsu(String isu) {
        this.isu = isu;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}

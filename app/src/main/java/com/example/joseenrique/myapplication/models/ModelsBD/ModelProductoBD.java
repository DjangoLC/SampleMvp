package com.example.joseenrique.myapplication.models.ModelsBD;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Enrique on 21/08/2018.
 */

@Entity(tableName = "products")
public class ModelProductoBD {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "c")
    private String c;
    @ColumnInfo(name = "g")
    private String g;
    @ColumnInfo(name = "u")
    private String u;
    @ColumnInfo(name = "gu")
    private String gu;
    @ColumnInfo(name = "su")
    private String su;
    @ColumnInfo(name = "iu")
    private String iu;
    @ColumnInfo(name = "isu")
    private String isu;
    @ColumnInfo(name = "ibu")
    private String ibu;
    @ColumnInfo(name = "cu")
    private String cu;
    @ColumnInfo(name = "a")
    private String a;
    @ColumnInfo(name = "s")
    private String s;
    @ColumnInfo(name = "i")
    private String i;
    @ColumnInfo(name = "ns")
    private String ns;
    @ColumnInfo(name = "m")
    private String m;
    @ColumnInfo(name = "fn")
    private String fn;
    @ColumnInfo(name = "t")
    private String t;

    public ModelProductoBD(){

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

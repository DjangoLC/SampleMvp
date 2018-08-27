package com.example.joseenrique.myapplication.models;

public class Credencials {

    private String user;
    private String tipo;
    private String device;

    public Credencials(){

    }

    public Credencials(String user, String tipo, String device) {
        this.user = user;
        this.tipo = tipo;
        this.device = device;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}

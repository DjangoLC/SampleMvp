package com.example.joseenrique.myapplication.ModelsGson;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Enrique on 21/08/2018.
 */

public class ContentGson {

    @SerializedName("groups")
    private List<Groups> groups;

    @SerializedName("products")
    private List<ProductsGson> content;

    @SerializedName("stocks")
    private List<StocksGson> stocks;

    @SerializedName("props")
    private List<PropsGson> propsGson;

    public ContentGson() {
    }

    @Ignore
    public ContentGson(List<Groups> groups, List<ProductsGson> content, List<StocksGson> stocks, List<PropsGson> propsGson) {
        this.groups = groups;
        this.content = content;
        this.stocks = stocks;
        this.propsGson = propsGson;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<ProductsGson> getContent() {
        return content;
    }

    public void setContent(List<ProductsGson> content) {
        this.content = content;
    }

    public List<StocksGson> getStocks() {
        return stocks;
    }

    public void setStocks(List<StocksGson> stocks) {
        this.stocks = stocks;
    }

    public List<PropsGson> getPropsGson() {
        return propsGson;
    }

    public void setPropsGson(List<PropsGson> propsGson) {
        this.propsGson = propsGson;
    }
}

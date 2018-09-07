package com.example.ren.projektmoblilne.recepti;

import java.io.Serializable;

/**
 * Created by ren on 22-May-17.
 */

public class Recept implements Serializable{
    private String url;
    private String title;
    private String picUrl;
    private String recept;

    public Recept (String url, String title, String picUrl, String recept){
        this.url = url;
        this.title = title;
        this.picUrl = picUrl;
        this.recept  = recept;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRecept() {
        return recept;
    }

    public void setRecept(String recept) {
        recept = recept;
    }
}

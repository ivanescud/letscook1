package com.simplelifestudio.letscook1.model;

import java.io.Serializable;

public class Banner implements Serializable {

private String bannerID;
private String bannerTitle;
private String bannerType;
private String bannersubTile;
private String bannerMainImg;
private String recetaID;
private String style;

    public Banner() {
    }

    public Banner(String bannerTitle, String bannerType, String bannersubTile, String bannerMainImg) {

        this.bannerTitle = bannerTitle;
        this.bannerType = bannerType;
        this.bannersubTile = bannersubTile;
        this.bannerMainImg = bannerMainImg;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRecetaID() {
        return recetaID;
    }

    public void setRecetaID(String recetaID) {
        this.recetaID = recetaID;
    }

    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getBannersubTile() {
        return bannersubTile;
    }

    public void setBannersubTile(String bannersubTile) {
        this.bannersubTile = bannersubTile;
    }

    public String getBannerMainImg() {
        return bannerMainImg;
    }

    public void setBannerMainImg(String bannerMainImg) {
        this.bannerMainImg = bannerMainImg;
    }
}

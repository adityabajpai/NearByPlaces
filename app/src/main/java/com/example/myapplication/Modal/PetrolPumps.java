package com.example.myapplication.Modal;

public class PetrolPumps {
    private String name, rating, img, distance, toilet, cafeteria, fav, src_lat, src_lng;

    public PetrolPumps(String name, String rating, String img, String distance, String toilet, String cafeteria) {
        this.name = name;
        this.rating = rating;
        this.img = img;
        this.distance = distance;
        this.toilet = toilet;
        this.cafeteria = cafeteria;
    }

    public PetrolPumps(String name, String rating, String distance, String fav) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.fav = fav;
    }

    public PetrolPumps(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    public PetrolPumps(String name, String rating, String distance) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
    }

    public PetrolPumps(String name, String rating, String distance, String src_lat, String src_lng) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.src_lat = src_lat;
        this.src_lng = src_lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public String getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(String cafeteria) {
        this.cafeteria = cafeteria;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getSrc_lat() {
        return src_lat;
    }

    public void setSrc_lat(String src_lat) {
        this.src_lat = src_lat;
    }

    public String getSrc_lng() {
        return src_lng;
    }

    public void setSrc_lng(String src_lng) {
        this.src_lng = src_lng;
    }
}

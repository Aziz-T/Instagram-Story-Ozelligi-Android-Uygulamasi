package com.t.instagramstory;

public class StoryModel {
    private String baslik, imageUri, profilImage;

    public StoryModel(){}

    public StoryModel(String baslik) {
        this.baslik = baslik;
    }

    public StoryModel(String baslik, String imageUri, String profilImage) {
        this.baslik = baslik;
        this.imageUri = imageUri;
        this.profilImage = profilImage;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(String profilImage) {
        this.profilImage = profilImage;
    }

}

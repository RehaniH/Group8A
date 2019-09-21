package com.example.group8a;

public class UploadImage {

    private String mUri;
    private String imageData;


    public UploadImage() {
    }

    public UploadImage(String mUri, String imageData) {
        this.mUri = mUri;
        this.imageData = imageData;
    }

    public String getImageUri() {

        return mUri;
    }

    public void setImageUri(String imageUri) {

        this.mUri = imageUri;
    }

    public String getImageData() {

        return imageData;
    }

    public void setImageData(String imageData) {

        this.imageData = imageData;
    }
}

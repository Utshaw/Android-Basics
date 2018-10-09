package com.example.android.myrecyclerview;

public class ListItem {


    private String imageUrl;
    private String imageName;

    public ListItem(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageName() {
        return imageName;
    }
}

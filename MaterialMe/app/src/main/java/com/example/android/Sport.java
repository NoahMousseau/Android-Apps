package com.example.android;

public class Sport {
    private String title;
    private String info;
    private final int imageResource;

    /**
     * Constructor for Sport data model
     *
     * @param title The name of the sport
     * @param info Information about the sport
     */
    Sport (String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    //Getter for imageResource
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Gets the title of the sport
     *
     * @return The title of the sport
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info of the sport
     *
     * @return The information about the sport
     */
    String getInfo() {
        return info;
    }
}

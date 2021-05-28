package com.example.mediatracker;

public class Album {
    private String title;
    private String artist;
    private String format;
    private int count;
    private int runtime;

    public Album(String title, String artist, String format, int count, int runtime) {
        this.title = title;
        this.artist = artist;
        this.format = format;
        this.count = count;
        this.runtime = runtime;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    void setFormat(String format) {
        this.format = format;
    }

    void setCount(int count) {
        this.count = count;
    }

    void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    String getFormat() {
        return format;
    }

    int getCount() {
        return count;
    }

    int getRuntime() {
        return runtime;
    }
}

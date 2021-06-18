package com.example.mediatracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Albums")
public class Album implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "albumId")
    private int id;

    private String title;
    private String artist;
    private String format;
    // private int count;
    private int runtime;

    public Album(String title, String artist, String format, int runtime) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.format = format;
        // this.count = count;
        this.runtime = runtime;
    }

    public void setId(int id) {
        this.id = id;
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

    //void setCount(int count) { this.count = count;}

    void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    int getId() { return id; }

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    String getFormat() {
        return format;
    }

    //int getCount() {return count;}

    int getRuntime() {
        return runtime;
    }
}

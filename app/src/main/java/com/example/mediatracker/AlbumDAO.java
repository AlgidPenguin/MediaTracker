package com.example.mediatracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AlbumDAO {
    @Insert
    void addAlbum(Album album);

    @Query("DELETE FROM Albums WHERE albumId = :albumId")
    void deleteAlbum(int albumId);

    @Query("SELECT * FROM Albums")
    LiveData<List<Album>> getAllAlbums();
}

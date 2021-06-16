package com.example.mediatracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AlbumDAO {
    @Insert
    void addAlbum(Album album);

    @Query("SELECT * FROM Albums WHERE title = :title")
    List<Album> findAlbum(String title);

    // My theory for deleting album is that we can retrieve albumId
    // from viewing page so a string would not be necessary
    @Query("DELETE FROM Albums WHERE albumId = :albumId")
    void deleteAlbum(int albumId);

    @Query("SELECT * FROM Albums")
    LiveData<List<Album>> getAllAlbums();
}

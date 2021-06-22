package com.example.mediatracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    private LiveData<List<Album>> albums;
    private MutableLiveData<List<Album>> searchResults;
    private MutableLiveData<List<Album>> albumsList;

    public AlbumViewModel (Application application) {
        super(application);
        repository = new AlbumRepository(application);
        albums = repository.getAlbums();
        albumsList = repository.getAlbumsList();
    }

    MutableLiveData<List<Album>> getAlbumsList() {
        return albumsList;
    }
    LiveData<List<Album>> getAlbumsLive() {
        return albums;
    }
    public void addAlbum(Album album) {
        repository.addAlbum(album);
    }
    public void deleteAlbum(int id) {
        repository.deleteAlbum(id);
    }
}




package com.example.mediatracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mediatracker.Album;
import com.example.mediatracker.AlbumRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository repository;
    private LiveData<List<Album>> albums;
    private MutableLiveData<List<Album>> searchResults;

    public AlbumViewModel (Application application) {
        super(application);
        repository = new AlbumRepository(application);
        albums = repository.getAlbums();
        searchResults = repository.getSearchResults();
    }

    MutableLiveData<List<Album>> getSearchResults() {
        return searchResults;
    }
    LiveData<List<Album>> getAlbumsLive() {
        return albums;
    }
    public void addAlbum(Album album) {
        repository.addAlbum(album);
    }
    public void findAlbum(String name) {
        repository.findAlbum(name);
    }
    public void deleteAlbum(int id) {
        repository.deleteAlbum(id);
    }
}




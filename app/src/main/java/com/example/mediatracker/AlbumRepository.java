package com.example.mediatracker;

import android.os.AsyncTask;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import androidx.lifecycle.LiveData;


public class AlbumRepository {
    private final LiveData<List<Album>> albums;
    private final AlbumDAO albumDAO;

    public AlbumRepository(Application application) {
        AlbumRoomDatabase db;
        db = AlbumRoomDatabase.getDatabase(application);
        albumDAO = db.albumDao();
        albums = albumDAO.getAllAlbums();
    }

    private static class InsertAsyncTask extends AsyncTask<Album, Void, Void> {
        private final AlbumDAO asyncTaskDao;
        InsertAsyncTask(AlbumDAO dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Album... params) {
            asyncTaskDao.addAlbum(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private final AlbumDAO asyncTaskDao;
        DeleteAsyncTask(AlbumDAO dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteAlbum(params[0]);
            return null;
        }
    }

    public void addAlbum(Album newAlbum) {
        InsertAsyncTask task = new InsertAsyncTask(albumDAO);
        task.execute(newAlbum);
    }
    public void deleteAlbum(int id) {
        DeleteAsyncTask task = new DeleteAsyncTask(albumDAO);
        task.execute(id);
    }

    public LiveData<List<Album>> getAlbums() {
        return albums;
    }
}

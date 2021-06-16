package com.example.mediatracker;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Album.class}, version = 1)
public abstract class AlbumRoomDatabase extends RoomDatabase {
    public abstract AlbumDAO albumDao();
    private static AlbumRoomDatabase INSTANCE;

    static AlbumRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlbumRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AlbumRoomDatabase.class,
                                    "album_database").build();
                }
            }
        }
        return INSTANCE;
    }
}

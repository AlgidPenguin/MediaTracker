package com.example.mediatracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mediatracker.RecyclerAdapter;
import java.util.List;
import java.util.Locale;

import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AlbumViewModel albumViewModel;
    private RecyclerView mediaList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;



    private TextView albumTitle;
    private TextView albumArtist;
    private TextView albumFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        Album tatsuro = new Album("Big Wave", "Tatsuro Yamashita", "CD", 83);
        albumViewModel.addAlbum(tatsuro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaList = findViewById(R.id.mediaList);
        layoutManager = new LinearLayoutManager(this);
        mediaList.setLayoutManager(layoutManager);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        mediaList.setAdapter(recyclerAdapter);

        albumTitle = findViewById(R.id.album_title);
        albumArtist = findViewById(R.id.album_artist);
        albumFormat = findViewById(R.id.album_format);

        albumViewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable final List<Album> albums) {
                recyclerAdapter.setAlbumList(albums);
            }
        });


//        listenerSetup();
//        recyclerSetup();
    }

}
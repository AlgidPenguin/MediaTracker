package com.example.mediatracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.AlbumListener{
    private AlbumViewModel albumViewModel;
    private RecyclerView mediaList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    private Button openAddActivity;
    private TextView albumTitle;
    private TextView albumArtist;
    private TextView albumFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openAddActivity = findViewById(R.id.addActivityButton);
        openAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAddActivity();
            }
        });

        albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        //albumViewModel.deleteAlbum(7);
        //Album tatsuro = new Album("Big Wave", "Tatsuro Yamashita", "CD", 83);
        //albumViewModel.addAlbum(tatsuro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaList = findViewById(R.id.mediaList);
        layoutManager = new LinearLayoutManager(this);
        mediaList.setLayoutManager(layoutManager);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
        mediaList.setAdapter(recyclerAdapter);

//        albumTitle = findViewById(R.id.album_title);
//        albumArtist = findViewById(R.id.album_artist);
//        albumFormat = findViewById(R.id.album_format);

        albumViewModel.getAlbumsLive().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable final List<Album> albums) {
                recyclerAdapter.setAlbumList(albums);
            }
        });

    }

    public void toAddActivity() {
        Intent intent = new Intent(this, AddAlbumActivity.class);
        startActivity(intent);
    }

    @Override
    public void albumClick(Album album) {
        Intent intent = new Intent(this, ViewAlbumActivity.class);
        intent.putExtra("album", album);
        startActivity(intent);
    }
}
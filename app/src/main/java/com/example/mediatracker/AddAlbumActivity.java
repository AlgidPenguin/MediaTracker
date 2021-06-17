package com.example.mediatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddAlbumActivity extends AppCompatActivity {

    private AlbumViewModel albumViewModel;

    private String title;
    private String artist;
    private String format;
    private int runtime;
    Button addAlbumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);

        addAlbumButton = findViewById(R.id.addAlbumButton);
        addAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlbum();
            }
        });
    }

    public void addAlbum() {
        TextView titleToAdd = findViewById(R.id.albumTitleInput);
        title = titleToAdd.getText().toString();

        TextView artistToAdd = findViewById(R.id.albumArtistInput);
        artist = artistToAdd.getText().toString();

        TextView formatToAdd = findViewById(R.id.albumFormatInput);
        format = formatToAdd.getText().toString();

        TextView runtimeToAdd = findViewById(R.id.albumRuntimeInput);
        runtime = Integer.parseInt(runtimeToAdd.getText().toString());

        Album albumToAdd = new Album(title, artist, format, runtime);


        albumViewModel.addAlbum(albumToAdd);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.mediatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class ViewAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_album);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Album albumToDisplay = (Album) getIntent().getSerializableExtra("album");

        TextView titleDisplay = findViewById(R.id.toolbarTitle);
        titleDisplay.setText(albumToDisplay.getTitle());

        TextView viewAlbumTitle = findViewById(R.id.albumViewTitle);
        viewAlbumTitle.setText(albumToDisplay.getTitle());

        TextView viewAlbumArtist = findViewById(R.id.albumViewArtist);
        viewAlbumArtist.setText(albumToDisplay.getArtist());

        TextView viewAlbumFormat = findViewById(R.id.albumViewFormat);
        viewAlbumFormat.setText(albumToDisplay.getFormat());

        TextView viewAlbumRuntime = findViewById(R.id.albumViewRuntime);
        viewAlbumRuntime.setText(Integer.toString(albumToDisplay.getRuntime()));
    }
}
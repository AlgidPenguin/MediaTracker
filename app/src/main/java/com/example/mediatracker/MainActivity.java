package com.example.mediatracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.AlbumListener{
    private AlbumViewModel albumViewModel;
    private RecyclerView mediaList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    private Button openAddActivity;
    ArrayAdapter<Album> arrayAdapter;
    ListView searchResults;
    List<Album> albumSearchList = new ArrayList<>();


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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaList = findViewById(R.id.mediaList);
        layoutManager = new LinearLayoutManager(this);
        mediaList.setLayoutManager(layoutManager);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
        mediaList.setAdapter(recyclerAdapter);

        albumViewModel.getAlbumsLive().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable final List<Album> albums) {
                recyclerAdapter.setAlbumList(albums);
                albumSearchList.clear();
                for (Album album:
                     albums) {
                    albumSearchList.add(album);
                }
            }
        });

        ListView albumSearchResults = findViewById(R.id.searchResults);
        albumSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Album albumToView = (Album) parent.getItemAtPosition(position);

                albumClick(albumToView);
            }
        });
        albumSearchResults.setVisibility(View.INVISIBLE);

        arrayAdapter = new ArrayAdapter<Album>(this, R.layout.search_results_layout, R.id.albumSearchTitle, albumSearchList);
        albumSearchResults.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchButton = (SearchView) searchItem.getActionView();

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openAddActivity.setVisibility(View.INVISIBLE);
                findViewById(R.id.toolbarTitle).setVisibility(View.INVISIBLE);
                return false;
            }
        });


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                openAddActivity.setVisibility(View.VISIBLE);
                findViewById(R.id.toolbarTitle).setVisibility(View.VISIBLE);
                findViewById(R.id.searchResults).setVisibility(View.INVISIBLE);
                return true;
            }
        });

        searchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findViewById(R.id.searchResults).setVisibility(View.VISIBLE);
                arrayAdapter.getFilter().filter(newText);
                ListView albumSearchResults = findViewById(R.id.searchResults);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
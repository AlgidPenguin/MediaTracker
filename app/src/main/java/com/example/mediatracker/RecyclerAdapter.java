package com.example.mediatracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] albumName = {"Big Wave", "After 5 Clash", "Big Wave", "After 5 Clash",
            "Big Wave", "After 5 Clash", "Big Wave", "After 5 Clash"};

    private String[] artist = {"Tatsuro Yamashita", "Toshiki Kadomatsu", "Tatsuro Yamashita",
            "Toshiki Kadomatsu", "Tatsuro Yamashita", "Toshiki Kadomatsu", "Tatsuro Yamashita", "Toshiki Kadomatsu"};

    private String[] format = {"CD", "LP", "CD", "LP", "CD", "LP", "CD", "LP"};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.media_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(albumName[i]);
        viewHolder.itemArtist.setText(artist[i]);
        viewHolder.itemFormat.setText(format[i]);
    }

    public int getItemCount() {
        return albumName.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemArtist;
        TextView itemFormat;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.album_title);
            itemArtist = itemView.findViewById(R.id.album_artist);
            itemFormat = itemView.findViewById(R.id.album_format);
        }
    }
}
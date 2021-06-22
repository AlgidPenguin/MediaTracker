package com.example.mediatracker;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Album> albumList;
    private AlbumListener globalAlbumListener;

     public RecyclerAdapter(AlbumListener albumListener) {
         this.globalAlbumListener = albumListener;
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.media_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v, globalAlbumListener);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(albumList.get(i).getTitle());
        viewHolder.itemArtist.setText(albumList.get(i).getArtist());
        viewHolder.itemFormat.setText(albumList.get(i).getFormat());
    }

    public int getItemCount() {
        if(albumList == null) {
            return 0;
        }

        else {
            return albumList.size();
        }
    }

    public void setAlbumList(List<Album> albums) {
        albumList = albums;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTitle;
        TextView itemArtist;
        TextView itemFormat;

        AlbumListener albumListener;

        ViewHolder(View itemView, AlbumListener albumListener ) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.album_title);
            itemArtist = itemView.findViewById(R.id.album_artist);
            itemFormat = itemView.findViewById(R.id.album_format);

            this.albumListener = albumListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            albumListener.albumClick(albumList.get(getAdapterPosition()));
        }
    }

    public interface AlbumListener {
        void albumClick(Album album);
    }
}

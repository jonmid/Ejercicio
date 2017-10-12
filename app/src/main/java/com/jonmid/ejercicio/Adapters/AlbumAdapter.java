package com.jonmid.ejercicio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jonmid.ejercicio.Models.AlbumModel;
import com.jonmid.ejercicio.R;
import com.jonmid.ejercicio.WindowTwoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    List<AlbumModel> albumModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AlbumAdapter(List<AlbumModel> albumModelList, Context context) {
        this.albumModelList = albumModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewAlbumId.setText(Integer.toString(albumModelList.get(position).getId()));
        holder.textViewAlbumTitle.setText(albumModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return albumModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewAlbumId;
        TextView textViewAlbumTitle;
        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            textViewAlbumId = (TextView) item.findViewById(R.id.id_tv_item_albumid);
            textViewAlbumTitle = (TextView) item.findViewById(R.id.id_tv_item_albumtitle);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, WindowTwoActivity.class);
            intent.putExtra("albumId", albumModelList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);


            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }

}

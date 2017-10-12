package com.jonmid.ejercicio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonmid.ejercicio.Models.PhotoModel;
import com.jonmid.ejercicio.R;
import com.jonmid.ejercicio.WindowOneActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    List<PhotoModel> photoModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public PhotoAdapter(List<PhotoModel> photoModelList, Context context) {
        this.photoModelList = photoModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.textViewPhotoId.setText(Integer.toString(photoModelList.get(position).getId()));
        holder.textViewPhotoTitle.setText(photoModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return photoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewPhotoId;
        TextView textViewPhotoTitle;
        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            textViewPhotoId = (TextView) item.findViewById(R.id.id_tv_item_photoid);
            textViewPhotoTitle = (TextView) item.findViewById(R.id.id_tv_item_phototitle);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, WindowOneActivity.class);
            contextItem.startActivity(intent);
        }
    }

}

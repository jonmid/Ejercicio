package com.jonmid.ejercicio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonmid.ejercicio.Adapters.AlbumAdapter;
import com.jonmid.ejercicio.Connection.HttpManager;
import com.jonmid.ejercicio.Models.AlbumModel;
import com.jonmid.ejercicio.Parser.AlbumJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class WindowOneActivity extends AppCompatActivity {

    ProgressBar progressBarAlbum;
    RecyclerView recyclerViewAlbum;
    List<AlbumModel> albumModelList;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_one);

        progressBarAlbum = (ProgressBar) findViewById(R.id.id_pb_item_album);
        recyclerViewAlbum = (RecyclerView) findViewById(R.id.id_rv_item_album);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAlbum.setLayoutManager(linearLayoutManager);

        loadData();
    }

    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(){
        if (isOnLine()){
            TaskAlbum taskAlbum = new TaskAlbum();
            taskAlbum.execute("https://jsonplaceholder.typicode.com/albums");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        albumAdapter = new AlbumAdapter(albumModelList, getApplicationContext());
        recyclerViewAlbum.setAdapter(albumAdapter);
    }

    public class TaskAlbum extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarAlbum.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                albumModelList = AlbumJson.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarAlbum.setVisibility(View.GONE);
        }
    }

}

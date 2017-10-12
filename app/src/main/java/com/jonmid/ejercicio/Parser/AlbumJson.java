package com.jonmid.ejercicio.Parser;

import com.jonmid.ejercicio.Models.AlbumModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by software on 12/10/2017.
 */

public class AlbumJson {

    public static List<AlbumModel> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<AlbumModel> albumModelList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            AlbumModel albumModel = new AlbumModel();
            albumModel.setId(item.getInt("id"));
            albumModel.setTitle(item.getString("title"));

            albumModelList.add(albumModel);
        }
        return albumModelList;
    }

}

package com.example.guillermo.proyecto_331_serviciosweb;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by guillermo on 9/04/18.
 */

public class PostAlumno extends AsyncTask<Item,Void,Void> {
    private Item item;
    private boolean eliminar;

    public PostAlumno(Item item) {

        this.item = item;
        this.eliminar=false;
    }

    public PostAlumno(Item item, boolean eliminar) {
        this.item = item;
        this.eliminar = eliminar;
    }

    @Override
    protected Void doInBackground(Item... alumnos) {

        try {

            URL url;
            if(item.getId().equals("0")){
                url = new URL("http://192.168.1.164/datos1/insertar_alumno.php");
            }else {
                url = new URL("http://192.168.1.164/datos1/actualizar_alumno.php");
            }
            if(eliminar){
                url = new URL("http://192.168.1.164/datos1/borrar_alumno.php");
            }

            Log.d("url",url.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            JSONObject jsonParam = new JSONObject();
            if(!item.getId().equals("0")) {jsonParam.put("idalumno", item.getId());}
            jsonParam.put("nombre", item.getNombre());
            jsonParam.put("direccion", item.getDireccion());

            Log.d("json",jsonParam.toString());

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.get=new GetAlumno("");
        MainActivity.get.execute();

    }

}

package com.example.guillermo.proyecto_331_serviciosweb;

import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by guillermo on 9/04/18.
 */

public class GetAlumno extends AsyncTask<String,Void,ArrayList<Item>> {

    private String datos;
    private ArrayList<Item> lista;
    private String buscar;

    public GetAlumno(String buscar) {
        this.buscar = buscar;
    }

    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        lista=new ArrayList<>();

        datos="";

        try{
            URL url;

            if(buscar.equals("")) {
                url = new URL("http://192.168.1.164/datos1/obtener_alumnos.php");
            }else{
                url = new URL("http://192.168.1.164/datos1/obtener_alumno_por_id.php?idalumno="+buscar);
            }

            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

            InputStream inputStream=httpURLConnection.getInputStream();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";

            while(line!=null){
                line = bufferedReader.readLine();
                datos+=line;
            }


            datos.replace("null","");
            //Log.d("datos",datos);
            JSONObject JO = new JSONObject(datos);

            if(buscar.equals("")) {
                //Log.d("JO",JO.toString());
                JSONArray JA = new JSONArray(JO.get("alumnos").toString());
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject JSO = (JSONObject) JA.get(i);
                    lista.add(new Item(JSO.getString("idalumno"),
                            JSO.getString("nombre"),
                            JSO.getString("direccion")));
                }
            }else{
                JSONObject JSO = (JSONObject) JO.get("alumno");
                lista.add(new Item(
                        JSO.getString("idAlumno"),
                        JSO.getString("nombre"),
                        JSO.getString("direccion")
                ));
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            //System.out.println(e.getCause());
            e.printStackTrace();
        }catch (JSONException e){
            //System.out.println(e.getCause());
            e.printStackTrace();
        }
        return lista;
    }
    protected void onPostExecute(ArrayList<Item> item){
        super.onPostExecute(item);

        MainActivity.adapter = new AdapterAlumno(lista);
        MainActivity.recyclerView.setAdapter(MainActivity.adapter);

    }
}

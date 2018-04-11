package com.example.guillermo.proyecto_331_serviciosweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static AdapterAlumno adapter;
    public static GetAlumno get;

    Button buscar;
    Button añadir;
    EditText idbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleralumno);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        idbuscar=findViewById(R.id.txtbuscar);

        buscar=findViewById(R.id.btnbuscar);
        buscar.setOnClickListener((view)->{
            get=new GetAlumno(idbuscar.getText().toString());
            get.execute();
        });

        añadir=findViewById(R.id.btnnuevo);
        añadir.setOnClickListener((view)->{
            Intent intent = new Intent(getApplicationContext(),InsAlumno.class);
            startActivity(intent);
                });

        get=new GetAlumno("");
        get.execute();
    }
}

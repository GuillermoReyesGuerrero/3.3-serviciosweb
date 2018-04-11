package com.example.guillermo.proyecto_331_serviciosweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UptAlumno extends AppCompatActivity {

    public static PostAlumno  postAlumno;

    EditText nombre,direccion;
    TextView id;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upt_alumno);

        id=findViewById(R.id.txtuptid);
        nombre=findViewById(R.id.txtuptnombre);
        direccion=findViewById(R.id.txtuptdireccion);
        guardar=findViewById(R.id.btnguardar);

        id.setText(this.getIntent().getStringExtra("id"));
        nombre.setText(this.getIntent().getStringExtra("nombre"));
        direccion.setText(this.getIntent().getStringExtra("direccion"));

        guardar.setOnClickListener((view)->{
            postAlumno = new PostAlumno(new Item(
                    id.getText().toString(),
                    nombre.getText().toString(),
                    direccion.getText().toString()));
            postAlumno.execute();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });


    }
}

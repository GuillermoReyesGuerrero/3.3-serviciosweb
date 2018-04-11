package com.example.guillermo.proyecto_331_serviciosweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsAlumno extends AppCompatActivity {

    public static PostAlumno  postAlumno;

    Button insertar;
    EditText nombre;
    EditText direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_alumno);

        nombre = findViewById(R.id.txtaddnombre);
        direccion = findViewById(R.id.txtadddireccion);

        insertar=findViewById(R.id.btninsertar);
        insertar.setOnClickListener((view) -> {
            postAlumno = new PostAlumno(new Item(
                    "0",
                    nombre.getText().toString(),
                    direccion.getText().toString()));
            postAlumno.execute();

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}

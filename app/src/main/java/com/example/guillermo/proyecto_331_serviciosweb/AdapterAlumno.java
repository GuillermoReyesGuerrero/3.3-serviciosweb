package com.example.guillermo.proyecto_331_serviciosweb;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guillermo on 9/04/18.
 */

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.DatosViewHolder> {
    private ArrayList<Item> lista;
    private PostAlumno postAlumno;

    public AdapterAlumno(ArrayList<Item> lista) {
        this.lista = lista;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new AdapterAlumno.DatosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DatosViewHolder holder, int position) {
        Item item = lista.get(position);

        holder.id.setText(item.getId());
        holder.nombre.setText(item.getNombre());
        holder.direccion.setText(item.getDireccion());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder{
        TextView id,nombre,direccion;
        Button editar,eliminar;
        public DatosViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.txtid);
            nombre=itemView.findViewById(R.id.txtnombre);
            direccion=itemView.findViewById(R.id.txtdireccion);
            editar=itemView.findViewById(R.id.btneditar);
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), UptAlumno.class);
                    intent.putExtra("id",id.getText().toString());
                    intent.putExtra("nombre",nombre.getText().toString());
                    intent.putExtra("direccion",direccion.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
            eliminar=itemView.findViewById(R.id.btneliminar);
            eliminar.setOnClickListener((view)->{
                postAlumno=new PostAlumno(new Item(id.getText().toString(),nombre.getText().toString(),direccion.getText().toString()),true);
                postAlumno.execute();

            });
        }
    }
}

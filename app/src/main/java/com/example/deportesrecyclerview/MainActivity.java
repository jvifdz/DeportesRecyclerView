package com.example.deportesrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Deporte> misFilas = new ArrayList<Deporte>();
    int posicionLongClick=0;
    AdaptadorRecycler adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        misFilas.add(new Deporte(R.mipmap.ic_baloncesto,"Baloncesto",false));
        misFilas.add(new Deporte(R.mipmap.ic_futbol,"Fútbol",false));
        misFilas.add(new Deporte(R.mipmap.ic_moto,"Motociclismo",false));
        misFilas.add(new Deporte(R.mipmap.ic_natacion,"Natación",false));
        misFilas.add(new Deporte(R.mipmap.ic_golf,"Golf",false));
        misFilas.add(new Deporte(R.mipmap.ic_atletismo,"Atletismo",false));

        adaptador = new AdaptadorRecycler(misFilas);

        RecyclerView miRecycler = findViewById(R.id.RecyclerViewDeportes);
        miRecycler.setAdapter(adaptador);
        miRecycler.setLayoutManager(new LinearLayoutManager(this));

        adaptador.setOnItemLongClickListener
                (new OnRecyclerViewLongItemClickListener() {
            @Override
            public void onItemLongClick(View view, int pos) {
                  posicionLongClick = pos;
                  openContextMenu(view);
            }
        });

        registerForContextMenu(miRecycler);


        Button botonAceptar = findViewById(R.id.button);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seleccionados="";
                for (int i = 0; i<misFilas.size(); i++){
                    if (misFilas.get(i).isCheckbox()){
                        seleccionados += " "+misFilas.get(i).texto;
                    }
                    Toast.makeText(getBaseContext(),seleccionados,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        Activity activity = (Activity ) v.getContext();
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuBorrar:
                Toast.makeText(this,
                        "Click largo sobre posicion "+posicionLongClick,
                        Toast.LENGTH_LONG).show();
                misFilas.remove(posicionLongClick);
                adaptador.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.Insertar){
            Toast.makeText(this, "Click en insertar",
                    Toast.LENGTH_SHORT).show();
        }
        return  true;
    }
}
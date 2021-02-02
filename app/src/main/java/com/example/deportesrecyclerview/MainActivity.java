package com.example.deportesrecyclerview;

import android.content.Intent;
import android.database.Cursor;
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
    DBInterface dbInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        dbInterface = new DBInterface(this);
        dbInterface.abre();
        cargarDeportes();

        /*misFilas.add(new Deporte(R.mipmap.ic_baloncesto,"Baloncesto",false));
        misFilas.add(new Deporte(R.mipmap.ic_futbol,"Fútbol",false));
        misFilas.add(new Deporte(R.mipmap.ic_moto,"Motociclismo",false));
        misFilas.add(new Deporte(R.mipmap.ic_natacion,"Natación",false));
        misFilas.add(new Deporte(R.mipmap.ic_golf,"Golf",false));
        misFilas.add(new Deporte(R.mipmap.ic_atletismo,"Atletismo",false));*/

        adaptador = new AdaptadorRecycler(misFilas);

        RecyclerView miRecycler = findViewById(R.id.RecyclerViewDeportes);
        miRecycler.setAdapter(adaptador);
        miRecycler.setLayoutManager(new LinearLayoutManager(this));

        //para el longclick abro y registro el menu contextual

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

    public void cargarDeportes(){
        misFilas.clear();
        Cursor c = dbInterface.obtenerDeportes();
        if (c==null){
            Toast.makeText(this,"Tabla vacía",Toast.LENGTH_LONG).show();
        }else{
            if (c.moveToFirst()){
                do{
                    boolean miCheck= true;

                    if (c.getInt(2)==0){
                        miCheck=false;
                    }
                    misFilas.add(new Deporte(c.getLong(0),
                            R.mipmap.ic_atletismo,
                            c.getString(1),
                            miCheck));
                }while(c.moveToNext());
            }
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        dbInterface.cierra();

    }

    //metodo en el que le digo que menu contextual esta abriendo y registrando arriba


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        Activity activity = (Activity ) v.getContext();
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    //metodo cual se ha elegido

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuBorrar:
                Toast.makeText(this,
                        "Click largo sobre posicion "+posicionLongClick,
                        Toast.LENGTH_LONG).show();

                dbInterface.borrarDeporte(misFilas.get(posicionLongClick).getId());
                misFilas.remove(posicionLongClick);

                adaptador.notifyDataSetChanged();
                break;
            case   R.id.menuModificar:
                Intent intent = new Intent(this,ModificarBBDD.class);
                intent.putExtra("id",misFilas.get(posicionLongClick).getId());
                intent.putExtra("nombre",misFilas.get(posicionLongClick).getTexto());
                intent.putExtra("check",misFilas.get(posicionLongClick).isCheckbox());

                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    //lo mismo pero de la toolbar


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.Insertar){
            startActivity(new Intent(this, InsertarBBDD.class));
        }
        if (id==R.id.configuracion){
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return  true;
    }
    //hasta aqui toolbar
    @Override
    public void onResume(){
        super.onResume();
        dbInterface.abre();
        cargarDeportes();
        adaptador.notifyDataSetChanged();

    }
}
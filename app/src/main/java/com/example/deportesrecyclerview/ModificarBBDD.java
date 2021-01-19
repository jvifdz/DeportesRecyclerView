package com.example.deportesrecyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ModificarBBDD extends AppCompatActivity {


    EditText nombreDeporte;
    CheckBox checkDeporte;
    Button modificar;
    DBInterface dbInterface;

    Intent i = getIntent();

    String nombre;
    boolean checked;

    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_b_b_d_d);


        nombreDeporte=findViewById(R.id.editTextModificaDeporte);
        checkDeporte = findViewById(R.id.checkBox);
        modificar= findViewById(R.id.buttonModificar);


        Intent i = getIntent();



        if (i != null) {
            nombre=i.getStringExtra("nombre");
            id=i.getLongExtra("id",0);
            checked=i.getBooleanExtra("check",true);

            nombreDeporte.setText(nombre);
            checkDeporte.setChecked(checked);


        }







        dbInterface = new DBInterface(this);
        dbInterface.abre();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDeporte.getText().toString().length()==0){
                    Toast.makeText(getBaseContext(),"El campo deporte no puede estar vacio",Toast.LENGTH_LONG).show();

                }else {
                    int check=0;
                    if (checkDeporte.isChecked()) check=1;
                    if (dbInterface.modificaDeporte(id,nombreDeporte.getText().toString(),check)==-1){
                        Toast.makeText(getBaseContext(),"Error en la inserccion",Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getBaseContext(),"Deporte insertado",Toast.LENGTH_LONG).show();

                    }
                    dbInterface.cierra();
                    finish();

                }
            }
        });


    }
}
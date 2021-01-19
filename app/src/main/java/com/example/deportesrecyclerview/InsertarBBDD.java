package com.example.deportesrecyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class InsertarBBDD extends AppCompatActivity {

    EditText nombreDeporte;
    CheckBox checkDeporte;
    Button insertar;
    DBInterface dbInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_b_b_d_d);


        nombreDeporte=findViewById(R.id.editTextTextPersonName);
        checkDeporte = findViewById(R.id.insertacheckBox);
        insertar= findViewById(R.id.buttonInsertar);

        dbInterface = new DBInterface(this);
        dbInterface.abre();

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDeporte.getText().toString().length()==0){
                    Toast.makeText(getBaseContext(),"El campo deporte no puede estar vacio",Toast.LENGTH_LONG).show();

                }else {
                    int check=0;
                    if (checkDeporte.isChecked()) check=1;
                    if (dbInterface.insertarDeporte(nombreDeporte.getText().toString(),check)==-1){
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
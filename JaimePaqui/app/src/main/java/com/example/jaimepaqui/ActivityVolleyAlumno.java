package com.example.jaimepaqui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jaimepaqui.controlador.swvolley.ServicioWebVolley;
import com.example.jaimepaqui.modelos.Alumno;


import org.json.JSONException;

public class ActivityVolleyAlumno extends AppCompatActivity {

    EditText cajaId, cajaNombre, cajaDireccion;
    Button botonGuardar, botonBuscarTodos;
    TextView datos;
    RecyclerView recyclerViewAlumno;

    ServicioWebVolley servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();
        servicio = new ServicioWebVolley(ActivityVolleyAlumno.this);
    }

    private void cargarComponentes(){
        cajaId = findViewById(R.id.txtIdAlumnoVolley);
        cajaNombre = findViewById(R.id.txtNombreAlumnoVolley);
        cajaDireccion = findViewById(R.id.txtDireccionAlumnoVolley);
        datos = findViewById(R.id.lblDatosAlumnoVolly);
        botonGuardar = findViewById(R.id.btnInsertarAlumnoVolly);
        botonBuscarTodos = findViewById(R.id.btnBuscarAlumnoVolly);
        recyclerViewAlumno = findViewById(R.id.recyclerAlumnoVolly);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnInsertarAlumnoVolly:
                Alumno alumno = new Alumno();
                alumno.setNombre(cajaNombre.getText().toString());
                alumno.setDireccion(cajaDireccion.getText().toString());
                try {
                    boolean estado = servicio.insertar(alumno);
                    if(estado){
                        Toast.makeText(ActivityVolleyAlumno.this, "Alumno Registrado", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ActivityVolleyAlumno.this, "Alumno no Registrado", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
        }
    }
}

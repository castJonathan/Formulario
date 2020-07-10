package com.jcastillo.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmacionDatos extends AppCompatActivity {

    private TextView txtNombre;
    private TextView txtFecNac;
    private TextView txtTelefono;
    private TextView txtEmail;
    private TextView txtDescripcion;
    private Button btnEditar;

    String nombre;
    String fecNac;
    String telefono;
    String email;
    String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtFecNac = (TextView) findViewById(R.id.txtFecNac);
        txtTelefono= (TextView) findViewById(R.id.txtTelefono);
        txtEmail= (TextView) findViewById(R.id.txtEmail);
        txtDescripcion= (TextView) findViewById(R.id.txtDescripcion);
        btnEditar= (Button) findViewById(R.id.btnEditar);

        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString(getResources().getString(R.string.var_nombre));
        fecNac = bundle.getString(getResources().getString(R.string.var_fec_nac));
        telefono = bundle.getString(getResources().getString(R.string.var_telefono));
        email = bundle.getString(getResources().getString(R.string.var_email));
        descripcion = bundle.getString(getResources().getString(R.string.var_descripcion));

        txtNombre.setText(nombre);
        txtFecNac.setText(fecNac);
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
        txtDescripcion.setText(descripcion);

        btnEditar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmacionDatos.this, MainActivity.class);
                i.putExtra(getResources().getString(R.string.var_nombre), nombre);
                i.putExtra(getResources().getString(R.string.var_fec_nac), fecNac);
                i.putExtra(getResources().getString(R.string.var_telefono), telefono);
                i.putExtra(getResources().getString(R.string.var_email), email);
                i.putExtra(getResources().getString(R.string.var_descripcion), descripcion);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(ConfirmacionDatos.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
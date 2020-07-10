package com.jcastillo.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnSiguiente;
    private EditText edtNombre;
    private EditText edtTelefono;
    private EditText edtEmail;
    private EditText edtDescripcion;
    private EditText edtFecNac;

    private Calendar calendar;
    private View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtFecNac = (EditText) findViewById(R.id.edtFecNac);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!requeridosVacios()) {
                    Intent i = new Intent(MainActivity.this, ConfirmacionDatos.class);
                    i.putExtra(getResources().getString(R.string.var_nombre), edtNombre.getText().toString());
                    i.putExtra(getResources().getString(R.string.var_fec_nac), edtFecNac.getText().toString());
                    i.putExtra(getResources().getString(R.string.var_telefono), edtTelefono.getText().toString());
                    i.putExtra(getResources().getString(R.string.var_email), edtEmail.getText().toString());
                    i.putExtra(getResources().getString(R.string.var_descripcion), edtDescripcion.getText().toString());
                    startActivity(i);
                    finish();
                }


            }
        });

        edtFecNac.setFocusable(false);
        edtFecNac.setFocusableInTouchMode(false);
        edtFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edtFecNac.setText(simpleDateFormat.format(calendar.getTime()));


                    }
                };
                DatePickerDialog dpDialog = new DatePickerDialog(
                        MainActivity.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                dpDialog.setTitle(getResources().getString(R.string.fec_nac));
                dpDialog.show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            edtNombre.setText(bundle.getString(getResources().getString(R.string.var_nombre)));
            edtFecNac.setText(bundle.getString(getResources().getString(R.string.var_fec_nac)));
            edtTelefono.setText(bundle.getString(getResources().getString(R.string.var_telefono)));
            edtEmail.setText(bundle.getString(getResources().getString(R.string.var_email)));
            edtDescripcion.setText(bundle.getString(getResources().getString(R.string.var_descripcion)));

        }
    }

    private boolean requeridosVacios() {

        boolean cancelar = false;

        //reset de errores
        edtNombre.setError(null);
        edtFecNac.setError(null);
        edtTelefono.setError(null);

        //valores de los EditText
        String nombre = edtNombre.getText().toString();
        String fecNac = edtFecNac.getText().toString();
        String telefono = edtTelefono.getText().toString();

        View focusView = null;

        if (TextUtils.isEmpty(nombre)) {
            edtNombre.setError(getResources().getString(R.string.error));
            focusView = edtNombre;
            cancelar = true;
        }

        if (TextUtils.isEmpty(fecNac)) {
            edtFecNac.setError(getResources().getString(R.string.error));
            focusView = edtFecNac;
            cancelar = true;
        }

        if (TextUtils.isEmpty(telefono)) {
            edtTelefono.setError(getResources().getString(R.string.error));
            focusView = edtTelefono;
            cancelar = true;
        }


        if (cancelar) {
            focusView.requestFocus();
        }
        return cancelar;
    }

}
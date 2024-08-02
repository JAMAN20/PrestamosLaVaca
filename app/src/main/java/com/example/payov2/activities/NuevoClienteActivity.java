package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.payov2.R;
import com.example.payov2.db.PayoDatabaseHelper;

public class NuevoClienteActivity extends AppCompatActivity {

    EditText txtNombreCompleto, txtIdentificacion, txtTelefono, txtTelefonoCasa, txtCorreo, txtDireccion, txtNombreRef, txtTelefonoRef, txtDireccionRef;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nuevo_cliente);

        ImageView atras;
        atras = findViewById(R.id.atrasIconNC);

        atras.setOnClickListener(v -> super.onBackPressed());

        txtNombreCompleto = findViewById(R.id.editTextNombreCompleto);
        txtIdentificacion = findViewById(R.id.editTextIdentificacion);
        txtTelefono = findViewById(R.id.editTextTelefono);
        txtTelefonoCasa = findViewById(R.id.editTextTelefonoDeCasa);
        txtCorreo = findViewById(R.id.editTextCorreo);
        txtDireccion = findViewById(R.id.editTextDireccion);
        txtNombreRef = findViewById(R.id.editTextRefNombre);
        txtTelefonoRef = findViewById(R.id.editTextRefTelefono);
        txtDireccionRef = findViewById(R.id.editTextRefDireccion);
        btnGuardar = findViewById(R.id.OK);

        btnGuardar.setOnClickListener(v -> {
            PayoDatabaseHelper db = new PayoDatabaseHelper(NuevoClienteActivity.this);
            db.addClient(txtNombreCompleto.getText().toString().trim(), txtIdentificacion.getText().toString().trim(), txtTelefono.getText().toString().trim(),
                        txtTelefonoCasa.getText().toString().trim(), txtCorreo.getText().toString().trim(), txtDireccion.getText().toString().trim(),
                        txtNombreRef.getText().toString().trim(), txtTelefonoRef.getText().toString().trim(), txtDireccionRef.getText().toString().trim());
            limpiar();
        });
    }

    private void limpiar()
    {
        txtNombreCompleto.setText("");
        txtIdentificacion.setText("");
        txtTelefono.setText("");
        txtTelefonoCasa.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtNombreRef.setText("");
        txtTelefonoRef.setText("");
        txtDireccionRef.setText("");
    }
}
package com.example.payov2.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payov2.R;
import com.example.payov2.db.PayoDatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    ImageView delete;
    TextView viewId;
    EditText fullName_input, identification_input, phone_input, homePhone_input, mail_input, direction_input, refFullName_input, refPhone_input, refDirection_input;
    String id, fullName, identification, phone, homePhone, mail, direction, refFullName, refPhone, refDirection;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        viewId = findViewById(R.id.txtId);
        fullName_input = findViewById(R.id.editTextNombreCompletoUpdate);
        identification_input = findViewById(R.id.editTextIdentificacionUpdate);
        phone_input = findViewById(R.id.editTextTelefonoUpdate);
        homePhone_input = findViewById(R.id.editTextTelefonodeCasaUpdate);
        mail_input = findViewById(R.id.editTextCorreoUpdate);
        direction_input = findViewById(R.id.editTextDireccionUpdate);
        refFullName_input = findViewById(R.id.editTextRefNombreUpdate);
        refPhone_input = findViewById(R.id.editTextRefTelefonoUpdate);
        refDirection_input = findViewById(R.id.editTextRefDireccionUpdate);
        save = findViewById(R.id.OKU);
        delete = findViewById(R.id.ImgViewDelete);

        getAndSetIntentData();

        save.setOnClickListener(v -> {
            PayoDatabaseHelper db = new PayoDatabaseHelper(this);
            db.updateData(id, fullName_input.getText().toString().trim(), identification_input.getText().toString(), phone_input.getText().toString(), homePhone_input.getText().toString(), mail_input.getText().toString(),
                    direction_input.getText().toString(), refFullName_input.getText().toString(), refPhone_input.getText().toString(), refDirection_input.getText().toString());
        });
        delete.setOnClickListener(v -> confirmDialog());
    }

    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("Id") && getIntent().hasExtra("FullName") && getIntent().hasExtra("Identification") && getIntent().hasExtra("Phone") && getIntent().hasExtra("HomePhone")
                && getIntent().hasExtra("Mail") && getIntent().hasExtra("Direction") && getIntent().hasExtra("RefFullName") && getIntent().hasExtra("RefPhone") && getIntent().hasExtra("RefDirection"))
        {
            id = getIntent().getStringExtra("Id");
            fullName = getIntent().getStringExtra("FullName");
            identification = getIntent().getStringExtra("Identification");
            phone = getIntent().getStringExtra("Phone");
            homePhone = getIntent().getStringExtra("HomePhone");
            mail = getIntent().getStringExtra("Mail");
            direction = getIntent().getStringExtra("Direction");
            refFullName = getIntent().getStringExtra("RefFullName");
            refPhone = getIntent().getStringExtra("RefPhone");
            refDirection = getIntent().getStringExtra("RefDirection");

            viewId.setText(id);
            fullName_input.setText(fullName);
            identification_input.setText(identification);
            phone_input.setText(phone);
            homePhone_input.setText(homePhone);
            mail_input.setText(mail);
            direction_input.setText(direction);
            refFullName_input.setText(refFullName);
            refPhone_input.setText(refPhone);
            refDirection_input.setText(refDirection);
        }
        else
        {
            Toast.makeText(this, "No hay datos del cliente selccionado", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar a " + fullName + "?");
        builder.setMessage("¿Estás seguro de que quieres borrar a " + fullName + "?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            PayoDatabaseHelper db = new PayoDatabaseHelper(UpdateActivity.this);
            db.deleteData(id);
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }

}
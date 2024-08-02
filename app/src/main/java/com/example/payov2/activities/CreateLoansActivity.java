package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payov2.R;
import com.example.payov2.db.PayoDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateLoansActivity extends AppCompatActivity {

    TextView TxTclientFullNameP, TxTclientDirectionP;
    EditText input_FechaInicio, input_FechaFin, input_Capital, input_Interes, input_NumeroDePagos, input_InteresMoratorio, input_DiasDeGracia;
    TextView input_Total, input_ValorCuota;
    Spinner paymentFrecuencySpinner, applyInterestToSpinner;
    ArrayAdapter<CharSequence> paymentFrecuencyAdapter, applyInterestToAdapter;
    SwitchCompat input_AplicarInteresMoratorio;
    String clientFullNameP, clientDirectionP;
    Button calcular, guardar;
    ImageButton inicio, fin;
    final Calendar myCalendarFechaInicio = Calendar.getInstance();
    final Calendar myCalendarFechaFin = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_loans);

        ImageView atras;
        atras = findViewById(R.id.atrasIconNPs);

        atras.setOnClickListener(v -> super.onBackPressed());

        TxTclientFullNameP = findViewById(R.id.viewNombreClientePrestamo);
        TxTclientDirectionP = findViewById(R.id.viewDireccionClientePrestamo);

        paymentFrecuencySpinner = findViewById(R.id.SpnFrecuenciaDePago);
        paymentFrecuencyAdapter = ArrayAdapter.createFromResource(this, R.array.PaymentFrecuency, android.R.layout.simple_spinner_item);
        paymentFrecuencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        paymentFrecuencySpinner.setAdapter(paymentFrecuencyAdapter);

        applyInterestToSpinner = findViewById(R.id.SpnAplicarInteresA);
        applyInterestToAdapter = ArrayAdapter.createFromResource(this, R.array.ApplyInterestTo, android.R.layout.simple_spinner_item);
        applyInterestToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        applyInterestToSpinner.setAdapter(applyInterestToAdapter);

        input_FechaInicio = findViewById(R.id.Date_FechaInicio);
        input_FechaFin = findViewById(R.id.Date_FechaFin);
        input_Capital = findViewById(R.id.editTextCapital);
        input_Interes = findViewById(R.id.editTextInteres);
        input_NumeroDePagos = findViewById(R.id.editTextNumeroDePagos);
        input_AplicarInteresMoratorio = findViewById(R.id.switchAplicarInteresMoratorio);
        input_InteresMoratorio = findViewById(R.id.editTextInteresMoratorio);
        input_DiasDeGracia = findViewById(R.id.editTextDiasDeGracia);
        input_Total = findViewById(R.id.editTextTotal);
        input_ValorCuota = findViewById(R.id.editTextValorCuota);

        inicio = findViewById(R.id.Btn_startDate);
        fin = findViewById(R.id.Btn_DueDate);
        calcular = findViewById(R.id.Btn_Calcular);
        guardar = findViewById(R.id.Btn_Guardar);

        input_InteresMoratorio.setText("0");
        input_DiasDeGracia.setText("0");

        getAndSetIntentData();

    input_AplicarInteresMoratorio.setOnClickListener(v -> {

        if(input_AplicarInteresMoratorio.isChecked())
        {
            input_AplicarInteresMoratorio.setShowText(true);
        }
        else
        {
            input_AplicarInteresMoratorio.setShowText(false);
        }

        if(input_AplicarInteresMoratorio.getShowText() == true)
        {
            input_InteresMoratorio.setEnabled(true);
            input_DiasDeGracia.setEnabled(true);
        }
        else
        {
            input_InteresMoratorio.setEnabled(false);
            input_DiasDeGracia.setEnabled(false);
            input_InteresMoratorio.setText("0");
            input_DiasDeGracia.setText("0");
        }

    });
        setInitialDate();

        DatePickerDialog.OnDateSetListener dateFechaInicio = (view, year, month, dayOfMonth) -> {
            myCalendarFechaInicio.set(Calendar.YEAR, year);
            myCalendarFechaInicio.set(Calendar.MONTH, month);
            myCalendarFechaInicio.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFechaInicio();
        };

        DatePickerDialog.OnDateSetListener dateFechaFin = (view, year, month, dayOfMonth) -> {
            myCalendarFechaFin.set(Calendar.YEAR, year);
            myCalendarFechaFin.set(Calendar.MONTH, month);
            myCalendarFechaFin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFechaFin();
        };

        inicio.setOnClickListener(v -> {
            new DatePickerDialog(CreateLoansActivity.this, dateFechaInicio, myCalendarFechaInicio.get(Calendar.YEAR), myCalendarFechaInicio.get(Calendar.MONTH), myCalendarFechaInicio.get(Calendar.DAY_OF_MONTH)).show();
        });

        fin.setOnClickListener(v -> {
            if(paymentFrecuencySpinner.getSelectedItem().toString().equals("Pago Único"))
            {
                fin.setEnabled(true);
                new DatePickerDialog(CreateLoansActivity.this, dateFechaFin, myCalendarFechaFin.get(Calendar.YEAR), myCalendarFechaFin.get(Calendar.MONTH), myCalendarFechaFin.get(Calendar.DAY_OF_MONTH)).show();
            }
            else
            {

            }
        });

        calcular.setOnClickListener(v -> {
            try
            {
                CalcularPrestamo();
            }
            catch (Exception e)
            {
                Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }

        });

        guardar.setOnClickListener(v -> {
            PayoDatabaseHelper db = new PayoDatabaseHelper(CreateLoansActivity.this);
            db.addLoan(TxTclientFullNameP.getText().toString().trim(), TxTclientDirectionP.getText().toString().trim(), paymentFrecuencySpinner.getSelectedItem().toString().trim(), input_FechaInicio.getText().toString().trim(),
                    input_FechaFin.getText().toString().trim(), input_Capital.getText().toString().trim(), applyInterestToSpinner.getSelectedItem().toString().trim(), input_Interes.getText().toString().trim(),
                    input_NumeroDePagos.getText().toString().trim(), String.valueOf(input_AplicarInteresMoratorio.isChecked()), input_InteresMoratorio.getText().toString().trim(), input_DiasDeGracia.getText().toString().trim(),
                    input_Total.getText().toString().trim(), input_ValorCuota.getText().toString().trim());
            limpiar();
        });

    }

    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("ClientFullName") && getIntent().hasExtra("ClientDirection"))
        {
            clientFullNameP = getIntent().getStringExtra("ClientFullName");
            clientDirectionP = getIntent().getStringExtra("ClientDirection");

            TxTclientFullNameP.setText(clientFullNameP);
            TxTclientDirectionP.setText(clientDirectionP);
        }
        else
        {
            Toast.makeText(this, "No hay datos del cliente selccionado", Toast.LENGTH_SHORT).show();
        }
    }
    void setInitialDate()
    {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        input_FechaInicio.setText(dateFormat.format(myCalendarFechaFin.getTime()));
    }
    void updateFechaInicio()
    {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        input_FechaInicio.setText(dateFormat.format(myCalendarFechaInicio.getTime()));
    }

    void updateFechaFin()
    {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        input_FechaFin.setText(dateFormat.format(myCalendarFechaFin.getTime()));
    }

    void CalcularPrestamo()
    {
        //Obtener los valores de los campos
        double capital = Double.parseDouble(input_Capital.getText().toString());
        String aplicarInteres = applyInterestToSpinner.getSelectedItem().toString();
        String frecuenciaDePago = paymentFrecuencySpinner.getSelectedItem().toString();
        double interes = Double.parseDouble(input_Interes.getText().toString());
        int numeroDePagos = Integer.parseInt(input_NumeroDePagos.getText().toString());
        boolean aplicarInteresMoratorio = input_AplicarInteresMoratorio.getShowText();
        double interesMoratorio = Double.parseDouble(input_InteresMoratorio.getText().toString());
        int diasDeGracia = Integer.parseInt(input_DiasDeGracia.getText().toString());

        //Calcular el interés total
        double interesTotal = 0;
        if(aplicarInteres.equals("Capital Inicial"))
        {
            interesTotal = capital * (interes/100);
        }
        else if (aplicarInteres.equals("Cada Cuota"))
        {
            interesTotal = capital * (interes/100) * numeroDePagos;
        }

        //Calcular el interés moratorio si aplica
        double interesMoratorioTotal = 0;
        if(aplicarInteresMoratorio)
        {
            if(diasDeGracia < 1)
            {
                Toast.makeText(this, "Debe asignar por lo menos 1 día de gracia", Toast.LENGTH_SHORT).show();
            }
            else
            {
                interesMoratorioTotal = (capital * (interesMoratorio/100)) / diasDeGracia;
            }
        }
        else
        {
            interesMoratorioTotal = 0;
        }

        //Calcular el total del préstamo
        double total = capital + interesTotal + interesMoratorioTotal;

        //Calcular el valor de la cuota
        double valorCuota = total / numeroDePagos;

        //Calcular plazo en el calendario de acuerdo al numero de pagos seleccionado
        if(frecuenciaDePago.equals("Pago Diario"))
        {
            myCalendarFechaFin.set(Calendar.YEAR, myCalendarFechaInicio.get(Calendar.YEAR));
            myCalendarFechaFin.set(Calendar.MONTH, myCalendarFechaInicio.get(Calendar.MONTH));
            myCalendarFechaFin.set(Calendar.DAY_OF_MONTH, myCalendarFechaInicio.get(Calendar.DAY_OF_MONTH) + numeroDePagos);
            updateFechaFin();
        }
        else if(frecuenciaDePago.equals("Pago Semanal"))
        {
            myCalendarFechaFin.set(Calendar.YEAR, myCalendarFechaInicio.get(Calendar.YEAR));
            myCalendarFechaFin.set(Calendar.MONTH, myCalendarFechaInicio.get(Calendar.MONTH));
            myCalendarFechaFin.set(Calendar.DAY_OF_MONTH, myCalendarFechaInicio.get(Calendar.DAY_OF_MONTH) + 7 * numeroDePagos);
            updateFechaFin();
        }
        else if (frecuenciaDePago.equals("Pago Quincenal"))
        {
            myCalendarFechaFin.set(Calendar.YEAR, myCalendarFechaInicio.get(Calendar.YEAR));
            myCalendarFechaFin.set(Calendar.MONTH, myCalendarFechaInicio.get(Calendar.MONTH));
            myCalendarFechaFin.set(Calendar.DAY_OF_MONTH, myCalendarFechaInicio.get(Calendar.DAY_OF_MONTH) + 15 * numeroDePagos);
            updateFechaFin();
        }
        else if (frecuenciaDePago.equals("Pago Mensual")) {
            myCalendarFechaFin.set(Calendar.YEAR, myCalendarFechaInicio.get(Calendar.YEAR));
            myCalendarFechaFin.set(Calendar.MONTH, myCalendarFechaInicio.get(Calendar.MONTH) + numeroDePagos);
            myCalendarFechaFin.set(Calendar.DAY_OF_MONTH, myCalendarFechaInicio.get(Calendar.DAY_OF_MONTH));
            updateFechaFin();
        }

        //Mostrar los resultados
        input_Total.setText(String.format("%.2f", total));
        input_ValorCuota.setText(String.format("%.2f", valorCuota));
    }
    void limpiar()
    {
        input_FechaFin.setText("");
        input_Capital.setText("");
        input_Interes.setText("");
        input_NumeroDePagos.setText("");
        input_InteresMoratorio.setText("");
        input_DiasDeGracia.setText("");
        input_Total.setText("");
        input_ValorCuota.setText("");
    }
}
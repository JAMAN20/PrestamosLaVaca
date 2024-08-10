package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payov2.R;

import java.util.Calendar;

public class LoanDetailsActivity extends AppCompatActivity {

    TextView TxTclientFullNameP, TxTloan_id, txtFecha, txtFechaVencimiento, txtCantidad, txtRatioInteres, txtInteres, txtTotal, txtFrecuenciaPago, txtNumeroPagos, txtValorCuota, txtBalance, txtCuotasPagadas, txtStatus;
    String loan_Id, clientFullName, clientDirection, paymentFrecuency, startDate, dueDate, capital, applyInterestTo, interest, numberOfPayments, applyInterestForDelay, interestForDelay, graceDays, total, quotaValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);

        TxTloan_id = findViewById(R.id.txtPrestamoId);
        TxTclientFullNameP = findViewById(R.id.txtNombre);

        txtFecha = findViewById(R.id.txtFecha);
        txtFechaVencimiento = findViewById(R.id.txtFechaVencimiento);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtRatioInteres = findViewById(R.id.txtRatioInteres);
        txtInteres = findViewById(R.id.txtInteres);
        txtTotal = findViewById(R.id.txtTotal);
        txtFrecuenciaPago = findViewById(R.id.txtFrecuenciaPago);
        txtNumeroPagos = findViewById(R.id.txtNumeroPagos);
        txtValorCuota = findViewById(R.id.txtValorCuota);
        txtBalance = findViewById(R.id.txtBalance);
        txtCuotasPagadas = findViewById(R.id.txtCuotasPagadas);
        txtStatus = findViewById(R.id.txtStatus);

        try
        {
            getAndSetIntentData();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("Loan_Id") && getIntent().hasExtra("ClientName") && getIntent().hasExtra("ClientDirection") && getIntent().hasExtra("PaymentFrecuency") && getIntent().hasExtra("StartDate") && getIntent().hasExtra("DueDate")
                && getIntent().hasExtra("Capital") && getIntent().hasExtra("ApplyInterestTo") && getIntent().hasExtra("Interest") && getIntent().hasExtra("NumberOfPayments") && getIntent().hasExtra("ApplyInterestForDelay")
                && getIntent().hasExtra("InterestForDelay") && getIntent().hasExtra("GraceDays") && getIntent().hasExtra("Total") && getIntent().hasExtra("QuotaValues"))
        {
            loan_Id = getIntent().getStringExtra("Loan_Id");
            clientFullName = getIntent().getStringExtra("ClientName");
            clientDirection = getIntent().getStringExtra("ClientDirection");
            paymentFrecuency = getIntent().getStringExtra("PaymentFrecuency");
            startDate = getIntent().getStringExtra("StartDate");
            dueDate = getIntent().getStringExtra("DueDate");
            capital = getIntent().getStringExtra("Capital");
            applyInterestTo = getIntent().getStringExtra("ApplyInterestTo");
            interest = getIntent().getStringExtra("Interest");
            numberOfPayments = getIntent().getStringExtra("NumberOfPayments");
            applyInterestForDelay = getIntent().getStringExtra("ApplyInterestForDelay");
            interestForDelay = getIntent().getStringExtra("InterestForDelay");
            graceDays = getIntent().getStringExtra("GraceDays");
            total = getIntent().getStringExtra("Total");
            quotaValue = getIntent().getStringExtra("QuotaValues");

            TxTloan_id.setText(loan_Id);
            TxTclientFullNameP.setText(clientFullName);
            txtFecha.setText(startDate);
            txtFechaVencimiento.setText(dueDate);
            txtCantidad.setText(capital);
            txtRatioInteres.setText(interest);
            txtNumeroPagos.setText(numberOfPayments);
            txtTotal.setText(total);
            txtFrecuenciaPago.setText(paymentFrecuency);
            txtNumeroPagos.setText(numberOfPayments);
            txtValorCuota.setText(quotaValue);

            CalcularInteres();
        } else {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
    }

    void CalcularInteres()
    {
        double Cantidad = Double.parseDouble(txtCantidad.getText().toString());
        double InteresRate = Double.parseDouble(txtRatioInteres.getText().toString());

        double Interes = (Cantidad * InteresRate) / 100;

        txtInteres.setText(""+Interes);
    }
}
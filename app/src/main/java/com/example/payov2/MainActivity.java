package com.example.payov2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.payov2.activities.ClientesActivity;
import com.example.payov2.activities.CobrosDelDiaActivity;
import com.example.payov2.activities.ConfiguracionActivity;
import com.example.payov2.activities.CuotasActivity;
import com.example.payov2.activities.EstadisticasGeneralesActivity;
import com.example.payov2.activities.NuevoClienteActivity;
import com.example.payov2.activities.NuevoPrestamoActivity;
import com.example.payov2.activities.PrestamosActivity;
import com.example.payov2.activities.SincronizacionActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        CardView cobros;
        CardView cuotas;
        CardView nuevoprestamo;
        CardView nuevocliente;
        CardView prestamos;
        CardView clientes;
        CardView estadisticasgenerales;
        CardView sincronizacion;
        CardView configuracion;

        cobros = findViewById(R.id.cobros_del_dia);
        cuotas = findViewById(R.id.cuotas_atrasadas);
        nuevoprestamo = findViewById(R.id.nuevo_prestamo);
        nuevocliente = findViewById(R.id.nuevo_cliente);
        prestamos = findViewById(R.id.prestamos);
        clientes = findViewById(R.id.clientes);
        estadisticasgenerales = findViewById(R.id.estadisticas_generales);
        sincronizacion = findViewById(R.id.sincronizacion);
        configuracion = findViewById(R.id.configuracion);

        cobros.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CobrosDelDiaActivity.class);
            startActivity(intent);
        });

        cuotas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CuotasActivity.class);
            startActivity(intent);
        });

        nuevoprestamo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevoPrestamoActivity.class);
            startActivity(intent);
        });

        nuevocliente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevoClienteActivity.class);
            startActivity(intent);
        });

        prestamos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PrestamosActivity.class);
            startActivity(intent);
        });

        clientes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClientesActivity.class);
            startActivity(intent);
        });

        estadisticasgenerales.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EstadisticasGeneralesActivity.class);
            startActivity(intent);
        });

        sincronizacion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SincronizacionActivity.class);
            startActivity(intent);
        });

        configuracion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
            startActivity(intent);
        });
    }
}
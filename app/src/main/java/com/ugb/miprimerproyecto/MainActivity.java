package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TabHost tbhConversores;
    Button btnConvertir;
    TextView tempVal;
    Spinner spnOpcionDe, spnOpcionA;
    conversores miConversor = new conversores();
    RelativeLayout contenidoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tbhConversores = findViewById(R.id.tbhConversores);
        tbhConversores.setup();

        tbhConversores.addTab(tbhConversores.newTabSpec("Monedas").setContent(R.id.tabMonedas));
        tbhConversores.addTab(tbhConversores.newTabSpec("Longitud").setContent(R.id.tabLongitud));
        tbhConversores.addTab(tbhConversores.newTabSpec("Tiempo").setContent(R.id.tabTiempo));
        tbhConversores.addTab(tbhConversores.newTabSpec("Volumen").setContent(R.id.tabVolumen));
        btnConvertir = findViewById(R.id.btnCalcular);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = (TextView) findViewById(R.id.txtcantidad);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDe);
                    spnOpcionA = findViewById(R.id.cboA);
                    tempVal = findViewById(R.id.lblRespuesta);
                    tempVal.setText("Respuesta: " + miConversor.convertir(0, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuesta);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnConvertir = findViewById(R.id.btnCalcularL);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtCantidadL);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeL);
                    spnOpcionA = findViewById(R.id.cboAL);
                    tempVal = findViewById(R.id.lblRespuestaL);

                    tempVal.setText("Respuesta: " + miConversor.convertir(1, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaL);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


class conversores{
    double[][] conversor = {
            {1.0,8.75,7.77, 24.03,34.8,611.10,0.82,105,3545.93,20.05},/*Monedas*/
            {1.0, 100.0,39.37,3.28,1.09,0.000621,0.00054,1000,1000000,1000000000,1.19},/*Longitud*/
            {1.00,8.64e+7,86400.00,1440.00,24.00,0.142857,0.0328767,0.00273973,0.000273973,2.7397e-5},/*Tiempo*/
            {1.00,1000.00,1000.00,0.001,202.88,67.628,4.22,1.056,0.26,61.023},/*Volumen*/
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
    }
}
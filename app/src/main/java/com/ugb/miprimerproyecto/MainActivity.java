package com.ugb.miprimerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

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

        contenidoView = findViewById(R.id.contenidoView);
        tbhConversores = findViewById(R.id.tbhconversores);
        tbhConversores.setup();
        tbhConversores.addTab(tbhConversores.newTabSpec("Superficie").setContent(R.id.tabsuperficie).setIndicator("",getResources().getDrawable(R.drawable.ic_money)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Superficie").setContent(R.id.tab2).setIndicator("",getResources().getDrawable(R.drawable.ic_iconfinder_hygiene_23_4443488)));

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
        btnConvertir = findViewById(R.id.btnCalcular1);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView respuesta = findViewById(R.id.lblRespuesta1);
                EditText num = findViewById(R.id.txtcantidad1);
                double valor = Double.parseDouble(num.getText().toString());

                double respuest = 6;
                if (valor >= 18){
                 respuest= 6;

                }
               if (valor >= 19){
               respuest = valor - 16 * 0.45 + 6 - 14.8;

               }
               if (valor >= 29 ){
               respuest = valor -26 * 0.65 - 18 * 0.45 + 6.5 + 4.5 + 6 - 13;

               }
                respuesta.setText(String.valueOf(respuest));
            }

        });

    }
}


class conversores{
    double[][] conversor = {
            {0.092903, 0.698896, 0.836127,1, 628.8, 7000, 10000},/*Superficie*/

    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
    }
}


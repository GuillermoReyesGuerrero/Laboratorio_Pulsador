package com.example.guillermo.ggrg_pulsor;

import android.content.Intent;
import android.os.Handler;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout full_view;
    private TextView numale;
    private TextView numsecue;
    private TextView result;
    Button botreiniciar;
    Button botalto;
    double numero;
    float secuencia,aux;
    boolean comparar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        full_view = findViewById(R.id.full_view);
        numale=findViewById(R.id.txtnumale);
        numsecue=findViewById(R.id.txtnumsecu);
        botreiniciar=findViewById(R.id.btnreiniciar);
        botalto=findViewById(R.id.btnalto);
        result=findViewById(R.id.txtresultado);


        numero=(int) (Math.random()*20+10);
        numero=numero/10;
        numale.setText(numero+"");

        secuencia=1;
        aux=(float)0.1;
        comparar=false;
        botalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparar=true;
                if(numale.getText().equals(numsecue.getText())){
                    result.setText("¡Ganaste!");
                    full_view.setBackgroundColor(Color.GREEN);
                }else{
                    result.setText("¡Perdiste!");
                    full_view.setBackgroundColor(Color.RED);
                }
            }
        });

        botreiniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent= getIntent();
                finish();
                startActivity(intent);
            }
        });
        actualizar();
    }
    private void actualizar(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new   Runnable() {
                            public void run() {
                                if(secuencia>=3.0)aux=(-1*aux);
                                secuencia+=aux;
                                if(secuencia<=1.0)aux=(-1*aux);
                                if(comparar)return;

                                numsecue.setText(String.format("%.1f", secuencia));
                                actualizar();
                            }
                        });
                    }
                },
                200
        );
    }
}

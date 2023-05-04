package com.example.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView contadordeTempo;
    CountDownTimer tempo;
    Button btn;
    String[] hora;
    String[] minuto;
    String[] segundo;
    ArrayAdapter<String> percentualAdaptado;
    MediaPlayer alarme;

    Spinner sh,sm,ss;
    long total=0, h=0, m=0, s=0;
    ImageView imv;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        contadordeTempo =  findViewById(R.id.txtcont);
        imv =  findViewById(R.id.imv);
        senha = findViewById(R.id.senha);
        sh = findViewById(R.id.spHora);
        sm = findViewById(R.id.spMinuto);
        ss = findViewById(R.id.spSegundo);
        hora = new String[14];
        hora[0] = "Hora";
        minuto = new String[13];
        minuto[0] = "Minuto";
        segundo = new String[13];
        segundo[0] = "Segundo";

        for (int i = 1; i <= 13; i++){
            hora[i] = i -1+"";

        }
        int cont = 0;
        for (int i = 1; i < 13; i++){
            minuto[i] = cont + "";
            segundo[i] = cont+ "";
            cont +=5;
        }

        percentualAdaptado = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hora);
        sh.setAdapter(percentualAdaptado);

        percentualAdaptado = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, minuto);
        sm.setAdapter(percentualAdaptado);

        percentualAdaptado = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, segundo);
        ss.setAdapter(percentualAdaptado);

        ss.setAdapter(percentualAdaptado);




        sh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int ItemPosicao, long l) {


                try {

                    h=Long.parseLong(sh.getSelectedItem().toString());

                }catch (Exception e){

                    h=0;

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        sm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int ItemPosicao, long l) {


                try {

                    m=Long.parseLong(sm.getSelectedItem().toString());

                }catch (Exception e){

                    m=0;

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int ItemPosicao, long l) {


                try {

                    s=Long.parseLong(ss.getSelectedItem().toString());

                }catch (Exception e){

                    s=0;

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void comecar(View v){
        total = (h * 3600 * 1000) + (m * 60 * 1000) + (s * 1000);
        imv.setImageResource(R.drawable.bomba);
        iniciarContagem();
    }

    public void iniciarContagem(){

        tempo = new CountDownTimer(total, 1000) {
            public void onTick(long millisegundos) {

                long horas = (millisegundos/1000)/3600;
                long minutos = ((millisegundos/1000)%3600)/60;
                long segundos = (millisegundos/1000)%60;
                String formato = String.format(Locale.getDefault(),"%02d:%02d:%02d:", horas, minutos, segundos);
                contadordeTempo.setText(formato);
            }
            public void onFinish() {

                contadordeTempo.setText("00:00:00");
                alarme = MediaPlayer.create(MainActivity.this,R.raw.bomba);
                imv.setImageResource(R.drawable.explosao);
                alarme.start();

            }
        }.start();


    }

    public void desativar(View v){
        if(senha.getText().toString().equals("123")){
            tempo.cancel();
        }
    }

}
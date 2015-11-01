package com.example.thiago.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraActivity extends AppCompatActivity {

    private TextView visor;
    private Button [] btNumericos = new Button[10];
    private Button [] btAcoes = new Button[6];
    private double total = 0.0;
    private String numStr = "";
    private double num = 0.0;
    private char ultAcao = 'I';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        visor = (TextView)findViewById(R.id.visor);
        int width = getWindowManager().getDefaultDisplay().getWidth();

        btNumericos[0] = (Button)findViewById(R.id.bt0);
        btNumericos[1] = (Button)findViewById(R.id.bt1);
        btNumericos[2] = (Button)findViewById(R.id.bt2);
        btNumericos[3] = (Button)findViewById(R.id.bt3);
        btNumericos[4] = (Button)findViewById(R.id.bt4);
        btNumericos[5] = (Button)findViewById(R.id.bt5);
        btNumericos[6] = (Button)findViewById(R.id.bt6);
        btNumericos[7] = (Button)findViewById(R.id.bt7);
        btNumericos[8] = (Button)findViewById(R.id.bt8);
        btNumericos[9] = (Button)findViewById(R.id.bt9);

        for (byte i = 0; i < btNumericos.length; i++){
            final byte num = i;
            btNumericos[i].setWidth(width/4);
            btNumericos[i].setText("" + i);
            btNumericos[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    montar(num);
                }
            });
        }

        final char [] acao = {'+', '-', 'X', '/', '=', 'C'};
        btAcoes[0] = (Button)findViewById(R.id.btSom);
        btAcoes[1] = (Button)findViewById(R.id.btSub);
        btAcoes[2] = (Button)findViewById(R.id.btMul);
        btAcoes[3] = (Button)findViewById(R.id.btDiv);
        btAcoes[4] = (Button)findViewById(R.id.btEql);
        btAcoes[5] = (Button)findViewById(R.id.btCls);

        for (byte i = 0; i < btAcoes.length; i++){
            final byte num = i;
            btAcoes[i].setWidth(width/4);
            btAcoes[i].setText(""+acao[i]);
            btAcoes[i].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    calcular(acao[num]);
                }
            });
        }
    }

    private void montar(byte i){
        numStr += i;
        visor.setText(numStr);
    }

    private void calcular(char c){
        if (c == 'C'){
            ultAcao = 'I';
            num = 0.0;
            total = 0.0;
            numStr = "";
        }else {
            if (numStr.length() > 0 ){
                num = Double.parseDouble(numStr);
                numStr = "";
            }
            switch (ultAcao){
                case 'I': total = num; break;
                case '+': total += num; break;
                case '-': total -= num; break;
                case 'X': total *= num; break;
                case '/': total /= num; break;
            }
            ultAcao = c;
        }
        visor.setText("" + total);
    }
}

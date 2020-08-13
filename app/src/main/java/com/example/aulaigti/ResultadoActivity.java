package com.example.aulaigti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultadoActivity extends AppCompatActivity {

    private Button btnRetornar;
    private TextView
            txtSalarioBruto,
            txtInss,
            txtIrrf,
            txtOutrosDescontos,
            txtSalarioLiquido,
            txtPercentualDescontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        btnRetornar = findViewById(R.id.btnretornar);

        txtSalarioBruto = findViewById(R.id.textsalariobruto);
        txtInss = findViewById(R.id.textinss);
        txtIrrf = findViewById(R.id.textirrf);
        txtOutrosDescontos = findViewById(R.id.textoutrosdescontos);
        txtSalarioLiquido = findViewById(R.id.textsalarioliquido);
        txtPercentualDescontos = findViewById(R.id.textdescontos);

        Intent intent = getIntent();
        double salarioBruto = intent.getDoubleExtra(MainActivity.SALARIO_BRUTO, 0);
        double dependentes = intent.getIntExtra(MainActivity.DEPENDENTES, 0);
        double descontos = intent.getDoubleExtra(MainActivity.DESCONTOS, 0);

        double contribuicaoINSS = calculaINSS(salarioBruto);
        double contribuicaoIRRF =
                calculaIRRF(salarioBruto - contribuicaoINSS - dependentes * 189.59);
        double salarioLiquido = salarioBruto - contribuicaoIRRF - contribuicaoINSS - descontos;
        double percentualDescontos = (1-salarioLiquido/salarioBruto)*100;

        txtSalarioBruto.setText(String.valueOf("R$ "+formatDouble(salarioBruto)));
        txtInss.setText(String.valueOf("- R$ "+formatDouble(contribuicaoINSS)));
        txtIrrf.setText(String.valueOf("- R$ "+formatDouble(contribuicaoIRRF)));
        txtOutrosDescontos.setText(String.valueOf("- R$ "+formatDouble(descontos)));

        txtSalarioLiquido.setText(String.valueOf("R$ "+formatDouble(salarioLiquido)));
        txtPercentualDescontos.setText(String.valueOf(formatDouble(percentualDescontos)+"%"));

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    private double formatDouble(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(d));
    }

    private double calculaIRRF(double baseCalculo) {
        if(baseCalculo <= 1903.98) return 0;
        if(baseCalculo <= 2826.65) return (baseCalculo*0.075) - 142.80;
        if(baseCalculo <=  3751.05) return (baseCalculo*0.150) - 354.80;
        if(baseCalculo <= 4664.68) return (baseCalculo*0.225) - 636.13;

        return (baseCalculo*0.275) - 869.36;
    }

    private double calculaINSS(double baseCalculo) {
        if(baseCalculo <= 1045.00) return (baseCalculo*0.075);
        if(baseCalculo <= 2089.60) return (baseCalculo*0.900) - 15.67;
        if(baseCalculo <=  3134.40) return (baseCalculo*0.120) - 78.36;
        if(baseCalculo <=  6101.06) return (baseCalculo*0.140) - 141.05;

        return 713.10;
    }

}
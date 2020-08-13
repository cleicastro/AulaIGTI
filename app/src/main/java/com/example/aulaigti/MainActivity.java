package com.example.aulaigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SALARIO_BRUTO = "com.example.aulaigti.SALARIO_BRUTO";
    public static final String DEPENDENTES = "com.example.aulaigti.DEPENDENTES";
    public static final String DESCONTOS = "com.example.aulaigti.DESCONTOS";

    private Button btnCalcular;
    private EditText edtTxtSalario, edtTxtDependentes, edtTxtDescontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btncalcular);
        edtTxtSalario = findViewById(R.id.editTextSalarioBruto);
        edtTxtDependentes = findViewById(R.id.editTextNumeroDependentes);
        edtTxtDescontos = findViewById(R.id.editTextOutrosDescontos);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateFields();
                Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);

                intent.putExtra(SALARIO_BRUTO, Double.parseDouble(edtTxtSalario.getText().toString()));
                intent.putExtra(DEPENDENTES, Integer.parseInt(edtTxtDependentes.getText().toString()));
                intent.putExtra(DESCONTOS, Double.parseDouble(edtTxtDependentes.getText().toString()));

                startActivity(intent);
            }
        });
    }

    private void validateFields() {
        edtTxtSalario = findViewById(R.id.editTextSalarioBruto);
        edtTxtDependentes = findViewById(R.id.editTextNumeroDependentes);
        edtTxtDescontos = findViewById(R.id.editTextOutrosDescontos);

        if(edtTxtSalario.getText().length() <= 0 ) {
            edtTxtSalario.setText("0");
        }
        if(edtTxtDependentes.getText().length() <= 0 ) {
            edtTxtDependentes.setText("0");
        }
        if(edtTxtDescontos.getText().length() <= 0 ) {
            edtTxtDescontos.setText("0");
        }
    }
}
package br.gov.sp.a260828_imc2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    //Declarar os componentes
    private EditText txtNome;

    private EditText txtPeso;

    private EditText txtAltura;

    private TextView txtResultado;

    private Button btnCalcular;

    private Button btnLimpar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Relacionar os componentes Java com os componentes XML
        txtNome = findViewById(R.id.txtNome);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);

        // btn calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // recupera os valores digitados:
                String nome = txtNome.getText().toString();
                String peso = txtPeso.getText().toString();
                String altura = txtAltura.getText().toString();

                // verifica se os campos foram preenchidos
                if (nome.isEmpty() || peso.isEmpty() || altura.isEmpty()){
                    txtResultado.setText("preencha os campos");
                    return;
                }
                // converte os valores
                double numPeso = Double.parseDouble(peso);
                double numAltura = Double.parseDouble(altura);

                // converte cm para metros
                double numAlturaCm = numAltura/100;

                // calcula o imc
                double numIMC = numPeso / (numAlturaCm*numAlturaCm);

                // classificação imc
                String classIMC;

                if (numIMC < 18.5){ classIMC = "Abaixo do Peso";}
                else if (numIMC < 25) { classIMC = "Peso Normal";}
                else if (numIMC < 30) { classIMC = "Sobrepeso";}
                else if (numIMC < 35) { classIMC = "Obesidade Grau I";}
                else if (numIMC < 40) { classIMC = "Obesidade Grau II";}
                else { classIMC = "Obesidade Grau III";}

                // formata o resultado com uma casa decimal
                String formatIMC = String.format(Locale.US, "%.1f", numIMC);

                // Apresenta o resultado
                txtResultado.setText(nome + "\nIMC: " + formatIMC + "\n" + classIMC);
            }
        });

        // btn limpar
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //limpar os campos
                txtNome.setText("");
                txtPeso.setText("");
                txtAltura.setText("");

                //limpa o resultado
                txtResultado.setText("Resultado");

                // Retorna o cursor para o nome
                txtNome.requestFocus();
            }
        });

    }
}
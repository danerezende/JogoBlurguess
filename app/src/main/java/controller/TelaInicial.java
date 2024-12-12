package controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.jogoblur.blurguess10.R;

public class TelaInicial extends AppCompatActivity {

    private static final String EXTRA_CATEGORIA = "categoria";
    private ImageButton btnFilmes, btnSerie, btnJogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarBotoes();
        configurarListeners();
    }

    private void inicializarBotoes() {
        btnFilmes = findViewById(R.id.imageButton);
        btnSerie = findViewById(R.id.imageButton2);
        btnJogos = findViewById(R.id.imageButton3);
    }

    private void configurarListeners() {
        btnFilmes.setOnClickListener(view -> iniciarJogo("Filmes"));
        btnSerie.setOnClickListener(view -> iniciarJogo("Series"));
        btnJogos.setOnClickListener(view -> iniciarJogo("Jogos"));
    }

    private void iniciarJogo(String categoria) {
        Log.d("TelaInicial", "Categoria selecionada: " + categoria);
        Intent intent = new Intent(this, TelaJogo.class);
        intent.putExtra("categoria", categoria.toUpperCase());
        startActivity(intent);
    }

}

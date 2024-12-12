package controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jogoblur.blurguess10.R;

public class TelaWinner extends AppCompatActivity {

    private ImageView imgWinner;
    private TextView tvFeedback, tvTitulo;
    private Button btnNovoJogo;
    private int imgResId; // ID da imagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telawinner);

        imgWinner = findViewById(R.id.imgBlurred);
        tvFeedback = findViewById(R.id.tvFeedback);
        tvTitulo = findViewById(R.id.tvTitulo);  // Adicionando TextView para o título
        btnNovoJogo = findViewById(R.id.btnNovoJogo);

        // Obter a mensagem, título da imagem e caminho da imagem da intent
        boolean venceu = getIntent().getBooleanExtra("venceu", false);
        String caminhoImagem = getIntent().getStringExtra("caminhoImagem");
        String tituloImagem = getIntent().getStringExtra("tituloImagem");

        // Carregar a imagem sem blur
        imgResId = getResources().getIdentifier(caminhoImagem, "drawable", getPackageName());
        if (imgResId != 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgResId);
            imgWinner.setImageBitmap(bitmap); // Exibe a imagem sem o blur
        }

        // Definir a mensagem de feedback
        if (venceu) {
            tvFeedback.setText("Parabéns! Você acertou!");
        } else {
            tvFeedback.setText("Poxa, não foi dessa vez. Tente novamente!");
        }

        // Exibir o título da imagem
        tvTitulo.setText("Título: " + tituloImagem);

        // Configurar o botão de "Novo Jogo"
        btnNovoJogo.setOnClickListener(v -> {
            // Reinicia o jogo
            Intent intent = new Intent(TelaWinner.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });
    }
}

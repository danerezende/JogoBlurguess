package controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jogoblur.blurguess10.R;

import model.Categoria;
import model.Jogo;
import model.Imagem;

public class TelaJogo extends AppCompatActivity {

    private Jogo jogo;
    private ImageView imgBlurred;
    private EditText edtResposta;
    private Button btnEnviar, btnDica;
    private TextView tvDica;
    private LinearLayout vidasContainer;
    private Imagem imagem;
    private int imgResId; // ID da imagem que será carregada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telajogo);

        imgBlurred = findViewById(R.id.imgBlurred);
        edtResposta = findViewById(R.id.edtResposta);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnDica = findViewById(R.id.btnDica);
        tvDica = findViewById(R.id.tvDica);
        vidasContainer = findViewById(R.id.vidasContainer);

        // Obter a categoria selecionada na tela anterior
        String categoriaNome = getIntent().getStringExtra("categoria");
        Categoria categoria = Categoria.valueOf(categoriaNome.toUpperCase());

        // Inicializar o jogo com a categoria
        jogo = new Jogo(categoria);

        // Inicializar a imagem com a categoria
        imagem = new Imagem(jogo.getImagem().getCaminho());

        // Carregar a imagem borrada
        carregarImagem();

        // Configurar os botões
        btnEnviar.setOnClickListener(v -> verificarResposta());
        btnDica.setOnClickListener(v -> mostrarDica());

        // Inicializar o número de vidas para 6
        jogo.setVidas(6);
    }

    private void carregarImagem() {
        // Carregar a imagem a partir do recurso drawable
        imgResId = getResources().getIdentifier(jogo.getImagem().getCaminho(), "drawable", getPackageName());
        if (imgResId != 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgResId);
            imagem.aplicarBlurNaImagem(this, imgBlurred, bitmap);  // Passando o contexto para o método
        } else {
            // Caso a imagem não seja encontrada
            Toast.makeText(this, "Imagem não encontrada!", Toast.LENGTH_SHORT).show();
        }
    }


    private void verificarResposta() {
        String resposta = edtResposta.getText().toString();

        if (jogo.getVidas() > 0) { // Verifique se ainda há vidas
            if (jogo.verificarResposta(resposta)) {
                // Exibir feedback de sucesso


                // Atualizar a vida com sucesso (ficar verde)
                atualizarVidas(true); // Passa true para indicar que o jogador acertou

                // Enviar para a TelaWinner com a imagem e título
                Intent intent = new Intent(TelaJogo.this, TelaWinner.class);
                intent.putExtra("venceu", true);
                intent.putExtra("caminhoImagem", jogo.getImagem().getCaminho());
                intent.putExtra("tituloImagem", jogo.getPergunta().getRespostaCerta());
                startActivity(intent);
                finish();
            } else {
                // Diminuir vida e mostrar feedback de erro
                jogo.diminuirVida();
                atualizarVidas(false); // Passa false para indicar que o jogador errou

                if (jogo.getVidas() == 0) {
                    // Enviar para a TelaWinner com a imagem e título quando perder
                    Intent intent = new Intent(TelaJogo.this, TelaWinner.class);
                    intent.putExtra("venceu", false);
                    intent.putExtra("caminhoImagem", jogo.getImagem().getCaminho());
                    intent.putExtra("tituloImagem", jogo.getPergunta().getRespostaCerta());
                    startActivity(intent);
                    finish();
                }
            }
        } else {
            // Caso não haja vidas restantes, fechar o jogo
            Toast.makeText(this, "Fim de jogo!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TelaJogo.this, TelaWinner.class);
            intent.putExtra("venceu", false);
            startActivity(intent);
            finish();
        }
    }

    private void mostrarDica() {
        tvDica.setText(jogo.getPergunta().getDica());
        tvDica.setVisibility(View.VISIBLE);
    }

    private void atualizarVidas(boolean acertou) {
        if (jogo.getVidas() >= 0 && jogo.getVidas() <= 6) { // Garantir que o número de vidas seja válido
            // Atualizar a interface de vidas
            for (int i = 0; i < 6; i++) {
                ImageView vida = (ImageView) vidasContainer.getChildAt(i);

                if (i < jogo.getVidas()) {
                    // Se ainda houver vida, manter o quadrado normal (sem cor alterada)
                    vida.setVisibility(View.VISIBLE);
                    vida.setImageResource(R.drawable.quadrado_vida); // Imagem original
                } else {
                    // Caso o jogador tenha perdido a vida, altere a cor do quadradinho
                    vida.setVisibility(View.VISIBLE);
                    if (acertou) {
                        vida.setImageResource(R.drawable.quadrado_acerto); // Cor verde
                    } else {
                        vida.setImageResource(R.drawable.quadrado_erro); // Cor vermelha
                    }
                }

            }

            // Diminuir o blur conforme as vidas restantes
            imagem.diminuirBlur(); // Chamando o método para diminuir o blur
            aplicarBlurNaImagem(); // Aplicando a imagem com o novo blur
        }
    }

    private void aplicarBlurNaImagem() {
        // Atualizar a imagem com o novo blur
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgResId);
        imagem.aplicarBlurNaImagem(this, imgBlurred, bitmap);  // Passando o contexto para aplicar o blur
    }


    private void mostrarFeedback(String mensagem) {
        // Exibir a mensagem de feedback
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}
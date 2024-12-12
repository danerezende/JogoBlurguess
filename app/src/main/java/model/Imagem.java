package model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.widget.ImageView;

import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class Imagem {

    private String caminho;
    private boolean isBlurred;
    private float currentBlurRadius;

    public Imagem(String caminho) {
        this.caminho = caminho;
        this.isBlurred = true; // A imagem começa borrada
        this.currentBlurRadius = 40f; // Começa com o blur máximo
    }

    // Aplicar o blur com o valor atual
    public void aplicarBlurNaImagem(Context context, ImageView imageView, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Usar RenderEffect no Android 12+ (API 31+)
            RenderEffect blurEffect = RenderEffect.createBlurEffect(
                    currentBlurRadius, currentBlurRadius, Shader.TileMode.CLAMP
            );
            imageView.setRenderEffect(blurEffect);
            imageView.setImageBitmap(bitmap);
        } else {
            // Usar RenderScript para versões inferiores
            Bitmap blurredBitmap = aplicarBlurRenderScript(context, bitmap, currentBlurRadius);
            imageView.setImageBitmap(blurredBitmap);
        }
    }

    // Método para diminuir o blur conforme o número de vidas
    public void diminuirBlur() {
        if (currentBlurRadius > 0) {
            currentBlurRadius -= 5f; // Reduz o blur a cada vida perdida
            if (currentBlurRadius < 0) {
                currentBlurRadius = 0; // Não permitir valores negativos de blur
            }
        }
    }

    // Implementação do RenderScript com redimensionamento e múltiplos passes de blur
    private Bitmap aplicarBlurRenderScript(Context context, Bitmap bitmap, float radius) {
        // O raio máximo permitido pelo RenderScript é 25
        radius = Math.min(25, Math.max(0, radius));

        // 1. Reduzir a escala da imagem (ajuste o fator conforme necessário)
        int width = Math.round(bitmap.getWidth() * 0.2f);
        int height = Math.round(bitmap.getHeight() * 0.2f);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

        // 2. Criar um bitmap de saída para armazenar o blur
        Bitmap outputBitmap = Bitmap.createBitmap(scaledBitmap);

        // 3. Inicializar RenderScript
        RenderScript rs = RenderScript.create(context);

        // 4. Criar alocações para entrada e saída
        Allocation input = Allocation.createFromBitmap(rs, scaledBitmap);
        Allocation output = Allocation.createFromBitmap(rs, outputBitmap);

        // 5. Configurar o ScriptIntrinsicBlur
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, input.getElement());
        blur.setInput(input);
        blur.setRadius(radius);

        // 6. Aplicar múltiplos passes de blur para aumentar o efeito
        for (int i = 0; i < 10; i++) { //
            blur.forEach(output);
            output.copyTo(outputBitmap);
        }

        // 7. Redimensionar de volta para o tamanho original
        Bitmap finalBitmap = Bitmap.createScaledBitmap(outputBitmap, bitmap.getWidth(), bitmap.getHeight(), false);

        // Liberar os recursos do RenderScript
        rs.destroy();

        return finalBitmap;
    }

    // Getters e Setters
    public String getCaminho() {
        return caminho;
    }

    public boolean isBlurred() {
        return isBlurred;
    }

    public void removerBlur() {
        this.isBlurred = false;
    }
}

package model;


public class Pergunta {


    private String pergunta;
    private String respostaCerta;
    private String dica;


    public Pergunta(String pergunta, String respostaCerta, String dica) {
        this.pergunta = pergunta;
        this.respostaCerta = respostaCerta;
        this.dica = dica;
    }


    public String getPergunta() {
        return pergunta;
    }


    public String getRespostaCerta() {
        return respostaCerta;
    }


    public String getDica() {
        return dica;
    }


    public boolean verificarResposta(String resposta) {
        return resposta.equalsIgnoreCase(respostaCerta);
    }
}

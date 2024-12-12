package model;


public enum Categoria {
    FILMES("Filmes"),
    JOGOS("Jogos"),
    SERIES("Series"); // Altere para SERIES


    private String nome;


    Categoria(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
}

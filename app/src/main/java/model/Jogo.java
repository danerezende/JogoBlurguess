package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private Categoria categoria;
    private Imagem imagem;
    private Pergunta pergunta;
    private int vidas;

    // Listas com filmes, jogos e séries
    private static final List<String[]> FILMES = new ArrayList<>();
    private static final List<String[]> JOGOS = new ArrayList<>();
    private static final List<String[]> SERIES = new ArrayList<>();

    // Populando as listas de filmes, jogos e séries
    static {
        FILMES.add(new String[]{"filme_1", "Barbie", "A boneca mais famosa do mundo."});
        FILMES.add(new String[]{"filme_2", "A Fantastica Fabrica de Chocolate", "Bilhetes dourados abrem portas mágicas."});
        FILMES.add(new String[]{"filme_3", "Batman O Cavaleiro das Trevas", "A palhaço do caos enfrenta o morcego da justiça."});
        FILMES.add(new String[]{"filme_4", "Crepusculo Amanhecer", "O clímax de uma saga entre vampiros e lobos."});
        FILMES.add(new String[]{"filme_5", "Gente Grande", "Amigos de infância se reúnem com suas famílias."});
        FILMES.add(new String[]{"filme_6", "Homem de Ferro", "O herói em armadura de ferro que começou tudo."});
        FILMES.add(new String[]{"filme_7", "Jogos Vorazes", "Arco, flecha e uma revolução em chamas."});
        FILMES.add(new String[]{"filme_8", "Poderoso Chefao", "A oferta que você não pode recusar."});
        FILMES.add(new String[]{"filme_9", "Senhor dos Aneis", "Uma jornada épica para destruir um anel."});
        FILMES.add(new String[]{"filme_10", "Valente", "Uma princesa ruiva desafiando o destino."});
        FILMES.add(new String[]{"filme_11", "Velozes e Furiosos 6", "Família, carros e ação de alta velocidade."});
        FILMES.add(new String[]{"filme_12", "Avatar", "Pandora e sua conexão com a natureza."});
        FILMES.add(new String[]{"filme_13", "Corra", "Uma visita à família que se torna um pesadelo."});
        FILMES.add(new String[]{"filme_14", "Django Livre", "Um escravo livre em busca de vingança."});
        FILMES.add(new String[]{"filme_15", "Se beber nao case", "A ressaca de uma despedida de solteiro inesquecível."});
        FILMES.add(new String[]{"filme_16", "Interestelar", "Uma odisseia espacial em busca de um novo lar."});
        FILMES.add(new String[]{"filme_17", "Jumanji", "Um jogo que literalmente ganha vida."});
        FILMES.add(new String[]{"filme_18", "Procurando Nemo", "Uma jornada no oceano para encontrar um peixinho."});
        FILMES.add(new String[]{"filme_19", "Raya", "A busca por um dragão emprestado para salvar o reino."});
        FILMES.add(new String[]{"filme_20", "Shrek Terceiro", "O ogro mais famoso em busca de um herdeiro."});
        FILMES.add(new String[]{"filme_21", "Homem Aranha 2", "O herói de Nova York enfrenta o Doutor Octopus."});
        FILMES.add(new String[]{"filme_22", "Star Wars", "A luta entre a Resistência e a Primeira Ordem continua."});
        FILMES.add(new String[]{"filme_23", "Top Gun Maverick", "O retorno de um piloto lendário aos céus."});
        FILMES.add(new String[]{"filme_24", "Transformers", "Robôs que se transformam para salvar o mundo."});
        FILMES.add(new String[]{"filme_25", "Up", "Uma casa que voa com balões e uma aventura inesquecível."});

        JOGOS.add(new String[]{"jogo_1", "FC25", "Futebol"});
        JOGOS.add(new String[]{"jogo_2", "Among Us", "Nave espacial"});
        JOGOS.add(new String[]{"jogo_3", "Dragon Ball Sparking Zero", "Kamehameha e batalhas emprestadas."});
        JOGOS.add(new String[]{"jogo_4", "Fortnite", "Construção rápida e batalhas no modo Battle Royale."});
        JOGOS.add(new String[]{"jogo_5", "Genshin Impact", "Exploração em um mundo mágico com vários elementos."});
        JOGOS.add(new String[]{"jogo_6", "Red Dead Redemption 2", "Velho Oeste e uma história de redenção."});
        JOGOS.add(new String[]{"jogo_7", "Apex Legends", "Batalhas de esquadrão em um mundo futurista."});
        JOGOS.add(new String[]{"jogo_8", "Clash of Clans ", "Construa seu vilarejo e lidere tropas na batalha."});
        JOGOS.add(new String[]{"jogo_9", "Donkey Kong", "Um gorila saltitante em busca de bananas."});
        JOGOS.add(new String[]{"jogo_10", "Elden Ring", "Exploração em um mundo sombrio e brutal."});
        JOGOS.add(new String[]{"jogo_11", "GTA V", "Três protagonistas em uma cidade cheia de crimes e possibilidades."});
        JOGOS.add(new String[]{"jogo_12", "Horizon", "Caçada a máquinas em um mundo pós-apocalíptico."});
        JOGOS.add(new String[]{"jogo_13", "League of Legends", "Batalhas estratégicas em Summoner's Rift."});
        JOGOS.add(new String[]{"jogo_14", "Minecraft", "Blocos e aventuras em um mundo infinito."});
        JOGOS.add(new String[]{"jogo_15", "Overwatch", "Heróis diversos em batalhas por objetivos."});
        JOGOS.add(new String[]{"jogo_16", "Rocket League", "Futebol com carros acrobáticos."});
        JOGOS.add(new String[]{"jogo_17", "Stardew Valley", "Uma fazenda para gerenciar e relaxar."});
        JOGOS.add(new String[]{"jogo_18", "Street Fighter", "Lutas icônicas com golpes especiais memoráveis."});
        JOGOS.add(new String[]{"jogo_19", "The Last of Us 2", "Sobrevivência e vingança em um mundo pós-apocalíptico."});
        JOGOS.add(new String[]{"jogo_20", "Valorant", "Tiroteios táticos com habilidades únicas."});
        JOGOS.add(new String[]{"jogo_21", "Hollow Knight", "Uma jornada em um mundo subterrâneo."});
        JOGOS.add(new String[]{"jogo_22", "Ghost of Tsushima", "Samurais enfrentando invasores mongóis."});

        SERIES.add(new String[]{"serie_1", "Friends", "Seis amigos vivendo as alegrias e dificuldades do cotidiano."});
        SERIES.add(new String[]{"serie_2", "13 Reasons Why", "As fitas que revelam segredos dolorosos."});
        SERIES.add(new String[]{"serie_3", "Brooklyn 99", "O dia a dia de uma delegacia de polícia bem humorada."});
        SERIES.add(new String[]{"serie_4", "Dexter", "Um serial killer que trabalha como perito forense."});
        SERIES.add(new String[]{"serie_5", "How I Met Your Mother", "Uma história contada por um pai sobre seu passado."});
        SERIES.add(new String[]{"serie_6", "Lucifer", "O diabo decide tirar férias em Los Angeles."});
        SERIES.add(new String[]{"serie_7", "Narcos", "O império do tráfico na Colômbia."});
        SERIES.add(new String[]{"serie_8", "Prison Break", "Um plano de fuga meticuloso para salvar o irmão."});
        SERIES.add(new String[]{"serie_9", "Sex and the City", "Quatro amigas explorando a vida e o amor em Nova York."});
        SERIES.add(new String[]{"serie_10", "The Umbrella Academy", "Irmãos superpoderosos reunidos para salvar o mundo."});
        SERIES.add(new String[]{"serie_11", "The Witcher", "Um caçador de monstros em um mundo mágico."});
        SERIES.add(new String[]{"serie_12", "The Office", "O cotidiano de uma empresa, com muito humor."});
        SERIES.add(new String[]{"serie_14", "Outer Banks", "Caça ao tesouro em um arquipélago repleto de mistérios."});
        SERIES.add(new String[]{"serie_15", "Dahmer", "A história perturbadora de um serial killer famoso."});
        SERIES.add(new String[]{"serie_16", "La Casa de Papel", "O maior assalto já planejado."});
        SERIES.add(new String[]{"serie_17", "Breaking Bad", "Um professor de química vira traficante para sustentar a família."});
        SERIES.add(new String[]{"serie_18", "Elite", "Mistério e drama em uma escola de elite."});
        SERIES.add(new String[]{"serie_19", "Game of Thrones", "A luta pelo trono em um mundo medieval."});
        SERIES.add(new String[]{"serie_20", "Grey's Anatomy", "Drama e romance em um hospital."});
        SERIES.add(new String[]{"serie_21", "Sex Education", "Descobertas adolescentes sobre sexualidade e vida."});
        SERIES.add(new String[]{"serie_22", "Stranger Things", "Eventos sobrenaturais e experimentos secretos nos anos 80."});
        SERIES.add(new String[]{"serie_23", "The Last of Us", "Uma jornada de sobrevivência em meio a um apocalipse zumbi."});
        SERIES.add(new String[]{"serie_24", "The Walking Dead", "Um grupo de sobreviventes em um mundo infestado por zumbis."});
        SERIES.add(new String[]{"serie_25", "Suits", "Um escritório de advocacia em Nova York onde estilo é tudo."});
        SERIES.add(new String[]{"serie_26", "The Vampire Diaries", "Dois irmãos imortais disputam o amor de uma jovem mortal."});


    }

    public Jogo(Categoria categoria) {
        this.categoria = categoria;
        this.vidas = 6; // Definindo as vidas iniciais

        // Dependendo da categoria, carregamos a imagem e a pergunta correspondente
        switch (categoria) {
            case FILMES:
                String[] filme = obterAleatorio(FILMES);
                this.imagem = new Imagem(filme[0]);
                this.pergunta = new Pergunta("Qual é o nome do filme?", filme[1], filme[2]);
                break;
            case JOGOS:
                String[] jogo = obterAleatorio(JOGOS);
                this.imagem = new Imagem(jogo[0]);
                this.pergunta = new Pergunta("Qual é o nome do jogo?", jogo[1], jogo[2]);
                break;
            case SERIES:
                String[] serie = obterAleatorio(SERIES);
                this.imagem = new Imagem(serie[0]);
                this.pergunta = new Pergunta("Qual é o nome da série?", serie[1], serie[2]);
                break;
        }
    }

    // Método para obter uma opção aleatória da lista
    private String[] obterAleatorio(List<String[]> lista) {
        Random random = new Random();
        return lista.get(random.nextInt(lista.size()));
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public int getVidas() {
        return vidas;
    }

    public void diminuirVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public boolean verificarResposta(String resposta) {
        return pergunta.verificarResposta(resposta);
    }

    public void removerBlurNaImagem() {
        imagem.removerBlur();
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}

import java.util.Scanner;

public class RestaUm {
    // Tabuleiro 7x7 (0 = vazio, 1 = peça, -1 = inválido)
    private int[][] tabuleiro = new int[7][7];
    private int pecasRestantes;

    public RestaUm() {
        inicializarTabuleiro();
        pecasRestantes = 32;
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // Definir posições válidas (em cruz)
                if ((i >= 2 && i <= 4) || (j >= 2 && j <= 4)) {
                    tabuleiro[i][j] = 1; // Peça
                } else {
                    tabuleiro[i][j] = -1; // Posição inválida
                }
            }
        }
        // Posição central vazia
        tabuleiro[3][3] = 0;
    }

    public void exibirTabuleiro() {
        System.out.println("  0 1 2 3 4 5 6");
        for (int i = 0; i < 7; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 7; j++) {
                char simbolo;
                if (tabuleiro[i][j] == 1)
                    simbolo = 'O';
                else if (tabuleiro[i][j] == 0)
                    simbolo = '.';
                else
                    simbolo = ' ';
                System.out.print(simbolo + " ");
            }
            System.out.println();
        }
    }

    public boolean moverPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // Verificar se as posições estão dentro do tabuleiro
        if (linhaOrigem < 0 || linhaOrigem > 6 || colunaOrigem < 0 || colunaOrigem > 6 ||
                linhaDestino < 0 || linhaDestino > 6 || colunaDestino < 0 || colunaDestino > 6) {
            return false;
        }

        // Verificar se é um movimento em linha reta
        boolean mesmaLinha = (linhaOrigem == linhaDestino);
        boolean mesmaColuna = (colunaOrigem == colunaDestino);

        if (!mesmaLinha && !mesmaColuna)
            return false; // Movimento diagonal

        // Verificar distância (deve ser exatamente 2 casas)
        int distancia;
        if (mesmaLinha) {
            distancia = colunaDestino - colunaOrigem;
        } else {
            distancia = linhaDestino - linhaOrigem;
        }

        if (distancia != 2 && distancia != -2)
            return false;

        // Verificar peça no meio
        int linhaMeio = (linhaOrigem + linhaDestino) / 2;
        int colunaMeio = (colunaOrigem + colunaDestino) / 2;

        // Verificar condições
        if (tabuleiro[linhaOrigem][colunaOrigem] != 1 ||
                tabuleiro[linhaMeio][colunaMeio] != 1 ||
                tabuleiro[linhaDestino][colunaDestino] != 0) {
            return false;
        }

        // Executar movimento
        tabuleiro[linhaOrigem][colunaOrigem] = 0;
        tabuleiro[linhaMeio][colunaMeio] = 0;
        tabuleiro[linhaDestino][colunaDestino] = 1;
        pecasRestantes--;

        return true;
    }

    public boolean jogoTerminado() {
        // Verificar se há movimentos possíveis
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (tabuleiro[i][j] == 1) {
                    // Verificar movimentos possíveis em todas as direções
                    if (podeMover(i, j, i, j + 2) || podeMover(i, j, i, j - 2) ||
                            podeMover(i, j, i + 2, j) || podeMover(i, j, i - 2, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean podeMover(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (linhaDestino < 0 || linhaDestino > 6 || colunaDestino < 0 || colunaDestino > 6) {
            return false;
        }

        int linhaMeio = (linhaOrigem + linhaDestino) / 2;
        int colunaMeio = (colunaOrigem + colunaDestino) / 2;

        return tabuleiro[linhaOrigem][colunaOrigem] == 1 &&
                tabuleiro[linhaMeio][colunaMeio] == 1 &&
                tabuleiro[linhaDestino][colunaDestino] == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaUm jogo = new RestaUm();

        System.out.println("=== Jogo Resta 1 (Versão Simplificada) ===");
        System.out.println("Instruções:");
        System.out.println("Digite as coordenadas no formato: linha_origem coluna_origem linha_destino coluna_destino");
        System.out.println("Exemplo: 2 4 2 2 (move a peça de (2,4) para (2,2))");
        System.out.println();

        while (!jogo.jogoTerminado()) {
            jogo.exibirTabuleiro();
            System.out.println("Peças restantes: " + jogo.pecasRestantes);
            System.out.print("Seu movimento: ");

            int linhaOrigem = scanner.nextInt();
            int colunaOrigem = scanner.nextInt();
            int linhaDestino = scanner.nextInt();
            int colunaDestino = scanner.nextInt();

            if (!jogo.moverPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                System.out.println("Movimento inválido! Tente novamente.");
            }
        }

        jogo.exibirTabuleiro();
        System.out.println("Fim do jogo! Peças restantes: " + jogo.pecasRestantes);
        if (jogo.pecasRestantes == 1 && jogo.tabuleiro[3][3] == 1) {
            System.out.println("Parabéns! Você venceu!");
        } else {
            System.out.println("Você perdeu. Tente novamente!");
        }
        scanner.close();
    }
}
package com.exemplo;

import java.io.PrintStream;

public class Jogo {
    private final Tabuleiro tab = new Tabuleiro();
    private final Jogador jogadorX;
    private final Jogador jogadorO;
    private final Historico historico;
    private char turno = 'X';

    public Jogo(Jogador jogadorX, Jogador jogadorO) {
        this(jogadorX, jogadorO, null);
    }

    public Jogo(Jogador jogadorX, Jogador jogadorO, Historico historico) {
        if (jogadorX.getSimbolo() != 'X' || jogadorO.getSimbolo() != 'O') {
            throw new IllegalArgumentException("Jogadores devem ser X e O, respectivamente");
        }
        this.jogadorX = jogadorX;
        this.jogadorO = jogadorO;
        this.historico = historico;
    }

    /**
     * Executa o jogo usando {@link System#out} para saída.
     * @return 'X' ou 'O' se houver vencedor, 'D' para empate.
     */
    public char executar() {
        return executar(System.out);
    }

    /**
     * Executa o jogo imprimindo o estado em {@code out}. Caso seja
     * passado {@code null}, não há saída alguma (útil para testes).
     */
    public char executar(PrintStream out) {
        while (true) {
            if (out != null) {
                imprimir(out);
            }
            Jogador atual = turno == 'X' ? jogadorX : jogadorO;
            int[] jogada;
            do {
                jogada = atual.escolherJogada(tab);
            } while (!tab.marcar(jogada[0], jogada[1], atual.getSimbolo()));
            if (historico != null) {
                historico.registra(atual.getSimbolo(), jogada[0], jogada[1]);
            }
            if (tab.venceu(turno)) {
                if (out != null) out.println("Vencedor: " + turno);
                return turno;
            }
            if (tab.cheio()) {
                if (out != null) out.println("Empate!");
                return 'D';
            }
            turno = (turno == 'X' ? 'O' : 'X');
        }
    }

    private void imprimir(PrintStream out) {
        if (out == null) return;
        // clear terminal (ANSI escape)
        out.print("\033[H\033[2J");
        out.flush();

        // header with column indices
        out.print("   ");
        for (int j = 0; j < tab.getSize(); j++) {
            out.print(" " + j + "  ");
        }
        out.println();

        for (int i = 0; i < tab.getSize(); i++) {
            // row index
            out.print(i + " ");
            for (int j = 0; j < tab.getSize(); j++) {
                out.print("[" + tab.obter(i, j) + "]");
                if (j < tab.getSize() - 1) out.print(" ");
            }
            out.println();
        }
    }
}

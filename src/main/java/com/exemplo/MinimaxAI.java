package com.exemplo;

import java.util.List;

/**
 * Inteligência de jogo que utiliza algoritmo minimax para escolher a melhor jogada.
 * Garante, com tabuleiro 3x3, pelo menos empate contra qualquer adversário.
 */
public class MinimaxAI implements Jogador {
    private final char simbolo;
    private final char outro;

    public MinimaxAI(char simbolo) {
        if (simbolo != 'X' && simbolo != 'O') {
            throw new IllegalArgumentException("Símbolo deve ser X ou O");
        }
        this.simbolo = simbolo;
        this.outro = (simbolo == 'X' ? 'O' : 'X');
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        // busca melhor movimento através de minimax
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        for (int[] pos : tab.vazias()) {
            Tabuleiro copia = tab.copy();
            copia.marcaForcada(pos[0], pos[1], simbolo);
            int score = minimax(copia, false);
            if (score > bestScore) {
                bestScore = score;
                bestMove = pos;
            }
        }
        // nunca deve ser nulo (há pelo menos uma casa vazia)
        return bestMove;
    }

    private int minimax(Tabuleiro tab, boolean isMaximizing) {
        if (tab.venceu(simbolo)) return 1;
        if (tab.venceu(outro)) return -1;
        if (tab.cheio()) return 0;

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] pos : tab.vazias()) {
                Tabuleiro copia = tab.copy();
                copia.marcaForcada(pos[0], pos[1], simbolo);
                int eval = minimax(copia, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] pos : tab.vazias()) {
                Tabuleiro copia = tab.copy();
                copia.marcaForcada(pos[0], pos[1], outro);
                int eval = minimax(copia, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}

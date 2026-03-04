package com.exemplo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Jogador que segue uma sequência pré‑definida de movimentos.
 * Útil para testes. Uma vez esgotadas as jogadas, retorna a última
 * fornecida (comportamento arbitrário, mas nunca usado em testes
 * bem‑formados).
 */
public class JogadorFixo implements Jogador {
    private final char simbolo;
    private final Queue<int[]> movimentos = new ArrayDeque<>();

    public JogadorFixo(char simbolo, int[]... movimentos) {
        if (simbolo != 'X' && simbolo != 'O') {
            throw new IllegalArgumentException("Símbolo deve ser X ou O");
        }
        this.simbolo = simbolo;
        for (int[] m : movimentos) {
            this.movimentos.add(m);
        }
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        int[] m = movimentos.poll();
        if (m == null) {
            // senão, retorne qualquer posição válida (primeira vazia)
            for (int i = 0; i < tab.getSize(); i++) {
                for (int j = 0; j < tab.getSize(); j++) {
                    if (tab.estaVazio(i, j)) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalStateException("Sem movimentos disponíveis");
        }
        return m;
    }
}

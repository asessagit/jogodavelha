package com.exemplo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogadorRandom implements Jogador {
    private final char simbolo;
    private final Random rand = new Random();

    public JogadorRandom(char simbolo) {
        if (simbolo != 'X' && simbolo != 'O') {
            throw new IllegalArgumentException("Símbolo deve ser X ou O");
        }
        this.simbolo = simbolo;
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        List<int[]> vagas = new ArrayList<>();
        for (int i = 0; i < tab.getSize(); i++) {
            for (int j = 0; j < tab.getSize(); j++) {
                if (tab.estaVazio(i, j)) {
                    vagas.add(new int[]{i, j});
                }
            }
        }
        if (vagas.isEmpty()) {
            throw new IllegalStateException("Não há jogadas possíveis");
        }
        return vagas.get(rand.nextInt(vagas.size()));
    }
}

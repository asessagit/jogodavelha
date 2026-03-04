package com.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimaxAITest {

    @Test
    void escolheVitoriaQuandoDisponivel() {
        Tabuleiro tab = new Tabuleiro();
        // X tem duas em linha horizontais na primeira linha
        tab.marcaForcada(0, 0, 'X');
        tab.marcaForcada(0, 1, 'X');
        tab.marcaForcada(1, 0, 'O');
        MinimaxAI ai = new MinimaxAI('X');
        int[] move = ai.escolherJogada(tab);
        assertArrayEquals(new int[]{0, 2}, move);
    }

    @Test
    void bloqueiaVitoriaAdversaria() {
        Tabuleiro tab = new Tabuleiro();
        // X prestes a ganhar na segunda coluna
        tab.marcaForcada(0, 1, 'X');
        tab.marcaForcada(1, 1, 'X');
        tab.marcaForcada(0, 0, 'O');
        MinimaxAI ai = new MinimaxAI('O');
        int[] move = ai.escolherJogada(tab);
        assertArrayEquals(new int[]{2, 1}, move);
    }

    @Test
    void naoPerdeContraRandom() {
        // joga 30 partidas aleatórias e garante que o Minimax nunca perde
        MinimaxAI aiX = new MinimaxAI('X');
        MinimaxAI aiO = new MinimaxAI('O');
        JogadorRandom randX = new JogadorRandom('X');
        JogadorRandom randO = new JogadorRandom('O');
        for (int i = 0; i < 30; i++) {
            // X é minimax, O aleatório
            Jogo jogo1 = new Jogo(aiX, randO);
            char res1 = jogo1.executar(null);
            assertTrue(res1 == 'X' || res1 == 'D');
            // X aleatório, O minimax
            Jogo jogo2 = new Jogo(randX, aiO);
            char res2 = jogo2.executar(null);
            assertTrue(res2 == 'O' || res2 == 'D');
        }
    }
}

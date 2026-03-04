package com.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {
    private Tabuleiro tabuleiro;

    @BeforeEach
    void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test
    @DisplayName("Tabuleiro recém-criado está vazio")
    void inicialmenteEstaVazio() {
        for (int i = 0; i < tabuleiro.getSize(); i++) {
            for (int j = 0; j < tabuleiro.getSize(); j++) {
                assertTrue(tabuleiro.estaVazio(i, j), "posição " + i + "," + j + " deveria estar vazia");
            }
        }
    }

    @Test
    @DisplayName("Marcar posição válida coloca símbolo e retorna true")
    void marcarPosicaoValida() {
        boolean resultado = tabuleiro.marcar(1, 2, 'X');
        assertTrue(resultado);
        assertEquals('X', tabuleiro.obter(1, 2));
    }

    @Test
    @DisplayName("Não é possível marcar fora do tabuleiro")
    void marcarForaDoTabuleiro() {
        assertFalse(tabuleiro.marcar(-1, 0, 'O'));
        assertFalse(tabuleiro.marcar(0, 3, 'O'));
    }

    @Test
    @DisplayName("Não é possível marcar posição já ocupada")
    void marcarPosicaoOcupada() {
        assertTrue(tabuleiro.marcar(0, 0, 'X'));
        assertFalse(tabuleiro.marcar(0, 0, 'O')); // já havia X
    }

    @Test
    @DisplayName("Obter posição inválida lança exceção")
    void obterInvalidaLancaExcecao() {
        assertThrows(IndexOutOfBoundsException.class, () -> tabuleiro.obter(5, 5));
    }

    @Test
    @DisplayName("Detecta vitória por linha, coluna e diagonal")
    void detectaVitoria() {
        // linha
        Tabuleiro t1 = new Tabuleiro();
        t1.marcar(0, 0, 'X');
        t1.marcar(0, 1, 'X');
        t1.marcar(0, 2, 'X');
        assertTrue(t1.venceu('X'));

        // coluna
        Tabuleiro t2 = new Tabuleiro();
        t2.marcar(0, 1, 'O');
        t2.marcar(1, 1, 'O');
        t2.marcar(2, 1, 'O');
        assertTrue(t2.venceu('O'));

        // diagonal
        Tabuleiro t3 = new Tabuleiro();
        t3.marcar(0, 0, 'X');
        t3.marcar(1, 1, 'X');
        t3.marcar(2, 2, 'X');
        assertTrue(t3.venceu('X'));
    }

    @Test
    @DisplayName("Detecta empate quando cheio e sem vencedor")
    void detectaEmpateSecheio() {
        Tabuleiro t = new Tabuleiro();
        char[] moves = {'X','O','X','X','O','O','O','X','X'};
        int idx=0;
        for (int i = 0; i < t.getSize(); i++) {
            for (int j = 0; j < t.getSize(); j++) {
                t.marcar(i, j, moves[idx++]);
            }
        }
        assertTrue(t.cheio());
        assertFalse(t.venceu('X'));
        assertFalse(t.venceu('O'));
    }
}

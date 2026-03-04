package com.exemplo;

public class Tabuleiro {
    private final char[][] grid;
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';

    public Tabuleiro() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }

    /**
     * Tenta marcar a posição (linha, coluna) com o símbolo fornecido.
     *
     * @param linha  índice da linha (0 a 2)
     * @param coluna índice da coluna (0 a 2)
     * @param simbolo caractere a colocar (tipicamente 'X' ou 'O')
     * @return true se a jogada foi válida e aplicada; false caso contrário
     */
    public boolean marcar(int linha, int coluna, char simbolo) {
        if (!posicaoValida(linha, coluna)) {
            return false;
        }
        if (grid[linha][coluna] != EMPTY) {
            return false;
        }
        grid[linha][coluna] = simbolo;
        return true;
    }

    private boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < SIZE && coluna >= 0 && coluna < SIZE;
    }

    public char obter(int linha, int coluna) {
        if (!posicaoValida(linha, coluna)) {
            throw new IndexOutOfBoundsException("Posicao fora do tabuleiro");
        }
        return grid[linha][coluna];
    }

    public boolean estaVazio(int linha, int coluna) {
        return obter(linha, coluna) == EMPTY;
    }

    public int getSize() {
        return SIZE;
    }

    /**
     * Verifica se o tabuleiro está completamente preenchido (empate potencial).
     */
    public boolean cheio() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determina se um determinado símbolo venceu o jogo.
     */
    public boolean venceu(char simbolo) {
        // verificar linhas
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == simbolo && grid[i][1] == simbolo && grid[i][2] == simbolo) {
                return true;
            }
        }
        // verificar colunas
        for (int j = 0; j < SIZE; j++) {
            if (grid[0][j] == simbolo && grid[1][j] == simbolo && grid[2][j] == simbolo) {
                return true;
            }
        }
        // diagonais
        if (grid[0][0] == simbolo && grid[1][1] == simbolo && grid[2][2] == simbolo) {
            return true;
        }
        if (grid[0][2] == simbolo && grid[1][1] == simbolo && grid[2][0] == simbolo) {
            return true;
        }
        return false;
    }

    /**
     * Retorna uma cópia profunda do tabuleiro.
     */
    public Tabuleiro copy() {
        Tabuleiro t = new Tabuleiro();
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(this.grid[i], 0, t.grid[i], 0, SIZE);
        }
        return t;
    }

    /**
     * Marca uma posição diretamente sem validações (para simulações).
     */
    public void marcaForcada(int linha, int coluna, char simbolo) {
        grid[linha][coluna] = simbolo;
    }

    /**
     * Lista de posições vazias como pares [linha,coluna].
     */
    public java.util.List<int[]> vazias() {
        java.util.List<int[]> res = new java.util.ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY) res.add(new int[]{i, j});
            }
        }
        return res;
    }
}

package com.exemplo;

public interface Jogador {
    /**
     * Retorna o símbolo que este jogador usa ('X' ou 'O').
     */
    char getSimbolo();

    /**
     * Escolhe uma jogada válida no tabuleiro fornecido.
     * Deve retornar um array de dois inteiros: {linha, coluna}.
     * O Jogo poderá reusar a jogada caso ela não seja aplicada com sucesso.
     */
    int[] escolherJogada(Tabuleiro tab);
}

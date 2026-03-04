package com.exemplo;

import java.io.PrintWriter;

/**
 * Decorator que delega para outro Jogador e envia a jogada pelo stream.
 */
public class JogadorEnvio implements Jogador {
    private final Jogador delegate;
    private final PrintWriter writer;

    public JogadorEnvio(Jogador delegate, PrintWriter writer) {
        this.delegate = delegate;
        this.writer = writer;
    }

    @Override
    public char getSimbolo() {
        return delegate.getSimbolo();
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        int[] move = delegate.escolherJogada(tab);
        writer.println(move[0] + "," + move[1]);
        writer.flush();
        return move;
    }
}

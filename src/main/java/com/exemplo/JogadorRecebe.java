package com.exemplo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Jogador que recebe movimentos de um socket/stream.
 */
public class JogadorRecebe implements Jogador {
    private final BufferedReader reader;
    private final char simbolo;

    public JogadorRecebe(BufferedReader reader, char simbolo) {
        this.reader = reader;
        this.simbolo = simbolo;
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        try {
            String line;
            do {
                line = reader.readLine();
                if (line == null) {
                    throw new IOException("Conexão encerrada");
                }
            } while (line.trim().isEmpty());
            String[] parts = line.trim().split(",");
            return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo jogada remota", e);
        }
    }
}

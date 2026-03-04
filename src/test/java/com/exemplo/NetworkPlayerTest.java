package com.exemplo;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class NetworkPlayerTest {
    @Test
    void envioERecepcaoSimples() throws Exception {
        // cria pipe em memória
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pis));
        PrintWriter writer = new PrintWriter(pos, true);

        // jogador fixo 2 movimentos
        Jogador fixo = new JogadorFixo('X', new int[]{0,0}, new int[]{1,1});
        Jogador envio = new JogadorEnvio(fixo, writer);
        Jogador recebe = new JogadorRecebe(reader, 'X');

        Tabuleiro tab = new Tabuleiro();
        int[] m1 = envio.escolherJogada(tab);
        int[] r1 = recebe.escolherJogada(tab);
        assertArrayEquals(m1, r1);

        int[] m2 = envio.escolherJogada(tab);
        int[] r2 = recebe.escolherJogada(tab);
        assertArrayEquals(m2, r2);
    }
}

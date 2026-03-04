package com.exemplo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JogoTest {

    @Test
    void sequenciaXvence() {
        Jogador x = new JogadorFixo('X', new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2});
        Jogador o = new JogadorFixo('O', new int[]{1, 0}, new int[]{1, 1});
        Jogo jogo = new Jogo(x, o);
        char vencedor = jogo.executar(new PrintStream(new ByteArrayOutputStream()));
        assertEquals('X', vencedor);
    }

    @Test
    void sequenciaOvence() {
        Jogador x = new JogadorFixo('X', new int[]{0, 0}, new int[]{1, 0});
        Jogador o = new JogadorFixo('O', new int[]{0, 1}, new int[]{1, 1}, new int[]{2, 1});
        Jogo jogo = new Jogo(x, o);
        char vencedor = jogo.executar(new PrintStream(new ByteArrayOutputStream()));
        assertEquals('O', vencedor);
    }

    @Test
    void empateCasoCheio() {
        // sequência retirada de busca para garantir empate
        Jogador x = new JogadorFixo('X',
                new int[]{0,0}, //1
                new int[]{0,2}, //3
                new int[]{1,1}, //5
                new int[]{1,2}, //7
                new int[]{2,1}  //9
        );
        Jogador o = new JogadorFixo('O',
                new int[]{0,1}, //2
                new int[]{1,0}, //4
                new int[]{2,0}, //6
                new int[]{2,2}  //8
        );
        Jogo jogo = new Jogo(x, o);
        char resultado = jogo.executar(new PrintStream(new ByteArrayOutputStream()));
        assertEquals('D', resultado);
    }

    @Test
    void construtorVerificaSimbolos() {
        Jogador inválido = new JogadorFixo('X', new int[]{0,0});
        Jogador outro = new JogadorFixo('X', new int[]{0,1});
        assertThrows(IllegalArgumentException.class, () -> new Jogo(inválido, outro));
    }
}

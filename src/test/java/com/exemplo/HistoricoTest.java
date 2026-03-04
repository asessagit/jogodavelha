package com.exemplo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoTest {
    @Test
    void salvarECarregar() throws IOException {
        Historico h = new Historico();
        h.registra('X', 0, 0);
        h.registra('O', 1, 1);
        Path temp = Files.createTempFile("hist", ".txt");
        h.salvar(temp);
        Historico lido = Historico.carregar(temp);
        assertEquals(h.getMovimentos(), lido.getMovimentos());
        Files.deleteIfExists(temp);
    }

    @Test
    void replayConstróiTabuleiro() {
        Historico h = new Historico();
        h.registra('X', 0, 0);
        h.registra('O', 1, 2);
        h.registra('X', 2, 2);
        Tabuleiro t = h.replay();
        assertEquals('X', t.obter(0, 0));
        assertEquals('O', t.obter(1, 2));
        assertEquals('X', t.obter(2, 2));
    }

    @Test
    void jogoComHistoricoAcumulaMovimentos() {
        Jogador x = new JogadorFixo('X', new int[]{0,0});
        Jogador o = new JogadorFixo('O', new int[]{1,1});
        Historico h = new Historico();
        Jogo jogo = new Jogo(x, o, h);
        char res = jogo.executar(null);
        assertTrue(h.getMovimentos().size() >= 2, "pelo menos duas jogadas devem ser gravadas");
        // first move deve ser a jogada X 0 0
        assertTrue(h.getMovimentos().get(0).startsWith("X 0 0"));
    }
}

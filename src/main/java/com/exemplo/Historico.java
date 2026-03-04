package com.exemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registra a sequência de jogadas de uma partida e permite salvar/carregar.
 */
public class Historico {
    private final List<String> movs = new ArrayList<>();

    public void registra(char simbolo, int linha, int coluna) {
        movs.add(simbolo + " " + linha + " " + coluna);
    }

    public List<String> getMovimentos() {
        return Collections.unmodifiableList(movs);
    }

    public void salvar(Path arquivo) throws IOException {
        Files.write(arquivo, movs);
    }

    public static Historico carregar(Path arquivo) throws IOException {
        List<String> linhas = Files.readAllLines(arquivo);
        Historico h = new Historico();
        h.movs.addAll(linhas);
        return h;
    }

    /**
     * Aplica os movimentos registrados em um tabuleiro vazio e retorna-o.
     */
    public Tabuleiro replay() {
        Tabuleiro t = new Tabuleiro();
        for (String linha : movs) {
            String[] partes = linha.split(" ");
            char s = partes[0].charAt(0);
            int l = Integer.parseInt(partes[1]);
            int c = Integer.parseInt(partes[2]);
            t.marcaForcada(l, c, s);
        }
        return t;
    }
}

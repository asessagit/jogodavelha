package com.exemplo;

import java.util.Scanner;

public class JogadorHumano implements Jogador {
    private final char simbolo;
    private final Scanner scanner;

    public JogadorHumano(char simbolo, Scanner scanner) {
        if (simbolo != 'X' && simbolo != 'O') {
            throw new IllegalArgumentException("Símbolo deve ser X ou O");
        }
        this.simbolo = simbolo;
        this.scanner = scanner;
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int[] escolherJogada(Tabuleiro tab) {
        // lê até obter dois inteiros
        while (true) {
            System.out.print("Jogador " + simbolo + ", informe linha e coluna (0-2) separadas por espaço: ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Formato inválido; informe dois números separados por espaço.");
                continue;
            }
            try {
                int l = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                return new int[]{l, c};
            } catch (NumberFormatException nfe) {
                System.out.println("Entrada não numérica; tente novamente.");
            }
        }
    }
}

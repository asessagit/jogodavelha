package com.exemplo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem‑vindo ao Jogo da Velha");
        System.out.println("1: Humano X vs Humano O");
        System.out.println("2: Humano X vs Random O");
        System.out.println("3: Humano X vs Minimax O");
        System.out.println("4: Random X vs Minimax O");
        System.out.println("5: Minimax X vs Minimax O");
        System.out.print("Escolha uma opção [1-5]: ");
        int opc = scanner.nextInt();
        scanner.nextLine(); // consume rest of line
        Jogador x, o;
        switch (opc) {
            case 1 -> {
                x = new JogadorHumano('X', scanner);
                o = new JogadorHumano('O', scanner);
            }
            case 2 -> {
                x = new JogadorHumano('X', scanner);
                o = new JogadorRandom('O');
            }
            case 3 -> {
                x = new JogadorHumano('X', scanner);
                o = new MinimaxAI('O');
            }
            case 4 -> {
                x = new JogadorRandom('X');
                o = new MinimaxAI('O');
            }
            case 5 -> {
                x = new MinimaxAI('X');
                o = new MinimaxAI('O');
            }
            default -> {
                System.out.println("Opção inválida, usando humano contra humano");
                x = new JogadorHumano('X', scanner);
                o = new JogadorHumano('O', scanner);
            }
        }
        Historico historico = new Historico();
        Jogo jogo = new Jogo(x, o, historico);
        char resultado = jogo.executar();
        switch (resultado) {
            case 'X' -> System.out.println("X venceu!");
            case 'O' -> System.out.println("O venceu!");
            case 'D' -> System.out.println("Empate!");
        }
        System.out.print("Deseja salvar o histórico das jogadas? (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        if (resp.equals("s") || resp.equals("y")) {
            System.out.print("Informe o caminho do arquivo: ");
            String path = scanner.nextLine().trim();
            try {
                historico.salvar(java.nio.file.Path.of(path));
                System.out.println("Histórico salvo em " + path);
            } catch (Exception e) {
                System.out.println("Falha ao salvar histórico: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

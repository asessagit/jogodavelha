package com.exemplo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetworkClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: NetworkClient <host> [port]");
            System.exit(1);
        }
        String host = args[0];
        int port = 5000;
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }
        System.out.println("Conectando a " + host + ":" + port);
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado ao servidor.");
            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Jogador oLocal = new JogadorHumano('O', scanner);
            Jogador oEnvio = new JogadorEnvio(oLocal, pw);
            Jogador xRemoto = new JogadorRecebe(br, 'X');

            Jogo jogo = new Jogo(xRemoto, oEnvio);
            char resultado = jogo.executar();
            System.out.println("Resultado: " + resultado);
            scanner.close();
        }
    }
}

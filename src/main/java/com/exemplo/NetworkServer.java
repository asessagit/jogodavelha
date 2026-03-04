package com.exemplo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkServer {
    public static void main(String[] args) throws Exception {
        int port = 5000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Servidor esperando conexão na porta " + port);
        try (ServerSocket server = new ServerSocket(port);
             Socket client = server.accept()) {
            System.out.println("Cliente conectado: " + client.getRemoteSocketAddress());

            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            Jogador xLocal = new JogadorHumano('X', scanner);
            Jogador xEnvio = new JogadorEnvio(xLocal, pw);
            Jogador oRemoto = new JogadorRecebe(br, 'O');

            Jogo jogo = new Jogo(xEnvio, oRemoto);
            char resultado = jogo.executar();
            System.out.println("Resultado: " + resultado);
            scanner.close();
        }
    }
}

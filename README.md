# Jogo da Velha (Tic-Tac-Toe)

Projeto Java (Maven) que implementa um jogo da velha completo com:

- Lógica de jogo encapsulada em classes reutilizáveis (`Tabuleiro`, `Jogo`, `Jogador` etc.).
- Vários tipos de jogadores: humano, aleatório e IA (Minimax).
- Interface de console aprimorada com índice de coordenadas e limpeza de tela.
- Histórico de partidas (salvar/carregar) e suporte a *replay*.
- Modo de rede usando sockets (server/client) para jogar contra outro computador.

## Requisitos

- Java 17+ (JDK)
- Maven 3.x

## Como compilar e executar

Use o script `run.sh` ou execute os comandos manualmente.

### script

```bash
chmod +x run.sh            # uma vez somente
./run.sh                    # menu interativo
```

Você também pode rodar diretamente com Maven:

```bash
mvn clean package           # compila e empacota em jar com dependências
mvn exec:java -Dexec.mainClass=com.exemplo.App
```

ou usar o jar gerado:

```bash
java -jar target/jogodavelha-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### opções de rede

O script `run.sh` também pode iniciar o jogo em modo servidor ou cliente. Isso permite que dois computadores se conectem e joguem entre si.

```bash
# iniciar servidor na porta 5000 (padrão) ou outra especificada:
./run.sh server [porta]

# iniciar cliente apontando para o endereço do servidor:
./run.sh client <host> [porta]
```

Depois de estabelecer a conexão, o jogador local se comporta como humano e as jogadas são automaticamente enviadas/recebidas através do socket. É uma forma simples de testar partidas remotas sem implementar protocolo complexo.

## Estrutura do projeto

Veja o diretório `src/main/java/com/exemplo` para as classes de implementação e `src/test/java/com/exemplo` para os testes.

## Testes

Execute `mvn test` para rodar o conjunto completo de testes (incluindo IA e componentes de rede).

## Salvando histórico

Ao término do jogo o programa pergunta se deseja salvar o histórico em arquivo. Informe um caminho válido para gravar.

## Contribuindo

Basta clonar este repositório, fazer alterações e enviar pull request. O código está escrito com tipagem estática e segue boas práticas de nomenclatura e tratamento de erros.

## Licença

Projeto de exemplo para fins educacionais. Use à vontade.

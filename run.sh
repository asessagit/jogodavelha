#!/usr/bin/env bash
sudo chown -R $USER:$USER "$(pwd)" 2>/dev/null
# simple wrapper for building and running the tic-tac-toe jar

set -euo pipefail

# build the project and create a fat jar (quiet mode so output doesn’t mix with game input)
mvn -q clean package

JAR=target/jogodavelha-1.0-SNAPSHOT-jar-with-dependencies.jar

# if first arg is server or client, delegate
case "${1:-}" in
    server)
        shift
        java -cp "$JAR" com.exemplo.NetworkServer "$@"
        ;;
    client)
        shift
        java -cp "$JAR" com.exemplo.NetworkClient "$@"
        ;;
    *)
        java -jar "$JAR"
        ;;
esac

package pl.poznan.jagent.registry.socket;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Sender {

    private final Socket socket;
    private final PrintWriter out;

    Sender(String address, int port) throws IOException {
        socket = new Socket(address, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    void send(String msg) {
        out.println(msg);
    }

    void close() {
        out.close();
    }
}

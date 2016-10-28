package pl.poznan.jagent.registry.socket;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class Sender {

    private final Socket socket;
    private final DataOutputStream out;

    Sender(String address, int port) throws IOException {
        socket = new Socket(address, port);
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    void send(String msg) throws IOException {
        out.writeBytes(msg + "\n");
        out.flush();
    }

    void close() throws IOException {
        socket.close();
    }
}

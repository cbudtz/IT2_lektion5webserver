import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerSingleThread {
    public static void main(String[] args) {
        try {
            System.out.println("Staring web server!");
            ServerSocket serverSocket = new ServerSocket(80);
            while(true) {
                Socket accept = serverSocket.accept();
                System.out.println("Connection established");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(accept.getInputStream()));
                String anmodning = in.readLine();
                OutputStream outputStream = accept.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println("HTTP/0.9 200 OK");
                printWriter.println();
                printWriter.println("<html><body>Tæller til 10: ");
                for (int i = 0; i < 10; i++) {
                    printWriter.println("" + i + "<BR>");
                    printWriter.flush();
                    Thread.sleep(1000);
                }
                accept.close();
                System.out.println("Connection closed!");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

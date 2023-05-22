import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("localhost", 2000);
        // get current time
        long currentTime = System.currentTimeMillis();
        // send time request to server
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
        out.println(currentTime);
        out.flush(); // Flush the PrintWriter to send the data immediately
        // receive current time from server
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String response = in.readLine();
        long serverTime = 0;
        if (response != null && !response.isEmpty()) {
            try {
                serverTime = Long.parseLong(response);
            } catch (NumberFormatException e) {
                System.err.println("Invalid response from the server: " + response);
                e.printStackTrace();
                server.close();
                return;
            }
        } else {
            System.err.println("Empty or null response from the server");
            server.close();
            return;
        }
        // calculate clock difference
        long clockDifference = serverTime - currentTime;
        // send clock difference to server
        PrintWriter serverOut = new PrintWriter(server.getOutputStream(), true);
        serverOut.println(clockDifference);
        serverOut.flush(); // Flush the PrintWriter to send the data immediately
        // receive average clock difference from server
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String averageClockDifference = serverIn.readLine();
        long averageDifference = 0;
        if (averageClockDifference != null && !averageClockDifference.isEmpty()) {
            try {
                averageDifference = Long.parseLong(averageClockDifference);
            } catch (NumberFormatException e) {
                System.err.println("Invalid average clock difference from the server: " + averageClockDifference);
                e.printStackTrace();
                server.close();
                return;
            }
        } else {
            System.err.println("Empty or null average clock difference from the server");
            server.close();
            return;
        }
        // adjust client clock
        long adjustedTime = serverTime + averageDifference;
        System.out.println("Client adjusted time: " + adjustedTime);
        // close sockets and streams
        serverOut.close();
        serverIn.close();
        in.close();
        out.close();
        server.close();
    }
}

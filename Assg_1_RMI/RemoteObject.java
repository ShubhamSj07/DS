import java.rmi.RemoteException;

public class RemoteObject implements RemoteInterface {
    @Override
    public String processRequest(String request) throws RemoteException {
        // Process the request on the server
        System.out.println("Server received request: " + request);
        String response = "Hello, " + request + "! This is the server response.";
        return response;
    }
}

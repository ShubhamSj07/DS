import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements RemoteInterface {
    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            RemoteInterface remoteObj = new RemoteObject();

            // Export the remote object to make it available for remote invocation
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(remoteObj, 0);

            // Create a registry on the server to bind the remote object's stub
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RemoteObject", stub);

            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String processRequest(String request) throws RemoteException {
        // Process the request on the server
        System.out.println("Server received request: " + request);
        String response = "Hello, " + request + "! This is the server response.";
        return response;
    }
}

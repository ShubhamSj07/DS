import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Locate the registry on the server
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object from the registry
            RemoteInterface remoteObj = (RemoteInterface) registry.lookup("RemoteObject");

            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            // Make remote method invocation
            String response = remoteObj.processRequest(name);

            System.out.println("Server response: " + response);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}

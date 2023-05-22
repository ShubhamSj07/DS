# Understanding the Code

The code consists of the following files:

- `RemoteInterface.java`: This interface defines the remote methods that can be invoked by the client.

- `RMIServer.java`: This class represents the server. It implements the RemoteInterface and provides an implementation for the processRequest method. The server exports the remote object and binds it to the RMI registry.

- `RMIClient.java`: This class represents the client. It connects to the RMI registry, looks up the remote object, prompts the user to enter their name, and sends the request to the server.

# How It Works

- The server starts and exports the remote object, making it available for remote invocation.

- The client connects to the RMI registry and looks up the remote object.

- The client prompts the user to enter their name.

- The client sends the name as a request to the server by invoking the processRequest method.

- The server receives the request, processes it by appending a personalized message to the client's name, and returns the response.

- The client displays the response received from the server.

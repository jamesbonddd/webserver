package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private int port;
	private int threadPoolMax = 5;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() throws IOException {
		// Init
		final ExecutorService executor = Executors.newFixedThreadPool(threadPoolMax);
		final ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("listening on port " + port + " ...");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			// This code will run when exiting server (through user interruption (^C))
		    public void run() { 
		    	try {
					serverSocket.close();
					executor.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		 });
		
		// Server loop
		while(true){
			Socket socket = serverSocket.accept();
			Callable<Void> callable = new ClientHandler(socket);
			executor.submit(callable);		
		}	
	}
}


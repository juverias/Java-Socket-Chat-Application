package server;

public class ServerMain {

	public static void main(String[] args) {

        Server s = new Server(); // Will invoke GUI Part 
        s.waitingForClient(); // will wait for the cilent
        s.setIOStreams(); //It will set the streams through which we will transfer data 
        
	}

}

package telnet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket socket;
	private OutputStream out;
	private InputStream in;
	private byte[] buffer;
	private PrintWriter pw;
	
	public Client(String address, int portNumber) throws UnknownHostException, IOException{
		socket = new Socket(address, portNumber);
		out = socket.getOutputStream();
		buffer = new byte[1024];
		pw = new PrintWriter(out, true);
	}
	
	public void sendFileToServer(File file) throws FileNotFoundException, IOException{
		in = new FileInputStream(file);
		
		pw.println("file");

		int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        
        in.close();
	}
	
	public void exit() throws IOException{
		out.close();
		socket.close();
	}
}

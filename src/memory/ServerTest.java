package memory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(12345);

		while (true) {
			Socket client = server.accept();
//fdgdf
			Scanner scanner = new Scanner(client.getInputStream());

			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
			System.out.println("thsd");
			scanner.close();
			server.close();
		}

	}
}
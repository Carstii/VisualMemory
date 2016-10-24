package memory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(12345);

		while (true) {
			Socket client = server.accept();

			Scanner scanner = new Scanner(client.getInputStream());

			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}

			scanner.close();
			server.close();
		}

	}
}
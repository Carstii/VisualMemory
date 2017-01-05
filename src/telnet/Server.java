package telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private TelnetShell ts;
	private Socket socket;
	private ServerSocket so;

	public Server(TelnetShell ts, int port) {

		this.ts = ts;
		try {
			so = new ServerSocket(port);
			socket = so.accept();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {

			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				String eingabe = br.readLine();

				if (eingabe == null) {

					System.out.println("Terminal geschlossen, warte auf neue Verbindung...");
					
					Thread.sleep(500);

					socket = so.accept();

					pw = new PrintWriter(socket.getOutputStream(), true);
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					continue;

				}

				String input[] = eingabe.split(" ");

				for (int i = 0; i < input.length; i++) {
					System.out.print(input[i] + " ");
				}

				System.out.println();

				switch (input[0]) {

				// schreibt ein Byte auf das Memory. Syntax: push [index] [byte]
				// [index] ist ein dezimaler int-Wert und bestimmt die Stelle im
				// Memory z.B: 20000
				// [byte] ist ein Hex-Wert, z.B: 6f (ohne vorangestelltes "0x")
				case "push":

					try {

						ts.writeByte(Integer.parseInt(input[1]), Integer.parseInt(input[2], 16));

					} catch (ArrayIndexOutOfBoundsException e) {

						e.printStackTrace();

					}

					break;

				// liest ein Byte aus dem Memory. Syntax: pull [index]
				// [index] ist ein dezimaler int-Wert und bestimmt die Stelle im
				// Memory z.B: 20000

				case "pull":

					pw.println(ts.readByte(Integer.parseInt(input[1])));

					break;

				// getSize gibt die Größe des Memorys in Byte zurück
				case "getSize":

					pw.println(ts.getSize());

					break;

				// switch 1 wechselt zur Pixelansicht
				// switch 2 wechselt zur Heatmap
				// switch 3 wechselt zur Graustufenansicht
				case "switch":

					ts.switchServerView(Integer.parseInt(input[1]));

					break;

				// shutdown beendet das Server-Programm
				case "shutdown":
					System.exit(0);

				default:
					pw.println("Dieser Befehl existiert nicht");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

import view.ModalException;

public class LoginInputThread implements Runnable {
	final Socket client;
	final String login;
	final String pass;

	public LoginInputThread(Socket client, String login, String pass) {
		this.client = client;
		this.login = login;
		this.pass = pass;
	}

	@Override
	public void run() {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;
		JSONObject obj;

		try {
			// Open the output stream of the client socket.
			out = client.getOutputStream();
			osw = new OutputStreamWriter(out);
			pw = new PrintWriter(osw);
			obj = new JSONObject();
			obj.put("login", login);
			obj.put("password", pass);
			obj.put("instruction", "connect");
			final String msg = obj.toString();
			// Print and flush the msg in the pipeline
			pw.println(msg);
			pw.flush();

		} catch (IllegalStateException e) {
			ModalException illegalStateException = new ModalException("Erreur lors de l'initialisation du socket : "+ e);
		} catch (IOException e) {
			ModalException iOException = new ModalException("Erreur lors de la récupération des requêtes serveur : "+ e);
		} 
	}
}

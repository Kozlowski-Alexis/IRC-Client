package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import view.ModalException;
import view.TchatIndex;

public class CanalRegistrationInputThread implements Runnable {
	private final Socket client;
	private final String login;
	private final String pass;
	private final String oldChannel;
	private final String newChannel;
	private final TchatIndex tchat;

	public CanalRegistrationInputThread(Socket client, String login, String pass, String oldChannel, String newChannel, TchatIndex tchat) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.oldChannel = oldChannel;
		this.newChannel = newChannel;
		this.tchat = tchat;
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
			obj.put("instruction", "subscribe_channel");
			obj.put("channel", oldChannel);
			obj.put("target_channel", newChannel);
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

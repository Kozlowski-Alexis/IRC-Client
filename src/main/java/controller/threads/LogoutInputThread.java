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

public class LogoutInputThread implements Runnable {
	final Socket client;
	final String login;
	final String pass;

	public LogoutInputThread(Socket client, String login, String pass) {
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
			obj.put("instruction", "disconnect");
			final String msg = obj.toString();
			// Print and flush the msg in the pipeline
			pw.println(msg);
			pw.flush();

		} catch (IllegalStateException e) {
			ModalException errorSocket = new ModalException("Erreur socket"+e);
		} catch (IOException e) {
			ModalException errorSocketOutput = new ModalException("Erreur socket ouptuStream"+e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(osw != null) {
					osw.close();
				}
				if(pw != null) {
					pw.close();
				}
			} catch (IOException e) {
				ModalException closeError = new ModalException("Erreur lors de la fermeture de la connexion");
			}
		}

	}

}

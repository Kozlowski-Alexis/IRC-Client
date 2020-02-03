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

public class SendMessageThread implements Runnable {
	final Socket client;
	final String login;
	final String pass;
	final String channel;
	final String message;

	public SendMessageThread(Socket client, String login, String pass, String channel, String message) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.channel = channel;
		this.message = message;
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
			obj.put("channel", channel);
			obj.put("instruction", "send_message");
			obj.put("message", message);
			final String msg = obj.toString();
			// Print and flush the msg in the pipeline
			pw.println(msg);
			pw.flush();

		} catch (IllegalStateException e) {
			ModalException errorSocketInit = new ModalException("Erreur initialisation Socket : "+e);
		} catch (IOException e) {
			ModalException errorGetSocketOutputStream = new ModalException("Erreur pendant le stream de la socket : "+e);
		} 
	}
}

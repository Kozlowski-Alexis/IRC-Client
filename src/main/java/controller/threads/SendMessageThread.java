package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;
import view.ModalException;
import view.TchatIndex;

public class SendMessageThread implements Runnable {
	private final Socket client;
	private final String login;
	private final String pass;
	private final String channel;
	private final String message;
	private final TchatIndex tchat;

	public SendMessageThread(Socket client, String login, String pass, String channel, String message, TchatIndex tchat) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.channel = channel;
		this.message = message;
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
			obj.put("channel", channel);
			obj.put("instruction", "send_message");
			obj.put("message", message);
			final String msg = obj.toString();
			// Print and flush the msg in the pipeline
			pw.println(msg);
			pw.flush();
//			client.getInputStream().read();
			
		} catch (IllegalStateException e) {
			ModalException errorSocketInit = new ModalException("Erreur initialisation Socket : "+e);
		} catch (IOException e) {
			ModalException errorGetSocketOutputStream = new ModalException("Erreur pendant le stream de la socket : "+e);
		} 
	}
}

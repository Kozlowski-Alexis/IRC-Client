package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class MembersListInputThread implements Runnable {
	private static final Logger LOG = Logger.getLogger(MembersListInputThread.class.getName());
	final Socket client;
	final String login;
	final String pass;
	final String channel;

	public MembersListInputThread(Socket client, String login, String pass, String channel) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.channel = channel;
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
			obj.put("instruction", "list_channel_members");
			final String msg = obj.toString();
			// Print and flush the msg in the pipeline
			pw.println(msg);
			pw.flush();

		} catch (IllegalStateException e) {
			LOG.error("Error socket init.", e);
		} catch (IOException e) {
			LOG.error("Error during getting socket outputstream.", e);
		} 
	}

}

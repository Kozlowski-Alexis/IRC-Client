package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class CanalRegistrationInputThread implements Runnable {
	private static final Logger LOG = Logger.getLogger(CanalRegistrationInputThread.class.getName());
	final Socket client;
	final String login;
	final String pass;
	final String oldChannel;
	final String newChannel;

	public CanalRegistrationInputThread(Socket client, String login, String pass, String oldChannel, String newChannel) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.oldChannel = oldChannel;
		this.newChannel = newChannel;
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
			System.out.println(msg);
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

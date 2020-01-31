package controller.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class AppClientInputThread implements Runnable {
	private static final Logger LOG = Logger.getLogger(AppClientInputThread.class.getName());
	final Socket client;
	final String login;
	final String pass;

	public AppClientInputThread(Socket client, String login, String pass) {
		this.client = client;
		this.login = login;
		this.pass = pass;
	}

	@Override
	public void run() {
		OutputStream out = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;

		try {
			// Open the output stream of the client socket.
			out = client.getOutputStream();
			osw = new OutputStreamWriter(out);
			pw = new PrintWriter(osw);
			final String msg = "{ \"login\" : " + login + " , \"password\" : " + pass
					+ ", \"instruction\" : \"connect\" }";
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

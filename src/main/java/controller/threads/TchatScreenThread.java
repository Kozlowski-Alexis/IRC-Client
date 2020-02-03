package controller.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

import view.ModalException;
import view.TchatIndex;

public class TchatScreenThread implements Runnable {
	private Socket client;
	private TchatIndex tchatView;

	public TchatScreenThread(Socket client, TchatIndex tchatView) {
		this.client = client;
		this.tchatView = tchatView;
	}

	@Override
	public void run() {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			in = client.getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);

			// Read the first line of the network stream
			String line = br.readLine();
			while (client.isClosed() == false) {
				JSONObject jsonObject = new JSONObject(line);
				Integer code = jsonObject.getInt("code");
				if (130 == code) {
					String message = jsonObject.getString("message");
					String user = jsonObject.getString("user");
					tchatView.setMessageField(user, message);

				} else if (code == 130 && code == 200) {
					ModalException exception = new ModalException("Erreur lors de la recuperation des messages");
				}
				line = br.readLine();
			}

		} catch (IOException e) {
			ModalException streamError = new ModalException("Erreur lors du stream"+e);
		} 
	}
}

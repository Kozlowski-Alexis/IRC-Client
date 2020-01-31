package controller.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import controller.TchatIndexController;
import view.ModalException;

public class AppClientScreenThread implements Runnable {
	private Socket client;

	public AppClientScreenThread(Socket client) {
		this.client = client;
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
			while (line != null) {
		        JSONObject jsonObject = new JSONObject(line);
		        Integer code = jsonObject.getInt("code");
		        String message = jsonObject.getString("message");
		        if(200 == code) {
		        	TchatIndexController tchatIndexController = new TchatIndexController(client);
		        } else {
		        	ModalException exception = new ModalException("Erreur dans les identifiants");
		        }
				// Read the next line readed on the network stream.
				line = br.readLine();
			}

		} catch (IOException e) {
			ModalException streamError = new ModalException("Erreur lors du stream");
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				if(isr != null) {
					isr.close();
				}
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				ModalException closeError = new ModalException("Erreur lors de la fermeture de la connexion");
			}
		}
		
	}

}

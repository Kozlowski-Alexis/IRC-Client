package controller.threads;

import java.awt.List;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import view.ModalException;
import view.TchatIndex;

public class CanalListScreenThread implements Runnable {
	private Socket client;
	private TchatIndex tchatView;

	public CanalListScreenThread(Socket client, TchatIndex tchatView) {
		this.client = client;
		this.tchatView = tchatView;
	}

	@Override
	public void run() {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		Boolean running = true;

		try {
			in = client.getInputStream();
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);

			// Read the first line of the network stream
			String line = br.readLine();

			while (running) {
				JSONObject jsonObject = new JSONObject(line);
				Integer code = jsonObject.getInt("code");
				if (code == 120) {
					JSONArray canals = jsonObject.getJSONArray("all_members");

					tchatView.setCanalField(canals);
					running = false;
					// Read the next line readed on the network stream.
				} else if (code != 200 && code != 120) {
					ModalException exception = new ModalException("Erreur lors de la recuperation des canaux");
					running = false;
				}
				line = br.readLine();
			}

		} catch (

		IOException e) {
			ModalException streamError = new ModalException("Erreur lors du stream");
		} 
	}
}

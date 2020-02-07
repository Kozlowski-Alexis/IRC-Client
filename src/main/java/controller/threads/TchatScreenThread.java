package controller.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONException;
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
		
		while (client.isClosed() == false) {
			try {
				in = client.getInputStream();
				isr = new InputStreamReader(in, "UTF-8");
				br = new BufferedReader(isr);

				// Read the first line of the network stream
				String line = br.readLine();
				while (true) {
					if (line.isEmpty()) {
						ModalException errorServerUnreachable = new ModalException("Serveur injoignable !");
						try {
							if(in != null) {
								in.close();
							}
							if(isr != null) {
								isr.close();
							}
							if(br != null) {
								br.close();
							}
							client.close();
							tchatView.close();
							
						} catch (IOException e) {
							ModalException closeError = new ModalException("Erreur lors de la fermeture de la connexion");
						} 
					} else if (isJSONValid(line)) {
						JSONObject jsonObject = new JSONObject(line);
						Integer code = jsonObject.getInt("code");
						if (130 == code) {
							String message = jsonObject.getString("message");
							String user = jsonObject.getString("user");
							tchatView.setMessageField(user, message);

						}
						if (120 == code) {
							JSONArray canals = jsonObject.getJSONArray("all_channel");
							tchatView.setCanalField(canals);

						}
						if (110 == code) {
							JSONArray members = jsonObject.getJSONArray("all_members");
							tchatView.setMembersField(members);

						} else if (code != 130 && code != 120 && code != 110 && code != 200) {
							String message = jsonObject.getString("message");
							ModalException exception = new ModalException(message);
						}
					}
					line = br.readLine();
				}

			} catch (IOException e) {
				ModalException streamError = new ModalException("Erreur lors du stream" + e);
			}
		}
	}
	
	public boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
}

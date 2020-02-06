package controller.threads;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;

import org.json.JSONObject;

import controller.TchatIndexController;
import view.LoginForm;
import view.ModalException;

public class LoginScreenThread implements Runnable {
	private Socket client;
	private String login;
	private String pass;
	private LoginForm loginView;

	public LoginScreenThread(Socket client, String login, String pass, LoginForm loginView) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.loginView = loginView;
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
		        String message = jsonObject.getString("message");
		        if(200 == code) {
		        	TchatIndexController tchatIndexController = new TchatIndexController(client, login, pass);
		        	tchatIndexController.newView();
		        	this.loginView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        	this.loginView.dispatchEvent(new WindowEvent(this.loginView, WindowEvent.WINDOW_CLOSING));
		        	running = false;
		        } else {
		        	ModalException exception = new ModalException(message);
		        	running = false;
		        }
				// Read the next line readed on the network stream.
				line = br.readLine();
			}

		} catch (IOException e) {
			ModalException streamError = new ModalException("Erreur lors du stream");
		}
		
	}

}

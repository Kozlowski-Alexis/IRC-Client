package controller;

import java.io.IOException;
import java.net.Socket;

import controller.threads.LoginInputThread;
import controller.threads.LoginScreenThread;
import view.LoginForm;
import view.ModalException;

public class LoginFormController {
	private LoginForm loginView;
	
	public LoginFormController() {
		loginView = new LoginForm(this);
	}
	
	public void login(String host, String login, char[] pass) {
		try {
			String[] arrayHost = host.split("\\+");
			String url = arrayHost[0].trim();
			Integer port = Integer.parseInt(arrayHost[1].trim());
			
			final Socket clientSocket = new Socket(url, port);

			// Start thread on client screen
			new Thread(new LoginScreenThread(clientSocket, login, String.valueOf(pass), this.loginView)).start();
			
			// Start thread on client input
			new Thread(new LoginInputThread(clientSocket, login, String.valueOf(pass))).start();
			

		} catch (IOException e) {
			ModalException loginError = new ModalException("Impossible de se connecter"+e);
		}
	}

}

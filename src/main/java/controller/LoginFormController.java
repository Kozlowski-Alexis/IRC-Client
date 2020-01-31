package controller;

import java.io.IOException;
import java.net.Socket;

import controller.threads.AppClientInputThread;
import controller.threads.AppClientScreenThread;
import view.LoginForm;
import view.ModalException;

public class LoginFormController {
	
	public LoginFormController() {
		LoginForm loginView = new LoginForm(this);
	}
	
	public void login(String host, String login, char[] pass) {
		try {
			String[] arrayHost = host.split("\\+");
			String url = arrayHost[0].trim();
			Integer port = Integer.parseInt(arrayHost[1].trim());
			
			final Socket clientSocket = new Socket(url, port);

			// Start thread on client screen
			new Thread(new AppClientScreenThread(clientSocket)).start();
			
			// Start thread on client input
			new Thread(new AppClientInputThread(clientSocket, login, String.valueOf(pass))).start();
			

		} catch (IOException e) {
			ModalException loginError = new ModalException("Impossible de se connecter"+e);
		}
	}

}

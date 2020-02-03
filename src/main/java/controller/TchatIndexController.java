package controller;

import java.net.Socket;

import controller.threads.CanalListInputThread;
import controller.threads.CanalListScreenThread;
import controller.threads.LogoutInputThread;
import controller.threads.SendMessageThread;
import controller.threads.TchatScreenThread;
import view.TchatIndex;

public class TchatIndexController {
	private Socket client;
	private String login;
	private String pass;
	private TchatIndex tchat;
	
	public TchatIndexController(Socket client, String login, String pass) {
		this.client = client;
		this.login = login;
		this.pass = pass;
	}
	
	public void newView() {
		TchatIndex tchat = new TchatIndex(this, client);
		this.tchat = tchat;
		listCanals();
		new Thread(new TchatScreenThread(client, tchat)).start();
	}
	
	public void sendMessage(String message, String channel) {
		// Start thread on client input
		new Thread(new SendMessageThread(client, login, pass, channel, message)).start();
	}
	
	public void listCanals() {
		new Thread(new CanalListInputThread(client, login, pass)).start();
		new Thread(new CanalListScreenThread(client, tchat)).start();
	}
	
	public void logout() {
		new Thread(new LogoutInputThread(client, login, pass)).start();
	}
}

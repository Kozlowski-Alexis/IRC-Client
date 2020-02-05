package controller;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

import controller.threads.CanalListInputThread;
import controller.threads.CanalRegistrationInputThread;
import controller.threads.LogoutInputThread;
import controller.threads.MembersListInputThread;
import controller.threads.SendMessageThread;
import controller.threads.TchatScreenThread;
import model.DAOException;
import model.DAOFactory;
import model.DAOLog;
import model.bean.Log;
import view.TchatIndex;

public class TchatIndexController {
	private Socket client;
	private String login;
	private String pass;
	private TchatIndex tchat;
	private String channel;
	
	public TchatIndexController(Socket client, String login, String pass) {
		this.client = client;
		this.login = login;
		this.pass = pass;
		this.channel = "default";
	}
	
	public void newView() {
		TchatIndex tchat = new TchatIndex(this, client);
		this.tchat = tchat;
		listCanals();
		listMembers();
		new Thread(new TchatScreenThread(client, tchat)).start();
	}
	
	public void sendMessage(String message) {
		// Start thread on client input
		new Thread(new SendMessageThread(client, login, pass, channel, message)).start();
		insertLog(message);
	}
	
	public void canalRegistration(String newChannel) {
		new Thread(new CanalRegistrationInputThread(client, login, pass, channel, newChannel)).start();
		this.channel = newChannel;
		listCanals();
		listMembers();
	}
	
	public void listCanals() {
		new Thread(new CanalListInputThread(client, login, pass)).start();
	}
	
	public void listMembers() {
		new Thread(new MembersListInputThread(client, login, pass, channel)).start();
	}
	
	public void logout() {
		new Thread(new LogoutInputThread(client, login, pass)).start();
	}
	
	public void insertLog(String message) {
		Connection c = null;
		Log newLog = new Log();
		newLog.setDate(new Date(Calendar.getInstance().getTime().getTime()));
		newLog.setMessage(message);
		newLog.setUserName(login);
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			Log logCreated = logDAO.create(newLog);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

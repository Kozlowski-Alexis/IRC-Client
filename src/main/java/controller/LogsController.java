package controller;

import java.sql.Connection;
import java.util.List;

import model.DAOException;
import model.DAOFactory;
import model.DAOLog;
import model.bean.Log;
import view.Logs;

public class LogsController {
	public LogsController() {
		Logs logs = new Logs(this);
	}
	
	public List<Log> getListLogs() {
		Connection c = null;
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			final List<Log> logList = logDAO.list();
			
			return logList;
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Log> getListLogsByUser(String userName) {
		Connection c = null;
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			final List<Log> logListUser = logDAO.listByUser(userName);
			
			return logListUser;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Log> getListLogsByText(String text) {
		Connection c = null;
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			final List<Log> logListText = logDAO.listByText(text);
			
			return logListText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

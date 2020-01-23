package controller;

import java.sql.Connection;
import java.util.List;

import model.DAOException;
import model.DAOFactory;
import model.DAOLog;
import model.bean.Log;
import view.Logs;
import view.TchatIndex;

public class Main {

	public static void main(String[] args) {
		Logs logs = new Logs();
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
	
	public List<Log> getListLogsByUser(String userName){
		Connection c = null;
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			final List<Log> logListUser = logDAO.listByUser(userName);
			
			return logListUser;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}

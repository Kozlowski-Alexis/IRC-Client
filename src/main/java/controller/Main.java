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
Connection c = null;
		
		try {
			c = DAOFactory.getConnection();
			DAOLog logDAO = DAOFactory.getDAOLog(c);
			
			final List<Log> logList = logDAO.list();
			
			for (Log log : logList) {
			    System.out.println(log.getId()+" / "+log.getDate()+" / "+log.getUserName()+" / "+log.getMessage());
			}
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

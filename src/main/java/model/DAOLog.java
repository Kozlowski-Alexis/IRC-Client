package model;

import java.util.List;

import model.bean.Log;

public interface DAOLog extends DAO<Log>{
	
	public List<Log> listByUser(String userName) throws DAOException;
}

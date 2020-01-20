package model;

import java.util.List;

public interface DAO<T> {

	public T create(T obj) throws DAOException;
	public List<T> list() throws DAOException;
	
}

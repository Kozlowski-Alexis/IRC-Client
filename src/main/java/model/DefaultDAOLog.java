package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


import model.bean.Log;

public class DefaultDAOLog extends AbstractDAO implements DAOLog{

	protected DefaultDAOLog(Connection connect) {
		super(connect);
	}

	@Override
	public Log create(Log obj) throws DAOException {
		final String sql = "INSERT INTO `log` (`id`, `user_name`, `message`, `date`) VALUES (NULL, ?, ?, ?)";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		int r;
		
		try {
			st = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getUserName());
			st.setString(2, obj.getMessage());
			st.setDate(3, obj.getDate());
			r = st.executeUpdate();
			
			rs = st.getGeneratedKeys();
			
			if(r > 0 && rs.next()) {
				obj.setId(rs.getInt(1));
				return obj;
			}
			throw new DAOException("Error during log insert in the database");
			
		} catch (SQLException e) {
			throw new DAOException("Error during loading log from database.",e);
		} finally {
			DAOUtils.close(st);
		}
	}
	@Override
	public List<Log> list() throws DAOException {
		final String sql = "SELECT DISTINCT(`user_name`) FROM `log`";

		final List<Log> logList = new LinkedList<>();
		
		PreparedStatement st = null;
		ResultSet r = null;
		
		try {
			st = connect.prepareStatement(sql);
			r = st.executeQuery();
			
			while(r.next()) {
				final Log l = new Log();
				l.setUserName(r.getString("user_name"));
				
				logList.add(l);
			}
			
			return logList;
			
		} catch (SQLException e) {
			throw new DAOException("Error during loading log from database.",e);
		} finally {
			DAOUtils.close(r, st);
		}
	}
	
	@Override
	public List<Log> listByUser(String userName) throws DAOException {
		final String sql = "SELECT * FROM `log` WHERE `user_name`= ?";

		final List<Log> logList = new LinkedList<>();
		
		PreparedStatement st = null;
		ResultSet r = null;
		
		try {
			st = connect.prepareStatement(sql);
			st.setString(1, userName);
			r = st.executeQuery();
			
			while(r.next()) {
				final Log l = new Log();
				l.setId(r.getInt("id"));
				l.setUserName(r.getString("user_name"));
				l.setMessage(r.getString("message"));
				l.setDate(r.getDate("date"));
				
				logList.add(l);
			}
			
			return logList;
			
		} catch (SQLException e) {
			throw new DAOException("Error during loading log from database.",e);
		} finally {
			DAOUtils.close(r, st);
		}
	}
	
	@Override
	public List<Log> listByText(String text) throws DAOException {
		final String sql = "SELECT * FROM `log` WHERE `message` LIKE ?";

		final List<Log> logList = new LinkedList<>();
		
		PreparedStatement st = null;
		ResultSet r = null;
		
		try {
			st = connect.prepareStatement(sql);
			st.setString(1, "%"+text+"%");
			r = st.executeQuery();
			
			while(r.next()) {
				final Log l = new Log();
				l.setId(r.getInt("id"));
				l.setUserName(r.getString("user_name"));
				l.setMessage(r.getString("message"));
				l.setDate(r.getDate("date"));
				
				logList.add(l);
			}
			
			return logList;
			
		} catch (SQLException e) {
			throw new DAOException("Error during loading log from database.",e);
		} finally {
			DAOUtils.close(r, st);
		}
	}
}

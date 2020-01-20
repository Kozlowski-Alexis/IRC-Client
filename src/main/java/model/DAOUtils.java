package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DAOUtils {
	
	private static final Logger LOG = Logger.getLogger(DAOUtils.class.getName());

	public DAOUtils() {
		
	}
	
	public static void close(ResultSet r) {
		try {
			if(r != null) {
				r.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void close(PreparedStatement st) {
		try {
			if(st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void close (ResultSet r, PreparedStatement st) {
		close(r);
		close(st);
	}

}

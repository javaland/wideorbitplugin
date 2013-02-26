package nl.caliope.onairdesk.wideorbit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnector
{
	public static final int CONNECTION_TIMEOUT = 5000;
	private static Logger logger = LoggerFactory.getLogger(DBConnector.class);
	private Connection con;

	static class SingletonHolder
	{
		final static DBConnector INSTANCE = new DBConnector();
	}

	public static DBConnector getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	public void connect(JSONObject configuration) throws SQLException
	{
		String host = configuration.optString("host");
		String database = configuration.optString("database");
		int port = configuration.optInt("port", 5432);

		if (host == null || host.isEmpty()
				|| database == null || database.isEmpty()) {
			return;
		}

		String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
		String username = configuration.optString("username", "ras");
		String password = configuration.optString("password", "dmarc");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("postgresql driver not found", e);
			return;
		}

		try {
			this.con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			showSqlWarnings();
			throw e;
		}
	}

	public boolean isConnected()
	{
		return (this.con != null);
	}

	public void disconnect()
	{
		if (this.con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				logger.error("exception when trying to close connection connection", e);
			}
		}
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException
	{
		if (!isConnected()) {
			throw new IllegalStateException("not connected");
		}

		return this.con.prepareStatement(sql);
	}

	private void showSqlWarnings() throws SQLException
	{
		if (this.con == null) {
			return;
		}

		SQLWarning warning = this.con.getWarnings();
		while (warning != null) {
			logger.debug("SQL Warning:\n\tMessage: {}\n\tSQL state: {}\n\t Error code: {}",
					warning.getMessage(),
					warning.getSQLState(),
					warning.getErrorCode());
			warning = warning.getNextWarning();
		}
		this.con.clearWarnings();
	}

}

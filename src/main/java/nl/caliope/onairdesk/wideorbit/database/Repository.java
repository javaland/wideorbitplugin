package nl.caliope.onairdesk.wideorbit.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.caliope.onairdesk.wideorbit.DBConnector;

import org.json.JSONObject;

public abstract class Repository
{
	private JSONObject configuration;

	public Repository(JSONObject configuration)
	{
		this.configuration = configuration;
	}

	public JSONObject getConfiguration()
	{
		return configuration;
	}

	public void setConfiguration(JSONObject configuration)
	{
		this.configuration = configuration;
	}

	protected PreparedStatement prepare(String sql) throws SQLException
	{
		if (!DBConnector.getInstance().isConnected()) {
			DBConnector.getInstance().connect(configuration);
		}

		return DBConnector.getInstance().prepareStatement(sql);
	}
}

package nl.caliope.onairdesk.wideorbit;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import nl.caliope.onairdesk.model.Item;
import nl.caliope.onairdesk.provider.ItemProvider;

import org.json.JSONObject;

public class WideOrbitItemProvider extends ItemProvider
{
	public static final String SQL_BY_ID = "SELECT " +
			"\"public\".media_asset.media_asset_id as automationid, " +
			"\"public\".media_asset.title as title, " +
			"\"public\".media_asset.artist as artists " +
			"FROM " + "\"public\".media_asset" +
			"WHERE " + "\"public\".media_asset.media_asset_id = ?";
	private JSONObject configuration;
	private PreparedStatement stmtItemById;

	public WideOrbitItemProvider(JSONObject configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public List<Item> getItems(Date date) throws IOException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Item getItem(String automationId) throws IOException
	{
		ensureConnected();

		try {
			stmtItemById.setString(1, automationId);
			ResultSet rs = stmtItemById.executeQuery();
			if (rs.next()) {
				return retrieveItem(rs);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return null;
	}

	@Override
	public List<Integer> getIdList() throws IOException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private Item retrieveItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setAutomationId(rs.getString("automationid"));
		item.setTitle(rs.getString("title"));
		
		
		String artists = rs.getString("artists");
		item.setArtists(Arrays.asList(artists.split("&")));
		
		return item;
	}

	private void ensureConnected() throws IOException
	{
		try {
			if (!DBConnector.getInstance().isConnected()) {
				DBConnector.getInstance().connect(configuration);
			}

			if (this.stmtItemById == null) {
				this.stmtItemById = DBConnector.getInstance().prepareStatement(SQL_BY_ID);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}
}

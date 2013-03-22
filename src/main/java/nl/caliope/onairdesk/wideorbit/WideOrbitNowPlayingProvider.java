package nl.caliope.onairdesk.wideorbit;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.caliope.onairdesk.AutomationController;
import nl.caliope.onairdesk.model.Item;
import nl.caliope.onairdesk.provider.AbstractNowPlayingProvider;
import nl.caliope.onairdesk.provider.ItemProvider;
import nl.caliope.onairdesk.wideorbit.xml.NowPlaying;
import nl.caliope.onairdesk.wideorbit.xml.NowPlayingParser;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WideOrbitNowPlayingProvider extends AbstractNowPlayingProvider
{

	private static final Logger logger = LoggerFactory.getLogger(WideOrbitNowPlayingProvider.class);

	public static final String SQL_SEARCH_ITEM = "SELECT "
			+ "\"public\".media_asset.media_asset_id as automationid "
			+ "FROM \"public\".media_asset "
			+ "WHERE \"public\".media_asset.title = ? "
			+ "AND \"public\".media_asset.artist = ?";

	private JSONObject configuration;
	private File file;

	public WideOrbitNowPlayingProvider(JSONObject configuration)
	{
		this.configuration = configuration;
		this.file = getFile();
	}

	private File getFile()
	{
		String path = configuration.optString("nowplaying_path", null);
		if (path == null)
			return null;
		else
			return new File(path);
	}

	@Override
	public Item retrieveNowPlaying()
	{
		if (this.file == null || !this.file.exists()) {
			// we can only find the currently playing item if a file is provided
			// and exists
			return null;
		}

		
		AutomationController automationController = getAutomationController();
		try {
			NowPlaying p = NowPlayingParser.parseFromXML(this.file);
			if (p == null) {
				// could not parse the nowplaying file
				return null;
			}

			int automationid = resolveItemId(p);
			if (automationid > 0) {
				ItemProvider provider = automationController.getItemProvider();
				return provider.byId(String.valueOf(automationid));
			}
		} catch (Exception e) {
			logger.error("failed to retrieve now playing", e);
		}
		return null;
	}

	private int resolveItemId(NowPlaying nowPlaying) throws SQLException
	{
		String title = nowPlaying.getTitle();
		String artists = nowPlaying.getArtist();

		DBConnector connector = DBConnector.getInstance();
		if (!connector.isConnected()) {
			connector.connect(configuration);
		}

		PreparedStatement stmt = connector.prepareStatement(SQL_SEARCH_ITEM);
		stmt.setString(1, title);
		stmt.setString(2, artists);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}

		return -1;
	}

}

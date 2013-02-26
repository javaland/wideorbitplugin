package nl.caliope.onairdesk.wideorbit;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import nl.caliope.onairdesk.model.ScheduledItem;
import nl.caliope.onairdesk.provider.ScheduledItemProvider;

import org.json.JSONObject;

public class WideOrbitPlaylistProvider extends ScheduledItemProvider
{

	private JSONObject configuration;

	public WideOrbitPlaylistProvider(JSONObject configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public List<ScheduledItem> getScheduledItems(Date from, Date to) throws IOException
	{
		return Collections.emptyList();
	}
}

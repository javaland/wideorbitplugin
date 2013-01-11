package nl.caliope.onairdesk.wideorbit;

import org.json.JSONObject;

import nl.caliope.onairdesk.model.Item;
import nl.caliope.onairdesk.provider.NowPlayingProvider;

public class XMLNowPlayingProvider extends NowPlayingProvider
{

	public XMLNowPlayingProvider(JSONObject configuration)
	{
		
	}

	@Override
	public Item getPrevious(int n)
	{
		return null;
	}

	@Override
	public Item getCurrent()
	{
		return null;
	}

	@Override
	public Item getNext(int n)
	{
		return null;
	}

	@Override
	public int getPreviousCount()
	{
		return 0;
	}

	@Override
	public int getNextCount()
	{
		return 0;
	}

}

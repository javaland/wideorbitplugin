package nl.caliope.onairdesk.wideorbit;

import org.json.JSONObject;

import nl.caliope.onairdesk.PluginConfigurator;
import nl.caliope.onairdesk.provider.NowPlayingFactory;
import nl.caliope.onairdesk.provider.NowPlayingProvider;

public class XMLNowPlayingFactory extends NowPlayingFactory
{
	@Override
	public NowPlayingProvider create(JSONObject configuration)
	{
		return new XMLNowPlayingProvider(configuration);
	}

	@Override
	public String getDescription()
	{
		return "Wide orbit (RAS) XML file";
	}

	@Override
	public PluginConfigurator createConfigurator(JSONObject configuration)
	{
		return new XMLNowPlayingConfigurator(configuration);
	}
}

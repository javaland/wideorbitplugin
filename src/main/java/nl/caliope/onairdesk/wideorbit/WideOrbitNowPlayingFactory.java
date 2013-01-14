package nl.caliope.onairdesk.wideorbit;

import org.json.JSONObject;

import nl.caliope.onairdesk.PluginConfigurator;
import nl.caliope.onairdesk.provider.NowPlayingFactory;
import nl.caliope.onairdesk.provider.NowPlayingProvider;
import nl.thanod.annotations.spi.ProviderFor;

@ProviderFor(NowPlayingFactory.class)
public class WideOrbitNowPlayingFactory extends NowPlayingFactory
{
	@Override
	public NowPlayingProvider create(JSONObject configuration)
	{
		return new WideOrbitNowPlayingProvider(configuration);
	}

	@Override
	public String getDescription()
	{
		return "WideOrbit (XML file)";
	}

	@Override
	public PluginConfigurator createConfigurator(JSONObject configuration)
	{
		return new WideOrbitNowPlayingConfigurator(configuration);
	}
}

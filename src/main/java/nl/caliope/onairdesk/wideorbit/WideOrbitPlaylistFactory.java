package nl.caliope.onairdesk.wideorbit;

import org.json.JSONObject;

import nl.caliope.onairdesk.PluginConfigurator;
import nl.caliope.onairdesk.provider.ScheduledItemFactory;
import nl.caliope.onairdesk.provider.ScheduledItemProvider;
import nl.thanod.annotations.spi.ProviderFor;

@ProviderFor(ScheduledItemFactory.class)
public class WideOrbitPlaylistFactory extends ScheduledItemFactory
{
	@Override
	public ScheduledItemProvider create(JSONObject configuration)
	{
		return new WideOrbitPlaylistProvider(configuration);
	}

	@Override
	public PluginConfigurator createConfigurator(JSONObject configuration)
	{
		return new WideOrbitDatabaseConfigurator(configuration);
	}

	@Override
	public String getDescription()
	{
		return "WideOrbit (Database)";
	}
}

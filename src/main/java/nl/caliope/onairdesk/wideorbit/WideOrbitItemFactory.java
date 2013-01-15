package nl.caliope.onairdesk.wideorbit;

import org.json.JSONObject;

import nl.caliope.onairdesk.provider.ItemFactory;
import nl.caliope.onairdesk.provider.ItemProvider;
import nl.caliope.onairdesk.provider.ProviderFactoryConfigurator;
import nl.thanod.annotations.spi.ProviderFor;

@ProviderFor(ItemFactory.class)
public class WideOrbitItemFactory extends ItemFactory
{

	@Override
	public ItemProvider create(JSONObject configuration)
	{
		return new WideOrbitItemProvider(configuration);
	}

	@Override
	public String getDescription()
	{
		return "WideOrbit (Database)";
	}

	@Override
	public ProviderFactoryConfigurator createConfigurator(JSONObject configuration)
	{
		return new WideOrbitDatabaseConfigurator(configuration);
	}
}

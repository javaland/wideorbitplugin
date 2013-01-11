package nl.caliope.onairdesk.wideorbit;

import javax.swing.JComponent;

import org.json.JSONObject;

import nl.caliope.onairdesk.PluginConfigurator;

public class XMLNowPlayingConfigurator extends PluginConfigurator
{

	public static final String IDENTIFIER = "RASXMLNowPlayingConfigurator";

	public XMLNowPlayingConfigurator(JSONObject configuration)
	{
		super(configuration);
	}

	@Override
	public String getIdentifier()
	{
		return IDENTIFIER;
	}

	@Override
	public JComponent getEditorComponent()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidConfiguration()
	{
		// TODO Auto-generated method stub
		return false;
	}

}

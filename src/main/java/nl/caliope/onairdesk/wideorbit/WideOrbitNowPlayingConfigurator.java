package nl.caliope.onairdesk.wideorbit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import nl.caliope.onairdesk.provider.ProviderFactoryConfigurator;
import nl.caliope.onairdesk.wideorbit.panels.NowPlayingConfigurationPanel;

import org.json.JSONObject;

public class WideOrbitNowPlayingConfigurator extends ProviderFactoryConfigurator
{

	public static final String IDENTIFIER = "RASXMLNowPlayingConfigurator";

	private JComponent editorComponent;

	public WideOrbitNowPlayingConfigurator(JSONObject configuration)
	{
		super(configuration);

		this.editorComponent = new NowPlayingConfigurationPanel(getConfiguration());
	}

	@Override
	public String getIdentifier()
	{
		return IDENTIFIER;
	}

	@Override
	public JComponent getEditorComponent()
	{
		return this.editorComponent;
	}

	@Override
	public List<String> getValidationErrors()
	{
		List<String> errors = new ArrayList<String>();
		String path = getConfiguration().optString("nowplaying_path", null);
		if (path == null || path.isEmpty()) {
			errors.add("Please select an xml file that contains WideOrbit now playing metadata");
		} else {
			File file = new File(path);
			if (!file.exists() || !file.canRead()) {
				errors.add("The selected file does not exist or can not be read");
			}
		}
		return errors;
	}
}

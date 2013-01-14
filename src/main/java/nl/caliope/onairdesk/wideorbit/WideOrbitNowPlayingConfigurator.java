package nl.caliope.onairdesk.wideorbit;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import nl.caliope.onairdesk.PluginConfigurator;
import nl.caliope.onairdesk.wideorbit.panels.NowPlayingConfigurationPanel;

import org.json.JSONObject;

public class WideOrbitNowPlayingConfigurator extends PluginConfigurator
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
	public boolean isValidConfiguration()
	{
		String path = getConfiguration().optString("nowplaying_path", null);
		if (path == null) {
			JOptionPane.showMessageDialog(editorComponent,
					"Please select an xml file that contains WideOrbit now playing metadata");
			return false;
		} else {
			File file = new File(path);
			if (file.exists() && path.toLowerCase().endsWith(".xml")) {
				return true;
			} else {
				JOptionPane.showMessageDialog(editorComponent,
						"Please select a valid xml file that contains" +
								" WideOrbit now playing metadata");
			}
		}
		return false;
	}

}

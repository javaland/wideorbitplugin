package nl.caliope.onairdesk.wideorbit;

import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import nl.caliope.onairdesk.PluginConfigurator;
import nl.caliope.onairdesk.wideorbit.panels.DatabaseConfigurationPanel;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WideOrbitDatabaseConfigurator extends PluginConfigurator
{
	private static final Logger logger = LoggerFactory
			.getLogger(WideOrbitDatabaseConfigurator.class);

	public static final String IDENTIFIER = "RASDatabaseConfigurator";

	private JComponent editorComponent;

	public WideOrbitDatabaseConfigurator(JSONObject configuration)
	{
		super(configuration);

		this.editorComponent = new DatabaseConfigurationPanel(getConfiguration());
	}

	@Override
	public String getIdentifier()
	{
		return IDENTIFIER;
	}

	@Override
	public JComponent getEditorComponent()
	{
		return editorComponent;
	}

	@Override
	public boolean isValidConfiguration()
	{
		DBConnector connector = DBConnector.getInstance();
		try {
			if (connector.isConnected()) {
				connector.disconnect();
			}

			connector.connect(getConfiguration());

			return connector.isConnected();
		} catch (SQLException e) {
			String message = "Failed to connect to the database";
			JOptionPane.showMessageDialog(
					editorComponent, message);
			logger.error(message, e);
			return false;
		}
	}
}

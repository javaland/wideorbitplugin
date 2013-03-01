package nl.caliope.onairdesk.wideorbit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import nl.caliope.onairdesk.provider.ProviderFactoryConfigurator;
import nl.caliope.onairdesk.wideorbit.panels.DatabaseConfigurationPanel;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WideOrbitDatabaseConfigurator extends ProviderFactoryConfigurator
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
	public List<String> getValidationErrors()
	{
		List<String> errors = new ArrayList<String>();
		DBConnector connector = DBConnector.getInstance();

		if (connector.isConnected()) {
			connector.disconnect();
		}
		try {
			connector.connect(getConfiguration());
		} catch (Exception e) {
			if (!connector.isConnected()) {
				errors.add("Could not connect to the database");
			}
		}

		return errors;
	}
}

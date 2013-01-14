package nl.caliope.onairdesk.wideorbit;

import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class DatabaseConfigurationPanel extends JPanel implements DocumentListener
{

	private JSONObject configuration;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtDatabase;

	public DatabaseConfigurationPanel(JSONObject configuration)
	{
		this.configuration = configuration;

		initComponents();
		initValues();
	}

	private void initComponents()
	{
		this.txtUsername = new JTextField(25);
		this.txtUsername.getDocument().addDocumentListener(this);
		this.txtPassword = new JPasswordField(25);
		this.txtPassword.getDocument().addDocumentListener(this);
		this.txtHost = new JTextField(25);
		this.txtHost.getDocument().addDocumentListener(this);
		this.txtPort = new JTextField(25);
		this.txtPort.setDocument(new FixedSizeNumberDocument(5));
		this.txtPort.getDocument().addDocumentListener(this);
		this.txtDatabase = new JTextField(25);
		this.txtDatabase.getDocument().addDocumentListener(this);

		JPanel panelDBInformation = new JPanel();
		panelDBInformation.setBorder(BorderFactory.createTitledBorder("Database information"));
		panelDBInformation.setLayout(new MigLayout("", "[][]", "[][][][][]"));
		panelDBInformation.add(new JLabel("Username"), "cell 0 0");
		panelDBInformation.add(txtUsername, "cell 1 0,growx");
		panelDBInformation.add(new JLabel("Password"), "cell 0 1");
		panelDBInformation.add(txtPassword, "cell 1 1,growx");
		panelDBInformation.add(new JLabel("Host"), "cell 0 2");
		panelDBInformation.add(txtHost, "cell 1 2,growx");
		panelDBInformation.add(new JLabel("Port"), "cell 0 3");
		panelDBInformation.add(txtPort, "cell 1 3,growx");
		panelDBInformation.add(new JLabel("Database"), "cell 0 4");
		panelDBInformation.add(txtDatabase, "cell 1 4,growx");

		setLayout(new MigLayout("", "[grow]", "[]"));
		add(panelDBInformation, "cell 0 0,grow");

	}

	private void initValues()
	{
		String username = configuration.optString("username", "ras");
		String password = configuration.optString("password", "dmarc");
		String host = configuration.optString("host", "localhost");
		int port = configuration.optInt("port", 5432);
		String database = configuration.optString("database", "onairdb");

		txtUsername.setText(username);
		txtPassword.setText(password);
		txtHost.setText(host);
		txtPort.setText(String.valueOf(port));
		txtDatabase.setText(database);
	}

	private void updateConfiguration()
	{
		String username = txtUsername.getText();
		String password = new String(txtPassword.getPassword());
		String host = txtHost.getText();
		String database = txtDatabase.getText();

		int port;
		try {
			port = Integer.parseInt(txtPort.getText());
		} catch (NumberFormatException e) {
			port = 5432;
		}

		try {
			configuration.put("username", username);
			configuration.put("password", password);
			configuration.put("host", host);
			configuration.put("port", port);
			configuration.put("database", database);
		} catch (JSONException e) {

		}
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		updateConfiguration();
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		updateConfiguration();
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		updateConfiguration();
	}

	@SuppressWarnings("serial")
	private class FixedSizeNumberDocument extends PlainDocument
	{

		private int maxNumbers = 8;

		public FixedSizeNumberDocument(int maxNumbers)
		{
			this.maxNumbers = maxNumbers;
		}

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException
		{
			if (getLength() + str.length() > maxNumbers) {
				str = str.substring(0, maxNumbers - getLength());
				getToolkit().beep();
			}

			try {
				Integer.parseInt(str);
			} catch (NumberFormatException e) {
				// inserted text is not a number
				getToolkit().beep();
				return;
			}

			super.insertString(offs, str, a);
		}
	}
}

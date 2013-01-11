package nl.caliope.onairdesk.wideorbit;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import nl.caliope.onairdesk.wideorbit.resources.Icons;

@SuppressWarnings("serial")
public class XMLNowPlayingConfigurationPanel extends JPanel
{
	private JTextField txtLocation;

	public XMLNowPlayingConfigurationPanel()
	{
		this.txtLocation = new JTextField();
		this.txtLocation.setEditable(false);

		JPanel panelXMLFile = new JPanel(new MigLayout("", "[][grow][]", "[][]"));
		panelXMLFile.setBorder(BorderFactory.createTitledBorder("XML File"));
		panelXMLFile.add(txtLocation, "cell 1 0,growx");
		panelXMLFile.add(new JLabel("XML File"), "cell 0 0,alignx trailing");
		panelXMLFile.add(new JButton(new ChooseXMLFileAction()), "cell 2 0");
		panelXMLFile
				.add(
						new JLabel(
								"Choose the XML File created by wide orbit that contains the now playing information."),
						"cell 0 1 3 1");

		setLayout(new MigLayout("", "[grow]", "[]"));
		add(panelXMLFile, "cell 0 0,grow");
	}

	protected class ChooseXMLFileAction extends AbstractAction
	{
		public ChooseXMLFileAction()
		{
			super("", Icons.ICON_FOLDER_SMALL);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			File current = null;
			if (!txtLocation.getText().isEmpty()) {
				current = new File(txtLocation.getText());
			}

			JFileChooser fc = new JFileChooser(current)
			{
				@Override
				protected JDialog createDialog(Component parent) throws HeadlessException
				{
					JDialog dialog = super.createDialog(parent);
					dialog.setIconImage(Icons.ICON_FOLDER_SMALL.getImage());
					return dialog;
				}
			};
			fc.setFileFilter(new FileNameExtensionFilter("XML Files", ".xml"));
			fc.setDialogTitle("Choose WideOrbit's Now playing XML Metadata file");
			fc.setAcceptAllFileFilterUsed(false);

			if (fc.showOpenDialog(XMLNowPlayingConfigurationPanel.this)
					== JFileChooser.APPROVE_OPTION) {

				File result = fc.getSelectedFile();

				txtLocation.setText(result.getAbsolutePath());
			}
		}
	}
}

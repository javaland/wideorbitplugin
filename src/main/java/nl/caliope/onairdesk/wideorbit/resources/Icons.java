package nl.caliope.onairdesk.wideorbit.resources;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Icons
{

	private static Logger logger = LoggerFactory.getLogger(Icons.class);

	public static final ImageIcon ICON_FOLDER_SMALL = safeLoad("icons/16x16/Folder.png");

	private static ImageIcon safeLoad(String resourcename)
	{
		try {
			ImageIcon icon = new ImageIcon(Icons.class.getClassLoader().getResource(resourcename));
			return icon;
		} catch (Throwable ball) {
			logger.error("Failed to load image " + resourcename);
			return createEmptyImage(16, 16);
		}
	}

	private static final ImageIcon createEmptyImage(int width, int height)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gs.getDefaultConfiguration();

		// Create an image that supports transparent pixels
		BufferedImage bimage = gc.createCompatibleImage(width, height, Transparency.BITMASK);

		return new ImageIcon(bimage);
	}
}

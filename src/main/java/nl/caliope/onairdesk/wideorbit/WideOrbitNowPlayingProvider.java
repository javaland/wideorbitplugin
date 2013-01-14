package nl.caliope.onairdesk.wideorbit;

import java.io.File;
import java.io.IOException;

import nl.caliope.onairdesk.model.Item;
import nl.caliope.onairdesk.provider.NowPlayingProvider;
import nl.caliope.onairdesk.wideorbit.xml.NowPlaying;
import nl.caliope.onairdesk.wideorbit.xml.NowPlayingParser;

import org.json.JSONObject;

public class WideOrbitNowPlayingProvider extends NowPlayingProvider
{

	private File file;

	public WideOrbitNowPlayingProvider(JSONObject configuration)
	{
		String path = configuration.optString("filename", null);
		if (path == null)
			this.file = null;
		else
			this.file = new File(path);
	}

	@Override
	public Item getPrevious(int n)
	{
		return null;
	}

	@Override
	public Item getCurrent()
	{
		if (this.getFile() == null || !this.getFile().exists()) {
			// we can only find the currently playing item if a file is provided
			// and exists
			return null;
		}

		NowPlaying p = NowPlayingParser.parseFromXML(this.getFile());
		if (p == null) {
			// could not parse the nowplaying file
			return null;
		}

		try {
			// p.cart is the most likely candidate for the item id
			// otherwise we can only find the item by searching for name and
			// artist
			return super.getAutomationController().getItemProvider().getItem(p.getCart());
		} catch (IOException e) {
			return null;
		}
	}

	private File getFile()
	{
		return this.file;
	}

	@Override
	public Item getNext(int n)
	{
		return null;
	}

	@Override
	public int getPreviousCount()
	{
		return 0;
	}

	@Override
	public int getNextCount()
	{
		return 0;
	}

}

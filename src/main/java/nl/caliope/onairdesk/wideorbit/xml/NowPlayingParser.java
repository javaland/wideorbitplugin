package nl.caliope.onairdesk.wideorbit.xml;

import java.io.File;

import com.thoughtworks.xstream.XStream;

public class NowPlayingParser
{
	private static XStream xStream;

	public static NowPlaying parseFromXML(File xmlFile)
	{
		if (xStream == null) {
			xStream = new XStream();
			xStream.alias("nowplaying", NowPlaying.class);
			
			// to read empty longs we need our own converter
			xStream.registerConverter(new LongConverter());
		}

		try {
			return (NowPlaying) xStream.fromXML(xmlFile);
		} catch (ClassCastException ball) {
			return null;
		}
	}
}

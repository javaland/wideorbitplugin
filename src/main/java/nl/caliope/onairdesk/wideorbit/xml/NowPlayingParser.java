package nl.caliope.onairdesk.wideorbit.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

import com.thoughtworks.xstream.XStream;

public class NowPlayingParser
{
	private static XStream xStream;

	public static NowPlaying parseFromXML(File xmlFile) throws IOException
	{
		if (xStream == null) {
			xStream = new XStream();
			xStream.alias("nowplaying", NowPlaying.class);

			// to read empty longs we need our own converter
			xStream.registerConverter(new LongConverter());
		}

		try {
			String xmlish = readFromFile(xmlFile);
			String xml = escapeXMLCharacters(xmlish);
			
			return (NowPlaying) xStream.fromXML(xml);
		} catch (ClassCastException ball) {
			return null;
		} catch (IOException e) {
			throw e;
		}
	}

	private static String readFromFile(File file) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		InputStream in = null;

		try {
			in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return sb.toString();
	}

	private static String escapeXMLCharacters(String s)
	{
		if (s != null && !s.isEmpty()) {
			s = s.replaceAll("&", "&amp;");
		}

		return s;
	}
}

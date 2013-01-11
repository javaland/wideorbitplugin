package nl.caliope.onairdesk.wideorbit;

import java.io.File;

import com.thoughtworks.xstream.XStream;

public class NowPlaying
{
	long sched_time;
	long air_time;
	Object stack_pos;
	String title;
	String artist;
	String trivia;
	String category;
	String cart;
	long intro;
	long end;
	String station;
	long duration;
	String media_type;
	long milliseconds_left;

	@Override
	public String toString()
	{
		return "NowPlaying [sched_time=" + sched_time + ", air_time=" + air_time + ", stack_pos="
				+ stack_pos + ", title=" + title + ", artist=" + artist + ", trivia=" + trivia
				+ ", category=" + category + ", cart=" + cart + ", intro=" + intro + ", end=" + end
				+ ", station=" + station + ", duration=" + duration + ", media_type=" + media_type
				+ ", milliseconds_left=" + milliseconds_left + "]";
	}

	public static NowPlaying read(File f)
	{
		XStream x = new XStream();
		
		// to read empty longs we need our own converter
		x.registerConverter(new LongConverter());
		
		x.alias("nowplaying", NowPlaying.class);
		try {
			return (NowPlaying) x.fromXML(f);
		} catch (ClassCastException ball) {
			return null;
		}
	}

	public static void main(String[] args)
	{
		NowPlaying p = NowPlaying.read(new File(
				"\\\\caliope.local\\DATA\\Temp\\wideorbit\\wideorbbit_nowplaying.xml"));
		System.out.println(p);
	}
}

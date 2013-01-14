package nl.caliope.onairdesk.wideorbit.xml;

public class NowPlaying
{
	private long sched_time;
	private long air_time;
	private Object stack_pos;
	private String title;
	private String artist;
	private String trivia;
	private String category;
	private String cart;
	private long intro;
	private long end;
	private String station;
	private long duration;
	private String media_type;
	private long milliseconds_left;

	public long getSched_time()
	{
		return sched_time;
	}

	public void setSched_time(long sched_time)
	{
		this.sched_time = sched_time;
	}

	public long getAir_time()
	{
		return air_time;
	}

	public void setAir_time(long air_time)
	{
		this.air_time = air_time;
	}

	public Object getStack_pos()
	{
		return stack_pos;
	}

	public void setStack_pos(Object stack_pos)
	{
		this.stack_pos = stack_pos;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getTrivia()
	{
		return trivia;
	}

	public void setTrivia(String trivia)
	{
		this.trivia = trivia;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getCart()
	{
		return cart;
	}

	public void setCart(String cart)
	{
		this.cart = cart;
	}

	public long getIntro()
	{
		return intro;
	}

	public void setIntro(long intro)
	{
		this.intro = intro;
	}

	public long getEnd()
	{
		return end;
	}

	public void setEnd(long end)
	{
		this.end = end;
	}

	public String getStation()
	{
		return station;
	}

	public void setStation(String station)
	{
		this.station = station;
	}

	public long getDuration()
	{
		return duration;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	public String getMedia_type()
	{
		return media_type;
	}

	public void setMedia_type(String media_type)
	{
		this.media_type = media_type;
	}

	public long getMilliseconds_left()
	{
		return milliseconds_left;
	}

	public void setMilliseconds_left(long milliseconds_left)
	{
		this.milliseconds_left = milliseconds_left;
	}

	@Override
	public String toString()
	{
		return "NowPlaying [sched_time=" + sched_time + ", air_time=" + air_time + ", stack_pos="
				+ stack_pos + ", title=" + title + ", artist=" + artist + ", trivia=" + trivia
				+ ", category=" + category + ", cart=" + cart + ", intro=" + intro + ", end=" + end
				+ ", station=" + station + ", duration=" + duration + ", media_type=" + media_type
				+ ", milliseconds_left=" + milliseconds_left + "]";
	}
}

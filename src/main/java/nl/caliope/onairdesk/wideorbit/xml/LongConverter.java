package nl.caliope.onairdesk.wideorbit.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class LongConverter implements Converter
{

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type)
	{
		return Long.class.equals(type) || long.class.equals(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
	{
		if (source == null)
			return;
		writer.setValue(source.toString());
	}

	@Override
	public Long unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
	{
		try {
			return Long.decode(reader.getValue());
		} catch (NumberFormatException ball) {
			return 0L;
		}
	}

}

package nl.caliope.onairdesk.wideorbit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import nl.caliope.onairdesk.model.Item;
import nl.caliope.onairdesk.provider.ItemProvider;
import nl.caliope.onairdesk.wideorbit.database.ItemRepository;

import org.json.JSONObject;

public class WideOrbitItemProvider extends ItemProvider
{

	private ItemRepository itemRepo;

	public WideOrbitItemProvider(JSONObject configuration)
	{
		this.itemRepo = new ItemRepository(configuration);
	}

	@Override
	public List<Item> getItems(Date date) throws IOException
	{
		try {
			return itemRepo.findAll();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Item byId(String automationid) throws IOException
	{
		try {
			return itemRepo.find(automationid);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Integer> getIdList() throws IOException
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
}

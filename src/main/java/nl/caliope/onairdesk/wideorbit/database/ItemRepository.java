package nl.caliope.onairdesk.wideorbit.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.caliope.onairdesk.model.Item;

import org.json.JSONObject;

public class ItemRepository extends Repository
{

	public static final String SQL_LIST_ITEMS = "SELECT "
			+ "\"public\".media_asset.media_asset_id as automationid, "
			+ "\"public\".media_asset.title as title, "
			+ "\"public\".media_asset.artist as artists, "
			+ "\"public\".category.description as categoryname "
			+ "FROM \"public\".media_asset "
			+ "LEFT OUTER JOIN \"public\".category ON \"public\".media_asset.category_id = \"public\".category.category_id";
	public static final String SQL_ITEM_BY_ID = SQL_LIST_ITEMS +
			" WHERE \"public\".media_asset.media_asset_id = ?";

	private PreparedStatement stmtListAll;
	private PreparedStatement stmtById;

	public ItemRepository(JSONObject configuration)
	{
		super(configuration);
	}

	public List<Item> findAll() throws SQLException
	{
		prepareStatements();

		List<Item> items = new ArrayList<Item>();
		ResultSet rs = this.stmtListAll.executeQuery();
		while (rs.next()) {
			Item item = retrieveItem(rs);
			if (item == null) {
				continue;
			}
			items.add(item);
		}

		return items;
	}

	public Item find(String automationid) throws SQLException
	{
		prepareStatements();

		this.stmtById.setInt(1, Integer.parseInt(automationid));
		ResultSet rs = this.stmtById.executeQuery();
		if (rs.next()) {
			return retrieveItem(rs);
		}

		return null;
	}

	private Item retrieveItem(ResultSet rs) throws SQLException
	{
		String automationid = rs.getString("automationid");
		String title = rs.getString("title");
		List<String> artists =
				Collections.singletonList(rs.getString("artists"));
		List<String[]> categories =
				Collections.singletonList(new String[]{rs.getString("categoryname")});

		Item item = new Item();
		item.setAutomationid(automationid);
		item.setTitle(title);
		item.setArtists(artists);
		item.setCategories(categories);

		return item;
	}

	private void prepareStatements() throws SQLException
	{
		stmtListAll = prepare(SQL_LIST_ITEMS);
		stmtById = prepare(SQL_ITEM_BY_ID);
	}
}

package raubach.sgud.resource;

import raubach.sgud.server.database.tables.pojos.Items;

import java.util.Map;

public class HeatmapData
{
	private Items item;
	private Map<String, Double> ratings;

	public Items getItem()
	{
		return item;
	}

	public void setItem(Items item)
	{
		this.item = item;
	}

	public Map<String, Double> getRatings()
	{
		return ratings;
	}

	public void setRatings(Map<String, Double> ratings)
	{
		this.ratings = ratings;
	}
}

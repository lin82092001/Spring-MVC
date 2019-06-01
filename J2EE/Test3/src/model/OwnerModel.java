package model;

public class OwnerModel
{
	private String name;
	private int id;
	private int ownerId;
	private int cash;
	private int asset;
	private int count;

	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}
	public void setOwnerId(int ownerId)
	{
		this.ownerId=ownerId;
	}
	
	public int getCash()
	{
		return cash;
	}
	public void setCash(int cash)
	{
		this.cash=cash;
	}
	
	public int getAsset()
	{
		return asset;
	}
	public void setAsset(int asset)
	{
		this.asset=asset;
	}
	
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count=count;
	}
}
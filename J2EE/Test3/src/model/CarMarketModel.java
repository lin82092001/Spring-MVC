package model;

public class CarMarketModel
{
	private String name;
	private int id;
	private int price;
	private int ownerId;
	private int cash;
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
	
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price=price;
	}
	
	public int getownerId()
	{
		return ownerId;
	}
	public void setownerId(int ownerId)
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
	
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count=count;
	}
	
}
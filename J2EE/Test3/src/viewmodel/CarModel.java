package viewmodel;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;


public class CarModel {
	private int id;
	private int ownerId;
	private int count;
	private int price;
	private String Carname;
	@NotEmpty(message="{error.empty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	
	public int getId()
	{
		return id;
	}	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}	
	public void setOwnerId(int ownerId)
	{
		this.ownerId=ownerId;
	}
	
	public int getCount()
	{
		return count;
	}	
	public void setCount(int count)
	{
		this.count=count;
	}
	
	public int getPrice()
	{
		return price;
	}	
	public void setPrice(int price)
	{
		this.price=price;
	}
	
	public String getCarName()
	{
		return Carname;
	}	
	public void setCarName(String Carname)
	{
		this.Carname=Carname;
	}
}

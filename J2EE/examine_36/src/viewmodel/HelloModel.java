package viewmodel;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;


public class HelloModel {
	private String name;
	private String car;
	private int count;
	
	@NotEmpty(message="{error.empty}")
	@Pattern(regexp="[a-zA-Z\u4E00-\u9FFF]+", message="{error.charOnly}")
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCar()
	{
		return car;
	}
	
	public void setCar(String car)
	{
		this.car= car;
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

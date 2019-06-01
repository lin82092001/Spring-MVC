package service.message;

public class HiMessage implements Message
{
	public String doMessage(String name)
	{
		String result;
		result="Hi, "+name;
		
		return result;
	}
}
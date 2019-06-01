package service.message;

public class HelloMessage implements Message
{
	public String doMessage(String name)
	{
		String result;
		result="Hello, "+name;
		
		return result;
	}
}
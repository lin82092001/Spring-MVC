package service.message;

public class HelloMessage implements Message
{
	public String doHello(String name)
	{
		String result;
		result="Hello, "+name;
		
		return result;
	}
}
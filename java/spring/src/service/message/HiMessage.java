package service.message;

public class HiMessage implements Message
{
	public String doHello(String name)
	{
		String result;
		result="Hi, "+name;
		
		return result;
	}
}
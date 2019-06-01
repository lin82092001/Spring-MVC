package service.message;

public class AlohaMessage implements Message
{
	public String doHello(String name)
	{
		String result;
		result="Aloha, "+name;
		
		return result;
	}
}
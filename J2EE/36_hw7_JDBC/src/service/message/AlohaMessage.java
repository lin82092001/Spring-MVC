package service.message;

public class AlohaMessage implements Message
{
	public String doMessage(String name)
	{
		String result;
		result="Aloha, "+name;
		
		return result;
	}
}
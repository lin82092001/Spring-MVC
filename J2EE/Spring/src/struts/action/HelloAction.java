package struts.action;

import com.opensymphony.xwork2.ActionSupport;

import service.message.HelloMessage;
import service.message.HiMessage;
import service.message.AlohaMessage;
import service.message.Message;

import service.mail.GoogleMail;
import service.mail.Mail;
import service.mail.MailAccount;

public class HelloAction extends ActionSupport
{
	private HelloForm helloform;
	
	public HelloForm getHelloForm()
	{
		return helloform;
	}
	public void setHelloForm(HelloForm helloform)
	{
		this.helloform= helloform;
	}
	
	public String doHello()
	{
		Message message=new HelloMessage();
		
		helloform.setResult(message.doHello(helloform.getName()));
		
		Mail mail=new GoogleMail();
		helloform.setStamp(mail.sendMail(helloform.getResult()));
		
		return SUCCESS;
	}
}
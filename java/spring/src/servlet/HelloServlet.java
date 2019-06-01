package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.message.HelloMessage;
import service.message.HiMessage;
import service.message.AlohaMessage;
import service.message.Message;

import service.mail.GoogleMail;
import service.mail.Mail;
import service.mail.MailAccount;

public class HelloServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String name=request.getParameter("name");
		String result;
		String stamp;
		
		Message message=new HelloMessage();
		//Message message=new HiMessage();
		//Message message=new AlohaMessage();
		result=message.doHello(name);
		
		Mail mail=new GoogleMail();
		stamp=mail.sendMail(result);
		
		request.setAttribute("stamp",stamp);	
		request.setAttribute("result",result);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
}
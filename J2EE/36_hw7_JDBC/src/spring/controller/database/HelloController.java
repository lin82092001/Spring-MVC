package spring.controller.database;

import javax.validation.Valid;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.fee.Fee;
import service.message.HelloMessage;
import service.message.HiMessage;
import service.message.AlohaMessage;
import service.message.Message;
import service.mail.GoogleMail;
import service.mail.Mail;
import service.mail.MailAccount;
import viewmodel.HelloModel;
import viewmodel.MessageModel;
import model.FeeModel;
import exceptions.NullAccountException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.database.HelloController")
@RequestMapping("/database")
public class HelloController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public ModelAndView hello()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		
		String HELLO=(String)context.getBean("HELLO");
		return new ModelAndView(HELLO);
	}
	
	@RequestMapping(value="/doMessage",method=RequestMethod.POST)
	public ModelAndView doMessage(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		Message message=(Message)context.getBean("message");		
		String result=message.doMessage(name);
		
		Mail mail=(Mail)context.getBean("mail");
		String stamp=mail.sendMail(result);
		
		Fee fee=(Fee)context.getBean("fee");
		FeeModel feeModel=new FeeModel();
		feeModel.setName(name);
		int count;
		
		try
		{
			feeModel=fee.find(feeModel);
			count=feeModel.getCount();
			if(count>=0)
			{
				feeModel.setCount(++count);
				fee.update(feeModel);
			}
		}
		catch(NullAccountException nullAccountException)
		{
			count=1;
			feeModel.setCount(count);
			try
			{
				fee.create(feeModel);
			}
			catch(Exception e)
			{
				feeErrors.add(new FieldError("HelloController","error.database",
						resource.getString("error.database")+"<br>"+e.getMessage()));
				return new ModelAndView(ERROR,"ErrorModel",feeErrors);
			}
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("HelloController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		MessageModel messageModel=(MessageModel)context.getBean("messageModel");
		messageModel.setResult(result);
		messageModel.setStamp(stamp);
		messageModel.setCount(count);
		
		String SUCCESS=(String)context.getBean("helloSUCCESS");
		return new ModelAndView(SUCCESS,"MessageModel",messageModel);
	}
}
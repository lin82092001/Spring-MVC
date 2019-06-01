package controller.test2;

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

import service.good.Good;
import service.message.HelloMessage;
import service.message.HiMessage;
import service.message.AlohaMessage;
import service.message.Message;
import service.mail.GoogleMail;
import service.mail.Mail;
import service.mail.MailAccount;
import viewmodel.HelloModel;
import viewmodel.MessageModel;
import model.GoodModel;
import exceptions.OneException;
import exceptions.ExceedException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test2.PayController")
@RequestMapping("/test2")
public class PayController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/pay",method=RequestMethod.GET)
	public ModelAndView pay()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		
		String PAY=(String)context.getBean("PAY");
		return new ModelAndView(PAY);
	}
	
	@RequestMapping(value="/doPay",method=RequestMethod.POST)
	public ModelAndView doPay(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		int count=helloModel.getCount();

		Message message=(Message)context.getBean("message");		
		String result=message.doMessage(name);
		
		Mail mail=(Mail)context.getBean("mail");
		
		Good fee=(Good)context.getBean("fee");
		GoodModel feeModel=new GoodModel();
		feeModel.setName(name);
		int count2;
		
		try
		{
			feeModel=fee.find(feeModel);
			count2=feeModel.getCount();
			if(count>count2)
			{
				throw new ExceedException();
			}
			else
			{
				mail.sendMail(result);
				feeModel.setCount(count2-count);
				fee.update(feeModel);
			}
			if((count2-count)==0)
			{
				fee.delete(feeModel);
			}			
		}
		catch(OneException e)
		{
			feeErrors.add(new FieldError("PayController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(ExceedException e)
		{
			feeErrors.add(new FieldError("PayController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("PayController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		MessageModel messageModel=(MessageModel)context.getBean("messageModel");
		messageModel.setResult(result);
		messageModel.setCount(count);
		
		String SUCCESS=(String)context.getBean("paySUCCESS");
		return new ModelAndView(SUCCESS,"MessageModel",messageModel);
	}
}
package controller.test1;

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

@Controller("Spring.test1.PayController")
@RequestMapping("/test1")
public class PayController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/pay",method=RequestMethod.GET)
	public ModelAndView pay()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
		
		String PAY=(String)context.getBean("PAY");
		return new ModelAndView(PAY);
	}
	
	@RequestMapping(value="/doPay",method=RequestMethod.POST)
	public ModelAndView doPay(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		String car=helloModel.getCar();

		Message message=(Message)context.getBean("message");		
		String result=message.doMessage(car)+name;
		
		Mail mail=(Mail)context.getBean("mail");
		
		Fee fee=(Fee)context.getBean("fee");
		FeeModel feeModel=new FeeModel();
		feeModel.setName(name);
		int count;
		
		try
		{
			feeModel=fee.find(feeModel);
			count=feeModel.getCount();
			if(count>0)
			{
				mail.sendMail(result);
				feeModel.setCount(--count);
				fee.update(feeModel);
			}
			if(count==0)
			{
				fee.delete(feeModel);
			}
		}
		catch(NullAccountException e)
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
		
		String SUCCESS=(String)context.getBean("paySUCCESS");
		return new ModelAndView(SUCCESS,"MessageModel",messageModel);
	}
}
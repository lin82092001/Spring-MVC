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

import viewmodel.HelloModel;
import viewmodel.MessageModel;
import model.GoodModel;
import exceptions.NullAccountException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test2.HelloController")
@RequestMapping("/test2")
public class HelloController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public ModelAndView hello()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		
		String HELLO=(String)context.getBean("HELLO");
		return new ModelAndView(HELLO);
	}
	
	@RequestMapping(value="/doMessage",method=RequestMethod.POST)
	public ModelAndView doMessage(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();		
		
		Good fee=(Good)context.getBean("fee");
		GoodModel feeModel=new GoodModel();
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
		messageModel.setName(name);
		messageModel.setCount(count);
		
		String SUCCESS=(String)context.getBean("helloSUCCESS");
		return new ModelAndView(SUCCESS,"MessageModel",messageModel);
	}
}
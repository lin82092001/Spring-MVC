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
import service.message.Message;

import viewmodel.HelloModel;
import viewmodel.MessageModel;
import model.FeeModel;
import exceptions.NullAccountException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test1.HelloController")
@RequestMapping("/test1")
public class HelloController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public ModelAndView hello()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
		
		String HELLO=(String)context.getBean("HELLO");
		return new ModelAndView(HELLO);
	}
	
	@RequestMapping(value="/doMessage",method=RequestMethod.POST)
	public ModelAndView doMessage(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		int count=helloModel.getCount();	
		
		Fee fee=(Fee)context.getBean("fee");
		FeeModel feeModel=new FeeModel();
		feeModel.setName(name);

		int count2=0;
		
		try
		{
			feeModel=fee.find(feeModel);
			count2=feeModel.getCount();
			if(count2>=0)
			{
				feeModel.setCount(count+count2);
				fee.update(feeModel);
			}
		}
		catch(NullAccountException nullAccountException)
		{
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
		messageModel.setCount(count+count2);
		
		String SUCCESS=(String)context.getBean("helloSUCCESS");
		return new ModelAndView(SUCCESS,"MessageModel",messageModel);
	}
}
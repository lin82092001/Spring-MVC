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
import model.GoodModel;

import exceptions.OneException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test2.QueryController")
@RequestMapping("/test2")
public class QueryController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public ModelAndView query()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		
		String QUERY=(String)context.getBean("QUERY");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value="/doQuery",method=RequestMethod.POST)
	public ModelAndView doQuery(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		List<FieldError>goodErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		
		Good good=(Good)context.getBean("fee");
		GoodModel goodModel=new GoodModel();
		goodModel.setName(name);
		
		try
		{
			goodModel=good.find(goodModel);
		}
		catch(OneException e)
		{
			goodErrors.add(new FieldError("QueryController",
					e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", goodErrors);			
		}
		catch(Exception e)
		{
			goodErrors.add(new FieldError("QueryController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",goodErrors);
		}
		
		String SUCCESS=(String)context.getBean("querySUCCESS");
		return new ModelAndView(SUCCESS,"GoodModel",goodModel);
	}
}
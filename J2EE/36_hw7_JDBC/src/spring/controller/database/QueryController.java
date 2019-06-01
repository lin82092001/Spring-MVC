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
import viewmodel.HelloModel;
import model.FeeModel;
import exceptions.NullAccountException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.database.QueryController")
@RequestMapping("/database")
public class QueryController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public ModelAndView query()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		
		String QUERY=(String)context.getBean("QUERY");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value="/doQuery",method=RequestMethod.POST)
	public ModelAndView doQuery(@Valid HelloModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		String name=helloModel.getName();
		
		Fee fee=(Fee)context.getBean("fee");
		FeeModel feeModel=new FeeModel();
		feeModel.setName(name);
		
		try
		{
			feeModel=fee.find(feeModel);
		}
		catch(NullAccountException e)
		{
			feeErrors.add(new FieldError("QueryController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);			
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("QueryController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		String SUCCESS=(String)context.getBean("querySUCCESS");
		return new ModelAndView(SUCCESS,"FeeModel",feeModel);
	}
}
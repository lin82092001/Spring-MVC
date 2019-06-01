package controller.test3;

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

import service.carmarket.CarMarket;
import viewmodel.CarModel;
import model.CarMarketModel;

import exceptions.OneException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.QueryCarController")
@RequestMapping("/test3")
public class QueryCarController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/queryCar",method=RequestMethod.GET)
	public ModelAndView queryCar()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		
		String QUERY=(String)context.getBean("QUERY");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value="/doQueryCar",method=RequestMethod.POST)
	public ModelAndView doQueryCar(CarModel helloModel)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		List<FieldError>goodErrors=new ArrayList<FieldError>();
		
		int ownerid=helloModel.getOwnerId();
		
		List <CarMarketModel> carMarketModel;
		CarMarket fee=(CarMarket)context.getBean("fee");		
		CarMarketModel feeModel=new CarMarketModel();
		feeModel.setownerId(ownerid);
		
		try
		{
			carMarketModel=fee.list(feeModel);
		}
		catch(OneException e)
		{
			goodErrors.add(new FieldError("QueryCarController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", goodErrors);			
		}
		catch(Exception e)
		{
			goodErrors.add(new FieldError("QueryCarController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",goodErrors);
		}
		
		String SUCCESS=(String)context.getBean("querycarSUCCESS");
		return new ModelAndView(SUCCESS,"CarMarketModel",carMarketModel);
	}
}
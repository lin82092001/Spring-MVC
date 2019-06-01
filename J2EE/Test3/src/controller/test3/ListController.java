package controller.test3;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.carmarket.CarMarket;
import model.CarMarketModel;
import exceptions.NullListException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.ListController")
@RequestMapping("/test3")
public class ListController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/doCarList",method=RequestMethod.GET)
	public ModelAndView doCarList()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		List<CarMarketModel>carListModel;
		CarMarket car=(CarMarket)context.getBean("fee");
		
		try
		{
			carListModel=car.list();
			if(carListModel.size()==0) 
				throw new NullListException();
		}
		catch(NullListException e)
		{
			feeErrors.add(new FieldError("ListController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);		
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("ListController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		String LIST=(String)context.getBean("listSUCCESS");
		return new ModelAndView(LIST,"CarListModel",carListModel);
	}
}
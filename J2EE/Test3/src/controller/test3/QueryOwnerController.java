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
import service.carmarket.Owner;
import viewmodel.CarModel;
import model.CarMarketModel;
import model.OwnerModel;
import exceptions.OneException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.QueryOwnerController")
@RequestMapping("/test3")
public class QueryOwnerController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/queryOwner",method=RequestMethod.GET)
	public ModelAndView queryOwner()
	{
		//ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		
		String QUERY=(String)context.getBean("QUERY2");
		return new ModelAndView(QUERY);
	}
	
	@RequestMapping(value="/doQueryOwner",method=RequestMethod.POST)
	public ModelAndView doQueryOwner(CarModel helloModel)
	{
		//ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		List<FieldError>goodErrors=new ArrayList<FieldError>();
		
		int ownerid=helloModel.getOwnerId();
		
		Owner owner=(Owner)context.getBean("testunit");
		OwnerModel ownerModel=new OwnerModel();
		ownerModel.setId(ownerid);
		
		try
		{
			ownerModel=owner.find(ownerModel);
		}
		catch(OneException e)
		{
			goodErrors.add(new FieldError("QueryOwnerController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", goodErrors);			
		}
		catch(Exception e)
		{
			goodErrors.add(new FieldError("QueryOwnerController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",goodErrors);
		}
		
		String SUCCESS=(String)context.getBean("queryownerSUCCESS");
		return new ModelAndView(SUCCESS,"CarMarketModel",ownerModel);
	}
}
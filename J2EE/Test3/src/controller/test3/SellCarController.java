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
import exceptions.MoneyException;
import exceptions.NullAccountException;
import exceptions.OneException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.SellCarController")
@RequestMapping("/test3")
public class SellCarController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/sellCar",method=RequestMethod.GET)
	public ModelAndView sellCar()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		
		String SELL=(String)context.getBean("SELL");
		return new ModelAndView(SELL);
	}
	
	@RequestMapping(value="/doSellCar",method=RequestMethod.POST)
	public ModelAndView doSellCar(@Valid CarModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		int ownerid=helloModel.getOwnerId();
		String Carname=helloModel.getCarName();
		int price=helloModel.getPrice();
		
		CarMarket fee=(CarMarket)context.getBean("fee");
		CarMarketModel feeModel=new CarMarketModel();
		feeModel.setownerId(ownerid);
		feeModel.setCarName(Carname);
		feeModel.setPrice(price);
		int cash;
		int count;
		int asset;
		
		try
		{
			feeModel=fee.find(feeModel);
			cash=feeModel.getCash();
			count=feeModel.getCount();
			asset=feeModel.getAsset();
			if(price>cash)
			{
				throw new MoneyException();
			}
			else
			{
				feeModel.setPrice(cash-price);
				feeModel.setCount(++count);
				feeModel.setAsset(price+asset);
				fee.update(feeModel);
			}			
		}
		catch(NullAccountException nullAccountException)
		{
			try
			{
				fee.create(feeModel);
			}
			catch(Exception e)
			{
				feeErrors.add(new FieldError("SellCarController","error.database",
						resource.getString("error.database")+"<br>"+e.getMessage()));
				return new ModelAndView(ERROR,"ErrorModel",feeErrors);
			}
		}
		catch(OneException e)
		{
			feeErrors.add(new FieldError("SellCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(MoneyException e)
		{
			feeErrors.add(new FieldError("SellCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("SellCarController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		
		String SUCCESS=(String)context.getBean("sellSUCCESS");
		return new ModelAndView(SUCCESS,"CarMarketModel",feeModel);
	}
}
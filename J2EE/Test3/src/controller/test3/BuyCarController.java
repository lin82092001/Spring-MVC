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
import exceptions.LowException;
import exceptions.MoneyException;
import exceptions.OneException;
import exceptions.SameException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.BuyCarController")
@RequestMapping("/test3")
public class BuyCarController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/buyCar",method=RequestMethod.GET)
	public ModelAndView pay()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		
		String PAY=(String)context.getBean("PAY");
		return new ModelAndView(PAY);
	}
	
	@RequestMapping(value="/doBuyCar",method=RequestMethod.POST)
	public ModelAndView doPay(@Valid CarModel helloModel,BindingResult bindingResult)
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test3/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		if(bindingResult.hasErrors())
		{
			return new ModelAndView(ERROR,"ErrorModel",bindingResult.getFieldErrors());
		}
		
		int ownerid=helloModel.getOwnerId();
		int id=helloModel.getId();
		int price=helloModel.getPrice();
		
		CarMarket fee=(CarMarket)context.getBean("fee");
		CarMarketModel feeModel=new CarMarketModel();
		feeModel.setownerId(ownerid);
		feeModel.setId(id);
		feeModel.setPrice(price);
		int cash;
		int count;
		int asset;
		int ownerid2;
		int price2;
		
		try
		{
			feeModel=fee.find(feeModel);
			ownerid2=feeModel.getownerId();
			price2=feeModel.getPrice();
			cash=feeModel.getCash();
			count=feeModel.getCount();
			asset=feeModel.getAsset();
			if(price>cash)
			{
				throw new MoneyException();
			}
			else if(ownerid2==ownerid)
			{
				throw new SameException();
			}
			else if(price<price2)
			{
				throw new LowException();
			}
			else
			{
				feeModel.setPrice(cash-price);
				feeModel.setCount(++count);
				feeModel.setAsset(price+asset);
				feeModel.setownerId(ownerid);
				fee.update(feeModel);
			}			
		}
		catch(OneException e)
		{
			feeErrors.add(new FieldError("BuyCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(MoneyException e)
		{
			feeErrors.add(new FieldError("BuyCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}catch(SameException e)
		{
			feeErrors.add(new FieldError("BuyCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(LowException e)
		{
			feeErrors.add(new FieldError("BuyCarController",e.getMessage(),
					resource.getString(e.getMessage())));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		catch(Exception e)
		{
			feeErrors.add(new FieldError("BuyCarController","error.database",
					resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR,"ErrorModel",feeErrors);
		}
		
		
		String SUCCESS=(String)context.getBean("paySUCCESS");
		return new ModelAndView(SUCCESS,"CarMarketModel",feeModel);
	}
}
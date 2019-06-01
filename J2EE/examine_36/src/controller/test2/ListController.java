package controller.test2;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.good.Good;
import model.GoodModel;
import exceptions.NullListException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test2.ListController")
@RequestMapping("/test2")
public class ListController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/doList",method=RequestMethod.GET)
	public ModelAndView doList()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		List<GoodModel>feeListModel;
		Good fee=(Good)context.getBean("fee");
		
		try
		{
			feeListModel=fee.list();
			if(feeListModel.size()==0)
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
		return new ModelAndView(LIST,"FeeListModel",feeListModel);
	}
}
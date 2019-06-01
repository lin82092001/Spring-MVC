package controller.test1;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.fee.Fee;
import model.FeeModel;
import exceptions.NullListException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test1.ListController")
@RequestMapping("/test1")
public class ListController
{
	ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
	String ERROR=(String)context.getBean("ERROR");
	ResourceBundle resource=ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value="/doList",method=RequestMethod.GET)
	public ModelAndView doList()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("controller/test1/spring.xml");
		List<FieldError>feeErrors=new ArrayList<FieldError>();
		
		List<FeeModel>feeListModel;
		Fee fee=(Fee)context.getBean("fee");
		
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
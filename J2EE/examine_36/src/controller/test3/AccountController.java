package controller.test3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.FieldError;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.ResourceBundle;

import viewmodel.MessageModel;
import viewmodel.AccountModel;
import model.OwnerModel;
import service.car.Owner;

import exceptions.NullOwnerException;

import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.AccountController")
@RequestMapping("/test3")
public class AccountController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView account() {
		
		String ACCOUNT = (String) context.getBean("ACCOUNT");
		return new ModelAndView(ACCOUNT);
	}
	
	@RequestMapping(value = "/doAccount", method = RequestMethod.POST)
	public ModelAndView doAccount(AccountModel accountModel) {
		
		List<FieldError> ownerErrors = new ArrayList<FieldError>();
		
		int id = accountModel.getId();
		
		Owner owner = (Owner) context.getBean("owner");
		OwnerModel ownerModel = new OwnerModel();
		ownerModel.setId(id);
		String name;
		int cash, asset, count;
		
		try {
			ownerModel = owner.find(ownerModel);
			name = ownerModel.getName();
			cash = ownerModel.getCash();
			asset = ownerModel.getAsset();
			count = ownerModel.getCount();
		}catch(NullOwnerException e) {
			ownerErrors.add(new FieldError("AccountController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(Exception e) {
			ownerErrors.add(new FieldError("AccountController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}
		
		MessageModel messageModel = (MessageModel) context.getBean("messageModel");
		messageModel.setId(id);
		messageModel.setName(name);
		messageModel.setCash(cash);
		messageModel.setAsset(asset);
		messageModel.setCount(count);
		
		String SUCCESS = (String) context.getBean("accountSUCCESS");
		return new ModelAndView(SUCCESS, "MessageModel", messageModel);
	}
	
}

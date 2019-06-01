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
import model.CarModel;
import model.OwnerModel;
import service.car.HCar;
import service.car.Owner;
import exceptions.CashNotEnoughException;
import exceptions.NullCarListException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("Spring.test3.CreateController")
@RequestMapping("/test3")
public class CreateController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		String CREATE = (String) context.getBean("CREATE");
		return new ModelAndView(CREATE);
	}
	
	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public ModelAndView doCreate(AccountModel accountModel) {
		
		List<FieldError> ownerErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		
		int id = accountModel.getId();
		String name = accountModel.getName();
		int price = accountModel.getPrice();
		
		Owner owner = (Owner) context.getBean("owner");
		HCar car = (HCar) context.getBean("car");
		OwnerModel ownerModel = new OwnerModel();
		CarModel carModel = new CarModel();
		ownerModel.setId(id);
		int count, cash, asset;
		
		try {
			ownerModel = owner.find(ownerModel);
			cash = ownerModel.getCash();
			asset = ownerModel.getAsset();
			count = ownerModel.getCount();
			
			if(cash < price){
				throw new CashNotEnoughException();
			}else {
				carModel.setName(name);
				carModel.setPrice(price);
				carModel.setOwnerId(id);
				car.create(carModel);
				ownerModel.setCash(cash - price);
				ownerModel.setAsset(asset + price);
				ownerModel.setCount(++count);
				owner.update(ownerModel);
			}
			
			carListModel = car.list(carModel);
			
			if(carListModel.size() == 0) {
				throw new NullCarListException();
			}
			
		}catch(CashNotEnoughException e) {
			ownerErrors.add(new FieldError("CreateController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(NullCarListException e) {
			ownerErrors.add(new FieldError("CreateController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(Exception e) {
			ownerErrors.add(new FieldError("CreateController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}
		Map<String, Object> Model = new HashMap<String, Object>();
		
		MessageModel messageModel = (MessageModel) context.getBean("messageModel");
		messageModel.setId(id);
		messageModel.setCount(count);
		
		Model.put("MessageModel", messageModel);
		Model.put("CarListModel", carListModel);
		
		String SUCCESS = (String) context.getBean("garageSUCCESS");
		return new ModelAndView(SUCCESS, "Model", Model);
	}
	
}

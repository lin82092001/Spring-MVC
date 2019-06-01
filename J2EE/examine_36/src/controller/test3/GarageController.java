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
import exceptions.NullCarListException;
import exceptions.NullOwnerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("Spring.test3.GarageController")
@RequestMapping("/test3")
public class GarageController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/garage", method = RequestMethod.GET)
	public ModelAndView garage() {
		
		String GARAGE = (String) context.getBean("GARAGE");
		return new ModelAndView(GARAGE);
	}
	
	@RequestMapping(value = "/doGarage", method = RequestMethod.POST)
	public ModelAndView doGarage(AccountModel accountModel) {
		
		List<FieldError> ownerErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		
		int id = accountModel.getId();
		
		Owner owner = (Owner) context.getBean("owner");
		HCar car = (HCar) context.getBean("car");
		OwnerModel ownerModel = new OwnerModel();
		CarModel carModel = new CarModel();
		ownerModel.setId(id);
		carModel.setOwnerId(id);
		int count;
		
		try {
			ownerModel = owner.find(ownerModel);
			count = ownerModel.getCount();
			
			carListModel = car.list(carModel);
			
			if(carListModel.size() == 0) {
				throw new NullCarListException();
			}
			
		}catch(NullOwnerException e) {
			ownerErrors.add(new FieldError("GarageController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(NullCarListException e) {
			ownerErrors.add(new FieldError("GarageController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(Exception e) {
			ownerErrors.add(new FieldError("GarageController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
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

package controller.test3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.FieldError;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.ResourceBundle;

import model.CarModel;
import service.car.HCar;

import exceptions.NullNiceListException;
import java.util.ArrayList;
import java.util.List;

@Controller("Spring.test3.CarListController")
@RequestMapping("/test3")
public class CarListController {

	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/doListCar", method = RequestMethod.GET)
	public ModelAndView doListCar() {
		
		List<FieldError> carErrors = new ArrayList<FieldError>();
		
		List<CarModel> carListModel;
		HCar car = (HCar) context.getBean("car");
		try {
			carListModel = car.list();
			
			if(carListModel.size() == 0) {
				throw new NullNiceListException();
			}
		}catch(NullNiceListException e) {
			carErrors.add(new FieldError("CarListController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", carErrors);
		}catch(Exception e) {
			carErrors.add(new FieldError("CarListController", "error.database", resource.getString("error.databse")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", carErrors);
		}
		
		String LIST = (String) context.getBean("listCarSUCCESS");
		return new ModelAndView(LIST, "CarListModel", carListModel);
	}
}

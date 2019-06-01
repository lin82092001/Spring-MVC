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
import exceptions.PriceNotEnoughException;
import exceptions.SameOwnerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("Spring.test3.TenderController")
@RequestMapping("/test3")
public class TenderController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/tender", method = RequestMethod.GET)
	public ModelAndView tender() {
		
		String TENDER = (String) context.getBean("TENDER");
		return new ModelAndView(TENDER);
	}
	
	@RequestMapping(value = "/doTender", method = RequestMethod.POST)
	public ModelAndView doTender(AccountModel accountModel) {
		
		List<FieldError> ownerErrors = new ArrayList<FieldError>();
		List<CarModel> carListModel;
		
		int ownerId = accountModel.getOwnerId(); //車商序號
		int id = accountModel.getId(); //汽車序號
		int price = accountModel.getPrice(); //投標金額
		
		Owner owner = (Owner) context.getBean("owner");
		HCar car = (HCar) context.getBean("car");
		OwnerModel ownerModel = new OwnerModel();
		OwnerModel findOwnerModel = new OwnerModel();
		CarModel carModel = new CarModel();
		ownerModel.setId(ownerId);
		carModel.setId(id);
		int count, cash, asset, findOwnerId, findPrice, findCash, findAsset;
		int findCount;
		
		try {
			carModel = car.findId(carModel); //找誰擁有這台車！
			ownerModel = owner.find(ownerModel); //找投標人的資訊
			findOwnerId = carModel.getOwnerId(); //XX號擁有這台車
			findOwnerModel.setId(findOwnerId);
			findOwnerModel = owner.find(findOwnerModel);
			findPrice = carModel.getPrice(); //這台車的價錢
			cash = ownerModel.getCash();  //投標人的財產
			asset = ownerModel.getAsset(); //投標人的汽車財產
			count = ownerModel.getCount(); //投標人的汽車數量
			findCash = findOwnerModel.getCash();
			findAsset = findOwnerModel.getAsset();
			findCount = findOwnerModel.getCount();
			
			if(ownerId == findOwnerId) {
				throw new SameOwnerException();
			}else if(cash < price){
				throw new CashNotEnoughException();
			}else if(price < findPrice) {
				throw new PriceNotEnoughException();
			}else {
				carModel.setId(id);
				carModel.setPrice(price);
				carModel.setOwnerId(ownerId);
				car.update(carModel);
				//---------------------------------
				findOwnerModel.setId(findOwnerId);
				findOwnerModel.setCash(findCash + price);
				findOwnerModel.setAsset(findAsset - findPrice);
				findOwnerModel.setCount(--findCount);
				owner.update(findOwnerModel);
				//---------------------------------
				ownerModel.setCash(cash - price);
				ownerModel.setAsset(asset + price);
				ownerModel.setCount(++count);
				owner.update(ownerModel);
			}
			
			carListModel = car.list(carModel);
			
			if(carListModel.size() == 0) {
				throw new NullCarListException();
			}
			
		}catch(SameOwnerException e) {
			ownerErrors.add(new FieldError("TenderController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(CashNotEnoughException e) {
			ownerErrors.add(new FieldError("TenderController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(PriceNotEnoughException e) {
			ownerErrors.add(new FieldError("TenderController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(NullCarListException e) {
			ownerErrors.add(new FieldError("TenderController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}catch(Exception e) {
			ownerErrors.add(new FieldError("TenderController", "error.database", resource.getString("error.database")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", ownerErrors);
		}
		Map<String, Object> Model = new HashMap<String, Object>();
		
		MessageModel messageModel = (MessageModel) context.getBean("messageModel");
		messageModel.setId(ownerId);
		messageModel.setCount(count);
		
		Model.put("MessageModel", messageModel);
		Model.put("CarListModel", carListModel);
		
		String SUCCESS = (String) context.getBean("garageSUCCESS");
		return new ModelAndView(SUCCESS, "Model", Model);
	}
	
}

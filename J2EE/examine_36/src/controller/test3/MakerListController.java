package controller.test3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.FieldError;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.ResourceBundle;

import model.OwnerModel;
import service.car.Owner;

import exceptions.NullNiceListException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("Spring.test3.MakerListController")
@RequestMapping("/test3")
public class MakerListController {

	ApplicationContext context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
	String ERROR = (String) context.getBean("ERROR");
	ResourceBundle resource = ResourceBundle.getBundle("resources.MessageDictionary");
	
	@RequestMapping(value = "/doListMaker", method = RequestMethod.GET)
	public ModelAndView doListMaker() {
		
		List<FieldError> makerErrors = new ArrayList<FieldError>();
		
		List<OwnerModel> ownerListModel;
		Owner owner = (Owner) context.getBean("owner");
		OwnerModel ownerModel = new OwnerModel();
		try {
			ownerListModel = owner.list();
			
			if(ownerListModel.size() == 0) {
				throw new NullNiceListException();
			}
			
			int allmaker = (int) ownerListModel.stream().mapToInt(OwnerModel::getId).count();
			int allcash = ownerListModel.stream().mapToInt(OwnerModel::getCash).sum();
			int allasset = ownerListModel.stream().mapToInt(OwnerModel::getAsset).sum();
			int allcount = ownerListModel.stream().mapToInt(OwnerModel::getCount).sum();
			
			ownerModel.setId(allmaker);
			ownerModel.setCash(allcash);
			ownerModel.setAsset(allasset);
			ownerModel.setCount(allcount);
			
			
		}catch(NullNiceListException e) {
			makerErrors.add(new FieldError("MakerListController", e.getMessage(), resource.getString(e.getMessage())));
			return new ModelAndView(ERROR, "ErrorModel", makerErrors);
		}catch(Exception e) {
			makerErrors.add(new FieldError("MakerListController", "error.database", resource.getString("error.databse")+"<br>"+e.getMessage()));
			return new ModelAndView(ERROR, "ErrorModel", makerErrors);
		}
		Map<String, Object> Model = new HashMap<String, Object>();
		
		Model.put("AllModel", ownerModel);
		Model.put("OwnerListModel", ownerListModel);
		
		String LIST = (String) context.getBean("listMakerSUCCESS");
		return new ModelAndView(LIST, "Model", Model);
	}
}

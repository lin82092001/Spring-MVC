package service.car;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import model.CarModel;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.NullHCarException;

public class HibernateCar implements HCar{
	
	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	private ApplicationContext context;
	String configXML;
	
	public HibernateCar() throws Exception {
		try {
			context = new ClassPathXmlApplicationContext("controller/test3/spring.xml");
			configXML = (String) context.getBean("HibernateConfig");
			Configuration hibernateConfig = new Configuration().configure(configXML);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
					applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public void create(CarModel carModel) throws Exception {
		try {
			//開啟Session，相當於開啟JDBC的Connection
			hibernateSession = hibernateSessionFactory.openSession();
			//Transaction表示一組會話操作
			Transaction tx = hibernateSession.beginTransaction();
			//將物件映射至資料庫表格中儲存
			hibernateSession.save(carModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void update(CarModel carModel) throws Exception {
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			Transaction tx = hibernateSession.beginTransaction();
			hibernateSession.update(carModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void delete(CarModel carModel) throws Exception {
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			Transaction tx = hibernateSession.beginTransaction();
			hibernateSession.delete(carModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public CarModel find(CarModel carModel) throws Exception {
		List<CarModel> carModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			//查詢Fee所有欄位
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			//查詢Fee中符合條件的欄位
			hibernateCriteria.add(Restrictions.eq("ownerId", carModel.getOwnerId()));
			carModelList = hibernateCriteria.list();
			Iterator iterator = carModelList.iterator();
			
			if(iterator.hasNext()) {
				carModel = (CarModel) iterator.next();
			}else {
				throw new NullHCarException();
			}
		}catch(Exception e) {
			throw e;
		}
		return carModel;
	}
	
	@Override
	public CarModel findId(CarModel carModel) throws Exception {
		List<CarModel> carModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			//查詢Fee所有欄位
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			//查詢Fee中符合條件的欄位
			hibernateCriteria.add(Restrictions.eq("id", carModel.getId()));
			carModelList = hibernateCriteria.list();
			Iterator iterator = carModelList.iterator();
			
			if(iterator.hasNext()) {
				carModel = (CarModel) iterator.next();
			}else {
				throw new NullHCarException();
			}
		}catch(Exception e) {
			throw e;
		}
		return carModel;
	}
	
	@Override
	public List<CarModel> list() throws Exception {
		List<CarModel> carModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			carModelList = hibernateCriteria.list();
		}catch(Exception e) {
			throw e;
		}
		
		return carModelList;
	}

	@Override
	public List<CarModel> list(CarModel carModel) throws Exception {
		List<CarModel> carModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(CarModel.class);
			hibernateCriteria.add(Restrictions.eq("ownerId", carModel.getOwnerId()));
			carModelList = hibernateCriteria.list();
		}catch(Exception e) {
			throw e;
		}
		
		return carModelList;
	}

}

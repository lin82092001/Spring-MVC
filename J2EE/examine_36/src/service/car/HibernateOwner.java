package service.car;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import model.OwnerModel;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.NullOwnerException;

public class HibernateOwner implements Owner{
	
	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	private ApplicationContext context;
	String configXML;
	
	public HibernateOwner() throws Exception {
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
	public void create(OwnerModel ownerModel) throws Exception {
		try {
			//開啟Session，相當於開啟JDBC的Connection
			hibernateSession = hibernateSessionFactory.openSession();
			//Transaction表示一組會話操作
			Transaction tx = hibernateSession.beginTransaction();
			//將物件映射至資料庫表格中儲存
			hibernateSession.save(ownerModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void update(OwnerModel ownerModel) throws Exception {
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			Transaction tx = hibernateSession.beginTransaction();
			hibernateSession.update(ownerModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void delete(OwnerModel ownerModel) throws Exception {
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			Transaction tx = hibernateSession.beginTransaction();
			hibernateSession.delete(ownerModel);
			tx.commit();
			hibernateSession.close();
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public OwnerModel find(OwnerModel ownerModel) throws Exception {
		List<OwnerModel> ownerModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			//查詢Fee所有欄位
			hibernateCriteria = hibernateSession.createCriteria(OwnerModel.class);
			//查詢Fee中符合條件的欄位
			hibernateCriteria.add(Restrictions.eq("id", ownerModel.getId()));
			ownerModelList = hibernateCriteria.list();
			Iterator iterator = ownerModelList.iterator();
			
			if(iterator.hasNext()) {
				ownerModel = (OwnerModel) iterator.next();
			}else {
				throw new NullOwnerException();
			}
		}catch(Exception e) {
			throw e;
		}
		return ownerModel;
	}

	@Override
	public List<OwnerModel> list() throws Exception {
		List<OwnerModel> ownerModelList;
		try {
			hibernateSession = hibernateSessionFactory.openSession();
			hibernateCriteria = hibernateSession.createCriteria(OwnerModel.class);
			ownerModelList = hibernateCriteria.list();
		}catch(Exception e) {
			throw e;
		}
		
		return ownerModelList;
	}

}

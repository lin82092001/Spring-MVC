package service.carmarket;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import model.CarMarketModel;

import java.util.Iterator;
import java.util.List;

import exceptions.NullAccountException;

public  class HibernateCarMarket implements CarMarket
{
	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateCarMarket ()throws Exception
	{
		try
		{
			Configuration hibernateConfig=new Configuration().configure("service/carmarket/feehibernate-configuration.xml");
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().
					applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory=hibernateConfig.buildSessionFactory(serviceRegistry);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void create(CarMarketModel feeModel)throws Exception
	{
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			Transaction tx=hibernateSession.beginTransaction();
			hibernateSession.save(feeModel);
			tx.commit();
			hibernateSession.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void update(CarMarketModel feeModel)throws Exception
	{
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			Transaction tx=hibernateSession.beginTransaction();
			hibernateSession.update(feeModel);
			tx.commit();
			hibernateSession.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void delete(CarMarketModel feeModel)throws Exception
	{
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			Transaction tx=hibernateSession.beginTransaction();
			hibernateSession.delete(feeModel);
			tx.commit();
			hibernateSession.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public CarMarketModel find(CarMarketModel feeModel)throws Exception
	{
		List<CarMarketModel> carModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(CarMarketModel.class);
			hibernateCriteria.add(Restrictions.eq("ownerId", feeModel.getownerId()));
			carModelList=hibernateCriteria.list();
			Iterator iterator=carModelList.iterator();
			
			if(iterator.hasNext())
			{			
				feeModel=(CarMarketModel)iterator.next();
			}
			else
			{
				throw new NullAccountException();
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return feeModel;
	}
	
	public List<CarMarketModel> list()throws Exception
	{
		List<CarMarketModel> carModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(CarMarketModel.class);
			carModelList=hibernateCriteria.list();
		}
		catch(Exception e)
		{
			throw e;
		}
		return carModelList;
	}
	
	public List<CarMarketModel> list(CarMarketModel feeModel)throws Exception
	{
		List<CarMarketModel> carModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(CarMarketModel.class);
			hibernateCriteria.add(Restrictions.eq("ownerId", feeModel.getownerId()));
			carModelList=hibernateCriteria.list();
		}
		catch(Exception e)
		{
			throw e;
		}
		return carModelList;
	}
}
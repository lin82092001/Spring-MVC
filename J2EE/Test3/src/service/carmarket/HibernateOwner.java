package service.carmarket;

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

import exceptions.NullAccountException;

public  class HibernateOwner implements Owner
{
	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateOwner ()throws Exception
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
	
	public void create(OwnerModel feeModel)throws Exception
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
	
	public void update(OwnerModel feeModel)throws Exception
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
	
	public void delete(OwnerModel feeModel)throws Exception
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
	
	public OwnerModel find(OwnerModel feeModel)throws Exception
	{
		List<OwnerModel> carModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(OwnerModel.class);
			hibernateCriteria.add(Restrictions.eq("id", feeModel.getId()));
			carModelList=hibernateCriteria.list();
			Iterator iterator=carModelList.iterator();
			
			if(iterator.hasNext())
			{			
				feeModel=(OwnerModel)iterator.next();
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
	
	public List<OwnerModel> list()throws Exception
	{
		List<OwnerModel> ownerModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(OwnerModel.class);
			ownerModelList=hibernateCriteria.list();
		}
		catch(Exception e)
		{
			throw e;
		}
		return ownerModelList;
	}
}
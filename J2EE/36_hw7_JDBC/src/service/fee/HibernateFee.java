package service.fee;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import model.FeeModel;

import java.util.Iterator;
import java.util.List;

import exceptions.NullAccountException;

public  class HibernateFee implements Fee
{
	private SessionFactory hibernateSessionFactory;
	private Session hibernateSession;
	private Criteria hibernateCriteria;
	
	public HibernateFee ()throws Exception
	{
		try
		{
			Configuration hibernateConfig=new Configuration().configure("service/fee/feehibernate-configuration.xml");
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().
					applySettings(hibernateConfig.getProperties()).build();
			hibernateSessionFactory=hibernateConfig.buildSessionFactory(serviceRegistry);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void create(FeeModel feeModel)throws Exception
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
	
	public void update(FeeModel feeModel)throws Exception
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
	
	public void delete(FeeModel feeModel)throws Exception
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
	
	public FeeModel find(FeeModel feeModel)throws Exception
	{
		List<FeeModel> feeModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(FeeModel.class);
			hibernateCriteria.add(Restrictions.eq("name", feeModel.getName()));
			feeModelList=hibernateCriteria.list();
			Iterator iterator=feeModelList.iterator();
			
			if(iterator.hasNext())
			{			
				feeModel=(FeeModel)iterator.next();
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
	
	public List<FeeModel> list()throws Exception
	{
		List<FeeModel> feeModelList;
		try
		{
			hibernateSession=hibernateSessionFactory.openSession();
			hibernateCriteria=hibernateSession.createCriteria(FeeModel.class);
			feeModelList=hibernateCriteria.list();
		}
		catch(Exception e)
		{
			throw e;
		}
		return feeModelList;
	}
}
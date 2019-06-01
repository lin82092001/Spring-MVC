package service.good;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.GoodModel;
import exceptions.NullAccountException;
import exceptions.OneException;

public  class JDBCGood implements Good
{
	private DataSource feeDS;
	private Connection dbConn;
	private PreparedStatement countStmt;
	private String sqlCommand;
	
	private int id;
	private String name;
	private int count;
	
	public JDBCGood ()throws Exception
	{
		try
		{
			ApplicationContext context=new ClassPathXmlApplicationContext("controller/test2/spring.xml");
			InitialContext ctxtJNDI=new InitialContext();
			feeDS=(DataSource)ctxtJNDI.lookup((String)context.getBean("DataSource"));
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@Override
	public void create(GoodModel feeModel)throws Exception
	{
		sqlCommand="INSERT INTO Fee(NAME,COUNT)VALUES(?,?)";
		try
		{
			dbConn=feeDS.getConnection();
			
			countStmt=dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, feeModel.getName());
			countStmt.setInt(2, feeModel.getCount());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@Override
	public void update(GoodModel feeModel)throws Exception
	{
		sqlCommand="UPDATE Fee SET COUNT=? WHERE NAME=?";
		try
		{
			dbConn=feeDS.getConnection();
			
			countStmt=dbConn.prepareStatement(sqlCommand);
			countStmt.setString(2, feeModel.getName());
			countStmt.setInt(1, feeModel.getCount());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@Override
	public void delete(GoodModel feeModel)throws Exception
	{
		sqlCommand="DELETE FROM Fee WHERE NAME=?";
		try
		{
			dbConn=feeDS.getConnection();
			
			countStmt=dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, feeModel.getName());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@Override
	public GoodModel find(GoodModel feeModel)throws Exception
	{
		sqlCommand="SELECT * FROM Fee WHERE NAME=?";
		try
		{
			dbConn=feeDS.getConnection();
			
			countStmt=dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, feeModel.getName());
			
			ResultSet countResultSet=countStmt.executeQuery();
			
			if(countResultSet.next())
			{
				id=countResultSet.getInt("ID");
				count=countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setCount(count);
			}
			else
			{
				throw new OneException();
			}
			countStmt.close();
			dbConn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		return feeModel;
	}
	
	@Override
	public List<GoodModel> list()throws Exception
	{
		sqlCommand="SELECT * FROM Fee Order by id";
		List<GoodModel>feeModelList=new ArrayList<GoodModel>();
		try
		{
			dbConn=feeDS.getConnection();		
			countStmt=dbConn.prepareStatement(sqlCommand);			
			ResultSet countResultSet=countStmt.executeQuery();
			
			while(countResultSet.next())
			{
				GoodModel feeModel=new GoodModel();
				id=countResultSet.getInt("ID");
				name=countResultSet.getString("NAME");
				count=countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setName(name);
				feeModel.setCount(count);
				feeModelList.add(feeModel);
			}
			countStmt.close();
			dbConn.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		return feeModelList;
	}
}
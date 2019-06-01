package service.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.NullCarException;
import model.CarModel;

public class JDBCCar implements Car{
	
	private DataSource carDS;
	private Connection dbConn;
	private PreparedStatement countStmt;
	private String sqlCommand;
	private ApplicationContext context;
	
	private int id;
	private String name;
	private int count;
	
	public JDBCCar() throws Exception {
		try {
			context = new ClassPathXmlApplicationContext("controller/test1/spring.xml");
			InitialContext ctxtJNDI = new InitialContext();
			carDS = (DataSource) ctxtJNDI.lookup( (String)context.getBean("DataSource") );
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public void create(CarModel carModel) throws Exception {
		sqlCommand = "INSERT INTO Car (NAME,COUNT) VALUES(?, ?)";
		try {
			dbConn = carDS.getConnection();
			
			countStmt = dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, carModel.getName());
			countStmt.setInt(2, carModel.getCount());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public void update(CarModel carModel) throws Exception {
		sqlCommand = "UPDATE Car SET COUNT=? WHERE NAME=?";
		try {
			dbConn = carDS.getConnection();
			
			countStmt = dbConn.prepareStatement(sqlCommand);
			countStmt.setInt(1, carModel.getCount());
			countStmt.setString(2, carModel.getName());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(CarModel carModel) throws Exception {
		sqlCommand = "DELETE FROM Car WHERE NAME=?";
		try {
			dbConn = carDS.getConnection();
			
			countStmt = dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, carModel.getName());
			
			countStmt.executeUpdate();
			countStmt.close();
			dbConn.close();
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public CarModel find(CarModel carModel) throws Exception {
		sqlCommand = "SELECT * FROM Car WHERE NAME=?";
		try {
			dbConn = carDS.getConnection();
			
			countStmt = dbConn.prepareStatement(sqlCommand);
			countStmt.setString(1, carModel.getName());
			
			ResultSet countResultSet = countStmt.executeQuery();
			
			if(countResultSet.next()) {
				id = countResultSet.getInt("ID");
				count = countResultSet.getInt("COUNT");
				
				carModel.setId(id);
				carModel.setCount(count);
			}else {
				throw new NullCarException();
			}
			
			countStmt.close();
			dbConn.close();
			
		}catch(Exception e) {
			throw e;
		}
		return carModel;
	}

	@Override
	public List<CarModel> list() throws Exception {
		sqlCommand = "SELECT * FROM Car Order by id";
		List<CarModel> carModelList = new ArrayList<CarModel>();
		
		try {
			dbConn = carDS.getConnection();
			countStmt = dbConn.prepareStatement(sqlCommand);
			ResultSet countResultSet = countStmt.executeQuery();
			
			while (countResultSet.next()) {
				CarModel carModel = new CarModel();
				
				id = countResultSet.getInt("ID");
				name = countResultSet.getString("NAME");
				count = countResultSet.getInt("COUNT");
				
				carModel.setId(id);
				carModel.setName(name);
				carModel.setCount(count);
				
				carModelList.add(carModel);
			}
			
			countStmt.close();
			dbConn.close();
			
		}catch(Exception e) {
			throw e;
		}
		return carModelList;
	}

}

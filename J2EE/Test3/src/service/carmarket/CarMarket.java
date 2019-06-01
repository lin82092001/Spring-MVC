package service.carmarket;

import java.util.List;

import model.CarMarketModel;

public interface CarMarket
{
	void create(CarMarketModel feeModel)throws Exception;
	void update(CarMarketModel feeModel)throws Exception;
	void delete(CarMarketModel feeModel)throws Exception;
	CarMarketModel find(CarMarketModel feeModel)throws Exception;
	List<CarMarketModel> list() throws Exception;
	List<CarMarketModel> list(CarMarketModel feeModel) throws Exception;
}
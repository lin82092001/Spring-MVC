package service.good;

import java.util.List;

import model.GoodModel;

public interface Good
{
	void create(GoodModel feeModel)throws Exception;
	void update(GoodModel feeModel)throws Exception;
	void delete(GoodModel feeModel)throws Exception;
	GoodModel find(GoodModel feeModel)throws Exception;
	List<GoodModel> list() throws Exception;
}
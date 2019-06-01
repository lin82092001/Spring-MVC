package service.carmarket;

import java.util.List;

import model.OwnerModel;

public interface Owner
{
	void create(OwnerModel feeModel)throws Exception;
	void update(OwnerModel feeModel)throws Exception;
	void delete(OwnerModel feeModel)throws Exception;
	OwnerModel find(OwnerModel feeModel)throws Exception;
	List<OwnerModel> list() throws Exception;
}
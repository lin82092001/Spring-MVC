package service.car;

import java.util.List;

import model.OwnerModel;

public interface Owner {
	void create(OwnerModel ownerModel) throws Exception;
	void update(OwnerModel ownerModel) throws Exception;
	void delete(OwnerModel ownerModel) throws Exception;
	OwnerModel find(OwnerModel ownerModel) throws Exception;
	List<OwnerModel> list() throws Exception;
}

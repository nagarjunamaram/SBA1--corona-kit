package com.iiht.evaluation.coronokit.service;

import java.sql.SQLException;
import java.util.List;

import com.iiht.evaluation.coronokit.exception.CoronakitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public interface CoronakitService {

	ProductMaster validateAndAdd(ProductMaster id) throws CoronakitException, SQLException;
	ProductMaster validateAndSave(ProductMaster id) throws CoronakitException, SQLException;
	
	boolean deleteproduct(int id) throws CoronakitException, SQLException;
	
	ProductMaster getProductbyId(int id) throws CoronakitException, SQLException;
	List<ProductMaster> getAllProducts() throws CoronakitException, SQLException;
}

package com.iiht.evaluation.coronokit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.exception.CoronakitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class CoronaKitServiceImpl implements CoronakitService{
ProductMasterDao productDao;
String jdbcURL;
String jdbcUsername;
String jdbcPassword;


public CoronaKitServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
    this.jdbcURL = jdbcURL;
    this.jdbcUsername = jdbcUsername;
    this.jdbcPassword = jdbcPassword;
    this.productDao=new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
}
	@Override
	public ProductMaster validateAndAdd(ProductMaster product) throws CoronakitException, SQLException {
		if(isValidProduct(product)) {
			productDao.add(product);
		}
		return product;	
	}

	private boolean isValidProduct(ProductMaster product) {
		List<String> errMsgs = new ArrayList<>();
		boolean isValid=true;
		if(product!=null) {
			if(!isValidProductId(product.getId())) {
				isValid=false;
				errMsgs.add("product Id must be a positive non-repetative number");
			}		
		}
		return isValid;
	}
		
	
	private boolean isValidProductId(Integer id) {
		return id!=null && id>0;
	}
	@Override
	public ProductMaster validateAndSave(ProductMaster product) throws CoronakitException, SQLException {
		if(isValidProduct(product)) {
			productDao.save(product);
		}
		return product;	
	}

	@Override
	public boolean deleteproduct(int id) throws CoronakitException, SQLException {
		
		return productDao.deleteById(id);
	}

	@Override
	public ProductMaster getProductbyId(int id) throws CoronakitException, SQLException {
		return productDao.getById(id);
	
	}

	@Override
	public List<ProductMaster> getAllProducts() throws CoronakitException, SQLException {				
				return productDao.getAllproducts();			
		
	}

}

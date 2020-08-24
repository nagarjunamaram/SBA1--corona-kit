package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.exception.CoronakitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;


public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	

	public static final String INS_PRDS_QRY = "INSERT INTO products(pid,productName,cost,productDescription) VALUES(?,?,?,?)";
	public static final String UPD_PRDS_QRY = "UPDATE products SET productName=?,cost=?,productDescription=? WHERE pid=?";
	public static final String DEL_PRDS_QRY = "DELETE FROM products WHERE pid=?";
	public static final String GET_BY_ID_PRDS_QRY = "SELECT pid,productName,cost,productDescription FROM products WHERE pid=?";
	public static final String GET_ALL_PRDS_QRY = "SELECT pid,productName,cost,productDescription FROM products";


	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	public ProductMaster add(ProductMaster product) throws CoronakitException, SQLException  {
		if (product != null) {
			this.connect();
			try ( 	Connection con = this.jdbcConnection;
					PreparedStatement pst = con.prepareStatement(INS_PRDS_QRY);) {		
				
				pst.setInt(1, product.getId());
				pst.setString(2, product.getProductName());
				pst.setString(3, product.getCost());
				pst.setString(4, product.getProductDescription());
				
				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new CoronakitException("An error occured, Could not add the product details!");
			}
		}
		return product;
	}
	
	public ProductMaster save(ProductMaster product) throws CoronakitException, SQLException  {
		if (product != null) {
			this.connect();
			try (Connection con = this.jdbcConnection;
					PreparedStatement pst = con.prepareStatement(UPD_PRDS_QRY);) {	
				
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getCost());			
				pst.setString(3, product.getProductDescription());
				pst.setInt(4, product.getId());
				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new CoronakitException("An error occured, Could not save the product details!");
			}
		}
		return product;
	}
	public boolean deleteById(int productId) throws CoronakitException, SQLException {
		boolean isDeleted = false;
		this.connect();
		try (Connection con = this.jdbcConnection;
				PreparedStatement pst = con.prepareStatement(DEL_PRDS_QRY);) {	


			pst.setInt(1, productId);

			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new CoronakitException("An error occured, Could not delete the product details!");
		}

		return isDeleted;
	}
	public ProductMaster getById(int productId) throws CoronakitException, SQLException {
		ProductMaster product=null;
		this.connect();
		try (Connection con = this.jdbcConnection;
				PreparedStatement pst = con.prepareStatement(GET_BY_ID_PRDS_QRY);) {	
	

			pst.setInt(1, productId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getString(3));
				product.setProductDescription(rs.getString(4));			
			}
			
		} catch (SQLException exp) {
			throw new CoronakitException("An error occured, Could not retrive the product details!");
		}
		
		return product;
	}
	public List<ProductMaster> getAllproducts() throws SQLException {
		List<ProductMaster> products = new ArrayList<>();
		this.connect();
		try (Connection con = this.jdbcConnection;
				PreparedStatement pst = con.prepareStatement(GET_ALL_PRDS_QRY);) {		

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getString(3));
				product.setProductDescription(rs.getString(4));		
			
				products.add(product);
			}
			
			if(products.isEmpty()) {
				products=null;
			}
		
		return products;
	}

	}	
}
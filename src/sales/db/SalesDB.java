package sales.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bigbox.business.Sales;
import bigbox.business.Store;
import bigbox.db.DBUtil;
import bigbox.util.Validator;

public class SalesDB implements SalesDAO{
	
	Scanner sc = new Scanner(System.in);
	
	public ArrayList<Sales> getAllSales() { 
		ArrayList<Sales> sales = new ArrayList<>();
		String sql = "select StoreID, StoreNumber, DivisionID, Name, sum(Sales), year "
						+ " from stores s, store_sales ss "
						+ " where s.ID = ss.StoreID "
						+ " group by (StoreNumber); ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
			while (rs.next()){
        		String strId = rs.getString(1);
        		String strNumber = rs.getString(2);
        		int divNumber = rs.getInt(3);
        		String name = rs.getString(4);
				Double totalSales = rs.getDouble(5);
				int year = rs.getInt(6);
                Sales s = new Sales(strId, strNumber, divNumber, name, totalSales, year );
                sales.add(s);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
		}
        return sales;
    }
	
	public ArrayList<Sales> getAllSalesForYear() { 
		ArrayList<Sales> sales = new ArrayList<>();
		String sql = "select StoreID, StoreNumber, DivisionID, Name, sum(Sales), year "
						+ " from stores s, store_sales ss "
						+ " where s.ID = ss.StoreID "
						+ " group by year, StoreNumber; ";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
			while (rs.next()){
        		String strId = rs.getString(1);
        		String strNumber = rs.getString(2);
        		int divId = rs.getInt(3);
        		String name = rs.getString(4);
				Double totalSales = rs.getDouble(5);
				int year = rs.getInt(6);
                Sales s = new Sales(strId, strNumber, divId, name, totalSales, year );
                sales.add(s);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
		}
        return sales;
    }
	public ArrayList<Sales> getAllSalesForDivision() { 
		ArrayList<Sales> sales = new ArrayList<>();
		String sql = "SELECT DivisionNumber, concat('$', format(sum(sales), 2)) as Total "
						 + " FROM stores s, store_sales ss, divisions d "
						 + " where s.ID = ss.StoreID "
						 + " and s.DivisionID = d.ID "
						 + " group by(d.ID); ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
			while (rs.next()){
        		String divNumber = rs.getString(1);
				String totalSales = rs.getString(2);
                Sales s = new Sales(divNumber, totalSales);
                sales.add(s);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
		}
        return sales;
    }
	public void addStore() {
		String divNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
			if (divNo.equalsIgnoreCase("001"))
				divNo = "1";
			else if (divNo.equalsIgnoreCase("111"))
				divNo = "2";
		String strNo = Validator.getStringNumeric(sc, "Enter store number:  ", 5);
		String name = Validator.getString(sc, "Enter store name:  ");
		String address = Validator.getString(sc, "Enter store address:  ");
		String city = Validator.getString(sc, "Enter store city:  ");
		String state = Validator.getString(sc, "Enter store state:  ");
		String zip = Validator.getString(sc, "Enter store zip:  ");
        
		String sql
                = "INSERT INTO stores (DivisionID, StoreNumber, Name, Address, City, State, ZipCode) "
                + "VALUES (?,?,?,?,?,?,?)";
        try ( Connection connection = DBUtil.getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, divNo);
            ps.setString(2, strNo);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, zip);
            ps.executeUpdate();
        	} 
        catch (SQLException e) {
            System.out.println(e);;
        }
    }
	public void delete(String divNo, String strNo) 
	{
		if (divNo.equalsIgnoreCase("001"))
			divNo = "1";
		else if (divNo.equalsIgnoreCase("111"))
			divNo = "2";
		else 
			divNo = "3";
		
		String sql = "DELETE FROM stores "
                   + "WHERE DivisionID = ? AND StoreNumber = ?";
		try ( Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
        	ps.setString(1, divNo);
        	ps.setString(2, strNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);;
        }
    }
	public void getStoresByDivision(String dNo) {        
		if (dNo.equalsIgnoreCase("001"))
			dNo = "1";
		else if (dNo.equalsIgnoreCase("111"))
			dNo = "2";
		else 
			dNo = "3";
		String sql = "SELECT * "
				+ "FROM stores "
				+ "WHERE DivisionID = ?"; 
		try( Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
        	ps.setString(1, dNo);	
            ResultSet rs = ps.executeQuery();
        
        	while (rs.next())
        	{
        		int id = rs.getInt("ID");
                String divNo = rs.getString("DivisionID");
				String strNo = rs.getString("StoreNumber");
                String name = rs.getString("Name");
				String address = rs.getString("Address");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zip = rs.getString("ZipCode");
                Store s = new Store(id, divNo, strNo, name, address, city, state, zip);
                System.out.println(s);
        	}
        }
         catch (SQLException e) {
            System.out.println(e);
        }
	}
}


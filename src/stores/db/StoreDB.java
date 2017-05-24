package stores.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import bigbox.business.Division;
import bigbox.business.Store;
import bigbox.db.DBUtil;

public class StoreDB implements StoreDAO{
	
	Scanner sc = new Scanner(System.in);
	public static HashMap<String,Division> divisionsNbrMap;
	
	public ArrayList<Store> getAllStores() { 
			
			ArrayList<Store> stores = new ArrayList<>();
			String sql = "SELECT * FROM stores ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					 ResultSet rs = ps.executeQuery()) {
	        	while (rs.next()){
	                int divId = rs.getInt(2);
					String strNo = rs.getString(3);
	                String name = rs.getString(4);
					String address = rs.getString(5);
					String city = rs.getString(6);
					String state = rs.getString(7);
					String zip = rs.getString(8);
	                Store s = new Store(divId, strNo, name, address, city, state, zip);
	                stores.add(s);
	        	}
	        } 
			catch (SQLException e) {
	            System.out.println(e);
	        }
	        return stores;
	    }

	public void addStore(Store s) {
		String sql
                = "INSERT INTO stores (DivisionID, StoreNumber, Name, Address, City, State, ZipCode) "
                + "VALUES (?,?,?,?,?,?,?)";
        try ( Connection connection = DBUtil.getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, s.getDivId());
            ps.setString(2, s.getStrNo());
            ps.setString(3, s.getName());
            ps.setString(4, s.getAddress());
            ps.setString(5, s.getCity());
            ps.setString(6, s.getState());
            ps.setString(7, s.getZip());
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }
	
	public void delete(int divId, String strNo) 
	{
		String sql = "DELETE FROM stores "
                   + "WHERE DivisionID = ? AND StoreNumber = ?";
		try ( Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
        	ps.setInt(1, divId);
        	ps.setString(2, strNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);;
        }
    }
}


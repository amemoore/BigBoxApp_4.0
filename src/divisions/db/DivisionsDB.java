package divisions.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import bigbox.business.Division;
import bigbox.db.DBUtil;

public class DivisionsDB implements DivisionsDAO{
	
	Scanner sc = new Scanner(System.in);
	
	public ArrayList<Division> getAllDivisions(){
		ArrayList<Division> divisions = new ArrayList<>();
		String sql = "SELECT * FROM divisions "; 
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int divId = rs.getInt(1);
				String divNo = rs.getString(2);
				String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				Division d = new Division(divId, divNo, name, address, city, state, zip);
				divisions.add(d);
			}
	}
    catch (SQLException e) {
            System.out.println(e);
    } 	
	return divisions;
	}
}


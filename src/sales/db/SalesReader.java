package sales.db;
import java.util.ArrayList;
import bigbox.business.Sales;

public interface SalesReader {

	public ArrayList<Sales> getAllSales(); 
	
	public ArrayList<Sales> getAllSalesForYear(); 
	
	public ArrayList<Sales> getAllSalesForDivision(); 
}

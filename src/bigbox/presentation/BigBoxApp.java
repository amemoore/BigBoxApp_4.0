package bigbox.presentation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import bigbox.business.Division;
import bigbox.business.Sales;
import bigbox.business.Store;
import bigbox.db.DAOFactory;
import bigbox.util.Validator;
import divisions.db.DivisionsDAO;
import sales.db.SalesDAO;
import stores.db.StoreDAO;

public class BigBoxApp {
	
	private static StoreDAO storeDAO = null;
	private static DivisionsDAO divisionsDAO = null;
	private static SalesDAO salesDAO = null;
	private static Scanner sc;
	private static HashMap<Integer,Division> divisionsIDMap = new HashMap<Integer,Division>();
	private static HashMap<String,Division> divisionsNbrMap = new HashMap<String,Division>();
	private static Division d;
	private static boolean isValid = false;
	
	public static void main(String[] args){
		
		System.out.println("Welcome to the Big Box App 4 using MySQl");
		System.out.println();
		
		storeDAO = DAOFactory.getStoreDAO();
		divisionsDAO = DAOFactory.getDivisionsDAO();
		salesDAO = DAOFactory.getSalesDAO();
		populateDivisionsMap();
		sc = new Scanner(System.in);
		String operation = "";
		
		displayMenu();
		
		
		while (isValid == false){
			
			operation = displayPrompt();

			if (operation.equalsIgnoreCase("list")){
				listStores();
			}
			else if (operation.equalsIgnoreCase("div") || operation.equalsIgnoreCase("division")){	
				listStoresByDivision();
			}	
			else if (operation.equalsIgnoreCase("add")){
				addStores();
			}
			else if (operation.equalsIgnoreCase("del")){
				deleteStores();
			}
			else if (operation.equalsIgnoreCase("sales")){
				listSales();
			}
			else if (operation.equalsIgnoreCase("yearsales")){
				listSalesForYear();
			}
			else if (operation.equalsIgnoreCase("divsales")){
				listSalesForDivision();
			}
			else if (operation.equalsIgnoreCase("help")){
				displayMenu();
			}
			else if(operation.equalsIgnoreCase("exit")){
				exit();
			}
			else 
				System.out.println("Invalid input.");
				System.out.println();
		}
		sc.close();
	}
	public static void displayMenu()
    {
        System.out.println("list  		-List all stores");
        System.out.println("div   		-List all stores for a division");
        System.out.println("add   		-Add a store");
        System.out.println("del   		-Delete a store");
        System.out.println("sales   	-List sale for a store");
        System.out.println("yearsales	-List sale for a store for a particular year");
        System.out.println("divsales   	-List total sales for a division");
        System.out.println("help  		-Show this menu");
        System.out.println("exit  		-Exit this application");
        System.out.println();
    }
	
	public static String displayPrompt(){
		String operation = Validator.getString(sc, "Enter a command:  ");
		System.out.println();
		return operation;
	}
	
	private static void populateDivisionsMap() {
		ArrayList<Division> divs = divisionsDAO.getAllDivisions();
		for (Division d: divs) {
			divisionsIDMap.put(d.getDivId(), d);
			divisionsNbrMap.put(d.getDivNo(), d);
		}
	}
	
	private static void listStores() {
		ArrayList<Store> stores = storeDAO.getAllStores();
		if (stores==null||stores.isEmpty())
			System.out.println("Uh-Oh, no stores were returned from the database.");
		else {
			System.out.println("Div Number\tStore number\tName");
			System.out.println("==========\t============\t==================");
			for (Store s: stores) {
				Division d = divisionsIDMap.get(s.getDivId());
				System.out.println(d.getDivNo()+"\t\t"
						+ ""+s.getStrNo()+"\t\t"+s.getName());
			}
		}
		System.out.println();
	}
	
	private static void listStoresByDivision(){
		String divNo = getUserDivNo();
		ArrayList<Store> stores = storeDAO.getAllStores();
		if (stores==null||stores.isEmpty())
			System.out.println("Uh-Oh, no stores were returned from the database.");
		else {
			System.out.println("Division No.\tStore No.\tName\\City");
			System.out.println("==========\t============\t==================================");
			for (Store s: stores) {
				Division d = divisionsIDMap.get(s.getDivId());
				if (divNo.equalsIgnoreCase(d.getDivNo())){
					System.out.println(d.getDivNo() + "\t\t" + ""+s.getStrNo() + 
										"\t\t" + s.getName() + "\\" + s.getCity());
			}
		}
		System.out.println();
	}
}
	
	private static void addStores(){
		String strNo = getUserStrNo();
		String divNo = getUserDivNo();
		d = divisionsNbrMap.get(divNo);
		d = getDivisionForId(divNo);
		int divId = d.getDivId();
		String name = Validator.getString(sc, "Enter store name:  ");
		String address = Validator.getString(sc, "Enter store address:  ");
		String city = Validator.getString(sc, "Enter store city:  ");
		String state = Validator.getString(sc, "Enter store state:  ", 2);
		String zip = Validator.getString(sc, "Enter store zip:  ");
		Store s = (new Store(divId, strNo, name, address, city, state, zip));
		storeDAO.addStore(s);
	}
	
	private static void deleteStores(){
		String strNo = getUserStrNo();
		String divNo = getUserDivNo();
		d = divisionsNbrMap.get(divNo);
		d = getDivisionForId(divNo);
		int divId = d.getDivId();
		storeDAO.delete(divId, strNo);
	}
	
	public static String getUserStrNo(){
		String strNo = Validator.getStringNumeric(sc, "Enter store number:  ", 5);
		System.out.println();
		//Check database.
		return strNo;
	}
	
	public static String getUserDivNo(){
		String divNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
		System.out.println();
		return divNo;
	}
	
	public static Division getDivisionForId(String divNo){
		d = divisionsNbrMap.get(divNo);
		boolean isValid = false;
		while (isValid==false) {
			if (d!=null){
				System.out.println("Yes, division exists.");
				isValid = true;
			}
			else{
				System.out.println("Division does not exist.");
				String dNo = Validator.getStringNumeric(sc, "Enter division number:  ", 3);
				d = divisionsNbrMap.get(dNo);
			}
		}
		return d;
	}
	
	private static void listSales() {
		String strNo = getUserStrNo();
		String divNo = getUserDivNo();
		d = divisionsNbrMap.get(divNo);
		d = getDivisionForId(divNo);
		int divId = d.getDivId();
		ArrayList<Sales> sales = salesDAO.getAllSales();
		if (sales==null||sales.isEmpty())
			System.out.println("Uh-Oh, no sales were returned from the database.");
		else {
			System.out.println("Store Name\tStoreNumber\tSales");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
			for (Sales s: sales) {
				if ((s.getStoreNumber().equalsIgnoreCase(strNo)) || (d.getDivId()==divId)) {
					System.out.println(s.getStoreNumber() + " \t\t" + toCurrency(s.getSales()) + " \t" + s.getName());
				}
			}
		}
	}
	
	private static void listSalesForYear() {
		String strNo = getUserStrNo();
		String divNo = getUserDivNo();
		int userYear = Validator.getInt(sc, "What year would you like sales data for?");
		d = getDivisionForId(divNo);
		int divId = d.getDivId();
		ArrayList<Sales> sales = salesDAO.getAllSalesForYear();
		if (sales==null||sales.isEmpty())
			System.out.println("Uh-Oh, no sales were returned from the database.");
		else {
			System.out.println("Store No.\tYear\tStoreName\t\tSales");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			for (Sales s: sales) {
				if (userYear==2016) {
					if ((s.getStoreNumber().equalsIgnoreCase(strNo)) && (s.getDivId()==divId)&& 
							((s.getYear())==2016)){
								System.out.println(s.getStoreNumber() + "\t\t" +s.getYear() + "\t" + s.getName()
														+ "\t" + toCurrency(s.getSales())); 
					}
				}
				else if (userYear==2017) {
					if ((s.getStoreNumber().equalsIgnoreCase(strNo)) && (s.getDivId()==divId)&& 
							((s.getYear())==2017)){
						System.out.println(s.getStoreNumber() + "\t\t" +s.getYear() + "\t" + s.getName()
													+ "\t" + toCurrency(s.getSales())); 
				}
			}
		}
	}
}
	private static void listSalesForDivision() {
		String divNo = getUserDivNo();
		ArrayList<Sales> sales = salesDAO.getAllSalesForDivision();
		if (sales==null||sales.isEmpty())
			System.out.println("Uh-Oh, no sales were returned from the database.");
		else {
			System.out.println("Division No.\tTotal Sales");
			System.out.println("+++++++++++++++++++++++++++++++++++");
			for (Sales s: sales) {
				if (s.getDivNumber().equalsIgnoreCase(divNo)) {
					System.out.println(s.getDivNumber() + " \t\t" + (s.getTotalSales()));
				}
			}
		}
	}
	private static String toCurrency(double sales){
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String salesFormatted = currency.format(sales);
		return salesFormatted;
	}
	
	private static boolean exit(){
		System.out.println("Bye!");
		isValid = true;
		return isValid;
	}
}	
	

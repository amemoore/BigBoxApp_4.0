package bigbox.business;

public class Sales {
	
	private String storeId;
	private String storeNumber;
	private int year;
	private String week;
	private Double sales;
	private int divId;
	private String name;
	private String divNumber;
	private String totalSales;
	
	public Sales (String storeIdIn, String storeNumberIn, int yearIn, String weekIn, Double salesIn){
		storeId = storeIdIn;
		storeNumber = storeNumberIn;
		year = yearIn;
		week = weekIn;
		sales = salesIn;
		year = yearIn;
	}
	public Sales (String storeNumberIn, Double salesIn){
		storeNumber = storeNumberIn;
		sales = salesIn;
	}
	public Sales (String storeIdIn, String storeNumberIn, int divIdIn, String nameIn, double salesIn, int yearIn){
		storeId = storeIdIn;
		storeNumber = storeNumberIn;
		divId= (divIdIn);
		name = (nameIn);
		sales = salesIn;
		year = yearIn;
	}
	public Sales (String divNumberIn, String totalSalesIn){
		divNumber = divNumberIn;
		totalSales = totalSalesIn;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}
	
	public String getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	public int getDivId() {
		return divId;
	}
	public void setDivId(int divId) {
		this.divId = divId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDivNumber() {
		return divNumber;
	}
	public void setDivNumber(String divNumber) {
		this.divNumber = divNumber;
	}
	public String getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(String totalSales) {
		this.totalSales = totalSales;
	}
	@Override
    public String toString()
    {
		return "Store Sales-- StoreId: " + storeId 
				+ " Year: " + year + " Week: " + week + " Sales: " +  sales + "\n";
    }
}

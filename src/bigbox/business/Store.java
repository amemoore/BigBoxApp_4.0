package bigbox.business;

public class Store extends Facility{
	
	private String strNo;
	private String divNo;
	private int divId;
	public Store(int idIn, String divNoIn, String strNoIn, String nameIn, String addressIn, 
			String cityIn, String stateIn, String zipIn)
	{
		super (idIn, nameIn, addressIn, cityIn, stateIn, zipIn);
		strNo = strNoIn;
		divNo = divNoIn;
	}
	
	public Store() {
		super();
	}
	
	public Store(int divIdin, String strNoin,String nameIn, String addressIn, 
			String cityIn, String stateIn, String zipIn){
		super (nameIn, addressIn, cityIn, stateIn, zipIn);
		divId = divIdin;
		strNo = strNoin;
	}

	public int getDivId() {
		return divId;
	}

	public void setDivId(int divId) {
		this.divId = divId;
	}

	public String getStrNo() {
		return strNo;
	}
	public void setStrNo(String strIn) {
		strNo = strIn;
	}
	public String getDivNo() {
		return divNo;
	}
	public void setDivNo(String divNoIn) {
		divNo = divNoIn;
	}
	@Override
	public String toString(){
		
		return "STORE-- Div Id: " + divId + ", Store No.: " + strNo 
				+ ", Name: " + getName() + "\n";
	}
}

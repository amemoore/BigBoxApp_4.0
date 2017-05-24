package bigbox.business;

public class Division extends Facility {
	
	private String divNo;
	private int divId;
	
	public Division (int divIdIn, String divNoIn, String nameIn, String addressIn, 
			String cityIn, String stateIn, String zipIn){
		
		super ( nameIn, addressIn, cityIn, stateIn, zipIn);
		divNo = divNoIn;
		divId = divIdIn;
	}
	public Division (int divIdIn, String divNoIn){
		divId = divIdIn;
		divNo = divNoIn;
	}
	
	public int getDivId() {
		return divId;
	}
	public void setDivId(int divId) {
		this.divId = divId;
	}
	public String getDivNo() {
		return divNo;
	}
	public void setDivNo(String divNo) {
		this.divNo = divNo;
	}
	@Override
    public String toString()
    {
		return "Division-- Div No: " + divNo 
				+ " " + super.toString() + "\n";
    }
}

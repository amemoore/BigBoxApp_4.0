package stores.db;
import bigbox.business.Store;

public interface StoreWriter {
	
	public void addStore(Store s);
	
	public void delete(int s, String t); 
}

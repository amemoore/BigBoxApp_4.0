package bigbox.db;

import stores.db.StoreDAO;
import stores.db.StoreDB;
import divisions.db.DivisionsDAO;
import divisions.db.DivisionsDB;
import sales.db.SalesDAO;
import sales.db.SalesDB;

	public class DAOFactory {
		public static StoreDAO getStoreDAO(){
			StoreDAO storeDAO = new StoreDB();
			return storeDAO;
		}
	public static DivisionsDAO getDivisionsDAO(){
			DivisionsDAO divisionsDAO = new DivisionsDB();
			return divisionsDAO;
		}
	public static SalesDAO getSalesDAO(){
			SalesDAO storesalesDAO = new SalesDB();
			return storesalesDAO;
		}	
}

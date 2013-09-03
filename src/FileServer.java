import java.rmi.*;
import java.rmi.registry.*;

public class FileServer {
	
	public static void main(String args[]){
		try{
			Syncer stub=new SyncerRemote();
			Naming.rebind("rmi://localhost:5000/sonoo", stub);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}


import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class SyncerRemote extends UnicastRemoteObject implements Syncer{
	
	SyncerRemote() throws RemoteException{
		super();
		
	}
	
	public int add(int x, int y){
		return x +y;
	}

}

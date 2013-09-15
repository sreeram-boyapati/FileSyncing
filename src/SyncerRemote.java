
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class SyncerRemote extends UnicastRemoteObject implements Syncer{
	
	SyncerRemote() throws RemoteException{
		super();
		
	}
	
	public int add(int x, int y){
		return x +y;
	}

	@Override
	public String createFile(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String openFile(String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMsg(String Msg) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recvMsg() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}

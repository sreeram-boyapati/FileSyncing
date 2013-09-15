import java.rmi.*;
public interface Syncer extends Remote{
	public int add(int X, int Y) throws RemoteException;
	public String createFile(String fileName) throws RemoteException;
	public String openFile(String fileName) throws RemoteException;
	public String sendMsg (String Msg) throws RemoteException;
	public String recvMsg () throws RemoteException;
}

import java.rmi.*;
public interface Syncer extends Remote{
	public int add(int X, int Y) throws RemoteException;
	public int createFile(String fileName) throws RemoteException;
	public int openFile(String fileName) throws RemoteException;
	public int closeFile(String fileName) throws RemoteException;
	public String sendMsg(String filename, String Msg) throws RemoteException;
	public String recvMsg(String filename) throws RemoteException;
}

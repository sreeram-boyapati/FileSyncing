import java.rmi.*;
public interface Syncer extends Remote{
	public int add(int X, int Y) throws RemoteException;
}

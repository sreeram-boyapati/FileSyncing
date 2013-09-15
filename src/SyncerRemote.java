
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class SyncerRemote extends UnicastRemoteObject implements Syncer{
	
	SyncerRemote() throws RemoteException{
		super();
		
	}
	
	public int add(int x, int y){
		return x + y;
	}

	@Override
	public int createFile(String fileName) throws RemoteException {
		try {
			File file = new File(fileName);
		    if (file.createNewFile()) {
		    	return 0; // New file successfully created. 		        
		    }
		    else {
		    	return 1; // File already exists. 
		    }
	    } 
		
		catch (IOException e) {
			  System.err.println(e.getMessage());
		      return -1; // Cannot create file, Exception thrown. 
		}
	}

	@Override
	public int openFile(String filename) throws RemoteException {
		boolean file_exists, lock_exists;
		try {
			File file = new File(filename);
			File lock = new File(filename+"~lock");
			
			if(file.exists()) {
				file_exists = true; // File exists.
			}
			else {
				file_exists = false; // File does not exist. 
			}
			
		    if (lock.exists()) {
		    	lock_exists = true; // Lock present. 
		    }
		    else {
		    	lock_exists = false; // No lock present. 
		    }		    
		    
		    if(file_exists&&(!lock_exists)) {
		    	return 0; // File exists and is free. 
		    }		    
		    else if(file_exists&&lock_exists) {
		    	return 1; // File exists and is being used.
		    }
		    else if(!file_exists&&!lock_exists) {
		    	return 2; // File does not exist on system at all. 
		    }
		    else {
		    	return -1; // File does not exist but lock exists, inconsistency has occured. 
		    }
		    	
	    }  		
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return -2; // Cannot check for file or lock, Exception thrown. 
		}
	}
	
	public int closeFile(String filename) throws RemoteException {
		
		boolean file_exists, lock_exists;
		try {
			File file = new File(filename);
			File lock = new File(filename+"~lock");
		
			if(file.exists()) {
				file_exists = true; // File exists.
			}
			else {
				file_exists = false; // File does not exist. 
			}
		
			if (lock.exists()) {
	    		lock_exists = true; // Lock present. 
	    	}
	    	else {
	    		lock_exists = false; // No lock present. 
	    	}
		
			if(file_exists&&(!lock_exists)) {
	    		return 0; // File exists and is free. 
	    	}		    
	    	else if(file_exists&&lock_exists) {
	    		if(lock.delete()) {
	    			return 1; // File exists and Lock deleted.
	    		}
	    		else {
	    			return 2; // Could not delete lock. 
	    		}
	    	}
	    	else if(!file_exists&&!lock_exists) {
	    		return -1; // File does not exist on system at all. 
	    	}
	    	else 	{
	    		return -2; // File does not exist but lock exists, inconsistency has occured. 
	    	}
		}
		
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return -3; // Cannot create file, Exception thrown. 
		}
	}

	@Override
	public int sendMsg(String filename, String Msg) throws RemoteException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
			out.println(Msg);
			return 0;
		}
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return -1; // Cannot write to file. 
		}
		finally {
			out.close();
		}
	}

	@Override
	public String recvMsg(String filename) throws RemoteException {
		try {
            String text = new Scanner( new File(filename), "UTF-8" ).useDelimiter("\\A").next();
            return text;
        }
        
        catch (Exception e) {
			return(e.getMessage()); // FileNotFoundException
		}
	}
}

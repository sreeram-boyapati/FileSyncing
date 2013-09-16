
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class SyncerRemote extends UnicastRemoteObject implements Syncer{
	
	File file;
	File lock;
	SyncerRemote() throws RemoteException{
		super();
		
	}
	
	public int add(int x, int y){
		return x + y;
	}

	@Override
	public String createFile(String fileName) throws RemoteException {
		try {
			file = new File(fileName);
		    if (file.createNewFile()) {
		    	return "File: "+ fileName + " Successfully Created."; // New file successfully created. 		        
		    }
		    else {
		    	return "File: "+ fileName + " Successfully Created."; // File already exists. 
		    }
	    } 
		
		catch (IOException e) {
			  System.err.println(e.getMessage());
		      return e.toString(); // Cannot create file, Exception thrown. 
		}
	}

	@Override
	public String openFile(String filename) throws RemoteException {
		boolean file_exists, lock_exists;
		try {
			if(!file.exists())
			{
			file = new File(filename);
			lock = new File(filename+"~lock");
			}
			
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
		    	return "File Exists and is not Free"; // File exists and is free. 
		    }		    
		    else if(file_exists&&lock_exists) {
		    	return "File Exists and is being Used"; // File exists and is being used.
		    }
		    else if(!file_exists&&!lock_exists) {
		    	return "File Doesnot Exist"; // File does not exist on system at all. 
		    }
		    else {
		    	return "Inconsistency as Lock Exists"; // File does not exist but lock exists, inconsistency has occured. 
		    }
		    	
	    }  		
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return e.getMessage(); // Cannot check for file or lock, Exception thrown. 
		}
	}
	
	public String closeFile(String filename) throws RemoteException {
		
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
	    		return "File is in use"; // File exists and is free. 
	    	}		    
	    	else if(file_exists&&lock_exists) {
	    		if(lock.delete()) {
	    			return "Lock Deleted"; // File exists and Lock deleted.
	    		}
	    		else {
	    			return "Could Not Delete Lock"; // Could not delete lock. 
	    		}
	    	}
	    	else if(!file_exists&&!lock_exists) {
	    		return "File Doesnot Exist"; // File does not exist on system at all. 
	    	}
	    	else 	{
	    		return "Inconsistent File"; // File does not exist but lock exists, inconsistency has occured. 
	    	}
		}
		
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return e.getMessage(); // Cannot create file, Exception thrown. 
		}
	}

	@Override
	public String sendMsg(String filename, String Msg) throws RemoteException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
			out.println(Msg);
			return "Message Successfully written to File: "+ filename;
		}
		catch (Exception e) {
			  System.err.println(e.getMessage());
		      return "Message couldnot be written"; // Cannot write to file. 
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

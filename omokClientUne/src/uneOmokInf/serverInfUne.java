package uneOmokInf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface serverInfUne extends Remote{
	
	public void setClient(clientInfUne client) throws RemoteException;

	public void ready() throws RemoteException;
	
	public void pMove(int color,int i, int j) throws RemoteException;
	
	public void pSelect(int[] xy, int color) throws RemoteException;

	
	
	

}

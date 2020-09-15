package uneOmokInf;

import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 *	RMI(Remote Method Invocation) 
 *		: �л�Ǿ� �����ϴ� ��ü ���� �޼��� ����(�޼ҵ�ȣ�� ����)�� �����ϰ� �ϴ� �������� 
 * 
 * 	RMI ����
 * 		1. Server
 * 			- ���� �������̽�
 * 			- ���� Ŭ����
 * 			- ���̷��� Ŭ����
 * 			- �������� Ŭ����
 * 
 * 		2. Client
 * 			- �����������̽�
 * 			- ���� Ŭ����
 * 			- Ŭ���̾�Ʈ ���� Ŭ����
 * 
 * 	RMI ������
 * 		1. server�� client,�������̽��� ��Ű������ ��� �����ؾ� �Ѵ�
 * 		2. server�� client�� �������̽��� ��� RemoteŬ������ ����Ͽ��� �Ѵ�
 * 		3. server�� client�� ����� �޼ҵ���� ��� RemoteException ���� Ŭ������ ������ �Ѵ�
 * 
 * 
 */

public interface clientInfUne extends Remote{ //server�� client�� �������̽��� ��� RemoteŬ������ ����Ͽ��� �Ѵ�

	//���� �޼���
	public void start() throws RemoteException;
	
	//��¿� �޼���
	public void printMap() throws RemoteException;
	
	//�浹 �鵹 ��Ÿ���� �޼���
	
	public void setMyTurn(boolean torf) throws RemoteException;
	//
	public void pMove(int color,int i, int j) throws RemoteException;
	
	public void pSelect(int[] xy,int color) throws RemoteException;
	
	
	
}

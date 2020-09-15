package uneOmokClient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.jar.JarFile;

import javax.swing.JFrame;

import uneOmokInf.clientInfUne;
import uneOmokInf.serverInfUne;

public class OmokClientUne extends UnicastRemoteObject implements clientInfUne{

	protected OmokClientUne() throws RemoteException {
		
	}
	
	
	//âũ��
	private int[][] map = new int[15][15];
	
	//�鵹 ó�� ��ġ
	private int[] whitexy = {7,7};
	
	//�浹 ó�� ��ġ
	private int[] blackxy = {7,7};
	
	//�浹 �鵹 ���� true�̸� �鵹 false�̸� �浹
	private boolean trun = true;
	
	//�� ����
	private boolean myTrun = true;
	
	private Key	key;
	
	public static serverInfUne server;
	
	
	public static void main(String[] args) {
	
			try {
				clientInfUne client = new OmokClientUne();
				Registry reg = LocateRegistry.getRegistry("192.168.43.41",1099);
				server = (serverInfUne) reg.lookup("omok");
				
				server.setClient(client);
				System.out.println("���� ���ӿ� �����ϼ̽��ϴ�");
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
	}
	
	public void start() throws RemoteException{
		JFrame chang = new JFrame();
		
		chang.setSize(100, 100);
		
		chang.setLayout(null);
		
		//FrameŬ���� �Ӽ��߿� ����ڿ��� ������ ������ �ƴ����� ���� ������ false�̹Ƿ� c
		//â�� ȭ�鿡 �����ֱ� ���� �޼ҵ� setVisible�� ȣ���Ѵ�
		chang.setVisible(true);
		
		chang.setTitle("���� �����̴�!!");
		
		if(myTrun){
			key = new Key(whitexy, 1);
		}else{
			key = new Key(blackxy, 2);
			
		}
		chang.addKeyListener(key);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printMap();
	}
	
	



	
	private void printMap() throws RemoteException{
		
		
		System.out.println("\n\n\n\n\n");
		for(int i = 0; i< map.length; i++){
			for(int j = 0; j<map[i].length;j++){
				if(j == 0){
					System.out.print("|");
				}
				if(map[i][j] == 1){
					if(i == whitexy[1] && j ==whitexy[0] || i == blackxy[1] && j == blackxy[0]){
						System.out.print(">��<");
					}else{
						System.out.print(" �� ");
					}
				}else if(map[i][j] == 2){
					if(i == whitexy[1] && j == whitexy[0] || i == blackxy[1] && j == blackxy[0]){
						System.out.print(">��<");
					}else{
						System.out.print(" �� ");
					}
				}else if(map[i][j] == 0){
					
					if(i==whitexy[1] && j == whitexy[0] || i == blackxy[1] && j == blackxy[0]){
						System.out.print("> <");
					}else{
						System.out.print("   ");
					}
					
				}
				if(j == 14){
					System.out.println("|");
				}
			}
			System.out.println();
		}
		
	}

	public void setMyTurn(boolean torf) throws RemoteException{
		myTrun = torf;
		if(torf){
			System.out.println("����� �鵹");
		}else{
			System.out.println("����� �浹");
		}
	}

	public void pMove(int color,int i , int j) throws RemoteException{
		
		if(color == 1){
			whitexy[i] += j;
		}else{
			blackxy[i] += j;
		}
		printMap();
	}

	
	public void pSelect(int[] xy, int color) throws RemoteException{
		map[xy[1]][xy[0]] = color;
		trun = ! trun;
		printMap();
	}

	class Key implements KeyListener{ //�ζ��� Ŭ����
		
		/*
		 * KeyListener �������̽�
		 * 	: Ű���带 ������ �� ȣ��Ǵ� �޼��带 ������ �ִ� �޼���
		 * 	
		 * 	- keyTyped(KeyEvent e) : ���ڸ� ������ �� ȣ��, ����Ű���� �����Ѵ�
		 * 	- keyReleased(KeyEvent e) : Ű���带 ���� ��  ȣ��, ��� Ű���� �����Ѵ�
		 * 	- keyPressed(KeyEvent e) : Ű���带 ������ �� ȣ��, ���Ű���� �����Ѵ�
		 * 	==>�Ű����� e���� ���� Ű�� ������ �Ѿ�´�
		 * 	==> e.getKeyChar() : ���� Ű�� ���ڸ� ���� 
		 * 	==> e.getKeyCode() : ���� Ű�� �ƽ�Ű �ڵ带 ���� 
		 * 	==> e.getKeyModifiers() : shifr,Ctrl,Alt Ű �ν� ���� 1,2,8 ���� 
		 * 
		 * 
		 */
		
		
		
		private int[] xy; //��ġ
		private int color;
		
		//������
		public Key(int[] xy, int color) {
			super();
			this.xy = xy;
			this.color = color;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) { //Ű���带 ������ �� ȣ��
			
			try {
				
				if(trun == myTrun){
					if(e.getKeyCode() == 37){
						if(xy[0]>0){
							server.pMove(color, 0, -1);
						}else if(e.getKeyCode() == 28){
							
						}
					}
				}
					
					
			} catch (Exception e2) {
				
			}
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}


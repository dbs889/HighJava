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
	
	
	//창크기
	private int[][] map = new int[15][15];
	
	//백돌 처음 위치
	private int[] whitexy = {7,7};
	
	//흑돌 처음 위치
	private int[] blackxy = {7,7};
	
	//흑돌 백돌 구분 true이면 백돌 false이면 흑돌
	private boolean trun = true;
	
	//내 돌색
	private boolean myTrun = true;
	
	private Key	key;
	
	public static serverInfUne server;
	
	
	public static void main(String[] args) {
	
			try {
				clientInfUne client = new OmokClientUne();
				Registry reg = LocateRegistry.getRegistry("192.168.43.41",1099);
				server = (serverInfUne) reg.lookup("omok");
				
				server.setClient(client);
				System.out.println("오목 게임에 접속하셨습니다");
				
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
		
		//Frame클래스 속성중에 사용자에게 보여줄 것인지 아닌지에 대한 설정이 false이므로 c
		//창을 화면에 보여주기 위한 메소드 setVisible을 호출한다
		chang.setVisible(true);
		
		chang.setTitle("오목 게임이다!!");
		
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
						System.out.print(">○<");
					}else{
						System.out.print(" ○ ");
					}
				}else if(map[i][j] == 2){
					if(i == whitexy[1] && j == whitexy[0] || i == blackxy[1] && j == blackxy[0]){
						System.out.print(">●<");
					}else{
						System.out.print(" ● ");
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
			System.out.println("당신은 백돌");
		}else{
			System.out.println("당신은 흑돌");
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

	class Key implements KeyListener{ //인라인 클래스
		
		/*
		 * KeyListener 인터페이스
		 * 	: 키보드를 눌렀을 때 호출되는 메서드를 가지고 있는 메서드
		 * 	
		 * 	- keyTyped(KeyEvent e) : 문자를 눌렀을 때 호출, 문자키에만 반응한다
		 * 	- keyReleased(KeyEvent e) : 키보드를 땠을 때  호출, 모든 키에만 반응한다
		 * 	- keyPressed(KeyEvent e) : 키보드를 눌렀을 때 호출, 모든키에만 반응한다
		 * 	==>매개변수 e에는 눌린 키의 정보가 넘어온다
		 * 	==> e.getKeyChar() : 눌린 키의 문자를 리턴 
		 * 	==> e.getKeyCode() : 눌린 키의 아스키 코드를 리턴 
		 * 	==> e.getKeyModifiers() : shifr,Ctrl,Alt 키 인식 각각 1,2,8 리턴 
		 * 
		 * 
		 */
		
		
		
		private int[] xy; //위치
		private int color;
		
		//생성자
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
		public void keyPressed(KeyEvent e) { //키보드를 눌렀을 때 호출
			
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


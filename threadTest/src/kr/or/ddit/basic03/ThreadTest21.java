package kr.or.ddit.basic03;

public class ThreadTest21 {
	
	public static void main(String[] args) {
		
		
		DataBox dbox = new DataBox();
		
		InputThread inth = new InputThread(dbox);
		OutputThread oth = new OutputThread(dbox);
		
		inth.start();
		oth.start();
		
	}

}


//공통으로 사용할 객체

class DataBox{
	private String data;
	
	//data값이 null이면 data의 문자열이 채워질때까지 기다린다.
	//data에 값이 있으면 해당 문자열을 반환한다
	//반환 후에는 data를 null로 만든다.
	
	public synchronized String getData(){//데이터를 빼내가는 메서드
		if(data == null){
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
			
		}
		String returnData = data;
		System.out.println("쓰레드가 읽어온 데이터 : " + returnData);
		
		data = null;
		notify();
		return returnData;
		
		}
	//data변수에 값이 있으면 data가 null이 될때까지 기다린다
	//data의 null이 되면 새로운 data를 저장한다
	public synchronized void setData(String data){
		if(this.data != null){
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		
		
		this.data = data;
		System.out.println("쓰레드에서 새로 저장한 data : " + data);
		notify();
		
	
	}
}


//데이터를 넣어주는 쓰레드

class InputThread extends Thread{
	private DataBox dbox;

	public InputThread(DataBox dbox) {
	
		this.dbox = dbox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			dbox.setData("공급 데이터 : " + i);
		}
	}
	
	
}

//데이터를 사용하는 쓰레드

class OutputThread extends Thread{
	
	private DataBox dbox;

	public OutputThread(DataBox dbox) {
		
		this.dbox = dbox;
	}
	
	
	@Override
	public void run() {
		for(int i = 1; i <=5; i++){
			String data = dbox.getData();
		}
	}
	
}
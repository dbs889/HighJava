package kr.or.ddit.basic;

public class UneThreadExam {

	public static void main(String[] args) {
		
		Thread thUne = new UneThread();
		Thread thUneCnt = new countThr();
		
		thUne.start();
		thUneCnt.start();
		
		
	
	}

}

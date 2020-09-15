package uneOmokVo;

import java.io.Serializable;

public class OmokVoUne implements Serializable{ //외부에서 객체가 사용되야 하니깐 직렬화 해준다
	
	
	//창크기
	private int[][] map = new int[15][15];
	
	//백돌 처음 위치
	private int[] whitexy = {7,7};
	
	//흑돌 처음 위치
	private int[] blackxy = {7,7};
	
	//흑돌 백돌 구분 true이면 백돌 false이면 흑돌
	private boolean trun = true;
	

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int[] getWhitexy() {
		return whitexy;
	}

	public void setWhitexy(int[] whitexy) {
		this.whitexy = whitexy;
	}

	public int[] getBlackxy() {
		return blackxy;
	}

	public void setBlackxy(int[] blackxy) {
		this.blackxy = blackxy;
	}

	public boolean isTrun() {
		return trun;
	}

	public void setTrun(boolean trun) {
		this.trun = trun;
	}
	
	
	

}

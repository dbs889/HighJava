package uneOmokVo;

import java.io.Serializable;

public class OmokVoUne implements Serializable{ //�ܺο��� ��ü�� ���Ǿ� �ϴϱ� ����ȭ ���ش�
	
	
	//âũ��
	private int[][] map = new int[15][15];
	
	//�鵹 ó�� ��ġ
	private int[] whitexy = {7,7};
	
	//�浹 ó�� ��ġ
	private int[] blackxy = {7,7};
	
	//�浹 �鵹 ���� true�̸� �鵹 false�̸� �浹
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
